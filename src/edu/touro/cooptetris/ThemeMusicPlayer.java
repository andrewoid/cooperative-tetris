package edu.touro.cooptetris;

import java.io.IOException;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import sound.SoundPlayer;

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
			testPlayer.pause();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			testPlayer.resume();
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

	public void pause() {
		clip.stop();
	}

	@Override
	public void play() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void restart() {
		clip.setMicrosecondPosition(0);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void resume() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

}
