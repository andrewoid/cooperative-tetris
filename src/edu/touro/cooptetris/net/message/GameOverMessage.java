package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.ClientGameController;
import edu.touro.cooptetris.GameController;

public class GameOverMessage implements Message {

	private static final long serialVersionUID = 1L;

	@Override
	public void handleByClient(ClientGameController gameController) {
		gameController.endGame();

	}

	@Override
	public void handleByServer(GameController gameController) {

	}

}
