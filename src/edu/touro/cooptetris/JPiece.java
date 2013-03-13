package edu.touro.cooptetris;

import java.awt.Color;

public class JPiece extends Piece {

	public JPiece(int x, int y) {
		super();
		for (int i = 0; i < 3; i++) {
			Square s = squares[i];
			s.setY(i * s.getSide()+y);
			s.setX(x);
			s.setColor(Color.BLUE);
		}

		Square s = squares[3];
		s.setY(2 * s.getSide()+y);
		s.setX(s.getX() - s.getSide()+x);
		s.setColor(Color.BLUE);
		
		center=squares[1];

	}

}
