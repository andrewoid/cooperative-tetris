package edu.touro.cooptetris.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler extends Thread {

	private InputStream in;
	private Scanner scanner;
	private WriterThread writer;

	public ClientHandler(Socket socket, WriterThread writer) throws IOException {
		in = socket.getInputStream();
		scanner = new Scanner(in);
		this.writer = writer;

	}

	@Override
	public void run() {
		String message;
		while (scanner.hasNextLine()) {
			message = scanner.nextLine();
			writer.addMessage(message);
		}
	}

}
