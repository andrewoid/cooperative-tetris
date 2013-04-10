package edu.touro.cooptetris.pieces;

import java.awt.Color;

public class TPiece extends Piece {

	public TPiece(int x, int y) {
		super();
		int side = Square.SIDE;
		for (int i = 0; i < 3; i++) {
			Square s = squares[i];
			s.setX(i * side + x);
			s.setY(y);
			s.setColor(Color.YELLOW);
		}
		Square s = squares[3];
		s.setX(1 * side + x);
		s.setY(1 * side + y);
		s.setColor(Color.YELLOW);
		center = squares[1];
	}

}
