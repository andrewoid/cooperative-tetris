package edu.touro.cooptetris;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinePieceTest {

	@Test
	public void testInitialPosition() {
	
		LinePiece aLine = new LinePiece();
		Square[] squares = aLine.getSquares();
		
		assertEquals(0, squares[0].getX());
		assertEquals(0, squares[0].getY());
		assertEquals(0, squares[1].getX());
		assertEquals(10, squares[1].getY());
		assertEquals(0, squares[2].getX());
		assertEquals(20, squares[2].getY());
		assertEquals(0, squares[3].getX());
		assertEquals(30, squares[3].getY());
		
		
	}

}
