package edu.touro.cooptetris;

import java.awt.Color;

public class LinePiece extends Piece {

	public LinePiece(int x, int y) {
		super();
		int side = Square.SIDE;
		for (int i = 0; i < squares.length; i++) {
			Square s = squares[i];
			s.setY(i * side + y);
			s.setX(x);
			s.setColor(Color.ORANGE);
		}
		center = squares[1];
	}

}
