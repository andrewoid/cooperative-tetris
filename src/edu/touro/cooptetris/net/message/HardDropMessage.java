package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.ServerGameController;
import edu.touro.cooptetris.net.client.ClientGameController;
import edu.touro.cooptetris.pieces.Piece;

public class HardDropMessage implements Message {

	private static final long serialVersionUID = 1L;
	private int pieceID;

	public HardDropMessage(int pieceID) {
		this.pieceID = pieceID;
	}

	@Override
	public void handleByClient(ClientGameController gameController) {
		Piece piece = gameController.getPieceByID(pieceID);
		gameController.drop(piece);
	}

	@Override
	public void handleByServer(ServerGameController gameController) {
		Piece piece = gameController.getPieceByID(pieceID);
		gameController.drop(piece);
	}

}