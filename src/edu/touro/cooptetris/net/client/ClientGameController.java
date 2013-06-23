package edu.touro.cooptetris.net.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import edu.touro.cooptetris.Board;
import edu.touro.cooptetris.GameLevel;
import edu.touro.cooptetris.GameStateListener;
import edu.touro.cooptetris.PieceFactory;
import edu.touro.cooptetris.PiecesAndBoardView;
import edu.touro.cooptetris.PiecesList;
import edu.touro.cooptetris.pieces.Piece;

public class ClientGameController {
	private final static Logger logger = Logger
			.getLogger(ClientGameController.class.getName());
	private Board board;
	private PiecesList list;
	private GameStateListener gameStateListener;
	private ArrayList<GameLevel> levels;
	private int playerID;
	private HashMap<Integer, Piece> activePieces;
	private PiecesAndBoardView view;

	@Inject
	public ClientGameController(Board board, PiecesList list,
			PieceFactory pieceFactory, PiecesAndBoardView view) {
		this.board = board;
		this.list = list;
		this.view = view;
		levels = new ArrayList<GameLevel>();
		for (int i = 0; i < 10; i++) {
			levels.add(new GameLevel(i, 1000 - (i * 100)));
		}
		this.activePieces = new HashMap<Integer, Piece>();
	}

	public PiecesList getList() {
		return list;
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

	public void removeRow(Piece p) {
		int numRows = board.checkFullRowsOfPiece(p);
		if (numRows > 0) {
			gameStateListener.onCompleteLine(numRows);
		}
	}

	public void addNewPiece(Piece nextPiece) {
		int playerID = nextPiece.getPlayerID();
		if (activePieces.containsKey(playerID)) {
			activePieces.remove(playerID);
		}
		activePieces.put(playerID, nextPiece);
		list.add(nextPiece);
		gameStateListener.onNewPiece(nextPiece);
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

	public void addPlayer() {
		board.increaseBoardSize();
		gameStateListener.onIncreaseSize();

	}

	public void setPiecesList(PiecesList list) {
		logger.log(Level.INFO, "Set List");
		this.list.clear();
		this.list.addAll(list);
	}

}
