package edu.touro.cooptetris.pieces;

import java.awt.Color;

public class BoxPiece extends Piece {

	public BoxPiece(int x, int y) {
		super();
		setLocation(x,y);
		center = squares[2];

		for (Square square : squares) {
			square.setColor(Color.RED);
		}
	}
	public void setLocation(int x, int y){
		squares[0].setX(x);
		squares[0].setY(y);
		squares[1].setX(Square.SIDE + x);
		squares[1].setY(y);
		squares[2].setY(Square.SIDE + y);
		squares[2].setX(x);
		squares[3].setX(Square.SIDE + x);
		squares[3].setY(Square.SIDE + y);
	}
	
	@Override
	public void rotate() {

	}

}
