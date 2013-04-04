package edu.touro.cooptetris;

public class DropTimer {

	private int timeIncrement;
	private long lastTime;

	public DropTimer(int timeIncrement) {
		lastTime = System.currentTimeMillis();
		this.timeIncrement = timeIncrement;
	}



	public boolean isTimeToDrop() {
		long currTime = System.currentTimeMillis();
		if (currTime - lastTime > timeIncrement) {
			lastTime = currTime;
			return true;
		} else {
			return false;
		}
	}

	public void setTimeIncrement(int timeIncrement) {
		this.timeIncrement = timeIncrement;
	}

}
