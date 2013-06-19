package edu.touro.cooptetris.net.server;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import edu.touro.cooptetris.Board;
import edu.touro.cooptetris.DropTimer;
import edu.touro.cooptetris.PiecesList;
import edu.touro.cooptetris.net.Player;
import edu.touro.cooptetris.pieces.LinePiece;
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
		DropTimer timer = serverGameController.getTimer();
		serverGameController.increaseSpeed();
		assertEquals(370, timer.getTimeIncrement());
	}

	@Test
	public void testRotate() {
		Piece piece = new LinePiece(45, 45, 1, 0);
		PiecesList list = serverGameController.getPiecesList();
		list.add(piece);
		serverGameController.rotate(piece);
		Square[] squares = list.get(0).getSquares();
		assertEquals(60, squares[0].getX());
		assertEquals(60, squares[0].getY());
	}

	@Test
	public void testMoveLeft() {
		Piece piece = new LinePiece(45, 45, 1, 0);
		PiecesList list = serverGameController.getPiecesList();
		list.add(piece);
		serverGameController.moveLeft(piece);
		Square[] squares = list.get(0).getSquares();
		assertEquals(30, squares[0].getX());
		assertEquals(45, squares[0].getY());

	}

	@Test
	public void testMoveRight() {
		Piece piece = new LinePiece(45, 45, 1, 0);
		PiecesList list = serverGameController.getPiecesList();
		list.add(piece);
		serverGameController.moveRight(piece);
		Square[] squares = list.get(0).getSquares();
		assertEquals(60, squares[0].getX());
		assertEquals(45, squares[0].getY());

	}

	@Test
	public void testMoveDown() {
		Piece piece = new LinePiece(45, 45, 1, 0);
		PiecesList list = serverGameController.getPiecesList();
		list.add(piece);
		serverGameController.moveDown(piece);
		Square[] squares = list.get(0).getSquares();
		assertEquals(45, squares[0].getX());
		assertEquals(60, squares[0].getY());
	}

	@Test
	public void testDrop() {
		Board board = new Board();
		Piece piece = new LinePiece(45, 45, 1, 0);
		PiecesList list = serverGameController.getPiecesList();
		list.add(piece);
		serverGameController.drop(piece);
		Square[] squares = list.get(0).getSquares();
		assertEquals(45, squares[0].getX());
		assertEquals(Square.SIDE * board.getNumRows() - Square.SIDE * 4,
				squares[0].getY());
	}

	@Test
	public void testAddNewPiece() {
		PiecesList list = serverGameController.getPiecesList();
		serverGameController.addNewPiece(0, 1);
		serverGameController.addNewPiece(2, 2);
		assertEquals(2, list.size());
	}

	@Test
	public void testCalculateXDrops() {
		given4Players();
		serverGameController.calculateXDrops();
		ArrayList<Player> players = serverGameController.getPlayerList();
		assertEquals(21, players.get(0).getxDrop());
		assertEquals(62, players.get(1).getxDrop());
		assertEquals(103, players.get(2).getxDrop());
		assertEquals(144, players.get(3).getxDrop());
	}

	@Test
	public void testCalculateXDropsForOnePlayer() {
		ArrayList<Player> players1 = serverGameController.getPlayerList();
		players1.add(new Player(0, 0));
		serverGameController.calculateXDrops();
		ArrayList<Player> players = serverGameController.getPlayerList();
		assertEquals(50, players.get(0).getxDrop());

	}

	private void given4Players() {
		ArrayList<Player> players = serverGameController.getPlayerList();
		for (int i = 0; i < 4; i++) {
			players.add(new Player(i, 0));
		}
	}

}
