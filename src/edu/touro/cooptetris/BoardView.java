package edu.touro.cooptetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.swing.JComponent;

import edu.touro.cooptetris.pieces.Square;

public class BoardView extends JComponent {

	private static final long serialVersionUID = 1L;
	private Board board;
	private ArrayList<Square[]> squares;
	private int boardMarginSide;
	private int boardMarginBottom;
	private int topY;
	private int bottomY;
	private int rightX;
	private int leftX;
	private int boardWidth;
	private int totalHeight;
	private int totalWidth;

	@Inject
	public BoardView(Board board) {

		this.board = board;
		this.totalWidth = 600;
		this.totalHeight = 500;

		squares = board.getSquares();

		setBoardDimensions();

		squares.get(0)[1] = new Square(50, 50, Color.GREEN);
		squares.get(2)[2] = new Square(300, 300, Color.red);
		squares.get(3)[3] = new Square(300, 50, Color.BLUE);

		Dimension dimension = new Dimension(Board.NUM_COLUMNS * Square.SIDE,
				Board.NUM_ROWS * Square.SIDE);
		this.setMinimumSize(dimension);
		this.setSize(dimension);

	}

	public void drawBoard(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(leftX, topY, leftX, bottomY);
		g.drawLine(rightX, topY, rightX, bottomY);
		g.drawLine(leftX, bottomY, rightX, bottomY);
		for (Square[] row : squares) {
			for (Square s : row) {
				if (s != null) {
					s.draw(g);
				}

			}
		}

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		drawBoard(g);

		repaint();
	}

	private void setBoardDimensions() {
		boardWidth = Board.NUM_COLUMNS * Square.SIDE;
		boardMarginSide = (totalWidth - boardWidth) / 2;
		boardMarginBottom = totalHeight / 4;
		bottomY = (totalHeight - boardMarginBottom);
		rightX = (totalWidth - boardMarginSide);
		leftX = boardMarginSide;

	}

}
