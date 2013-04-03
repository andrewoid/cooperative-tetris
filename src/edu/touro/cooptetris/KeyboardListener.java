package edu.touro.cooptetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

	private Piece piece;

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	@Override
	public void keyPressed(KeyEvent event) {

		if (piece != null) {
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
		} else {
			System.out.println("piece is null");
		}

	}

	@Override
	public void keyReleased(KeyEvent event) {

	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

}
