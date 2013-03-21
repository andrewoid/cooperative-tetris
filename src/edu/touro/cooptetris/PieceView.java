package edu.touro.cooptetris;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class PieceView extends JComponent {

	private static final long serialVersionUID = 1L;
	private final Piece p;
	private final ArrayList<Piece> pieces;
	private final int x;
	private final int y;
	private final DropTimer timer;
	private final ArrayList<Level> levels;
	private int score;
	private final Level currLevel;

	public PieceView() {
		levels = new ArrayList<Level>();
		for (int i = 0; i < 10; i++) {
			levels.add(new Level(i, 1000 - (i * 100)));
		}
		currLevel = levels.get(0);
		timer = new DropTimer(300);
		setSize(800, 600);
		pieces = new ArrayList<Piece>();
		x = getWidth() / 2;
		y = 0;

		p = new LPiece(x, y);
		pieces.add(p);
		for (int i = 0; i < 5; i++) {
			p.moveDown();
		}

		// pieces.add(new TPiece());
		// pieces.add(new LinePiece());
		// pieces.add(new BoxPiece());
		// pieces.add(new ZPiece());
		// pieces.add(new SPiece());
		// pieces.add(new LPiece());
		KeyboardListener keyListener = new KeyboardListener();
		addKeyListener(keyListener);
		keyListener.setPiece(p);
		setFocusable(true);

	}

	public Level getCurrLevel() {
		return currLevel;
	}

	public int getScore() {
		return score;
	}

	public void lineCompleted(int numLines) {
		switch (numLines) {
		case 1:
			setScore(getScore() + 100);
			break;
		case 2:
			setScore(getScore() + 250);
			break;
		case 3:
			setScore(getScore() + 500);
			break;
		case 4:
			setScore(getScore() + 1000);
			break;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		for (Piece p : pieces) {
			p.drawPiece(g);
		}

		if (timer.isTimeToDrop()) {
			pieces.get(0).moveDown();
		}

		repaint();
	}

	public void setScore(int score) {
		this.score = score;
	}
}
