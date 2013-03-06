package edu.touro.cooptetris;

import java.awt.Color;

public class TPiece extends Piece {

	public TPiece() {
		super();
		for (int i = 0; i < 3; i++) {
			Square s = squares[i];
			s.setX(i * s.getSide());
			s.setColor(Color.YELLOW);
		}
		Square s = squares[3];
		s.setX(1 * s.getSide());
		s.setY(1 * s.getSide());
		s.setColor(Color.YELLOW);
	}

	@Override
	public void rotate() {

	}
}
