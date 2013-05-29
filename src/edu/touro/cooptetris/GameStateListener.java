package edu.touro.cooptetris;

import edu.touro.cooptetris.pieces.Piece;

public interface GameStateListener {

	public void onGameOver();

	public void onCompleteLine();

	public void onLevelChange();

	public void onHitFloor();

	public void onNewPiece(Piece piece);

}
