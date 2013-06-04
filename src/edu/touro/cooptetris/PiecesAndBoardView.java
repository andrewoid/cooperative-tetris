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
	private boolean wasResized;
	private int playerID;

	public PiecesAndBoardView() {
		setSize(board.getNumColumns() * Square.SIDE + 15, board.getNumRows()
				* Square.SIDE);
		setBorder(BorderFactory.createMatteBorder(0, 7, 0, 7, Color.GREEN));
		setFocusable(true);
		wasResized = false;
		playerID = 0;
	}

	@Inject
	public PiecesAndBoardView(Board board, PiecesList list) {
		this();
		this.board = board;
		this.list = list;
		wasResized = false;
		playerID = 0;

	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public boolean getWasResized() {
		return wasResized;
	}

	public void setWasResized(boolean wasResized) {
		this.wasResized = wasResized;
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
			p.drawPiece(g, playerID);
		}

		// repaint();

	}

	private void clearScreen(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, board.getNumColumns() * Square.SIDE + 15, this.getHeight());
	}

}
