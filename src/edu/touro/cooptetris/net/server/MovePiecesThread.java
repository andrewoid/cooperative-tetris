package edu.touro.cooptetris.net.server;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.touro.cooptetris.net.message.MovePiecesMessage;

@Singleton
public class MovePiecesThread extends Thread {

	private GameControllerThread gameControllerThread;

	@Inject
	public MovePiecesThread(GameControllerThread gameControllerThread) {
		this.gameControllerThread = gameControllerThread;
	}

	@Override
	public void run() {
		while (true) {
			gameControllerThread.addMessage(new MovePiecesMessage());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
