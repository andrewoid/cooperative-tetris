package edu.touro.cooptetris.net.client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import edu.touro.cooptetris.GameStateListener;
import edu.touro.cooptetris.net.message.HardDropMessage;
import edu.touro.cooptetris.net.message.SoftDropMessage;
import edu.touro.cooptetris.net.message.MoveLeftMessage;
import edu.touro.cooptetris.net.message.MoveRightMessage;
import edu.touro.cooptetris.net.message.RotateMessage;
import edu.touro.cooptetris.pieces.Piece;

public class MultiplayerKeyboardListener implements KeyListener {

	private Piece piece;
	private boolean paused;
	private GameStateListener gameStateListener;
	private TetrisClient tetrisClient;

	public MultiplayerKeyboardListener(TetrisClient client) {
		paused = false;
		tetrisClient = client;
	}

	@Override
	public void keyPressed(KeyEvent event) {

		int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.VK_T) {
			gameStateListener.onToggleThemeMusic();
		} else if (piece != null && !paused) {
			switch (keyCode) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_KP_LEFT:
			case KeyEvent.VK_A:
				try {
					tetrisClient.send(new MoveLeftMessage(piece.getPieceID()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_KP_RIGHT:
			case KeyEvent.VK_D:
				try {
					tetrisClient.send(new MoveRightMessage(piece.getPieceID()));
				} catch (IOException e) {
					e.printStackTrace();
				}
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
					tetrisClient.send(new SoftDropMessage(piece.getPieceID()));
				} catch (IOException io) {
					io.printStackTrace();
				}
				break;
			case KeyEvent.VK_SPACE:
				try {
					tetrisClient.send(new HardDropMessage(piece.getPieceID()));
				} catch (IOException e) {
					e.printStackTrace();
				}
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
