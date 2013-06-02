package edu.touro.cooptetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.touro.cooptetris.pieces.Piece;

public class KeyboardListener implements KeyListener {

	private Piece piece;
	private boolean paused;
	private Board board;
	private GameStateListener gameStateListener;

	public KeyboardListener(Board board) {
		this.board = board;
		paused = false;
	}

	@Override
	public void keyPressed(KeyEvent event) {

		int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.VK_P) {
			paused = !paused;
			gameStateListener.onPause();
		} else if (keyCode == KeyEvent.VK_T) {
			gameStateListener.onToggleThemeMusic();
		} else if (piece != null && !paused) {
			switch (keyCode) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_KP_LEFT:
			case KeyEvent.VK_A:
				if (!board.willCollideWithFloorLeft(piece)) {
					piece.moveLeft();
				}
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_KP_RIGHT:
			case KeyEvent.VK_D:
				if (!board.willCollideWithFloorRight(piece)) {
					piece.moveRight();
				}
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
			case KeyEvent.VK_W:
				piece.rotate();
				if (!board.onBoard(piece) || board.collidedWithPiece(piece)) {
					piece.unrotate();
				} else {
					gameStateListener.onRotate();
				}
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
			case KeyEvent.VK_S:
				if (!board.willCollideWithFloorVertical(piece)
						&& !board.willCollideWithLandedPieceVertical(piece)) {
					piece.moveDown();
				}
				break;
			case KeyEvent.VK_SPACE:
				while (!board.willCollideWithFloorVertical(piece)
						&& !board.willCollideWithLandedPieceVertical(piece)) {
					piece.moveDown();
				}
			default:
				break;

			}

		}

	}

	@Override
	public void keyReleased(KeyEvent event) {

	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public void setGameStateListener(GameStateListener gameStateListener) {
		this.gameStateListener = gameStateListener;
	}

}
