package edu.touro.cooptetris.net;

import com.google.inject.Singleton;

@Singleton
public class PlayerIDGenerator {

	private int currPlayerID;

	public PlayerIDGenerator() {
		currPlayerID = 0;
	}

	public int getNextPlayerID() {
		return currPlayerID++;
	}

	public void resetPlayerID() {
		currPlayerID = 0;
	}

}
