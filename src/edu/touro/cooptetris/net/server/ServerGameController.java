package edu.touro.cooptetris.net.server;

import java.util.ArrayList;
import java.util.Iterator;

import javax.inject.Inject;

import edu.touro.cooptetris.Board;
import edu.touro.cooptetris.DropTimer;
import edu.touro.cooptetris.GameStateListener;
import edu.touro.cooptetris.Level;
import edu.touro.cooptetris.PieceFactory;
import edu.touro.cooptetris.PiecesList;
import edu.touro.cooptetris.net.Player;
import edu.touro.cooptetris.net.PlayerIDGenerator;
import edu.touro.cooptetris.net.message.HardDropMessage;
import edu.touro.cooptetris.net.message.MoveLeftMessage;
import edu.touro.cooptetris.net.message.MoveRightMessage;
import edu.touro.cooptetris.net.message.NewPieceMessage;
import edu.touro.cooptetris.net.message.RemoveRowMessage;
import edu.touro.cooptetris.net.message.RotateMessage;
import edu.touro.cooptetris.net.message.SetUpPlayerMessage;
import edu.touro.cooptetris.net.message.SoftDropMessage;
import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.Square;

public class ServerGameController {

	private Board board;
	private PiecesList list;
	private PieceFactory pieceFactory;
	// private GameStateListener gameStateListener;
	private DropTimer timer;
	private ArrayList<Level> levels;
	private int score;
	private int currLevel;
	private int xDrop;
	private WriterThread writer;
	private ArrayList<Player> playerList;
	private PlayerIDGenerator playerIDGenerator;

	@Inject
	public ServerGameController(Board board, PiecesList list,
			PieceFactory pieceFactory, WriterThread writer,
			PlayerIDGenerator playerIDGenerator) {
		this.board = board;
		this.list = list;
		this.pieceFactory = pieceFactory;
		// didn't set xDrop
		// must initially drop a piece for each player
		playerList = new ArrayList<Player>();

		levels = new ArrayList<Level>();
		for (int i = 0; i < 10; i++) {
			levels.add(new Level(i, 1000 - (i * 100)));
		}
		currLevel = 1;
		timer = new DropTimer(400);
		this.writer = writer;
		this.playerIDGenerator = playerIDGenerator;

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
			writer.addMessage(new RotateMessage(piece.getPieceID()));
		}

	}

	public void moveLeft(Piece piece) {
		if (!board.willCollideWithFloorLeft(piece)) {
			piece.moveLeft();
			writer.addMessage(new MoveLeftMessage(piece.getPieceID()));
		}
	}

	public void moveDown(Piece piece) {
		if (!board.willCollideWithFloorVertical(piece)
				&& !board.willCollideWithLandedPieceVertical(piece)) {
			piece.moveDown();
			writer.addMessage(new SoftDropMessage(piece.getPieceID()));
		}

	}

	public void moveRight(Piece piece) {
		if (!board.willCollideWithFloorRight(piece)) {
			piece.moveRight();
			writer.addMessage(new MoveRightMessage(piece.getPieceID()));
		}
	}

	public void drop(Piece piece) {
		while (!board.willCollideWithFloorVertical(piece)
				&& !board.willCollideWithLandedPieceVertical(piece)) {
			piece.moveDown();
		}
		writer.addMessage(new HardDropMessage(piece.getPieceID()));
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
			// gameStateListener.onCompleteLine(numRows);
		}
		writer.addMessage(new RemoveRowMessage(p.getPieceID()));
	}

	public void movePieces() {
		ArrayList<Player> playersNeedNewPieces = new ArrayList<Player>();
		Iterator<Piece> iter = list.iterator();
		Piece p;

		if (timer.isTimeToDrop()) {
			boolean landed = false;
			while (iter.hasNext()) {
				p = iter.next();

				if (!board.willCollideWithFloorVertical(p)
						&& !board.willCollideWithLandedPieceVertical(p)) {
					p.moveDown();
					writer.addMessage(new SoftDropMessage(p.getPieceID()));
				} else {
					board.landPiece(p);
					// gameStateListener.onHitFloor();
					landed = true;
					removeRow(p);
				}

				if (landed) {
					if (!board.isFull() && score < 9999) {
						for (Player player : playerList) {
							if (player.getPlayerID() == p.getPlayerID()) {
								playersNeedNewPieces.add(player);
								break;
							}
						}
						iter.remove();
					} else {
						endGame();
					}
				}
			}
			for (Player needyPlayer : playersNeedNewPieces) {
				addNewPiece(needyPlayer.getxDrop(), needyPlayer.getPlayerID());
			}
		}
	}

	public void addNewPiece(int xDrop, int playerID) {
		Piece p = pieceFactory.getNextPiece(xDrop, 0, playerID);
		list.add(p);
		// gameStateListener.onNewPiece(p);
		writer.addMessage(new NewPieceMessage(p));
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
		// this.gameStateListener = gameStateListener;
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

	public void addPlayer() {
		Player p = new Player(playerIDGenerator.getNextPlayerID(), 0);
		playerList.add(p);
		writer.addMessage(new SetUpPlayerMessage(board, p.getPlayerID()));
		System.out.println("Writing out new player: player id:"
				+ p.getPlayerID());
		board.increaseBoardSize();
		calculateXDrops();
		addNewPiece(p.getxDrop(), p.getPlayerID());
	}

	public void endGame() {
		// gameStateListener.onGameOver();

		// send endGameMessage
	}

	public void calculateXDrops() {
		int numPlayers = playerList.size();
		int dropInterval = (board.getNumColumns() * Square.SIDE) / numPlayers;
		Player p;
		for (int i = 0; i < playerList.size(); i++) {
			p = playerList.get(i);
			p.setxDrop((i + 1) * (dropInterval) - (dropInterval / 2));
		}
	}

}
