package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.GameController;
import edu.touro.cooptetris.net.client.ClientGameController;

public class SoftDropAllMessage implements Message {
	private static final long serialVersionUID = 1L;

	@Override
	public void handleByClient(ClientGameController gameController) {
		gameController.dropAll();
	}

	@Override
	public void handleByServer(GameController gameController) {
		// TODO Auto-generated method stub

	}

}
