package edu.touro.cooptetris.net.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import edu.touro.cooptetris.net.message.Message;

public class ClientHandler extends Thread {

	private InputStream in;
	private GameControllerThread gameControllerThread;

	public ClientHandler(Socket socket,
			GameControllerThread gameControllerThread) throws IOException {
		in = socket.getInputStream();
		this.gameControllerThread = gameControllerThread;

	}

	@Override
	public void run() {

		ObjectInputStream oiStream = null;
		try {
			oiStream = new ObjectInputStream(in);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (true) {
			Message message;
			try {
				message = (Message) oiStream.readObject();
				gameControllerThread.addMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

}
