package edu.touro.cooptetris.net.server;

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

	private void readMessage() throws InterruptedException {
		Message message = messages.take();
		message.handleByServer(serverGameController);
	}

	public void addMessage(Message message) {
		messages.add(message);
	}

	@Override
	public void run() {
		while (true) {
			try {
				readMessage();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
