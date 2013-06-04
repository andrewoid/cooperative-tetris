package edu.touro.cooptetris.pieces;

import java.awt.Color;

public class LPiece extends Piece {

	public LPiece(int x, int y, int pieceID, int playerID) {
		super(pieceID, playerID);

		setLocation(x, y);
		center = squares[1];
		for (Square square : squares) {
			square.setColor(Color.PINK);
		}
	}

	@Override
	public void setLocation(int x, int y) {
		for (int i = 0; i < 3; i++) {
			Square s = squares[i];
			s.setY(i * Square.SIDE + y);
			s.setX(x);
		}

		Square s = squares[3];
		s.setY(2 * Square.SIDE + y);
		s.setX(Square.SIDE + x);
	}

}
