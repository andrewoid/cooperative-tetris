package edu.touro.cooptetris.net.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import edu.touro.cooptetris.net.message.HardDropMessage;
import edu.touro.cooptetris.net.message.MoveLeftMessage;
import edu.touro.cooptetris.net.message.MoveRightMessage;
import edu.touro.cooptetris.net.message.NewPieceMessage;
import edu.touro.cooptetris.net.message.NewPlayerMessage;
import edu.touro.cooptetris.net.message.RotateMessage;
import edu.touro.cooptetris.net.message.SoftDropMessage;
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
		writer.start();
	}

	public void runServer() {

		try {
			while ((socket = server.accept()) != null) {
				MockClientHandler aClientHandler = new MockClientHandler(socket);
				writer.addSocket(socket);
				aClientHandler.start();
				// writer.addMessage(new NewPlayerMessage());
				Piece p = new JPiece(20, 20, 0, 0);
				// Piece p2 = new JPiece(50, 10, 1, 1);
				writer.addMessage(new NewPieceMessage(p));
				Thread.sleep(1000);
				writer.addMessage(new SoftDropMessage(0));
				writer.addMessage(new SoftDropMessage(0));
				writer.addMessage(new SoftDropMessage(0));
				Thread.sleep(1000);
				writer.addMessage(new MoveLeftMessage(0));
				Thread.sleep(1000);
				writer.addMessage(new SoftDropMessage(0));
				writer.addMessage(new MoveRightMessage(0));
				Thread.sleep(1000);
				writer.addMessage(new RotateMessage(0));
				Thread.sleep(1000);
				writer.addMessage(new RotateMessage(0));
				Thread.sleep(1000);
				writer.addMessage(new HardDropMessage(0));
				// writer.addMessage(new NewPieceMessage(p2));
				// Thread.sleep(1000);
				// writer.addMessage(new SoftDropMessage(1));
				// //writer.addMessage(new SoftDropMessage(1));
				// writer.addMessage(new SoftDropMessage(1));
				// Thread.sleep(1000);
				// writer.addMessage(new MoveLeftMessage(1));
				// Thread.sleep(1000);
				// writer.addMessage(new SoftDropMessage(1));
				// writer.addMessage(new MoveRightMessage(1));
				// Thread.sleep(1000);
				// writer.addMessage(new RotateMessage(1));
				Thread.sleep(1000);
				// writer.addMessage(new RotateMessage(1));
				writer.addMessage(new NewPlayerMessage());
				Thread.sleep(1000);
				writer.addMessage(new NewPlayerMessage());
				Thread.sleep(1000);
				writer.addMessage(new NewPlayerMessage());
				Thread.sleep(1000);
				writer.addMessage(new NewPlayerMessage());
				// writer.addMessage(new HardDropMessage(0));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		MockServer server = new MockServer();
		server.runServer();
	}

}
