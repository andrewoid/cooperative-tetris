package edu.touro.cooptetris;

import java.awt.Color;

public class LPiece extends Piece {

	public LPiece() {
		super();
		for (int i = 0; i < 3; i++) {
			Square s = squares[i];
			s.setY(i * s.getSide());
			s.setColor(Color.PINK);
		}

		Square s = squares[3];
		s.setY(2 * s.getSide());
		s.setX(s.getX() + s.getSide());
		s.setColor(Color.PINK);

	}

}
