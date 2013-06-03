package edu.touro.cooptetris.pieces;

import com.google.inject.Singleton;

@Singleton
public class PieceIDGenerator {

	private int currPieceID;

	public PieceIDGenerator() {
		currPieceID = 0;
	}

	public int getNextPieceID() {
		return currPieceID++;
	}

	public void resetPieceID() {
		currPieceID = 0;
	}

}
