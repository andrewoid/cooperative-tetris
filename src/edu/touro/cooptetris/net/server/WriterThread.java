package edu.touro.cooptetris.net.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
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

	// DataOutputStream

	public WriterThread() {
		messages = new LinkedBlockingQueue<Message>();
		outs = new LinkedList<ObjectOutputStream>();
	}

	public void addSocket(final Socket socket) throws IOException {
		final OutputStream out = socket.getOutputStream();
		outs.add(new ObjectOutputStream(out));
	}

	private void serializeMessage(final Message message) {
		for (final ObjectOutputStream out : outs) {
			try {
				out.writeObject(message);
				out.flush();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addMessage(final Message message) {
		messages.add(message);
		log.log(Level.INFO,"got message 1");
	}

	public void writeMessage() throws InterruptedException {
		log.log(Level.INFO,"Taking a message?");
		final Message message = messages.take();
		log.log(Level.INFO,"Took message");
		serializeMessage(message);
	}

	@Override
	public void run() {
		while (true) {
			try {
				writeMessage();
			} catch (final InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
