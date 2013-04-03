package edu.touro.cooptetris;

import java.awt.Color;
import java.awt.Graphics;

public class Piece {

	protected Square[] squares;
	protected Square center;

	public Piece() {
		squares = new Square[4];

		for (int i = 0; i < 4; i++) {
			squares[i] = new Square(0, 0, Color.BLACK);
		}
	}

	public boolean collidesWith(Piece p) {
		Square[] currSquares = this.getSquares();
		Square[] pSquares = p.getSquares();
		for (Square currSquare : currSquares) {
			for (Square pSquare : pSquares) {
				if (currSquare.equals(pSquare)) {
					return true;
				}
			}
		}
		return false;
	}

	public void drawPiece(Graphics g) {
		for (Square s : squares) {
			g.setColor(s.getColor());
			int x = s.getX();
			int y = -s.getY();
			int side = s.getSide();
			g.fillRect(x, y, side, side);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, side, side);
		}

	}

	public Square[] getSquares() {
		return squares;
	}

	public void moveDown() {
		for (Square s : squares) {
			int side = s.getSide();
			s.setY(s.getY() - side);
			// if it is + side then it moves up
		}
	}

	public void moveLeft() {
		for (Square s : squares) {
			int side = s.getSide();
			s.setX(s.getX() - side);
		}
	}

	public void moveRight() {
		for (Square s : squares) {
			int side = s.getSide();
			s.setX(s.getX() + side);
		}
	}

	public void rotate() {
		int rx = center.getX();
		int ry = center.getY();
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

}
