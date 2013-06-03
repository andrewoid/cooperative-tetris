package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.GameController;
import edu.touro.cooptetris.GameStateListener;
import edu.touro.cooptetris.pieces.Piece;

public class MoveRightMessage implements Message {

	
	private static final long serialVersionUID = 1L;
	private int pieceID;

	public MoveRightMessage(int pieceID) {
		this.pieceID = pieceID;
	}

	@Override
	public void handleByClient(GameController gameController) {
		Piece piece = gameController.getPieceByID(pieceID);
		gameController.moveRight(piece);
	}

	@Override
	public void handleByServer(GameController gameController) {
		Piece piece = gameController.getPieceByID(pieceID);
		gameController.moveRight(piece);
	}
}
