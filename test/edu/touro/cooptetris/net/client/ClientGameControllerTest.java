package edu.touro.cooptetris.net.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import edu.touro.cooptetris.pieces.LinePiece;

public class ClientGameControllerTest {
	private ClientGameController cgc;
	private LinePiece piece;

	@Before
	public void initializeTest() {
		Injector injector = Guice.createInjector(new Module[0]);
		cgc = injector.getInstance(ClientGameController.class);
		piece = new LinePiece(25, 25, 0, 0);
	}

	@Test
	public void testRotate() {
		cgc.rotate(piece);
		assertEquals(25, piece.getSquares()[0].getX());
		assertEquals(25, piece.getSquares()[0].getY());
	}

	@Test
	public void testMoveLeft() {
		cgc.moveLeft(piece);
		assertEquals(10, piece.getSquares()[0].getX());
		assertEquals(25, piece.getSquares()[0].getY());

	}

	@Test
	public void testMoveDown() {
		cgc.moveDown(piece);
		assertEquals(25, piece.getSquares()[0].getX());
		assertEquals(40, piece.getSquares()[0].getY());
	}

	@Test
	public void testMoveRight() {
		cgc.moveRight(piece);
		assertEquals(40, piece.getSquares()[0].getX());
		assertEquals(25, piece.getSquares()[0].getY());
	}

	@Test
	public void testDrop() {
		cgc.drop(piece);
		assertEquals(25, piece.getSquares()[0].getX());
		assertEquals(250, piece.getSquares()[0].getY());
	}

	@Test
	public void testAddNewPiece() {
		cgc.addNewPiece(piece);
		assertTrue(cgc.getList().contains(piece));
	}

}
