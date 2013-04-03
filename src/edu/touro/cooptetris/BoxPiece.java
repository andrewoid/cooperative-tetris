package edu.touro.cooptetris;

import java.awt.Color;

public class BoxPiece extends Piece {

	public BoxPiece(int x, int y) {
		super();
		int side = Square.SIDE;
		squares[0].setX(x);
		squares[0].setY(y);
		squares[1].setX(side + x);
		squares[1].setY(y);
		squares[2].setY(side + y);
		squares[2].setX(x);
		squares[3].setX(side + x);
		squares[3].setY(side + y);
		center = squares[2];

		for (Square square : squares) {
			square.setColor(Color.RED);
		}
	}

	@Override
	public void rotate() {

	}

}
