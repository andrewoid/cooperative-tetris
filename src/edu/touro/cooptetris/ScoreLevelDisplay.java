package edu.touro.cooptetris;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import edu.touro.cooptetris.pieces.Piece;

public class ScoreLevelDisplay extends JPanel {

	private static final long serialVersionUID = -4724424751560236726L;

	private JTextArea scoreTextArea;
	private JTextArea levelTextArea;
	private String scoreString;
	private String levelString;
	private JPanel nextPiece;
	private Piece piece;

	public ScoreLevelDisplay() {
		setSize(100, 300);
		this.piece = null;
		scoreString = "Score: ";
		levelString = "Level: ";
		scoreTextArea = new JTextArea(scoreString + String.valueOf(0));
		scoreTextArea.setSize(94, 30);
		scoreTextArea.setLineWrap(true);
		scoreTextArea.setEnabled(false);
		levelTextArea = new JTextArea(levelString + String.valueOf(1));
		levelTextArea.setSize(94, 30);
		levelTextArea.setLineWrap(true);
		levelTextArea.setEnabled(false);
		createNextPiece();
		Border paddingBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border compoundBorder = BorderFactory.createCompoundBorder(border,
				paddingBorder);
		scoreTextArea.setBorder(compoundBorder);
		levelTextArea.setBorder(compoundBorder);
		Font font = new Font("Calibri", Font.BOLD, 18);
		scoreTextArea.setFont(font);
		levelTextArea.setFont(font);
		setLayout(new GridLayout(6, 1));
		add(new JLabel());
		add(scoreTextArea);
		add(new JLabel());
		add(levelTextArea);
		add(new JLabel());
		add(nextPiece);
		setVisible(true);
	}

	public void createNextPiece() {
		this.nextPiece = new NextPiecePanel();
		nextPiece.setBackground(Color.BLACK);

	}

	private class NextPiecePanel extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.setLayout(new FlowLayout());
			piece.drawPiece(g);
		}
	}

	public void setScore(int score) {
		scoreTextArea.setText(scoreString + String.valueOf(score));

	}

	public void setLevel(int level) {
		levelTextArea.setText(levelString + String.valueOf(level));
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

}
