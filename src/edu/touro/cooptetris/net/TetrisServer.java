package edu.touro.cooptetris.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TetrisServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8080);
		Socket socket;
		WriterThread writer = new WriterThread();

		while ((socket = server.accept()) != null) {
			ClientHandler aClientHandler = new ClientHandler(socket, writer);
			writer.addSocket(socket);
			aClientHandler.start();

		}
	}

}
