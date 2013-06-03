package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.GameController;

public class GameOverMessage implements Message{

	@Override
	public void handleByClient(GameController gameController) {
		gameController.endGame();
		
	}

	@Override
	public void handleByServer(GameController gameController) {
		
		
	}

}
