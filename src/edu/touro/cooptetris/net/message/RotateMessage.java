package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.ClientGameController;
import edu.touro.cooptetris.pieces.Piece;

public class RotateMessage implements Message {

	private static final long serialVersionUID = 1L;
	private int pieceID;

	public RotateMessage(int pieceID) {
		this.pieceID = pieceID;
	}

	@Override
	public void handleByClient(ClientGameController gameController) {
		Piece p = gameController.getPieceByID(pieceID);
		p.rotate();
	}

	@Override
	public void handleByServer(ClientGameController gameController) {
		Piece piece = gameController.getPieceByID(pieceID);
		gameController.rotate(piece);
	}

}
