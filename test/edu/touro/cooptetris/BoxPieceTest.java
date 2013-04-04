package edu.touro.cooptetris;

import junit.framework.Assert;

import org.junit.Test;

public class BoxPieceTest {

	@Test
	public void TestInitalPosition() {
		BoxPiece aBoxPiece = new BoxPiece(0,0);
		Square[] squares = aBoxPiece.getSquares();

		Assert.assertEquals(0, squares[0].getX());
		Assert.assertEquals(0, squares[0].getY());
		Assert.assertEquals(10, squares[1].getX());
		Assert.assertEquals(0, squares[1].getY());
		Assert.assertEquals(0, squares[2].getX());
		Assert.assertEquals(10, squares[2].getY());
		Assert.assertEquals(10, squares[3].getX());
		Assert.assertEquals(10, squares[3].getY());
	}

}
