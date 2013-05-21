package edu.touro.cooptetris.server;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TetrisClient {
	
	private WritingThread readerThread;
	private Socket socket;
	private String myName;
	private MessageParser parser;

	// private static int currentNameID;
	// private int myID;

	public TetrisClient() throws IOException {
		
		myName = "";// + String.valueOf(myID);
		parser = new MessageParser();
		initializeClient();
		// initializeServer();
		readerThread.start();

	}

	

	private void initializeClient() throws UnknownHostException, IOException {
		// socket = new Socket("192.168.117.105", 8080);
		socket = new Socket("localhost", 8080);
		readerThread = new Client(socket, this);
		
	}

	public void receiveChatMessage(String s) throws IOException {
		MessageType type = parser.getMessageType(s);
		String message = "";
		String name = parser.getName(s);
		switch (type) {
		case MOVE:
			break;
		case ROTATE:
			break;
		case REMOVE_ROW:
			break;
		case NEW_PIECE:
			break;
		case NEW_PLAYER:
			break;
		case GAME_OVER:
			break;
		default:
			message = "cannot parse message";

		}
		
		message=parser.getMessage();

		System.out.println(name+" "+message);
	}

	public void sendMessage(String message) throws IOException {
		readerThread.send("SAY " + myName + " " + s);
		
	}

	public static void main(String[] args) {
		try {
			new TetrisClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}
