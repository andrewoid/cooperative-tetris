package edu.touro.cooptetris;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.swing.JComponent;

import edu.touro.cooptetris.pieces.JPiece;
import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.Square;
import edu.touro.cooptetris.sound.CompleteLineMusicPlayer;
import edu.touro.cooptetris.sound.HitFloorMusicPlayer;
import edu.touro.cooptetris.sound.LevelChangeMusicPlayer;

public class TetrisGameView extends JComponent {

	private Board board;

	private static final long serialVersionUID = 1L;
	private Piece p;
	private ArrayList<Piece> pieces;
	private int x;
	private int y;
	private DropTimer timer;
	private ArrayList<Level> levels;
	private int score;
	private int currLevel;
	private int boardMarginSide;
	private int boardMarginBottom;
	private int topY;
	private int bottomY;
	private int rightX;
	private int leftX;
	private int boardWidth;
	private int totalHeight;
	private int totalWidth;
	// private ThemeMusicPlayer themeMusicPlayer;
	private CompleteLineMusicPlayer completeLinePlayer;
	private LevelChangeMusicPlayer levelChangePlayer;
	private HitFloorMusicPlayer hitFloorPlayer;

	public TetrisGameView() {
		this.totalHeight = 500;
		this.totalWidth = 600;
		levels = new ArrayList<Level>();
		for (int i = 0; i < 10; i++) {
			levels.add(new Level(i, 1000 - (i * 100)));
		}
		currLevel = 1;
		timer = new DropTimer(300);
		setSize(800, 600);
		pieces = new ArrayList<Piece>();

		setBoardDimensions();
		x = boardMarginSide + (boardWidth / 2);
		y = 0;
		p = new JPiece(x, y);

		pieces.add(p);

		KeyboardListener keyListener = new KeyboardListener();
		addKeyListener(keyListener);
		keyListener.setPiece(p);
		setFocusable(true);

	}

	@Inject
	public TetrisGameView(Board board,
			CompleteLineMusicPlayer completeLinePlayer,
			LevelChangeMusicPlayer levelChangePlayer,
			HitFloorMusicPlayer hitFloorPlayer) {
		this();
		this.board = board;
		this.completeLinePlayer = completeLinePlayer;
		this.levelChangePlayer = levelChangePlayer;
		this.hitFloorPlayer = hitFloorPlayer;
	}

	public void drawBoard(Graphics g) {
		g.drawLine(leftX, topY, leftX, bottomY);
		g.drawLine(rightX, topY, rightX, bottomY);
		g.drawLine(leftX, bottomY, rightX, bottomY);
	}

	public int getCurrLevel() {
		return currLevel;
	}

	public HitFloorMusicPlayer getHitFloorPlayer() {
		return hitFloorPlayer;
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
		completeLinePlayer.play();
		if (score > currLevel * 4000) {
			currLevel++;
			levelChangePlayer.play();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		drawBoard(g);

		for (Piece p : pieces) {
			p.drawPiece(g);
		}

		if (timer.isTimeToDrop()) {
			boolean landed = false;
			for (Piece p : pieces) {

				if (board.willCollideWithFloorVertical(p)
						|| board.willCollideWithLandedPieceVertical(p)) {
					board.landPiece(p);
					landed = true;
				} else {
					p.moveDown();
				}

			}
			if (landed) {
				pieces.clear();
				pieces.add(new JPiece(boardMarginSide + (boardWidth / 2), 0));
			}

		}

		repaint();
	}

	private void setBoardDimensions() {
		boardWidth = Board.getNumColumns() * Square.SIDE;
		boardMarginSide = (totalWidth - boardWidth) / 2;
		boardMarginBottom = totalHeight / 4;
		bottomY = (totalHeight - boardMarginBottom);
		rightX = (totalWidth - boardMarginSide);
		leftX = boardMarginSide;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
