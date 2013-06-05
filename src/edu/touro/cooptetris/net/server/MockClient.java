package edu.touro.cooptetris.net.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.touro.cooptetris.net.message.Message;
import edu.touro.cooptetris.net.message.NewPlayerMessage;
import edu.touro.cooptetris.net.message.SetUpPlayerMessage;

public class MockClient {
	
	Socket socket;
	OutputStream out;
	ObjectOutputStream objectOut;
	ObjectInputStream objectIn;
	
	public MockClient(){
		try {
			socket=new Socket("192.168.117.124",8080);
			out=socket.getOutputStream();
			System.out.println("Got output stream");
			objectOut=new ObjectOutputStream(out);
			System.out.println("made objectOutput stream");
			
			objectOut.writeObject(new NewPlayerMessage());
			System.out.println("sent new player message");
			objectOut.flush();

			objectIn=new ObjectInputStream(socket.getInputStream());
			System.out.println("set up object input stream");
			
			System.out.println("waiting to receive reply message");
			Message message= (Message) objectIn.readObject();
			if(message instanceof SetUpPlayerMessage){
				System
				.out.println("WE DID IT");
			}else{
				System.out.println("TIME TO GO HOME");
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
