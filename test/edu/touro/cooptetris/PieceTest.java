package edu.touro.cooptetris;

import org.junit.Assert;
import org.junit.Test;

public class PieceTest {

	@Test
	public void testMoveRight() {
		LinePiece aLine = new LinePiece(0, 0);
		Square[] squares = aLine.getSquares();
		aLine.moveRight();
		Assert.assertEquals(Square.SIDE, squares[0].getX());
		Assert.assertEquals(0, squares[0].getY());
		Assert.assertEquals(Square.SIDE, squares[1].getX());
		Assert.assertEquals(Square.SIDE, squares[1].getY());
		Assert.assertEquals(Square.SIDE, squares[2].getX());
		Assert.assertEquals(2 * Square.SIDE, squares[2].getY());
		Assert.assertEquals(Square.SIDE, squares[3].getX());
		Assert.assertEquals(3 * Square.SIDE, squares[3].getY());
	}

	@Test
	public void testMoveLeft() {
		SPiece anSPiece = new SPiece(0, 0);
		Square[] squares = anSPiece.getSquares();
		anSPiece.moveLeft();
		Assert.assertEquals(-Square.SIDE, squares[0].getX());
		Assert.assertEquals(0, squares[0].getY());
		Assert.assertEquals(-Square.SIDE, squares[1].getX());
		Assert.assertEquals(Square.SIDE, squares[1].getY());
		Assert.assertEquals(-2 * Square.SIDE, squares[2].getX());
		Assert.assertEquals(Square.SIDE, squares[2].getY());
		Assert.assertEquals(0, squares[3].getX());
		Assert.assertEquals(0, squares[3].getY());
	}

	@Test
	public void testMoveDown() {
		BoxPiece aBox = new BoxPiece(0, 0);
		Square[] squares = aBox.getSquares();
		aBox.moveDown();
		Assert.assertEquals(0, squares[0].getX());
		Assert.assertEquals(-Square.SIDE, squares[0].getY());
		Assert.assertEquals(Square.SIDE, squares[1].getX());
		Assert.assertEquals(-Square.SIDE, squares[1].getY());
		Assert.assertEquals(0, squares[2].getX());
		Assert.assertEquals(0, squares[2].getY());
		Assert.assertEquals(Square.SIDE, squares[3].getX());
		Assert.assertEquals(0, squares[3].getY());
	}

	@Test
	public void testCollidesWith() {
		BoxPiece aBox = new BoxPiece(0, 0);
		LinePiece aLine = new LinePiece(0, 0);
		Assert.assertTrue(aBox.collidesWith(aLine));
	}

	@Test
	public void testDoesNotCollideWith() {
		BoxPiece aBox = new BoxPiece(0, 0);
		LinePiece aLine = new LinePiece(0, 0);
		aLine.moveRight();
		aLine.moveRight();
		Assert.assertTrue(!aBox.collidesWith(aLine));
	}

}
