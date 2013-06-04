package edu.touro.cooptetris.net.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import edu.touro.cooptetris.GameController;
import edu.touro.cooptetris.net.message.Message;

public class ClientHandler extends Thread {

	private InputStream in;
	private GameController gameController;

	public ClientHandler(Socket socket, GameController gameController)
			throws IOException {
		in = socket.getInputStream();
		this.gameController=gameController;

	}

	@Override
	public void run() {

		while (true) {
			Message message;
			ObjectInputStream oiStream;
			try {
				oiStream = new ObjectInputStream(in);
				message = (Message) oiStream.readObject();
				message.handleByServer(gameController);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
