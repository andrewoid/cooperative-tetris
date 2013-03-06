package edu.touro.cooptetris;

import java.awt.Color;

public class ZPiece extends Piece {

	public ZPiece() {
		super();
		Color color = Color.GREEN;
		int side = squares[0].getSide();

		for (int i = 0; i < 2; i++) {
			Square s = squares[i];
			s.setY(i * side);
			s.setColor(color);
		}
		Square s = squares[2];
		s.setY(0);
		s.setX(-side);
		s.setColor(color);
		s = squares[3];
		s.setY(side);
		s.setX(side);
		s.setColor(color);

	}

	@Override
	public void rotate() {

	}
}
