package edu.touro.cooptetris;

import java.awt.Color;

public class Square {

	private int side;
	private int x;
	private int y;
	private Color color;

	public Square(int side, int x, int y) {
		super();
		this.side = side;
		this.x = x;
		this.y = y;
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

}
