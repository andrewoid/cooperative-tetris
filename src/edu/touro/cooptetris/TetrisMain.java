package edu.touro.cooptetris;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class TetrisMain extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new TetrisMain();
	}

	public TetrisMain() {
		int height = 600, width = 500;
		setTitle("piece GUI");
		setSize(height, width);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(new TetrisGameView(height, width), BorderLayout.CENTER);
		add(new ScoreLevelDisplay(0, 1), BorderLayout.EAST);

		setVisible(true);

	}

}
