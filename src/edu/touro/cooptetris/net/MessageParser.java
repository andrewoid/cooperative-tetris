package edu.touro.cooptetris.net;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageParser {
	private final static Logger log = Logger
			.getLogger(MessageParser.class.getName());
	private MessageType messageType;
	private String player;
	private int pieceID;
	
	public MessageParser(String msg){
		try{
		parse(msg);
		}catch(Exception e){
			log.log(Level.INFO,"error parsing message");
		}
	}
	private void parse(String msg)throws Exception{
		StringTokenizer tokenizer=new StringTokenizer(msg);
		try{
		this.messageType=MessageType.valueOf(tokenizer.nextToken().toUpperCase());
		this.player=tokenizer.nextToken();
		this.pieceID=Integer.parseInt(tokenizer.nextToken());
		}catch(Exception e){
			throw e;
		}
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public String getPlayer(){
		return player;
	}
	public int getPieceID(){
		return pieceID;
	}
}
