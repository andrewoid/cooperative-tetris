package edu.touro.cooptetris;

import java.awt.BorderLayout;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.Square;
import edu.touro.cooptetris.sound.CompleteLineMusicPlayer;
import edu.touro.cooptetris.sound.HitFloorMusicPlayer;
import edu.touro.cooptetris.sound.LevelChangeMusicPlayer;

public class TetrisMain extends JFrame implements GameStateListener {

	private static final long serialVersionUID = 1L;
	private CompleteLineMusicPlayer completeLinePlayer;
	private LevelChangeMusicPlayer levelChangePlayer;
	private HitFloorMusicPlayer hitFloorPlayer;
	private GameController gameController;
	private KeyboardListener keyboardListener;

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new Module[0]);
		injector.getInstance(TetrisMain.class);
	}

	@Inject
	public TetrisMain(PiecesAndBoardView gameView,
			ScoreLevelDisplay scoreLevelDisplay,
			CompleteLineMusicPlayer completeLinePlayer,
			LevelChangeMusicPlayer levelChangePlayer,
			HitFloorMusicPlayer hitFloorPlayer, GameController gameController) {
		this.completeLinePlayer = completeLinePlayer;
		this.levelChangePlayer = levelChangePlayer;
		this.hitFloorPlayer = hitFloorPlayer;
		this.gameController = gameController;
		this.keyboardListener = new KeyboardListener(gameController.getBoard());

		addKeyListener(keyboardListener);

		gameController.setGameStateListener(this);
		int height = scoreLevelDisplay.getHeight() + 30, width = 100
				+ Board.NUM_COLUMNS * Square.SIDE + 15;
		setLocationRelativeTo(getRootPane());
		setResizable(false);
		setTitle("Single Player Tetris");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(gameView, BorderLayout.CENTER);
		add(scoreLevelDisplay, BorderLayout.EAST);
		setVisible(true);

	}

	@Override
	public void onGameOver() {
		String message = "Game over! Score is " + gameController.getScore()
				+ ".\n" + "Do you want to play again?";
		int gameOver = JOptionPane.showConfirmDialog(null, message);
		if (gameOver == 0) {
			gameController.setScore(0);
			gameController.setCurrLevel(1);
			this.dispose();
			Injector injector = Guice.createInjector(new Module[0]);
			injector.getInstance(TetrisMain.class);
		}
		if (gameOver == 1) {
			System.exit(0);
		}
	}

	@Override
	public void onCompleteLine() {
		completeLinePlayer.play();
	}

	@Override
	public void onLevelChange() {
		levelChangePlayer.play();
	}

	@Override
	public void onHitFloor() {
		hitFloorPlayer.play();
	}

	@Override
	public void onNewPiece(Piece piece) {
		keyboardListener.setPiece(piece);
	}
}
