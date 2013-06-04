package edu.touro.cooptetris.net.client;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import edu.touro.cooptetris.Board;
import edu.touro.cooptetris.ClientGameController;
import edu.touro.cooptetris.GameStateListener;
import edu.touro.cooptetris.PiecesAndBoardView;
import edu.touro.cooptetris.ScoreLevelNextPieceDisplay;
import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.Square;
import edu.touro.cooptetris.sound.CompleteLineMusicPlayer;
import edu.touro.cooptetris.sound.HitFloorMusicPlayer;
import edu.touro.cooptetris.sound.LevelChangeMusicPlayer;
import edu.touro.cooptetris.sound.RotateMusicPlayer;
import edu.touro.cooptetris.sound.ThemeMusicPlayer;

public class TetrisMultiplayerMain extends JFrame implements GameStateListener {

	private static final long serialVersionUID = 1L;
	private ClientGameController gameController;
	private PiecesAndBoardView gameView;
	private MultiplayerKeyboardListener keyboardListener;
	private ScoreLevelNextPieceDisplay scoreLevelDisplay;
	private ThemeMusicPlayer themeMusicPlayer;
	private boolean paused;
	// private boolean mute;
	private TetrisClient tetrisClient;
	private Board board;

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new Module[0]);
		injector.getInstance(TetrisMultiplayerMain.class);
	}

	@Inject
	public TetrisMultiplayerMain(final PiecesAndBoardView gameView,
			ScoreLevelNextPieceDisplay scoreLevelDisplay,
			ThemeMusicPlayer themeMusicPlayer,
			final ClientGameController gameController, Board board)
			throws UnknownHostException, IOException {
		this.board = board;
		this.gameController = gameController;
		gameController.setGameStateListener(this);
		this.gameView = gameView;
		this.tetrisClient = new TetrisClient(gameController);
		this.gameView.setPlayerID(this.tetrisClient.getPlayerID());
		this.keyboardListener = new MultiplayerKeyboardListener(tetrisClient);
		this.scoreLevelDisplay = scoreLevelDisplay;
		this.gameView.addKeyListener(keyboardListener);
		keyboardListener.setGameStateListener(this);
		this.themeMusicPlayer = themeMusicPlayer;
		setSize();

		scoreLevelDisplay.setPiece(gameController.getNextPiece());

		setLocationRelativeTo(getRootPane());
		setResizable(false);
		setTitle("Single Player Tetris");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(gameView, BorderLayout.CENTER);
		add(scoreLevelDisplay, BorderLayout.EAST);
		setVisible(true);
		themeMusicPlayer.play();

		new Thread() {
			@Override
			public void run() {
				while (true) {
					gameView.repaint();
					if (gameView.getWasResized()) {
						setSize();
						gameView.setWasResized(false);
					}
					gameController.movePieces();
				}
			}
		}.start();

	}

	@Override
	public void onGameOver() {
		themeMusicPlayer.stop();
		String message = "Game over! Score is " + gameController.getScore()
				+ ".\n" + "Do you want to play again?";
		int gameOver = JOptionPane.showConfirmDialog(null, message);
		if (gameOver == 0) {
			gameController.setScore(0);
			gameController.setCurrLevel(1);
			this.dispose();
			Injector injector = Guice.createInjector(new Module[0]);
			injector.getInstance(TetrisMultiplayerMain.class);
		}
		if (gameOver == 1) {
			System.exit(0);
		}
	}

	public void setSize() {
		int height = scoreLevelDisplay.getHeight() + 30, width = 100
				+ board.getNumColumns() * Square.SIDE + 15;
		setSize(width, height);
	}

	@Override
	public void onCompleteLine(int numLines) {
		CompleteLineMusicPlayer completePlayer;
		try {
			completePlayer = new CompleteLineMusicPlayer();
			completePlayer.play();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		gameController.lineCompleted(numLines);
		scoreLevelDisplay.setScore(gameController.getScore());
		int currLevel = gameController.getCurrLevel();
		if (gameController.getScore() > currLevel * 200) {
			onLevelChange();
		}
	}

	@Override
	public void onLevelChange() {
		LevelChangeMusicPlayer levelPlayer;
		try {
			levelPlayer = new LevelChangeMusicPlayer();
			levelPlayer.play();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		int currLevel = gameController.getCurrLevel();
		gameController.setCurrLevel(currLevel + 1);
		scoreLevelDisplay.setLevel(currLevel + 1);
		gameController.increaseSpeed();
	}

	@Override
	public void onHitFloor() {
		HitFloorMusicPlayer hitFloorPlayer;
		try {
			hitFloorPlayer = new HitFloorMusicPlayer();
			hitFloorPlayer.play();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		scoreLevelDisplay.repaint();
		scoreLevelDisplay.setPiece(gameController.getNextPiece());
	}

	@Override
	public void onNewPiece(Piece piece) {
		keyboardListener.setPiece(piece);
		scoreLevelDisplay.setPiece(gameController.getNextPiece());
		scoreLevelDisplay.repaint();
	}

	@Override
	public void onRotate() {
		RotateMusicPlayer rotatePlayer;
		try {
			rotatePlayer = new RotateMusicPlayer();
			rotatePlayer.play();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPause() {
		gameController.pauseAndUnPause();
		themeMusicPlayer.pauseAndUnPause();
		paused = !paused;

	}

	public boolean isPaused() {
		return paused;
	}

	@Override
	public void onToggleThemeMusic() {
		themeMusicPlayer.stop();

	}

}
