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
	private WriterThread writer;
	private GameControllerThread gameControllerThread;

	@Inject
	public TetrisServer(WriterThread writer,
			GameControllerThread gameControllerThread,
			MovePiecesThread movePiecesThread) {
		try {
			server = new ServerSocket(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.writer = writer;
		writer.start();
		this.gameControllerThread = gameControllerThread;
		gameControllerThread.start();
		movePiecesThread.start();
	}

	public void runServer() {

		try {
			while ((socket = server.accept()) != null) {
				writer.addSocket(socket);

				ClientHandler aClientHandler = new ClientHandler(socket,
						gameControllerThread);
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
