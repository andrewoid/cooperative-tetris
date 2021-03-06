package edu.touro.cooptetris.sound;

import java.io.IOException;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ThemeMusicPlayer extends SoundPlayer {

	public static void main(String[] args) {
		try {
			ThemeMusicPlayer testPlayer = new ThemeMusicPlayer();
			testPlayer.play();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			testPlayer.pauseAndUnPause();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			testPlayer.pauseAndUnPause();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			testPlayer.restart();

		} catch (UnsupportedAudioFileException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (LineUnavailableException e) {

			e.printStackTrace();
		}
	}

	public ThemeMusicPlayer() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {

		super("./tetristheme.wav");

	}

	public void pauseAndUnPause() {
		if (clip.isActive()) {
			clip.stop();
		} else {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}

	}

	public void restart() {
		clip.setMicrosecondPosition(0);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void play() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}

}
