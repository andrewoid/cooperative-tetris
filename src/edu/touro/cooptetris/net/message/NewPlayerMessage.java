package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.net.client.ClientGameController;
import edu.touro.cooptetris.net.server.ServerGameController;

public class NewPlayerMessage implements Message {

	private static final long serialVersionUID = 1L;

	// public NewPlayerMessage(String playerName, int playerID) {
	// Player player=new Player(playerID, playerName);
	// }

	@Override
	public void handleByClient(ClientGameController gameController) {
	}

	@Override
	public void handleByServer(ServerGameController gameController) {
		gameController.addPlayer();
		
	}

}
