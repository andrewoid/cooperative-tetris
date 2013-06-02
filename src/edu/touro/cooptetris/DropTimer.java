package edu.touro.cooptetris;

public class DropTimer {

	private int timeIncrement;
	private long lastTime;
	private boolean paused;

	public DropTimer(int timeIncrement) {
		lastTime = System.currentTimeMillis();
		this.timeIncrement = timeIncrement;
		paused = false;
	}

	public boolean isTimeToDrop() {
		if (paused) {
			return false;
		}

		long currTime = System.currentTimeMillis();
		if (currTime - lastTime > timeIncrement) {
			lastTime = currTime;
			return true;
		} else {
			return false;
		}

	}

	public void setTimeIncrement(int timeIncrement) {
		this.timeIncrement = timeIncrement > 0 ? timeIncrement
				: this.timeIncrement;
	}

	public int getTimeIncrement() {
		return timeIncrement;
	}

	public void pauseAndUnPause() {
		paused = !paused;
	}

}
