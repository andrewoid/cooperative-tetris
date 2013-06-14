package edu.touro.cooptetris.net.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import edu.touro.cooptetris.pieces.LinePiece;

public class ClientGameControllerTest {
	private ClientGameController cgc;
	private LinePiece piece;

	public void initializeTest() {
		Injector injector = Guice.createInjector(new Module[0]);
		cgc = injector.getInstance(ClientGameController.class);
		piece = new LinePiece(25, 25, 0, 0);
	}

	@Test
	public void testIncreaseSpeed() {
		fail("Not yet implemented");
	}

	@Test
	public void testRotate() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveLeft() {
		initializeTest();
		cgc.moveLeft(piece);
		assertEquals(10, piece.getSquares()[0].getX());
		assertEquals(25, piece.getSquares()[0].getY());
	}

	@Test
	public void testMoveDown() {
		initializeTest();
		cgc.moveDown(piece);
		assertEquals(25, piece.getSquares()[0].getX());
		assertEquals(40, piece.getSquares()[0].getY());
	}

	@Test
	public void testMoveRight() {
		initializeTest();
		cgc.moveRight(piece);
		assertEquals(40, piece.getSquares()[0].getX());
		assertEquals(25, piece.getSquares()[0].getY());
	}

	@Test
	public void testDrop() {
		fail("Not yet implemented");
	}

	@Test
	public void testLineCompleted() {
		fail("Not yet implemented");
	}

	@Test
	public void testPauseAndUnPause() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveRow() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNewPiece() {
		fail("Not yet implemented");
	}

	@Test
	public void testEndGame() {
		fail("Not yet implemented");
	}

}
