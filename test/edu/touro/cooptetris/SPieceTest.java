package edu.touro.cooptetris;

import static org.junit.Assert.*;

import org.junit.Test;

public class SPieceTest {

	@Test
	public void testSPiece() {
		SPiece piece = new SPiece(0,0);
		Square[] squares = piece.getSquares();
		assertEquals(squares[0].getX(), 0);
		assertEquals(squares[0].getY(), 0);
		assertEquals(squares[1].getX(), 0);
		assertEquals(squares[1].getY(), 10);
		assertEquals(squares[2].getX(), -10);
		assertEquals(squares[2].getY(), 10);
		assertEquals(squares[3].getX(), 10);
		assertEquals(squares[3].getY(), 0);

	}

}
