package edu.touro.cooptetris;

import java.util.Random;

public class PieceFactory {

	private Random random;

	public PieceFactory() {
		random = new Random();
	}

	public Piece getRandomPiece() {
		int r = random.nextInt(7);
		switch (r) {
		case 0:
			return new BoxPiece();
		case 1:
			return new JPiece();
		case 2:
			return new LPiece();
		case 3:
			return new SPiece();
		case 4:
			return new TPiece();
		case 5:
			return new ZPiece();
		case 6:
			return new LinePiece();
		}

		throw new IllegalStateException("Piece not found");
	}

}
