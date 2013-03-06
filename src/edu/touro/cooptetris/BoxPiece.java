package edu.touro.cooptetris;

import java.awt.Color;

public class BoxPiece extends Piece{
	
	public BoxPiece(){
		super();
		
		int side=squares[0].getSide();
		
	
		
		squares[1].setX(side);
		squares[2].setY(side);
		squares[3].setX(side);
		squares[3].setY(side);
		
		for(int i=0; i<squares.length; i++){
			squares[i].setColor(Color.RED);
		}
	}

}
