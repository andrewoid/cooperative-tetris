package edu.touro.cooptetris;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LinePieceTest {

	@Test
	public void testInitialPosition() {

		LinePiece aLine = new LinePiece(0, 0);
		Square[] squares = aLine.getSquares();

		assertEquals(0, squares[0].getX());
		assertEquals(0, squares[0].getY());
		assertEquals(0, squares[1].getX());
		assertEquals(Square.SIDE, squares[1].getY());
		assertEquals(0, squares[2].getX());
		assertEquals(2 * Square.SIDE, squares[2].getY());
		assertEquals(0, squares[3].getX());
		assertEquals(3 * Square.SIDE, squares[3].getY());

	}

}
