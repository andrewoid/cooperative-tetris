package edu.touro.cooptetris.net.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.touro.cooptetris.GameController;
import edu.touro.cooptetris.GameStateListener;
import edu.touro.cooptetris.net.message.Message;

public class TetrisClient {

	private Socket socket;
	private ObjectOutputStream objectOut;
	private GameController gameController;
	private GameStateListener gameStateListener;
	private ReaderThread readerThread;
	
	public TetrisClient(GameController gameController, GameStateListener gameStateListener)throws UnknownHostException, IOException{
		initializeClient();
		this.gameController = gameController;
		this.gameStateListener = gameStateListener;
		
		readerThread.start();
	}

	private void initializeClient() throws UnknownHostException, IOException {
		socket = new Socket("localhost", 8080);
		objectOut=new ObjectOutputStream(socket.getOutputStream());
		readerThread = new ReaderThread(socket, gameController, gameStateListener);
		
		
		//String s="join message"
		//send(s);
	}
	
	public void send(Message message) throws IOException {
		objectOut.writeObject(message);
		objectOut.flush();
	}
}
