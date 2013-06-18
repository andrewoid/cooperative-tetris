package edu.touro.cooptetris.pieces;

import java.awt.Color;

public class LinePiece extends Piece {

	private static final long serialVersionUID = 1L;

	public LinePiece(int x, int y, int pieceID, int playerID) {
		super(pieceID, playerID);

		setLocation(x, y);
		for (Square square : squares) {
			square.setColor(Color.ORANGE);
		}
		center = squares[1];
	}

	@Override
	public void setLocation(int x, int y) {
		for (int i = 0; i < squares.length; i++) {
			Square s = squares[i];
			s.setY(i * Square.SIDE + y);
			s.setX(x);
		}
	}
}
