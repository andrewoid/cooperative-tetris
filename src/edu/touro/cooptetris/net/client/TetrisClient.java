package edu.touro.cooptetris.net.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.inject.Inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import edu.touro.cooptetris.net.discovery.DiscoverClient;
import edu.touro.cooptetris.net.message.Message;
import edu.touro.cooptetris.net.message.NewPlayerMessage;
import edu.touro.cooptetris.net.server.TetrisServer;

public class TetrisClient {

	private Socket socket;
	private ObjectOutputStream objectOut;
	private ClientGameController gameController;
	private ReaderThread readerThread;

	@Inject
	public TetrisClient(ClientGameController gameController)
			throws UnknownHostException, IOException {
		initializeClient();
		this.gameController = gameController;
		readerThread.start();

	}

	private void initializeClient() throws UnknownHostException, IOException {
		// socket = new Socket(new DiscoverClient().discoverTetrisServer(),
		// 8080);
		socket = new Socket("192.168.117.124", 8080);
		objectOut = new ObjectOutputStream(socket.getOutputStream());
		readerThread = new ReaderThread(socket, gameController);

		send(new NewPlayerMessage());
	}

	public void send(Message message) throws IOException {
		objectOut.writeObject(message);
		objectOut.flush();
	}

	public ClientGameController getGameController() {
		return gameController;
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new Module[0]);
		TetrisClient client = injector.getInstance(TetrisClient.class);
	}
}
