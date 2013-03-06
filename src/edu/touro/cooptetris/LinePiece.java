package edu.touro.cooptetris;

import java.awt.Color;

public class LinePiece extends Piece {

	public LinePiece() {
		super();
		for (int i = 0; i < squares.length; i++) {
			Square s = squares[i];
			s.setY(i * s.getSide());
			s.setColor(Color.ORANGE);
		}
	}

	@Override
	public void rotate() {

	}

}
