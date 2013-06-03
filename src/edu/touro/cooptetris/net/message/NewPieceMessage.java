package edu.touro.cooptetris.net.message;

import edu.touro.cooptetris.GameController;
import edu.touro.cooptetris.net.Player;
import edu.touro.cooptetris.pieces.Piece;

public class NewPieceMessage implements Message{
	
	private int xDrop;
	
	public NewPieceMessage(Player player, Piece piece){
		//server should set xDrop of piece to player's xDrop
		
	}

	@Override
	public void handleByClient(GameController gameController) {
		
		
	}

	@Override
	public void handleByServer(GameController gameController) {
		
		
	}

}
