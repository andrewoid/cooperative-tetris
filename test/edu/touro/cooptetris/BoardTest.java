package edu.touro.cooptetris;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

import edu.touro.cooptetris.pieces.LinePiece;
import edu.touro.cooptetris.pieces.Square;

public class BoardTest {

	private Board board;
	private Square[] fullRow;

	private void givenBoard() {
		board = new Board();
	}

	private void givenFullRow() {
		fullRow = new Square[Board.getNumColumns()];

		for (int i = 0; i < fullRow.length; i++) {
			fullRow[i] = new Square(0, 0, Color.BLACK);
		}
	}

	private LinePiece givenLinePiece() {
		LinePiece linePiece = new LinePiece(20, 37 * Square.SIDE);
		return linePiece;
	}

	/*
	 * @Test public void testCollidesWithFloorLeft() { givenBoard();
	 * givenFullRow();
	 * 
	 * LinePiece linePiece = givenLinePiece();
	 * 
	 * assertFalse(board.willCollideWithFloorLeft(linePiece));
	 * 
	 * board.setSquareFull(new Square(10, 10, Color.BLACK));
	 * assertTrue(board.willCollideWithFloorLeft(linePiece));
	 * 
	 * }
	 * 
	 * @Test public void testCollidesWithFloorRight() { givenBoard();
	 * givenFullRow();
	 * 
	 * LinePiece linePiece = givenLinePiece();
	 * assertFalse(board.willCollideWithFloorRight(linePiece));
	 * 
	 * board.setSquareFull(new Square(30, 40, Color.BLACK));
	 * 
	 * assertTrue(board.willCollideWithFloorRight(linePiece)); }
	 */
	@Test
	public void testCollidesWithFloorVertical() {
		givenBoard();
		givenFullRow();

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
	public void testLandPiece() {
		givenBoard();
		givenFullRow();

		givenLinePiece();

		LinePiece SecondLinePiece = new LinePiece(30, 10);
		board.landPiece(SecondLinePiece);

		// assertTrue(board.willCollideWithFloorRight(linePiece));
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
