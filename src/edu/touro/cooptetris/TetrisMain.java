package edu.touro.cooptetris;

import java.awt.BorderLayout;

import javax.inject.Inject;
import javax.swing.JFrame;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class TetrisMain extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new Module[0]);
		TetrisMain main = injector.getInstance(TetrisMain.class);
	}

	@Inject
	public TetrisMain(TetrisGameView gameView,
			ScoreLevelDisplay scoreLevelDisplay) {
		int height = 600, width = 500;
		setTitle("piece GUI");
		setSize(height, width);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(gameView, BorderLayout.CENTER);
		add(scoreLevelDisplay, BorderLayout.EAST);

		setVisible(true);
	}

}
