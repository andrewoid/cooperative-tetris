package edu.touro.cooptetris;

public class Level {

	private int timeIncrement;
	private int levelNum;
	
	public Level(int levelNum, int timeIncrement){
		this.timeIncrement=timeIncrement;
		this.levelNum=levelNum;
	}

	public int getTimeIncrement() {
		return timeIncrement;
	}

	public void setTimeIncrement(int timeIncrement) {
		this.timeIncrement = timeIncrement;
	}

	public int getLevelNum() {
		return levelNum;
	}

	
}
