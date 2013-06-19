package edu.touro.cooptetris.net.message;

import java.util.logging.Logger;

import edu.touro.cooptetris.net.client.ClientGameController;
import edu.touro.cooptetris.net.server.ServerGameController;
import edu.touro.cooptetris.pieces.Piece;

public class NewPieceMessage implements Message {
	private final static Logger logger = Logger.getLogger(NewPieceMessage.class
			.getName());

	private static final long serialVersionUID = 1L;
	private Piece piece;

	public NewPieceMessage(Piece piece) {
		this.piece = piece;
	}

	@Override
	public void handleByClient(ClientGameController gameController) {

		logger.info("received new piece message");
		gameController.addNewPiece(piece);
	}

	@Override
	public void handleByServer(ServerGameController gameController) {

	}

	public Piece getPiece() {
		return piece;
	}

}
