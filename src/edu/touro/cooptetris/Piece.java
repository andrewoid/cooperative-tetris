package edu.touro.cooptetris;

public class Piece {

	Square[] squares;

	public Piece() {
		squares = new Square[4];

		for (int i = 0; i < 4; i++) {
			squares[i] = new Square(10, 0, 0);

		}
	}

}
