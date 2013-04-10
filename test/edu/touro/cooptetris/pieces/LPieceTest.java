package edu.touro.cooptetris.pieces;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LPieceTest {

	@Test
	public void testInitialPosition() {
		LPiece L = new LPiece(0, 0);
		Square[] squares = L.getSquares();

		assertEquals(0, squares[0].getX());
		assertEquals(0, squares[0].getY());

		assertEquals(0, squares[1].getX());
		assertEquals(15, squares[1].getY());

		assertEquals(0, squares[2].getX());
		assertEquals(2 * Square.SIDE, squares[2].getY());

		assertEquals(15, squares[3].getX());
		assertEquals(2 * Square.SIDE, squares[3].getY());
	}

}