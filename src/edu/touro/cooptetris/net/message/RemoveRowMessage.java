package edu.touro.cooptetris.net.message;

import java.util.logging.Logger;

import edu.touro.cooptetris.Board;
import edu.touro.cooptetris.net.client.ClientGameController;
import edu.touro.cooptetris.net.server.ServerGameController;
import edu.touro.cooptetris.pieces.Piece;

public class RemoveRowMessage implements Message {
	private final static Logger logger = Logger
			.getLogger(RemoveRowMessage.class.getName());
	private static final long serialVersionUID = 1L;
	private int pieceID;

	public RemoveRowMessage(int pieceID) {
		this.pieceID = pieceID;
	}

	@Override
	public void handleByClient(ClientGameController gameController) {
		logger.info("received " + this.getClass());
		Piece piece = gameController.getPieceByID(pieceID);
		gameController.removeRow(piece);

	}

	@Override
	public void handleByServer(ServerGameController gameController) {

	}

}
