package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.GameController;
import edu.touro.cooptetris.GameStateListener;
import edu.touro.cooptetris.pieces.Piece;

public class RotateMessage implements Message {

	private static final long serialVersionUID = 1L;
	private int pieceID;

	public RotateMessage(int pieceID) {
		this.pieceID = pieceID;
	}

	@Override
	public void handleByClient(GameController gameController,
			GameStateListener gameStateListener) {
		Piece p = gameController.getPieceByID(pieceID);
		p.rotate();
		gameStateListener.onRotate();
	}

	@Override
	public void handleByServer(GameController gameController) {
		Piece piece = gameController.getPieceByID(pieceID);
		gameController.rotate(piece);
	}

}
