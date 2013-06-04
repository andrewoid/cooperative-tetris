package edu.touro.cooptetris.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import edu.touro.cooptetris.GameController;

public class TetrisServer {

	private ServerSocket server;
	private Socket socket;
	private GameController gameController;
	private WriterThread writer;

	public TetrisServer(GameController gameController) {
		try {
			server = new ServerSocket(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}

		writer = new WriterThread();
		this.gameController = gameController;
	}

	public void runServer() {

		try {
			while ((socket = server.accept()) != null) {
				ClientHandler aClientHandler = new ClientHandler(socket,
						gameController);
				writer.addSocket(socket);
				aClientHandler.start();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
	}
}
