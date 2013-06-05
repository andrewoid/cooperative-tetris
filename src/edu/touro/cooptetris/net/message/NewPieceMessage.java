package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.net.client.ClientGameController;
import edu.touro.cooptetris.net.server.ServerGameController;
import edu.touro.cooptetris.pieces.Piece;

public class NewPieceMessage implements Message {

	private static final long serialVersionUID = 1L;
	private Piece piece;

	public NewPieceMessage(Piece piece) {
		this.piece = piece;
	}

	@Override
	public void handleByClient(ClientGameController gameController) {
		gameController.addNewPiece(piece);
	}

	@Override
	public void handleByServer(ServerGameController gameController) {

	}

}
