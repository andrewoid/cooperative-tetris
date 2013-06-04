package edu.touro.cooptetris.net.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.inject.Singleton;

import edu.touro.cooptetris.net.message.Message;

@Singleton
public class WriterThread extends Thread {

	private LinkedBlockingQueue<Message> messages;
	private LinkedList<OutputStream> outs;

	// DataOutputStream

	public WriterThread() {
		messages = new LinkedBlockingQueue<Message>();
		outs = new LinkedList<OutputStream>();
	}

	public void addSocket(Socket socket) throws IOException {
		OutputStream out = socket.getOutputStream();
		outs.add(out);
	}

	private void serializeMessage(OutputStream out, Message message) {
		for (int i = 0; i < outs.size(); i++) {
			try {
				ObjectOutputStream ooStream = new ObjectOutputStream(out);
				ooStream.writeObject(message);
				ooStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addMessage(Message message) {
		messages.add(message);
	}

	public void writeMessage() throws InterruptedException {
		Message message = messages.take();
		Iterator<OutputStream> iter = outs.iterator();
		OutputStream outputStream;
		while (iter.hasNext()) {
			outputStream = iter.next();
			serializeMessage(outputStream, message);
		}

	}

	public void run() {
		while (true) {
			try {
				writeMessage();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
