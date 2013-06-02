package edu.touro.cooptetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import edu.touro.cooptetris.pieces.BoxPiece;
import edu.touro.cooptetris.pieces.JPiece;
import edu.touro.cooptetris.pieces.LPiece;
import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.SPiece;
import edu.touro.cooptetris.pieces.Square;
import edu.touro.cooptetris.pieces.TPiece;
import edu.touro.cooptetris.pieces.ZPiece;

public class ScoreLevelNextPieceDisplay extends JPanel {

	private static final long serialVersionUID = -4724424751560236726L;

	private JTextArea scoreTextArea;
	private JTextArea levelTextArea;
	private String scoreString;
	private String levelString;
	private JPanel nextPiece;
	private Piece piece;

	public ScoreLevelNextPieceDisplay() {
		setSize(100, 300);
		scoreString = "Score: ";
		levelString = "Level: ";
		scoreTextArea = new JTextArea(scoreString + String.valueOf(0));
		scoreTextArea.setSize(94, 30);
		scoreTextArea.setLineWrap(true);
		scoreTextArea.setEnabled(false);
		levelTextArea = new JTextArea(levelString + String.valueOf(1));
		levelTextArea.setSize(94, 30);
		levelTextArea.setAlignmentY(CENTER_ALIGNMENT);
		levelTextArea.setLineWrap(true);
		levelTextArea.setEnabled(false);
		createNextPiece();
		JLabel nextPieceLabel = new JLabel("Next piece");
		nextPieceLabel.setEnabled(false);
		Border paddingBorder = BorderFactory.createEmptyBorder(10, 1, 10, 1);
		Border paddingBorder2 = BorderFactory.createEmptyBorder(35, 1, 1, 1);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border compoundBorder = BorderFactory.createCompoundBorder(border,
				paddingBorder);
		scoreTextArea.setBorder(compoundBorder);
		levelTextArea.setBorder(compoundBorder);
		nextPieceLabel.setBorder(paddingBorder2);
		Font font = new Font("Calibri", Font.BOLD, 18);
		scoreTextArea.setFont(font);
		levelTextArea.setFont(font);
		nextPieceLabel.setFont(font);

		setLayout(new GridLayout(5, 1));
		add(scoreTextArea);
		add(new JLabel());
		add(levelTextArea);
		add(nextPieceLabel);
		add(nextPiece);
		setVisible(true);
	}

	public void createNextPiece() {
		this.nextPiece = new NextPiecePanel();
		nextPiece.setBackground(Color.BLACK);
		Dimension dim = new Dimension(60, 60);
		this.setMinimumSize(dim);
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

	private class NextPiecePanel extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (piece instanceof BoxPiece || piece instanceof SPiece
					|| piece instanceof ZPiece) {
				piece.setLocation(35, 15);
			} else if (piece instanceof JPiece || piece instanceof LPiece) {
				piece.setLocation(30, 5);
			} else if (piece instanceof TPiece) {
				piece.setLocation(25, 15);
			} else {
				piece.setLocation(35, 0);
			}
			piece.drawPiece(g);
			piece.setLocation(Board.NUM_COLUMNS * Square.SIDE / 2, 0);
		}
	}
}
