package edu.touro.cooptetris;

import java.awt.Color;

public class Line extends Piece {

	public Line() {
		super();
		for (int i = 0; i < squares.length; i++) {
			Square s = squares[i];
			s.setY(i * s.getSide());
			s.setColor(Color.ORANGE);
		}
	}

}
