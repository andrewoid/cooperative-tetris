package edu.touro.cooptetris.net.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import edu.touro.cooptetris.net.message.Message;
import edu.touro.cooptetris.net.server.ServerGameController;

public class MockClientHandler extends Thread {

	private InputStream in;
	private OutputStream out;

	public MockClientHandler(Socket socket) throws IOException {
		in = socket.getInputStream();
		out = socket.getOutputStream();
	}

	@Override
	public void run() {

		while (true) {
			Message message;
			ObjectInputStream oiStream;
			try {
				oiStream = new ObjectInputStream(in);
				message = (Message) oiStream.readObject();
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
