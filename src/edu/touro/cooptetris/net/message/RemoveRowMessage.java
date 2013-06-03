package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.ClientGameController;
import edu.touro.cooptetris.GameController;
import edu.touro.cooptetris.pieces.Piece;

public class RemoveRowMessage implements Message {

	private static final long serialVersionUID = 1L;
	private int pieceID;

	public RemoveRowMessage(int pieceID) {
		this.pieceID = pieceID;
	}
	
	@Override
	public void handleByClient(ClientGameController gameController) {
		Piece piece=gameController.getPieceByID(pieceID);
		gameController.removeRow(piece);
		
	}

	@Override
	public void handleByServer(GameController gameController) {
		
		
	}
	
	

}
