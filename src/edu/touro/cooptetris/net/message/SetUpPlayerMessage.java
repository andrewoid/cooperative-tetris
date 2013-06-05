package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.Board;
import edu.touro.cooptetris.net.client.ClientGameController;
import edu.touro.cooptetris.net.server.ServerGameController;

public class SetUpPlayerMessage implements Message {

	private static final long serialVersionUID = 1L;
	private Board board;
	private int playerID;

	public SetUpPlayerMessage(Board board, int playerID) {
		this.board = board;
		this.playerID = playerID;
	}

	@Override
	public void handleByServer(ServerGameController gameController) {
	}

	@Override
	public void handleByClient(ClientGameController gameController) {
		gameController.setBoard(board);
		if (gameController.getPlayerID() <= 0) {
			gameController.setPlayerID(playerID);
		}

	}
}
