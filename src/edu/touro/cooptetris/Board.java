package edu.touro.cooptetris;

import java.util.ArrayList;

public class Board {

	private static final int NUM_ROWS = 150;
	private static final int NUM_COLUMNS = 10;

	public static int getNumColumns() {
		return NUM_COLUMNS;
	}

	public static int getNumRows() {
		return NUM_ROWS;
	}

	private ArrayList<Square[]> squares;

	public Board() {
		squares = new ArrayList<Square[]>();
		for (int i = 0; i < NUM_ROWS; i++) {
			squares.add(new Square[NUM_COLUMNS]);
		}
	}

	public ArrayList<Square[]> getSquares() {
		return squares;
	}

	public boolean isRowFull(int rowNumber) {
		for (int i = 0; i < squares.get(rowNumber).length; i++) {
			if (squares.get(rowNumber)[i] == null) {
				return false;
			}
		}
		return true;
	}

	public void removeRow(int rowNumber) {
		squares.remove(rowNumber);
		squares.add(new Square[NUM_COLUMNS]);
	}

	public void setSquareEmpty(int rowNumber, int colNumber) {
		squares.get(rowNumber)[colNumber] = null;
	}

	public void setSquareFull(Square square) {
		int rowNumber = square.getY() / Square.SIDE;
		int colNumber = square.getX() / Square.SIDE;

		squares.get(rowNumber)[colNumber] = square;
	}

	public void setSquares(ArrayList<Square[]> squares) {
		this.squares = squares;
	}

	public void setSquaresArray(Square[] squaresArray, int numRow) {
		squares.set(numRow, squaresArray);
	}

	public boolean willCollideWithFloorLeft(Piece piece) {
		for (Square square : piece.getSquares()) {
			int rowNumber = square.getY() / Square.SIDE;
			int colNumber = square.getX() / Square.SIDE;

			if (colNumber == 0) {
				return true;
			}

			if (squares.get(rowNumber)[colNumber - 1] != null) {
				return true;
			}
		}
		return false;
	}

	public boolean willCollideWithFloorRight(Piece piece) {
		for (Square square : piece.getSquares()) {
			int rowNumber = square.getY() / Square.SIDE;
			int colNumber = square.getX() / Square.SIDE;

			if (colNumber == NUM_COLUMNS - 1) {
				return true;
			}

			if (squares.get(rowNumber)[colNumber + 1] != null) {
				return true;
			}
		}
		return false;
	}
	

	public boolean willCollideWithFloorVertical(Piece piece) {
		for (Square square : piece.getSquares()) {
			int rowNumber = square.getY() / Square.SIDE;
			int colNumber = square.getX() / Square.SIDE;

			if (rowNumber == 0) {
				return true;
			}

			if (squares.get(rowNumber - 1)[colNumber] != null) {
				return true;
			}
		}
		return false;
	}
	
	public void landPiece(Piece piece){
		for(Square square: piece.getSquares()){
			this.setSquareFull(square); 
		}
	}

}
