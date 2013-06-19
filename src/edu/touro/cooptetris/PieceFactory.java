package edu.touro.cooptetris;

import java.util.Random;

import com.google.inject.Inject;

import edu.touro.cooptetris.pieces.BoxPiece;
import edu.touro.cooptetris.pieces.JPiece;
import edu.touro.cooptetris.pieces.LPiece;
import edu.touro.cooptetris.pieces.LinePiece;
import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.PieceIDGenerator;
import edu.touro.cooptetris.pieces.SPiece;
import edu.touro.cooptetris.pieces.Square;
import edu.touro.cooptetris.pieces.TPiece;
import edu.touro.cooptetris.pieces.ZPiece;

public class PieceFactory {

	private Random random;
	private Piece nextPiece;
	private PieceIDGenerator pieceIDGenerator;

	@Inject
	public PieceFactory(PieceIDGenerator pieceIDGenerator) {
		random = new Random();
		this.pieceIDGenerator = pieceIDGenerator;
		nextPiece = getRandomPiece(0, 0, 0);// what should player id be?
	}

	public Piece getNextPiece(int x, int y, int playerID) {
		Piece tempPiece = nextPiece;
		tempPiece.setLocation(x, y);
		tempPiece.setPlayerID(playerID);

		nextPiece = getRandomPiece(0, 0, playerID);
		return tempPiece;
	}

	public Piece peekPiece() {
		return nextPiece;
	}

	private Piece getRandomPiece(int x, int y, int playerID) {
		int r = random.nextInt(7);
		switch (r) {
		case 0:
			return new BoxPiece(x, y - 2 * Square.SIDE,
					pieceIDGenerator.getNextPieceID(), playerID);
		case 1:
			return new JPiece(x, y - 3 * Square.SIDE,
					pieceIDGenerator.getNextPieceID(), playerID);
		case 2:
			return new LPiece(x, y - 3 * Square.SIDE,
					pieceIDGenerator.getNextPieceID(), playerID);
		case 3:
			return new SPiece(x, y - 2 * Square.SIDE,
					pieceIDGenerator.getNextPieceID(), playerID);
		case 4:
			return new TPiece(x, y - 2 * Square.SIDE,
					pieceIDGenerator.getNextPieceID(), playerID);
		case 5:
			return new ZPiece(x, y - 2 * Square.SIDE,
					pieceIDGenerator.getNextPieceID(), playerID);
		case 6:
			return new LinePiece(x, y - 4 * Square.SIDE,
					pieceIDGenerator.getNextPieceID(), playerID);
		}
		throw new IllegalStateException("Piece not found");
	}

}
