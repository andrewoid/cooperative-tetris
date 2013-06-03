package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.ClientGameController;
import edu.touro.cooptetris.GameController;
import edu.touro.cooptetris.pieces.Piece;

public class NewPieceMessage implements Message {

	private Piece piece;

	public NewPieceMessage(Piece piece) {
		this.piece = piece;
	}

	@Override
	public void handleByClient(ClientGameController gameController) {
		gameController.addNewPiece(piece);
	}

	@Override
	public void handleByServer(GameController gameController) {

	}

}
