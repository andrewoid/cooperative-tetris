package edu.touro.cooptetris.net.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.touro.cooptetris.net.message.Message;
import edu.touro.cooptetris.net.message.NewPlayerMessage;
import edu.touro.cooptetris.net.message.SetUpPlayerMessage;

public class MockClient {
	
	private final static Logger log = Logger
			.getLogger(MockClient.class.getName());
	Socket socket;
	OutputStream out;
	ObjectOutputStream objectOut;
	ObjectInputStream objectIn;
	
	public MockClient(){
		try {
			socket=new Socket("192.168.117.124",8080);
			out=socket.getOutputStream();
			log.log(Level.INFO,"Got output stream");
			objectOut=new ObjectOutputStream(out);
			log.log(Level.INFO,"made objectOutput stream");
			
			objectOut.writeObject(new NewPlayerMessage());
			log.log(Level.INFO,"sent new player message");
			objectOut.flush();

			objectIn=new ObjectInputStream(socket.getInputStream());
			log.log(Level.INFO,"set up object input stream");
			
			log.log(Level.INFO,"waiting to receive reply message");
			Message message= (Message) objectIn.readObject();
			if(message instanceof SetUpPlayerMessage){
				log.log(Level.INFO,"WE DID IT");
			}else{
				log.log(Level.INFO,"TIME TO GO HOME");
			}
			
			objectIn.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		MockClient m=new MockClient();
	}
}
