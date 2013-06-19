package edu.touro.cooptetris.net.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import edu.touro.cooptetris.net.message.Message;

public class MockClientHandler extends Thread {

	private InputStream in;

	public MockClientHandler(Socket socket) throws IOException {
		in = socket.getInputStream();
	}

	@Override
	public void run() {

		while (true) {

			ObjectInputStream oiStream;
			try {
				oiStream = new ObjectInputStream(in);
				Message message = (Message) oiStream.readObject();

			} catch (Exception e) {
				e.printStackTrace();
				break;
			}

		}
	}

}
