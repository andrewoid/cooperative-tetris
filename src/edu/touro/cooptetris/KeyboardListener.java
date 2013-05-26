package edu.touro.cooptetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.touro.cooptetris.pieces.Piece;

public class KeyboardListener implements KeyListener {

	private Piece piece;
	private PiecesAndBoardView view;
	private boolean paused;

	public KeyboardListener(PiecesAndBoardView view) {
		this.view = view;
		paused = false;
	}

	@Override
	public void keyPressed(KeyEvent event) {

		if (event.getKeyCode() == KeyEvent.VK_P) {
			paused = !paused;
			view.pauseAndUnPauseGame();
		} else if (piece != null && !paused) {
			switch (event.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_KP_LEFT:
			case KeyEvent.VK_A:
				piece.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_KP_RIGHT:
			case KeyEvent.VK_D:
				piece.moveRight();
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
			case KeyEvent.VK_W:
				piece.rotate();
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
			case KeyEvent.VK_S:
				piece.moveDown();
				break;
			default:
				break;

			}

		} // else {
			// System.out.println("piece is null");
		// }

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

}
