package edu.touro.coopptetris.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class WriterThread extends Thread {

	private LinkedBlockingQueue<String> messages;
	private LinkedList<OutputStream> outs;

	public WriterThread() {
		messages = new LinkedBlockingQueue<String>();
		outs = new LinkedList<OutputStream>();
	}

	public void addSocket(Socket socket) throws IOException {
		OutputStream out = socket.getOutputStream();
		outs.add(out);
	}

	public void addMessage(String message) {
		messages.add(message);
	}

	public void writeMessage() throws InterruptedException {
		String message = messages.take();
		Iterator<OutputStream> iter = outs.iterator();
		OutputStream outputStream;
		while (iter.hasNext()) {
			outputStream = iter.next();
			try {
				writeToStream(message, outputStream);
			} catch (IOException e) {
				iter.remove();
			}
		}

	}

	private void writeToStream(String message, OutputStream outputStream)
			throws IOException {
		outputStream.write(message.getBytes());
		outputStream.write("\n".getBytes());
		outputStream.flush();
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
