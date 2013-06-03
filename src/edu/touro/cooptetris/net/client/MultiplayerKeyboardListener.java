package edu.touro.cooptetris.net.client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import edu.touro.cooptetris.Board;
import edu.touro.cooptetris.GameStateListener;
import edu.touro.cooptetris.net.message.DropMessage;
import edu.touro.cooptetris.net.message.RotateMessage;
import edu.touro.cooptetris.pieces.Piece;

public class MultiplayerKeyboardListener implements KeyListener {

	private Piece piece;
	private boolean paused;
	private Board board;
	private GameStateListener gameStateListener;
	private TetrisClient tetrisClient;

	public MultiplayerKeyboardListener(Board board, TetrisClient client) {
		this.board = board;
		paused = false;
		tetrisClient = client;
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
				// tetrisClient.send();
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_KP_RIGHT:
			case KeyEvent.VK_D:
				// tetrisClient.send();
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
			case KeyEvent.VK_W:
				try {
					tetrisClient.send(new RotateMessage(piece.getPieceID()));
				} catch (IOException io) {
					io.printStackTrace();
				}
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
			case KeyEvent.VK_S:
				try {
					tetrisClient.send(new DropMessage(piece.getPieceID()));
				} catch (IOException io) {
					io.printStackTrace();
				}
				break;
			case KeyEvent.VK_SPACE:
				// tetrisClient.send();
				break;
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
