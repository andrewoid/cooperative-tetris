package edu.touro.cooptetris;

import java.awt.Color;

public class Square {

	// TODO: if this never changes, shouldn't it be a constant?
	private int side;
	private int x;
	private int y;
	private Color color;

	public Square(int side, int x, int y) {
		super();
		this.side = side;
		this.x = x;
		this.y = y;
		// TODO: Why is this is default color? 
		// Couldn't the color be passed in through constructor?
		this.color = Color.GRAY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
