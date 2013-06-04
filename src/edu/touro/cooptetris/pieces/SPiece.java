package edu.touro.cooptetris.pieces;

import java.awt.Color;

public class SPiece extends Piece {

	public SPiece(int x, int y, int pieceID, int playerID) {
		super(pieceID, playerID);

		setLocation(x, y);
		for (Square square : squares) {
			square.setColor(Color.BLUE);
		}
		center = squares[0];
	}

	@Override
	public void setLocation(int x, int y) {
		for (int i = 0; i < 2; i++) {
			Square s = squares[i];
			s.setY(i * Square.SIDE + y);
			s.setX(x);
		}
		Square s = squares[2];
		s.setY(Square.SIDE + y);
		s.setX(-Square.SIDE + x);

		s = squares[3];
		s.setY(y);
		s.setX(Square.SIDE + x);
	}

}
