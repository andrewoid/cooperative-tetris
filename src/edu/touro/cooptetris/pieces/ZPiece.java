package edu.touro.cooptetris.pieces;

import java.awt.Color;

public class ZPiece extends Piece {

	public ZPiece(int x, int y, int pieceID) {
		super(pieceID);
		
		setLocation(x,y);
		for (Square square : squares) {
			square.setColor(Color.GREEN);
		}
		
		center=squares[0];

	}
	public void setLocation(int x, int y){
		for (int i = 0; i < 2; i++) {
			Square s = squares[i];
			s.setY(i * Square.SIDE + y);
			s.setX(x);
		}
		Square s = squares[2];
		s.setY(y);
		s.setX(-Square.SIDE+x);
		s = squares[3];
		s.setY(Square.SIDE+y);
		s.setX(Square.SIDE+x);
	}

}
