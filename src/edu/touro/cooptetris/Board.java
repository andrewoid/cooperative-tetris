package edu.touro.cooptetris;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Singleton;

import edu.touro.cooptetris.pieces.Piece;
import edu.touro.cooptetris.pieces.Square;

@Singleton
public class Board {

	public static int numRows = 20;
	public static int numColumns = 11;

	private List<ArrayList<Square>> squares;

	public Board() {
		squares = new LinkedList<ArrayList<Square>>();

		for (int i = 0; i < numRows; i++) {
			squares.add(new ArrayList<Square>());

		}
	}

	public void removeAll() {
		squares = new LinkedList<ArrayList<Square>>();
	}

	public List<ArrayList<Square>> getSquares() {
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
		Iterator<ArrayList<Square>> i = squares.iterator();
		int rowsToAdd = 0;
		while (i.hasNext()) {
			ArrayList<Square> row = i.next();
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

	private boolean isFullRow(ArrayList<Square> row) {
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
			ArrayList<Square> rowSquares = squares.get(i);
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

	public void setSquares(ArrayList<ArrayList<Square>> squares) {
		this.squares = squares;
	}

	public void setSquaresArray(ArrayList<Square> squaresArrayList, int numRow) {
		squares.set(numRow, squaresArrayList);
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
		//numRows += 3;
		numColumns += 6;

		/*for (int i = 0; i < 3; i++) {
			squares.add(new ArrayList<Square>());
		}*/
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
			int colNumber = square.getX() / Square.SIDE;
			if (rowNumber >= 0) {
				if (squares.get(rowNumber + 1).get(colNumber) != null) {
					return true;
				}
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		for (ArrayList<Square> row : squares) {
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

}
