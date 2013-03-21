package edu.touro.cooptetris;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

public class BoardTest {

	private Board board;
	private Square[] fullRow;

	private void givenBoard() {
		board = new Board();
	}

	private void givenFullRow() {
		fullRow = new Square[50];

		for (int i = 0; i < fullRow.length; i++) {
			fullRow[i] = new Square(0, 0, Color.BLACK);
		}
	}

	private LinePiece givenLinePiece() {
		LinePiece linePiece = new LinePiece(10, 450);
		for (int i = 0; i < linePiece.getSquares().length; i++) {
			Square s = linePiece.getSquares()[i];
			s.setX(450 + 10 * i);
			s.setY(10 + i * 10);
		}
		return linePiece;
	}

	@Test
	public void testCollidesWithFloorLeft() {
		givenBoard();
		givenFullRow();

		LinePiece linePiece = givenLinePiece();
		for (int i = 0; i < linePiece.getSquares().length; i++) {
			Square s = linePiece.getSquares()[i];
			s.setX(20 + 10 * i);
			s.setY(10 + i * 10);
		}

		assertFalse(board.willCollideWithFloorLeft(linePiece));

		board.setSquareFull(new Square(10, 10, Color.BLACK));
		assertTrue(board.willCollideWithFloorLeft(linePiece));

	}

	@Test
	public void testCollidesWithFloorRight() {
		givenBoard();
		givenFullRow();

		LinePiece linePiece = givenLinePiece();
		assertFalse(board.willCollideWithFloorRight(linePiece));

		board.setSquareFull(new Square(490, 40, Color.BLACK));

		assertTrue(board.willCollideWithFloorRight(linePiece));
	}

	@Test
	public void testCollidesWithFloorVertical() {
		givenBoard();
		givenFullRow();

		whenRowIsFull(0);

		LinePiece linePiece = givenLinePiece();

		assertTrue(board.willCollideWithFloorVertical(linePiece));

	}

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

	private void whenRowIsRemoved(int rowNumber) {
		board.removeRow(rowNumber);
	}

}
