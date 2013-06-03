package edu.touro.cooptetris.pieces;

import java.awt.Color;

public class TPiece extends Piece {

	public TPiece(int x, int y, int pieceID) {
		super(pieceID);
		setLocation(x,y);
		for (Square square : squares) {
			square.setColor(Color.YELLOW);
		}
		
		center = squares[1];
	}

	public void setLocation(int x, int y){
		for (int i = 0; i < 3; i++) {
			Square s = squares[i];
			s.setX(i * Square.SIDE + x);
			s.setY(y);
		}
		Square s = squares[3];
		s.setX(1 * Square.SIDE + x);
		s.setY(1 * Square.SIDE + y);
	}
}
