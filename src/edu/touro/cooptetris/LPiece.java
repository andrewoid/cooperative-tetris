package edu.touro.cooptetris;

import java.awt.Color;

public class LPiece extends Piece {

	public LPiece(int x, int y) {
		super();
		int side = Square.SIDE;
		for (int i = 0; i < 3; i++) {
			Square s = squares[i];
			s.setY(i * side + y);
			s.setX(x);
			s.setColor(Color.PINK);
		}

		Square s = squares[3];
		s.setY(2 * side + y);
		s.setX(s.getX() + side + x);
		s.setColor(Color.PINK);
		center = squares[1];
	}

}
