package edu.touro.cooptetris;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;

import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.Square;

@Singleton
public class Board {

	private final static Logger log = Logger
			.getLogger(Board.class.getName());
	private int numRows = 20;
	private int numColumns = 11;

	private List<List<Square>> squares;

	public Board() {
		squares = new LinkedList<List<Square>>();

		for (int i = 0; i < numRows; i++) {
			List<Square> row = new ArrayList<Square>();
			for (int j = 0; j < numColumns; j++) {
				row.add(null);
			}
			squares.add(row);

		}
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public void copyBoard(Board b) {
		this.numColumns = b.numColumns;
		this.numRows = b.numRows;
		this.squares = b.squares;
	}

	public void removeAll() {
		squares = new LinkedList<List<Square>>();
	}

	public List<List<Square>> getSquares() {
		return squares;
	}

	public boolean isRowFull(int rowNumber) {
		for (int i = 0; i < squares.get(rowNumber).size(); i++) {
			if (squares.get(rowNumber).get(i) == null) {
				return false;
			}
		}
		return true;
	}

	public void landPiece(Piece piece) {
		for (Square square : piece.getSquares()) {
			this.setSquareFull(square);

		}

	}

	public int checkFullRowsOfPiece(Piece p) {
		int i = 0;
		for (Square square : p.getSquares()) {
			int rowNumber = square.getY() / Square.SIDE;
			if (isRowFull(rowNumber)) {
				this.removeRow(rowNumber);
				i++;
			}
		}
		return i;
	}

	public void removeFullRows() {
		Iterator<List<Square>> i = squares.iterator();
		int rowsToAdd = 0;
		while (i.hasNext()) {
			List<Square> row = i.next();
			if (isFullRow(row)) {
				i.remove();
				rowsToAdd++;
			}
		}
		addBlankRows(rowsToAdd);
	}

	private void addBlankRows(int rowsToAdd) {
		for (int i = 0; i < rowsToAdd; i++) {
			squares.add(new ArrayList<Square>());
		}
	}

	private boolean isFullRow(List<Square> row) {
		for (Square s : row) {
			if (s == null) {
				return false;
			}
		}
		return true;
	}

	public void removeRow(int rowNumber) {
		squares.remove(rowNumber);
		for (int i = 0; i < rowNumber; i++) {
			List<Square> rowSquares = squares.get(i);
			for (Square square : rowSquares) {
				if (square != null) {
					square.setY(square.getY() + Square.SIDE);
				}
			}
		}
		squares.add(0, new ArrayList<Square>());
	}

	public void setSquareEmpty(int rowNumber, int colNumber) {
		squares.get(rowNumber).set(colNumber, null);
	}

	public void setSquareFull(Square square) {
		int rowNumber = square.getY() / Square.SIDE;
		int colNumber = square.getX() / Square.SIDE;

		if (rowNumber >= 0) {
			squares.get(rowNumber).set(colNumber, square);
		}
	}

	public void setSquares(List<List<Square>> squares) {
		this.squares = squares;
	}

	public void setSquaresArray(List<Square> list, int numRow) {
		squares.set(numRow, list);
	}

	public boolean willCollideWithFloorLeft(Piece piece) {
		for (Square square : piece.getSquares()) {
			int rowNumber = square.getY() / Square.SIDE;
			int colNumber = square.getX() / Square.SIDE;

			if (colNumber == 0) {
				return true;
			}

			if (rowNumber >= 0) {
				if (squares.get(rowNumber).get(colNumber - 1) != null) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean willCollideWithFloorRight(Piece piece) {
		for (Square square : piece.getSquares()) {
			int rowNumber = square.getY() / Square.SIDE;
			int colNumber = square.getX() / Square.SIDE;

			if (colNumber == numColumns - 1) {
				return true;
			}

			if (rowNumber >= 0) {
				if (squares.get(rowNumber).get(colNumber + 1) != null) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isFull() {
		if (squares.get(0).get(numColumns / 2) != null) {
			return true;
		} else {
			return false;
		}
	}

	public int getNumRows() {
		return squares.size();
	}

	public void increaseBoardSize() {
		// numRows += 3;
		numColumns += 6;

		/*
		 * for (int i = 0; i < 3; i++) { squares.add(new ArrayList<Square>()); }
		 */
	}

	public boolean willCollideWithFloorVertical(Piece piece) {
		for (Square square : piece.getSquares()) {
			int rowNumber = square.getY() / Square.SIDE;
			if (rowNumber == numRows - 1) {
				return true;
			}
		}
		return false;
	}

	public boolean willCollideWithLandedPieceVertical(Piece piece) {
		for (Square square : piece.getSquares()) {
			int rowNumber = square.getY() / Square.SIDE;
			log.log(Level.INFO,"rowNumber " + rowNumber);
			int colNumber = square.getX() / Square.SIDE;
			log.log(Level.INFO,"colNumber " + colNumber);
			if (rowNumber >= 0) {
				if (squares.get(rowNumber + 1).get(colNumber) != null) {
					return true;
				}
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		for (List<Square> row : squares) {
			for (Square s : row) {
				if (s != null) {
					s.draw(g);
				}
			}
		}
	}

	public boolean onBoard(Piece p) {
		for (Square square : p.getSquares()) {
			int rowNumber = square.getY() / Square.SIDE;
			int colNumber = square.getX() / Square.SIDE;

			if (square.getX() < 0 || rowNumber >= numRows) {
				return false;
			}
			if (square.getY() < 0 || colNumber >= numColumns) {
				return false;
			}
		}
		return true;
	}

	public boolean collidedWithPiece(Piece p) {
		for (Square square : p.getSquares()) {
			int rowNumber = square.getY() / Square.SIDE;
			int colNumber = square.getX() / Square.SIDE;
			if (rowNumber >= 0) {
				if (squares.get(rowNumber).get(colNumber) != null) {
					return true;
				}
			}
		}
		return false;
	}

	public void setSquaresArray(Square[] fullRow, int j) {
		List<Square> list = new ArrayList<Square>();
		for (Square s : fullRow) {
			list.add(s);
		}
		setSquaresArray(list, j);
	}

}
