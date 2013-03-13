package edu.touro.cooptetris;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	private Board board;
	private Square[] fullRow;

	@Test
	public void testIsRowFull() {
		givenBoard();
		whenRowIsFull(2);
		whenRowIsFull(3);

		thenRowIsFull(2);
		thenRowIsFull(3);
	}

	@Test
	public void testRemoveRow() {
		givenBoard();

		whenRowIsFull(2);
		whenRowIsFull(3);

		whenRowIsRemoved(2);

		thenRowIsNotFull(3);
		thenRowIsFull(2);
	}

	private void whenRowIsRemoved(int rowNumber) {
		board.removeRow(rowNumber);
	}

	private void thenRowIsFull(int rowNumber) {
		assertTrue(board.isRowFull(rowNumber));
	}

	private void thenRowIsNotFull(int rowNumber) {
		assertFalse(board.isRowFull(rowNumber));
	}

	private void whenRowIsFull(int rowNumber) {
		givenFullRow();
		board.setSquaresArray(fullRow, rowNumber);
	}

	private void givenFullRow() {
		fullRow = new Square[50];

		for (int i = 0; i < fullRow.length; i++) {
			fullRow[i] = new Square(10, 0, 0);
		}
	}

	private void givenBoard() {
		board = new Board();
	}

	@Test
	public void testCollidesWithFloor() {
		givenBoard();
		givenFullRow();

		whenRowIsFull(0);

		LinePiece linePiece = givenLinePiece();
		
		assertTrue(board.willCollideWithFloorVertical(linePiece));
	}

	private LinePiece givenLinePiece() {
		LinePiece linePiece = new LinePiece();
		for(int i=0;i<linePiece.getSquares().length;i++){
			Square s=linePiece.getSquares()[i];
			s.setX(10);
			s.setY(10+i*10);
		}
		return linePiece;
	}
}
