package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.ClientGameController;

public class GameOverMessage implements Message{

	@Override
	public void handleByClient(ClientGameController gameController) {
		gameController.endGame();
		
	}

	@Override
	public void handleByServer(ClientGameController gameController) {
		
		
	}

}
