package edu.touro.cooptetris;

import java.awt.Color;

public class LPiece extends Piece {

	public LPiece(int x, int y) {
		super();
		// TODO: is it a waste to call .getSide() so many times?
		for (int i = 0; i < 3; i++) {
			Square s = squares[i];
			s.setY(i * s.getSide()+y);
			s.setX(x);
			s.setColor(Color.PINK);
		}

		Square s = squares[3];
		s.setY(2 * s.getSide()+y);
		s.setX(s.getX() + s.getSide()+x);
		s.setColor(Color.PINK);
		center=squares[1];
	}

}
