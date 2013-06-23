package edu.touro.cooptetris.net.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Singleton;

import edu.touro.cooptetris.net.message.Message;

@Singleton
public class WriterThread extends Thread {

	private final static Logger log = Logger.getLogger(WriterThread.class
			.getName());
	private final LinkedBlockingQueue<Message> messages;
	private final LinkedList<ObjectOutputStream> outs;

	public WriterThread() {
		messages = new LinkedBlockingQueue<Message>();
		outs = new LinkedList<ObjectOutputStream>();
	}

	public void addSocket(final Socket socket) throws IOException {
		final OutputStream out = socket.getOutputStream();
		synchronized (outs) {
			outs.add(new ObjectOutputStream(out));
		}
	}

	private void serializeMessage(final Message message) {
		synchronized (outs) {
			Iterator<ObjectOutputStream> iter = outs.iterator();
			ObjectOutputStream out;
			while (iter.hasNext()) {
				out = iter.next();
				try {
					out.writeObject(message);
					out.flush();
				} catch (final Exception e) {
					iter.remove();
					log.log(Level.INFO, "Removed a client");
				}
			}
		}
	}

	public void addMessage(final Message message) {
		log.log(Level.INFO, "Adding " + message.getClass());
		messages.add(message);
	}

	public void writeMessage() throws InterruptedException {
		final Message message = messages.take();
		log.log(Level.INFO, "Writing " + message.getClass());
		serializeMessage(message);
	}

	@Override
	public void run() {
		while (true) {
			try {
				writeMessage();
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
