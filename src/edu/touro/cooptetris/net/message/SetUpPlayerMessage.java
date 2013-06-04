package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.Board;
import edu.touro.cooptetris.ClientGameController;
import edu.touro.cooptetris.GameController;

public class SetUpPlayerMessage implements Message {

	private static final long serialVersionUID = 1L;
	private Board board;
	private int playerID;

	public SetUpPlayerMessage(Board board, int playerID) {
		this.board = board;
		this.playerID=playerID;
	}

	@Override
	public void handleByClient(ClientGameController gameController) {
		gameController.setBoard(board);
	}

	@Override
	public void handleByServer(GameController gameController) {
		// TODO Auto-generated method stub

	}

}
