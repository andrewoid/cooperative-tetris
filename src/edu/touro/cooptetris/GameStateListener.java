package edu.touro.cooptetris;

import edu.touro.cooptetris.pieces.Piece;

public interface GameStateListener {

	public void onGameOver();

	public void onCompleteLine(int numLines);

	public void onLevelChange();

	public void onHitFloor();

	public void onNewPiece(Piece piece);

	public void onRotate();

	public void onPause();

	public void onToggleThemeMusic();

}
