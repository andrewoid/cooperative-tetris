package edu.touro.cooptetris.pieces;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TPieceTest {

	@Test
	public void testTPiece() {
		TPiece tPiece = new TPiece(0, 0, 1);
		Square[] squares = tPiece.getSquares();
		assertEquals(0, squares[0].getX());
		assertEquals(0, squares[0].getY());

		assertEquals(Square.SIDE, squares[1].getX());
		assertEquals(0, squares[1].getY());

		assertEquals(2 * Square.SIDE, squares[2].getX());
		assertEquals(0, squares[2].getY());

		assertEquals(Square.SIDE, squares[3].getX());
		assertEquals(Square.SIDE, squares[3].getY());
	}

}
