package edu.touro.cooptetris;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JPieceTest {

	@Test
	public void testInitialPosition() {
		JPiece j = new JPiece(0, 0);
		Square[] squares = j.getSquares();

		assertEquals(0, squares[0].getX());
		assertEquals(0, squares[0].getY());
		assertEquals(0, squares[1].getX());
		assertEquals(Square.SIDE, squares[1].getY());
		assertEquals(0, squares[2].getX());
		assertEquals(2 * Square.SIDE, squares[2].getY());
		assertEquals(-Square.SIDE, squares[3].getX());

		assertEquals(2 * Square.SIDE, squares[3].getY());
	}

	@Test
	public void testRotate() {
		JPiece j = new JPiece(0, 0);
		j.rotate();
		Square[] squares = j.getSquares();

		// (Rx + Ry - Py, -Rx + Ry + Px)

		assertEquals(Square.SIDE, squares[0].getX());
		assertEquals(Square.SIDE, squares[0].getY());
		assertEquals(0, squares[1].getX());
		assertEquals(Square.SIDE, squares[1].getY());
		assertEquals(-Square.SIDE, squares[2].getX());
		assertEquals(Square.SIDE, squares[2].getY());
		assertEquals(-Square.SIDE, squares[3].getX());
		assertEquals(0, squares[3].getY());

	}
}
