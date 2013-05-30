package edu.touro.cooptetris.pieces;

import java.awt.Color;

public class LPiece extends Piece {

	public LPiece(int x, int y) {
		super();
		setLocation(x,y);
		center = squares[1];
		for (Square square : squares) {
			square.setColor(Color.PINK);
		}
	}
	
	public void setLocation(int x, int y){
		for (int i = 0; i < 3; i++) {
			Square s = squares[i];
			s.setY(i * Square.SIDE + y);
			s.setX(x);
		}

		Square s = squares[3];
		s.setY(2 * Square.SIDE + y);
		s.setX(Square.SIDE + x);
	}

}
