package edu.touro.cooptetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ScoreLevelDisplay extends JPanel {

	private static final long serialVersionUID = -4724424751560236726L;

	private static JLabel scoreLabel;
	private static JLabel levelLabel;
	private static String scoreString;
	private static String levelString;

	public ScoreLevelDisplay() {
		scoreString = "Score: ";
		levelString = "Level: ";
		scoreLabel = new JLabel(scoreString + String.valueOf(0));
		levelLabel = new JLabel(levelString + String.valueOf(1));
		setSize(100, 300);
		Border paddingBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border compoundBorder = BorderFactory.createCompoundBorder(border,
				paddingBorder);
		scoreLabel.setBorder(compoundBorder);
		levelLabel.setBorder(compoundBorder);
		Font font = new Font("Calibri", Font.BOLD, 18);
		scoreLabel.setFont(font);
		levelLabel.setFont(font);
		setLayout(new GridLayout(5, 1));
		add(new JLabel());
		add(scoreLabel);
		add(new JLabel());
		add(levelLabel);
		add(new JLabel());

		setVisible(true);
	}

	public static void setScore(int score) {
		scoreLabel.setText(scoreString + String.valueOf(score));

	}

	public static void setLevel(int level) {
		levelLabel.setText(levelString + String.valueOf(level));
	}

}
