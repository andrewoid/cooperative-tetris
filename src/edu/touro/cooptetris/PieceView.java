package edu.touro.cooptetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class PieceView extends JComponent {

	private JPiece j;
	private TPiece t;
	private LinePiece line;
	private ArrayList<Piece> pieces;

	public PieceView() {
		pieces = new ArrayList<Piece>();
		pieces.add(new JPiece());
		pieces.add(new TPiece());
		pieces.add(new LinePiece());
		pieces.add(new BoxPiece());
		pieces.add(new ZPiece());
		pieces.add(new SPiece());
		pieces.add(new LPiece());

		for (int i = 0; i < 20; i++) {
			for (Piece p : pieces) {
				p.moveDown();
				p.moveRight();
			}

		}
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.translate(this.getWidth() / 2, this.getHeight() / 2);
		for (Piece p : pieces) {
			p.rotate();
			p.drawPiece(g);
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		repaint();
	}
}
