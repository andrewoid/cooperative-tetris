package edu.touro.cooptetris.net.client;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import edu.touro.cooptetris.Board;
import edu.touro.cooptetris.DropTimer;
import edu.touro.cooptetris.GameLevel;
import edu.touro.cooptetris.GameStateListener;
import edu.touro.cooptetris.PieceFactory;
import edu.touro.cooptetris.PiecesAndBoardView;
import edu.touro.cooptetris.PiecesList;
import edu.touro.cooptetris.pieces.Piece;

public class ClientGameController {

	private Board board;
	private PiecesList list;
	private GameStateListener gameStateListener;
	private DropTimer timer;

	public DropTimer getTimer() {
		return timer;
	}

	public void setTimer(DropTimer timer) {
		this.timer = timer;
	}

	private ArrayList<GameLevel> levels;
	private int score;
	private int currLevel;
	// private Piece nextPiece;
	private int playerID;
	private HashMap<Integer, Piece> activePieces;
	private PiecesAndBoardView view;

	@Inject
	public ClientGameController(Board board, PiecesList list,
			PieceFactory pieceFactory, PiecesAndBoardView view) {
		this.board = board;
		this.list = list;
		this.view = view;
		// look below!!! For errors!!
		// setNextPiece(nextPiece);
		// look above!!
		levels = new ArrayList<GameLevel>();
		for (int i = 0; i < 10; i++) {
			levels.add(new GameLevel(i, 1000 - (i * 100)));
		}
		currLevel = 1;
		timer = new DropTimer(400);
		this.activePieces = new HashMap<Integer, Piece>();
	}

	public void increaseSpeed() {
		int currIncrement = timer.getTimeIncrement();
		timer.setTimeIncrement(currIncrement - 30);
	}

	public void rotate(Piece piece) {
		piece.rotate();
		if (!board.onBoard(piece) || board.collidedWithPiece(piece)) {
			piece.unrotate();
		} else {
			gameStateListener.onRotate();
		}
	}

	public void moveLeft(Piece piece) {
		if (!board.willCollideWithFloorLeft(piece)) {
			piece.moveLeft();
		}
	}

	public void moveDown(Piece piece) {
		if (!board.willCollideWithFloorVertical(piece)
				&& !board.willCollideWithLandedPieceVertical(piece)) {
			piece.moveDown();
		}

	}

	public void moveRight(Piece piece) {
		if (!board.willCollideWithFloorRight(piece)) {
			piece.moveRight();
		}
	}

	public void drop(Piece piece) {
		while (!board.willCollideWithFloorVertical(piece)
				&& !board.willCollideWithLandedPieceVertical(piece)) {
			piece.moveDown();
		}
	}

	public void lineCompleted(int numLines) {
		switch (numLines) {
		case 1:
			setScore(score + 10);
			break;
		case 2:
			setScore(score + 25);
			break;
		case 3:
			setScore(score + 50);
			break;
		case 4:
			setScore(score + 100);
			break;
		}

	}

	public void pauseAndUnPause() {
		timer.pauseAndUnPause();
	}

	public void removeRow(Piece p) {
		int numRows = board.checkFullRowsOfPiece(p);
		if (numRows > 0) {
			gameStateListener.onCompleteLine(numRows);
		}
	}

	/*
	 * public void movePieces() {
	 * 
	 * if (timer.isTimeToDrop()) { boolean landed = false;
	 * 
	 * for (Piece p : list) {
	 * 
	 * if (!board.willCollideWithFloorVertical(p) &&
	 * !board.willCollideWithLandedPieceVertical(p)) { p.moveDown(); } else {
	 * board.landPiece(p); gameStateListener.onHitFloor(); int numRows =
	 * board.checkFullRowsOfPiece(p); landed = true; if (numRows > 0) {
	 * gameStateListener.onCompleteLine(numRows); } } } if (landed) {
	 * list.clear(); if (!board.isFull() && score < 9999) { //addNewPiece(); }
	 * else { endGame(); } }
	 * 
	 * } }
	 */

	public void addNewPiece(Piece nextPiece) {
		int playerID = nextPiece.getPlayerID();
		if (activePieces.containsKey(playerID)) {
			activePieces.remove(playerID);
		}

		activePieces.put(playerID, nextPiece);
		list.add(nextPiece);

		Piece tempPiece = nextPiece;
		// setNextPiece(nextPiece);
		gameStateListener.onNewPiece(tempPiece);

	}

	public int getCurrLevel() {
		return currLevel;
	}

	public void setCurrLevel(int currLevel) {
		this.currLevel = currLevel;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;

	}

	public void setGameStateListener(GameStateListener gameStateListener) {
		this.gameStateListener = gameStateListener;
	}

	public Board getBoard() {
		return board;
	}

	public Piece getPieceByID(int pieceID) {
		for (Piece p : list) {
			if (p.getPieceID() == pieceID) {
				return p;
			}
		}
		return null;
	}

	public void endGame() {
		gameStateListener.onGameOver();
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public void setBoard(Board b) {
		this.board.copyBoard(b);
	}

	public void repaint() {
		view.repaint();
	}

}
