package edu.touro.cooptetris.net.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import edu.touro.cooptetris.net.message.NewPieceMessage;
import edu.touro.cooptetris.net.message.NewPlayerMessage;
import edu.touro.cooptetris.net.server.WriterThread;
import edu.touro.cooptetris.pieces.JPiece;
import edu.touro.cooptetris.pieces.Piece;

public class MockServer {

	private ServerSocket server;
	private Socket socket;
	private WriterThread writer;

	public MockServer() {
		try {
			server = new ServerSocket(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}

		writer = new WriterThread();
	}

	public void runServer() {

		try {
			while ((socket = server.accept()) != null) {
				MockClientHandler aClientHandler = new MockClientHandler(socket);
				writer.addSocket(socket);
				aClientHandler.start();
			}
			writer.addMessage(new NewPlayerMessage());
			Piece p = new JPiece(0, 0, 0, 0);
			writer.addMessage(new NewPieceMessage(p));
			writer.addMessage(new NewPieceMessage(p));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		MockServer server = new MockServer();
		server.runServer();
	}

}
