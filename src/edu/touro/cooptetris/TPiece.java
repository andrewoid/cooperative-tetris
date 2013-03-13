package edu.touro.cooptetris;

import java.awt.Color;

public class TPiece extends Piece {

	public TPiece(int x, int y) {
		super();
		for (int i = 0; i < 3; i++) {
			Square s = squares[i];
			s.setX(i * s.getSide()+x);
			s.setY(y);
			s.setColor(Color.YELLOW);
		}
		Square s = squares[3];
		s.setX(1 * s.getSide()+x);
		s.setY(1 * s.getSide()+y);
		s.setColor(Color.YELLOW);
		center=squares[1];
	}

}
