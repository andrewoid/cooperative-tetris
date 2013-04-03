package edu.touro.cooptetris;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	private ThemeMusicPlayer themeMusicPlayer;
	private CompleteLineMusicPlayer completeLinePlayer;
	private RotateMusicPlayer rotatePlayer;
	private LevelChangeMusicPlayer levelChangePlayer;
	private HitFloorMusicPlayer hitFloorPlayer;

	public PieceView(int totalHeight, int totalWidth) {
		this.totalHeight = totalHeight;
		this.totalWidth = totalWidth;
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
		for (int i = 0; i < 5; i++) {
			p.moveDown();
		}

		try {
			themeMusicPlayer = new ThemeMusicPlayer();
			rotatePlayer = new RotateMusicPlayer();
			levelChangePlayer = new LevelChangeMusicPlayer();
			hitFloorPlayer = new HitFloorMusicPlayer();
			completeLinePlayer = new CompleteLineMusicPlayer();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	private void setBoardDimensions() {
		boardWidth = Board.getNumColumns() * Square.SIDE;
		boardMarginSide = (totalWidth - boardWidth) / 2;
		boardMarginBottom = totalHeight / 4;
		bottomY = (totalHeight - boardMarginBottom);
		rightX = (totalWidth - boardMarginSide);
		leftX = boardMarginSide;
	}

	public int getCurrLevel() {
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
			pieces.get(0).moveDown();
		}

		repaint();
	}

	public void drawBoard(Graphics g) {

		g.drawLine(leftX, topY, leftX, bottomY);
		g.drawLine(rightX, topY, rightX, bottomY);
		g.drawLine(leftX, bottomY, rightX, bottomY);
	}

	public void setScore(int score) {
		this.score = score;
	}
}
