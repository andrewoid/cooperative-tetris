package edu.touro.cooptetris;

import java.awt.BorderLayout;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import edu.touro.cooptetris.pieces.Square;

public class TetrisMain extends JFrame implements GameStateListener {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new Module[0]);
		injector.getInstance(TetrisMain.class);
	}

	@Inject
	public TetrisMain(PiecesAndBoardView gameView,
			ScoreLevelDisplay scoreLevelDisplay) {
		gameView.setOnGameStateListener(this);
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
	public void onGameOver(PiecesAndBoardView pabv) {
		String message = "Game over! Score is " + PiecesAndBoardView.getScore()
				+ ".\n" + "Do you want to play again?";
		int gameOver = JOptionPane.showConfirmDialog(null, message);
		if (gameOver == 0) {

		}
		if (gameOver == 1) {
			System.exit(0);
		}
	}

}
