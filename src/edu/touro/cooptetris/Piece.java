package edu.touro.cooptetris;

import java.awt.Color;
import java.awt.Graphics;

public class Piece {

	protected Square[] squares;

	public Piece() {
		squares = new Square[4];

		for (int i = 0; i < 4; i++) {
			squares[i] = new Square(10, 0, 0);

		}
	}

	public Square[] getSquares() {
		return squares;
	}

	public void moveRight() {
		for (int i = 0; i < squares.length; i++) {
			Square s = squares[i];
			int side = s.getSide();
			s.setX(s.getX() + side);
		}
	}

	public void rotate() {
		int rx = squares[2].getX();
		int ry = squares[2].getY();
		// (Rx + Ry - Py, -Rx + Ry + Px)
		// explanation is here:
		// http://answers.yahoo.com/question/index?qid=20100826111525AAh16QO
		for (Square s : squares) {
			int px = s.getX();
			int py = s.getY();
			s.setX(rx + ry - py);
			s.setY(-rx + ry + px);
		}
	}

	public void moveDown() {
		for (int i = 0; i < squares.length; i++) {
			Square s = squares[i];
			int side = s.getSide();
			s.setY(s.getY() + side);
		}
	}

	public void moveLeft() {
		for (int i = 0; i < squares.length; i++) {
			Square s = squares[i];
			int side = s.getSide();
			s.setX(s.getX() - side);
		}
	}

	public void drawPiece(Graphics g) {
		for (Square s : squares) {
			g.setColor(s.getColor());
			g.fillRect(s.getX(), -s.getY(), s.getSide(), s.getSide());
			g.setColor(Color.BLACK);
			g.drawRect(s.getX(), -s.getY(), s.getSide(), s.getSide());
		}

	}

}
