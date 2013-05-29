package edu.touro.cooptetris.pieces;

import java.awt.Color;

public class LinePiece extends Piece {

	public LinePiece(int x, int y) {
		super();
		
		setLocation(x,y);
		for (Square square : squares) {
			square.setColor(Color.ORANGE);
		}
		center = squares[1];
	}

	public void setLocation(int x, int y){
		for (int i = 0; i < squares.length; i++) {
			Square s = squares[i];
			s.setY(i * Square.SIDE + y);
			s.setX(x);
		}
	}
}
