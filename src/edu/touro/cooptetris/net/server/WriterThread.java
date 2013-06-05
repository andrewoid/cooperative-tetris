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
	private LinkedList<ObjectOutputStream> outs;

	// DataOutputStream

	public WriterThread() {
		messages = new LinkedBlockingQueue<Message>();
		outs = new LinkedList<ObjectOutputStream>();
	}

	public void addSocket(Socket socket) throws IOException {
		OutputStream out = socket.getOutputStream();
		outs.add(new ObjectOutputStream(out));
	}

	private void serializeMessage(ObjectOutputStream out, Message message) {
		try {
			out.writeObject(message);
			System.out.println("after write object");
			out.flush();
			System.out.println("after flush");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addMessage(Message message) {
		messages.add(message);
		System.out.println("got message 1");
	}

	public void writeMessage() throws InterruptedException {
		System.out.println("Taking a message?");
		Message message = messages.take();
		System.out.println("in write message");
		Iterator<ObjectOutputStream> iter = outs.iterator();
		ObjectOutputStream outputStream;
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
