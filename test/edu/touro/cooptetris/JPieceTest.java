package edu.touro.cooptetris;

import static org.junit.Assert.*;

import org.junit.Test;

public class JPieceTest {

	@Test
	public void testInitialPosition() {
		JPiece j = new JPiece();
		Square[] squares = j.getSquares();

		assertEquals(0, squares[0].getX());
		assertEquals(0, squares[0].getY());
		assertEquals(0, squares[1].getX());
		assertEquals(10, squares[1].getY());
		assertEquals(0, squares[2].getX());
		assertEquals(20, squares[2].getY());
		assertEquals(-10, squares[3].getX());
		assertEquals(20, squares[3].getY());
	}

	@Test
	public void testRotate() {
		JPiece j = new JPiece();
		j.rotate();
		Square[] squares = j.getSquares();

		assertEquals(0, squares[0].getX());
		assertEquals(0, squares[0].getY());
		assertEquals(10, squares[1].getX());
		assertEquals(0, squares[1].getY());
		assertEquals(20, squares[2].getX());
		assertEquals(0, squares[2].getY());
		assertEquals(20, squares[3].getX());
		assertEquals(10, squares[3].getY());

		j.rotate();

		assertEquals(0, squares[0].getX());
		assertEquals(0, squares[0].getY());
		assertEquals(0, squares[1].getX());
		assertEquals(-10, squares[1].getY());
		assertEquals(0, squares[2].getX());
		assertEquals(-20, squares[2].getY());
		assertEquals(10, squares[3].getX());
		assertEquals(-20, squares[3].getY());

		j.rotate();

		assertEquals(0, squares[0].getX());
		assertEquals(0, squares[0].getY());
		assertEquals(-10, squares[1].getX());
		assertEquals(0, squares[1].getY());
		assertEquals(-20, squares[2].getX());
		assertEquals(0, squares[2].getY());
		assertEquals(-20, squares[3].getX());
		assertEquals(-10, squares[3].getY());

		j.rotate();

		assertEquals(0, squares[0].getX());
		assertEquals(0, squares[0].getY());
		assertEquals(0, squares[1].getX());
		assertEquals(10, squares[1].getY());
		assertEquals(0, squares[2].getX());
		assertEquals(20, squares[2].getY());
		assertEquals(-10, squares[3].getX());
		assertEquals(20, squares[3].getY());
	}
}
