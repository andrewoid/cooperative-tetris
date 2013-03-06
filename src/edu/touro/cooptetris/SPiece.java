package edu.touro.cooptetris;

import java.awt.Color;

public class SPiece extends Piece {

	public SPiece() {
		super();
		Color color = Color.BLUE;
		int side = squares[0].getSide();
		for (int i = 0; i < 2; i++) {
			Square s = squares[i];
			s.setY(i * side);
			s.setColor(color);
		}
		Square s = squares[2];
		s.setY(side);
		s.setX(-side);
		s.setColor(color);
		s = squares[3];
		s.setY(0);
		s.setX(side);
		s.setColor(color);

	}

}
