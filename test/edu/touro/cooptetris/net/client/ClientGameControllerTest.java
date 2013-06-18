package edu.touro.cooptetris.net.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import edu.touro.cooptetris.DropTimer;
import edu.touro.cooptetris.net.client.ClientGameController;
import edu.touro.cooptetris.pieces.LinePiece;

public class ClientGameControllerTest {
	private ClientGameController cgc;
	private LinePiece piece;

	// private final static Logger log = Logger
	// .getLogger(ClientGameControllerTest.class.getName());

	@Before
	public void initializeTest() {
		Injector injector = Guice.createInjector(new Module[0]);
		cgc = injector.getInstance(ClientGameController.class);
		piece = new LinePiece(25, 25, 0, 0);
	}

	@Test
	public void testIncreaseSpeed() {
		DropTimer timer = cgc.getTimer();
		int currIncrement = timer.getTimeIncrement();
		int newIncrement = currIncrement - 30;
		// log.log(Level.INFO, "increasing speed");
		cgc.increaseSpeed();
		assertEquals(newIncrement, timer.getTimeIncrement());
	}

	@Test
	public void testRotate() {
		cgc.rotate(piece);
		assertEquals(10, piece.getSquares()[0].getX());
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
