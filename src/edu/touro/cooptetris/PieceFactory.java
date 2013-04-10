package edu.touro.cooptetris;

import java.util.Random;

import edu.touro.cooptetris.pieces.BoxPiece;
import edu.touro.cooptetris.pieces.JPiece;
import edu.touro.cooptetris.pieces.LPiece;
import edu.touro.cooptetris.pieces.LinePiece;
import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.SPiece;
import edu.touro.cooptetris.pieces.TPiece;
import edu.touro.cooptetris.pieces.ZPiece;

public class PieceFactory {

	private Random random;

	public PieceFactory() {
		random = new Random();
	}

	public Piece getRandomPiece(int x, int y) {
		int r = random.nextInt(7);
		switch (r) {
		case 0:
			return new BoxPiece(x, y);
		case 1:
			return new JPiece(x, y);
		case 2:
			return new LPiece(x, y);
		case 3:
			return new SPiece(x, y);
		case 4:
			return new TPiece(x, y);
		case 5:
			return new ZPiece(x, y);
		case 6:
			return new LinePiece(x, y);
		}

		throw new IllegalStateException("Piece not found");
	}

}
