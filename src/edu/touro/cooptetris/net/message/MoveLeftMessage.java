package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.net.client.ClientGameController;
import edu.touro.cooptetris.net.server.ServerGameController;
import edu.touro.cooptetris.pieces.Piece;

public class MoveLeftMessage implements Message{
	
	private static final long serialVersionUID = 1L;
	private int pieceID;

	public MoveLeftMessage(int pieceID) {
		this.pieceID = pieceID;
	}

	@Override
	public void handleByClient(ClientGameController gameController) {
		Piece piece = gameController.getPieceByID(pieceID);
		gameController.moveLeft(piece);
	}

	@Override
	public void handleByServer(ServerGameController gameController) {
		Piece piece = gameController.getPieceByID(pieceID);
		gameController.moveLeft(piece);
	}

}
