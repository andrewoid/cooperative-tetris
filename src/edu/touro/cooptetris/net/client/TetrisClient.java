package edu.touro.cooptetris.net.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.touro.cooptetris.ClientGameController;
import edu.touro.cooptetris.net.message.Message;

public class TetrisClient {

	private Socket socket;
	private ObjectOutputStream objectOut;
	private ClientGameController gameController;
	private ReaderThread readerThread;
	
	public TetrisClient(ClientGameController gameController)throws UnknownHostException, IOException{
		initializeClient();
		this.gameController = gameController;
		
		readerThread.start();
	}

	private void initializeClient() throws UnknownHostException, IOException {
		socket = new Socket("localhost", 8080);
		objectOut=new ObjectOutputStream(socket.getOutputStream());
		readerThread = new ReaderThread(socket, gameController);
		
		
		//String s="join message"
		//send(s);
	}
	
	public void send(Message message) throws IOException {
		objectOut.writeObject(message);
		objectOut.flush();
	}
}
