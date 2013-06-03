package edu.touro.cooptetris.net.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import edu.touro.cooptetris.ClientGameController;
import edu.touro.cooptetris.net.message.Message;

public class ReaderThread extends Thread {

	private ObjectInputStream objectIn;
	private ClientGameController gameController;
	
	public ReaderThread(Socket socket, ClientGameController gameController) throws IOException {
		objectIn = new ObjectInputStream(socket.getInputStream());
		this.gameController = gameController;
	}

	public void run() {

		while (true) {
			Message message;
			try {
				message = (Message) objectIn.readObject();
				message.handleByClient(gameController);
			} catch (IOException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} 
		}
	}

}