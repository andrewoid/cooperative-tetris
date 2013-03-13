package edu.touro.cooptetris;

import java.util.ArrayList;

public class Board {

	private static final int NUM_ROWS = 200;
	private static final int NUM_COLUMNS = 50;
	private ArrayList<Square[]> squares;

	public Board() {
		squares = new ArrayList<Square[]>();
		for (int i = 0; i < NUM_ROWS; i++) {
			squares.add(new Square[NUM_COLUMNS]);
		}
	}

	public boolean isRowFull(int rowNumber) {
		for (int i = 0; i < squares.get(rowNumber).length; i++) {
			if (squares.get(rowNumber)[i] == null) {
				return false;
			}
		}
		return true;
	}

	public void setSquareFull(Square square) {
		int rowNumber = square.getY() / square.getSide();
		int colNumber = square.getX() / square.getSide();

		squares.get(rowNumber)[colNumber] = square;
	}

	public void setSquareEmpty(int rowNumber, int colNumber) {
		squares.get(rowNumber)[colNumber] = null;
	}

	public void removeRow(int rowNumber) {
		squares.remove(rowNumber);
		squares.add(new Square[NUM_COLUMNS]);
	}

	public boolean willCollideWithFloorVertical(Piece piece) {
		for (Square square : piece.getSquares()) {
			int rowNumber = square.getY() / square.getSide();
			int colNumber = square.getX() / square.getSide();

			if (rowNumber == 0)
				return true;
			
			if (squares.get(rowNumber - 1)[colNumber] != null)
				return true;
		}
		return false;
	}
	

	public boolean willCollideWithFloorRight(Piece piece){
		for(Square square: piece.getSquares()){
			int rowNumber = square.getY() / square.getSide();
			int colNumber = square.getX() / square.getSide();
			
			if(colNumber==NUM_COLUMNS-1)
				return true;
				
			if(squares.get(rowNumber)[colNumber+1]!=null)
				return true;
		}
		return false;
	}
	
	public boolean willCollideWithFloorLeft(Piece piece){
		for(Square square: piece.getSquares()){
			int rowNumber = square.getY() / square.getSide();
			int colNumber = square.getX() / square.getSide();
			
			if(colNumber==0)
				return true;
				
			if(squares.get(rowNumber)[colNumber-1]!=null)
			return true;
		}
		return false;
	}

	public ArrayList<Square[]> getSquares() {
		return squares;
	}

	public void setSquares(ArrayList<Square[]> squares) {
		this.squares = squares;
	}

	public void setSquaresArray(Square[] squaresArray, int numRow) {
		squares.set(numRow, squaresArray);
	}

}
