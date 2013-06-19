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
import edu.touro.cooptetris.GameStateListener;
import edu.touro.cooptetris.PiecesAndBoardView;
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
	private ThemeMusicPlayer themeMusicPlayer;
	private TetrisClient tetrisClient;
	private Board board;

	@Inject
	public TetrisMultiplayerMain(final PiecesAndBoardView gameView,
			final ClientGameController gameController, Board board)
			throws UnknownHostException, IOException {
		this.board = board;
		this.gameController = gameController;
		gameController.setGameStateListener(this);
		this.gameView = gameView;
		this.tetrisClient = new TetrisClient(gameController);
		this.gameView.setPlayerID(this.tetrisClient.getGameController()
				.getPlayerID());
		this.keyboardListener = new MultiplayerKeyboardListener(tetrisClient);
		this.gameView.addKeyListener(keyboardListener);
		keyboardListener.setGameStateListener(this);
		setSize();
		setLocationRelativeTo(getRootPane());
		setResizable(false);
		setTitle("MultiPlayer Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(gameView, BorderLayout.CENTER);
		setVisible(true);

	}

	@Override
	public void onGameOver() {
		themeMusicPlayer.stop();
		String message = "Game over! " + ".\n" + "Do you want to play again?";
		int gameOver = JOptionPane.showConfirmDialog(null, message);
		if (gameOver == JOptionPane.YES_OPTION) {
			this.dispose();
			Injector injector = Guice.createInjector(new Module[0]);
			injector.getInstance(TetrisMultiplayerMain.class);
		}
		if (gameOver == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}

	public void setSize() {
		int height = 300 + 30;
		int width = board.getNumColumns() * Square.SIDE + 15;
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
	}

	@Override
	public void onNewPiece(Piece piece) {
		keyboardListener.setPiece(piece);
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

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new Module[0]);
		injector.getInstance(TetrisMultiplayerMain.class);
	}

	@Override
	public void onToggleThemeMusic() {
		themeMusicPlayer.stop();

	}

	@Override
	public void onIncreaseSize() {
		setSize();

	}

	public ClientGameController getGameController() {
		return gameController;
	}

}
