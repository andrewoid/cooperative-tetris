package edu.touro.cooptetris;

import java.util.Random;

import edu.touro.cooptetris.pieces.BoxPiece;
import edu.touro.cooptetris.pieces.JPiece;
import edu.touro.cooptetris.pieces.LPiece;
import edu.touro.cooptetris.pieces.LinePiece;
import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.SPiece;
import edu.touro.cooptetris.pieces.Square;
import edu.touro.cooptetris.pieces.TPiece;
import edu.touro.cooptetris.pieces.ZPiece;

public class PieceFactory {

	private Random random;
	private Piece nextPiece;

	public PieceFactory() {
		random = new Random();
		nextPiece=getRandomPiece(0,0);
	}
	public Piece getNextPiece(int x, int y){
		Piece tempPiece=nextPiece;
		tempPiece.setLocation(x, y);
		
		nextPiece=getRandomPiece(0,0);
		return tempPiece;
	}
	
	public Piece peekPiece(){
		return nextPiece;
	}

	private Piece getRandomPiece(int x, int y) {
		int r=random.nextInt(7);
		switch (r) {
		case 0:
			return new BoxPiece(x, y-2*Square.SIDE);
		case 1:
			return new JPiece(x, y-3*Square.SIDE);
		case 2:
			return new LPiece(x, y-3*Square.SIDE);
		case 3:
			return new SPiece(x, y-2*Square.SIDE);
		case 4:
			return new TPiece(x, y-2*Square.SIDE);
		case 5:
			return new ZPiece(x, y-2*Square.SIDE);
		case 6:
			return new LinePiece(x, y-4*Square.SIDE);
		}
		throw new IllegalStateException("Piece not found");
	}

}
