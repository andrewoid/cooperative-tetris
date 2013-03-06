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

	public void moveRight() {
		for (int i = 0; i < squares.length; i++) {
			Square s = squares[i];
			int side = s.getSide();
			s.setX(s.getX() + side);
		}
	}

	public void rotate() {

		for (Square s : squares) {
			int currX = s.getX();
			s.setX(s.getY());
			s.setY(-currX);
		}
	}

	public void moveDown() {
		for (int i = 0; i < squares.length; i++) {
			Square s = squares[i];
			int side = s.getSide();
			s.setY(s.getY() + side);
		}
	}

	public void moveLeft() {
		for (int i = 0; i < squares.length; i++) {
			Square s = squares[i];
			int side = s.getSide();
			s.setX(s.getX() - side);
		}
	}

}
