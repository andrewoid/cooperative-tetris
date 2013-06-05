package edu.touro.cooptetris.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.inject.Inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class TetrisServer {

	private ServerSocket server;
	private Socket socket;
	private ServerGameController gameController;
	private WriterThread writer;

	@Inject
	public TetrisServer(ServerGameController gameController) {
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
		Injector injector = Guice.createInjector(new Module[0]);
		TetrisServer server = injector.getInstance(TetrisServer.class);
		server.runServer();
	}
}
