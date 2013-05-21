package edu.touro.cooptetris.net;

import java.util.StringTokenizer;

public class MessageParser {
	private MessageType messageType;
	private String player;
	private int pieceID;
	
	public MessageParser(String msg){
		try{
		parse(msg);
		}catch(Exception e){
			System.out.println("error parsing message");
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
