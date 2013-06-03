package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.ClientGameController;
import edu.touro.cooptetris.GameStateListener;
import edu.touro.cooptetris.pieces.Piece;

public class MoveDownMessage implements Message{

	private static final long serialVersionUID = 1L;
	private int pieceID;

	public MoveDownMessage(int pieceID) {
		this.pieceID = pieceID;
	}

	@Override
	public void handleByClient(ClientGameController gameController) {
		Piece piece = gameController.getPieceByID(pieceID);
		gameController.moveDown(piece);
	}

	@Override
	public void handleByServer(ClientGameController gameController) {
		Piece piece = gameController.getPieceByID(pieceID);
		gameController.moveDown(piece);
	}
	
}
