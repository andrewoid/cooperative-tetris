package edu.touro.cooptetris.net.server;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import edu.touro.cooptetris.PiecesList;
import edu.touro.cooptetris.pieces.JPiece;
import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.Square;

public class ServerGameControllerTest {
	private ServerGameController serverGameController;

	@Before
	public void setUp() {
		Injector injector = Guice.createInjector(new Module[0]);
		serverGameController = injector.getInstance(ServerGameController.class);
	}

	@Test
	public void testIncreaseSpeed() {

	}

	@Test
	public void testRotate() {
		Piece piece = new JPiece(0, 0, 1, 0);
		PiecesList list = serverGameController.getPiecesList();
		list.add(piece);
		serverGameController.rotate(piece);
		Square[] squares = piece.getSquares();
		assertEquals(Square.SIDE, squares[0].getX());
		assertEquals(Square.SIDE, squares[0].getY());
		assertEquals(0, squares[1].getX());
		assertEquals(Square.SIDE, squares[1].getY());
		assertEquals(-Square.SIDE, squares[2].getX());
		assertEquals(Square.SIDE, squares[2].getY());
		assertEquals(-Square.SIDE, squares[3].getX());
		assertEquals(0, squares[3].getY());
	}

	@Test
	public void testMoveLeft() {

	}

	@Test
	public void testMoveRight() {

	}

	@Test
	public void testMoveDown() {

	}

	@Test
	public void testDrop() {

	}

	@Test
	public void testRemoveRow() {

	}

	@Test
	public void testMovePieces() {

	}

	@Test
	public void testAddNewPiece() {

	}

	@Test
	public void testCalculateXDrops() {

	}

}
