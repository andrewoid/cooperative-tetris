package edu.touro.cooptetris.net.server;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

import javax.inject.Inject;

import com.google.inject.Singleton;

import edu.touro.cooptetris.net.message.Message;

@Singleton
public class GameControllerThread extends Thread {

	private LinkedBlockingQueue<Message> messages;
	private ServerGameController serverGameController;

	@Inject
	public GameControllerThread(ServerGameController serverGameController) {
		this.serverGameController = serverGameController;
		messages = new LinkedBlockingQueue<Message>();
	}

	private void sendMessage() {
		Iterator<Message> iter = messages.iterator();
		Message message;

		while (iter.hasNext()) {
			message = iter.next();
			message.handleByServer(serverGameController);
			iter.remove();
		}
	}

	public void addMessage(Message message) {
		messages.add(message);
	}

	@Override
	public void run() {
		while (true) {
			sendMessage();
		}
	}

}
