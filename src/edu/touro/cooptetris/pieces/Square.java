package edu.touro.cooptetris.pieces;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Square implements Serializable {

	public static final int SIDE = 15;
	private int x;
	private int y;
	private Color color;

	public Square(int x, int y, Color color) {
		super();
		this.x = x;
		this.y = y;

		this.color = color;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Square other = (Square) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	public Color getColor() {
		return color;
	}

	public int getSide() {
		return SIDE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, SIDE, SIDE);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIDE, SIDE);
	}

	public void drawBorderSquare(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, SIDE, SIDE);
		g.setColor(Color.lightGray);
		g.drawRect(x, y, SIDE, SIDE);
	}

}
