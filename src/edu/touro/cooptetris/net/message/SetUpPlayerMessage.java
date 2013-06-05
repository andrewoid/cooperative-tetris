package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.Board;
import edu.touro.cooptetris.net.client.ClientGameController;
import edu.touro.cooptetris.ServerGameController;


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
		// TODO Auto-generated method stub

	}

	@Override
	public void handleByClient(ClientGameController gameController) {
		gameController.setBoard(board);
		gameController.setPlayerID(playerID);

	}

}
