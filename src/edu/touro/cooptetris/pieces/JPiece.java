package edu.touro.cooptetris.pieces;

import java.awt.Color;

public class JPiece extends Piece {

	public JPiece(int x, int y) {
		super();
		int side = Square.SIDE;
		for (int i = 0; i < 3; i++) {
			Square s = squares[i];
			s.setY(i * side + y);
			s.setX(x);
			s.setColor(Color.BLUE);
		}

		Square s = squares[3];
		s.setY(2 * side + y);
		s.setX(s.getX() - side + x);
		s.setColor(Color.BLUE);

		center = squares[1];

	}

}
