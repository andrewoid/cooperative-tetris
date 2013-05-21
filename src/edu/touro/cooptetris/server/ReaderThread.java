package edu.touro.cooptetris.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.util.MessageFormatter;

public class ReaderThread extends Thread {

	private Scanner scanner;
	private TetrisClient client;

	public ReaderThread(Socket socket, TetrisClient client) {
		this.client = client;
		try {
			InputStream in = socket.getInputStream();
			scanner = new Scanner(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (scanner.hasNextLine()) {
			String message = scanner.nextLine();

			if (message != null) {
				client.receiveMessage(message);
			}
		}
	}

}
