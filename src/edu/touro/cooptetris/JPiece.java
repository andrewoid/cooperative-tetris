package edu.touro.cooptetris;

import java.awt.Color;

public class JPiece extends Piece {

	public JPiece() {
		super();
		for (int i = 0; i < 3; i++) {
			Square s = squares[i];
			s.setY(i * s.getSide());
			s.setColor(Color.BLUE);
		}

		Square s = squares[3];
		s.setY(2 * s.getSide());
		s.setX(s.getX() - s.getSide());
		s.setColor(Color.BLUE);

	}

	@Override
	public void rotate() {

	}
}
