package edu.touro.cooptetris.net.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Logger;

import edu.touro.cooptetris.net.message.Message;

public class ReaderThread extends Thread {
	private final static Logger logger = Logger.getLogger(ReaderThread.class
			.getName());
	private ObjectInputStream objectIn;
	private ClientGameController gameController;

	public ReaderThread(Socket socket, ClientGameController gameController)
			throws IOException {
		objectIn = new ObjectInputStream(socket.getInputStream());
		this.gameController = gameController;
	}

	@Override
	public void run() {

		while (true) {
			Message message;
			try {
				message = (Message) objectIn.readObject();
				logger.info("received message " + message.getClass());
				message.handleByClient(gameController);
				gameController.repaint();

			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

}