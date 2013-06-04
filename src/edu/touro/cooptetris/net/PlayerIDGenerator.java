package edu.touro.cooptetris.net;

import com.google.inject.Singleton;

@Singleton
public class PlayerIDGenerator {

	private int currPlayerID;

	public PlayerIDGenerator() {
		currPlayerID = 0;
	}

	public int getNextPieceID() {
		return currPlayerID++;
	}

	public void resetPieceID() {
		currPlayerID = 0;
	}

}
