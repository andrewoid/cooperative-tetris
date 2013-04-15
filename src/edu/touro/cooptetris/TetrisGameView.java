package edu.touro.cooptetris;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.swing.JComponent;

import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.Square;
import edu.touro.cooptetris.sound.CompleteLineMusicPlayer;
import edu.touro.cooptetris.sound.HitFloorMusicPlayer;
import edu.touro.cooptetris.sound.LevelChangeMusicPlayer;

public class TetrisGameView extends JComponent {

	private Board board;
	private BoardView boardView;
	private static final long serialVersionUID = 1L;
	private Piece p;
	private ArrayList<Piece> pieces;
	private DropTimer timer;
	private ArrayList<Level> levels;
	private int score;
	private int currLevel;
	// private ThemeMusicPlayer themeMusicPlayer;
	private CompleteLineMusicPlayer completeLinePlayer;
	private LevelChangeMusicPlayer levelChangePlayer;
	private HitFloorMusicPlayer hitFloorPlayer;
	private PieceFactory pieceFactory;
	private KeyboardListener keyListener;

	public TetrisGameView() {
		levels = new ArrayList<Level>();
		for (int i = 0; i < 10; i++) {
			levels.add(new Level(i, 1000 - (i * 100)));
		}
		currLevel = 1;
		timer = new DropTimer(300);
		setSize(800, 600);
		pieces = new ArrayList<Piece>();

		keyListener = new KeyboardListener();
		addKeyListener(keyListener);
		keyListener.setPiece(p);
		setFocusable(true);

	}

	@Inject
	public TetrisGameView(Board board, BoardView boardView,
			CompleteLineMusicPlayer completeLinePlayer,
			LevelChangeMusicPlayer levelChangePlayer,
			HitFloorMusicPlayer hitFloorPlayer, PieceFactory pieceFactory) {
		this();
		this.board = board;
		this.boardView = boardView;
		this.completeLinePlayer = completeLinePlayer;
		this.levelChangePlayer = levelChangePlayer;
		this.hitFloorPlayer = hitFloorPlayer;
		this.pieceFactory = pieceFactory;

		pieces.add(pieceFactory.getRandomPiece(Board.NUM_COLUMNS * Square.SIDE
				/ 2, 0));
		keyListener.setPiece(pieces.get(pieces.size() - 1));

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
		boardView.drawBoard(g);

		for (Piece p : pieces) {
			p.drawPiece(g);
		}

		if (timer.isTimeToDrop()) {
			boolean landed = false;
			for (Piece p : pieces) {
				p.moveDown();

				if (board.willCollideWithFloorVertical(p)) {
					board.landPiece(p);
					landed = true;
				} else if (board.willCollideWithLandedPieceVertical(p)) {
					p.moveUp();
					board.landPiece(p);
					landed = true;
				}

			}
			if (landed) {
				pieces.clear();
				pieces.add(pieceFactory.getRandomPiece(Board.NUM_COLUMNS
						* Square.SIDE / 2, 0));
				keyListener.setPiece(pieces.get(pieces.size() - 1));
			}

		}
		repaint();
	}

	public void setScore(int score) {
		this.score = score;
	}
}
