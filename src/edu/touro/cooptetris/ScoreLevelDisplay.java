package edu.touro.cooptetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class ScoreLevelDisplay extends JPanel {

	private static final long serialVersionUID = -4724424751560236726L;

	private static JTextArea scoreTextArea;
	private static JTextArea levelTextArea;
	private static String scoreString;
	private static String levelString;
	private JLabel nextPieceString;

	public ScoreLevelDisplay() {
		setSize(100, 300);

		nextPieceString=new JLabel("Next Piece: ");
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
		Border paddingBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border compoundBorder = BorderFactory.createCompoundBorder(border,
				paddingBorder);
		scoreTextArea.setBorder(compoundBorder);
		levelTextArea.setBorder(compoundBorder);
		Font font = new Font("Calibri", Font.BOLD, 18);
		scoreTextArea.setFont(font);
		levelTextArea.setFont(font);
		setLayout(new GridLayout(10, 1));
		add(nextPieceString);
		add(new JLabel());
		add(new JLabel());
		add(scoreTextArea);
		add(new JLabel());
		add(levelTextArea);

		setVisible(true);
	}

	public static void setScore(int score) {
		scoreTextArea.setText(scoreString + String.valueOf(score));

	}

	public static void setLevel(int level) {
		levelTextArea.setText(levelString + String.valueOf(level));
	}

}
