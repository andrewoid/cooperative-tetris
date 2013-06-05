package edu.touro.cooptetris.net;

public class Player {

	private int playerID;
	private int xDrop;
	
	
	public Player(int playerID, int xDrop) {
		this.playerID = playerID;
		this.xDrop = xDrop;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public int getxDrop() {
		return xDrop;
	}
	public void setxDrop(int xDrop) {
		this.xDrop = xDrop;
	}
	
	
	
}
