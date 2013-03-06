package edu.touro.cooptetris;

import java.awt.Graphics;

import javax.swing.JComponent;

public class PieceView extends JComponent {

	private JPiece j;
	private int time;

	public PieceView() {
		j = new JPiece();
		time = 0;
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		j.rotate();
		j.moveDown();
		j.moveRight();
		Square[] squares = j.getSquares();
		for (Square s : squares) {
			g.drawRect(s.getX(), s.getY(), s.getSide(), s.getSide());
		}

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		repaint();
	}

}
