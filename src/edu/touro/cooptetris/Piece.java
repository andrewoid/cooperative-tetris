package edu.touro.cooptetris;

public class Piece {

	protected Square[] squares;

	public Piece() {
		squares = new Square[4];

		for (int i = 0; i < 4; i++) {
			squares[i] = new Square(10, 0, 0);

		}
	}

	public Square[] getSquares() {
		return squares;
	}

	public void rotate() {

		for (Square s : squares) {
			int currX = s.getX();
			s.setX(s.getY());
			s.setY(-currX);
		}
	}

}
