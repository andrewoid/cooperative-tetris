package edu.touro.cooptetris;

import java.awt.Color;
import java.awt.Graphics;

import javax.inject.Inject;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.Square;

public class PiecesAndBoardView extends JComponent {

	private Board board;

	private static final long serialVersionUID = 1L;
	private PiecesList list;

	public PiecesAndBoardView() {

		setSize(Board.NUM_COLUMNS * Square.SIDE + 15, Board.NUM_ROWS
				* Square.SIDE);
		setBorder(BorderFactory.createMatteBorder(0, 7, 0, 7, Color.GREEN));
		setFocusable(true);

	}

	@Inject
	public PiecesAndBoardView(Board board, PiecesList list) {
		this();
		this.board = board;

	}

	public Board getBoard() {
		return board;
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		clearScreen(g);

		board.draw(g);

		for (Piece p : list) {
			p.drawPiece(g);
		}

		// repaint();

	}

	private void clearScreen(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Board.NUM_COLUMNS * Square.SIDE + 15, this.getHeight());
	}

}
