package edu.touro.cooptetris.net.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.touro.cooptetris.net.discovery.DiscoverClient;
import edu.touro.cooptetris.net.message.Message;
import edu.touro.cooptetris.net.message.NewPlayerMessage;

public class TetrisClient {

	public ClientGameController getGameController() {
		return gameController;
	}

	private Socket socket;
	private ObjectOutputStream objectOut;
	private ClientGameController gameController;
	private ReaderThread readerThread;
	private int playerID;

	public TetrisClient(ClientGameController gameController)
			throws UnknownHostException, IOException {
		initializeClient();
		this.gameController = gameController;
		this.playerID = gameController.getPlayerID();
		readerThread.start();

	}

	// public int getPlayerID() {
	// return playerID;
	// }

	private void initializeClient() throws UnknownHostException, IOException {
		socket = new Socket(new DiscoverClient().discoverTetrisServer(), 8080);
		objectOut = new ObjectOutputStream(socket.getOutputStream());
		readerThread = new ReaderThread(socket, gameController);

		send(new NewPlayerMessage());
	}

	public void send(Message message) throws IOException {
		objectOut.writeObject(message);
		objectOut.flush();
	}
}
