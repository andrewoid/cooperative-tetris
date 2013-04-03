package edu.touro.cooptetris;

import java.awt.Color;

public class SPiece extends Piece {

	public SPiece(int x, int y) {
		super();
		Color color = Color.BLUE;
		int side = Square.SIDE;
		for (int i = 0; i < 2; i++) {
			Square s = squares[i];
			s.setY(i * side+y);
			s.setX(x);
			s.setColor(color);
		}
		Square s = squares[2];
		s.setY(side+y);
		s.setX(-side+x);
		s.setColor(color);
		s = squares[3];
		s.setY(y);
		s.setX(side+x);
		s.setColor(color);
		center=squares[0];
	}

}
