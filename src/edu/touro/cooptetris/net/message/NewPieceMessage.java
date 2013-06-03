package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.ClientGameController;
import edu.touro.cooptetris.GameController;
import edu.touro.cooptetris.pieces.Piece;

public class NewPieceMessage implements Message{
	
	private int xDrop;
	
	public NewPieceMessage(int xDrop, Piece piece){
		//server should set xDrop of piece to player's xDrop
		this.xDrop=xDrop;
		
	}

	@Override
	public void handleByClient(ClientGameController gameController) {
		
		
	}

	@Override
	public void handleByServer(GameController gameController) {
		
		
	}

}
