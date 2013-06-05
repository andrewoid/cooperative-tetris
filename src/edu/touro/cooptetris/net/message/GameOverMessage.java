package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.ServerGameController;
import edu.touro.cooptetris.net.client.ClientGameController;

public class GameOverMessage implements Message {

	private static final long serialVersionUID = 1L;

	@Override
	public void handleByClient(ClientGameController gameController) {
		gameController.endGame();

	}

	@Override
	public void handleByServer(ServerGameController gameController) {

	}

}
