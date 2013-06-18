package edu.touro.cooptetris.net.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
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
			Message message;
			ObjectInputStream oiStream;
			try {
				oiStream = new ObjectInputStream(in);
				message = (Message) oiStream.readObject();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}

		}
	}

}
