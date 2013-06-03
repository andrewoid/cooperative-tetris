package edu.touro.cooptetris.net.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import edu.touro.cooptetris.GameController;
import edu.touro.cooptetris.GameStateListener;
import edu.touro.cooptetris.net.message.Message;

public class ReaderThread extends Thread {

	private ObjectInputStream objectIn;
	private GameController gameController;
	private GameStateListener gameStateListener;

	public ReaderThread(Socket socket, GameController gameController,
			GameStateListener gameStateListener) throws IOException {
		objectIn = new ObjectInputStream(socket.getInputStream());
		this.gameController = gameController;
		this.gameStateListener = gameStateListener;
	}

	public void run() {

		while (true) {
			Message message;
			try {
				message = (Message) objectIn.readObject();
				message.handleByClient(gameController, gameStateListener);
			} catch (IOException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} 
		}
	}

}