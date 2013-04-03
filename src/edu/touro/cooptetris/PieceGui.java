package edu.touro.cooptetris;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class PieceGui extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new PieceGui();
	}

	public PieceGui() {
		setTitle("piece GUI");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(new PieceView(), BorderLayout.CENTER);
		add(new ScoreLevelDisplay(0, 1), BorderLayout.EAST);

		setVisible(true);

	}

}
