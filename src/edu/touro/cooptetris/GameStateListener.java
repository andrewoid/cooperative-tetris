package edu.touro.cooptetris;

public interface GameStateListener {

	public void onGameOver();

	public void onCompleteLine();

	public void onLevelChange();

	public void onHitFloor();

}
