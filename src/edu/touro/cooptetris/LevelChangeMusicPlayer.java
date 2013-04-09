package edu.touro.cooptetris;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class LevelChangeMusicPlayer extends SoundPlayer {

	public static void main(String[] args) {
		try {
			LevelChangeMusicPlayer test = new LevelChangeMusicPlayer();
			test.play();
			Thread.sleep(10000);

		} catch (UnsupportedAudioFileException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (LineUnavailableException e) {

			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public LevelChangeMusicPlayer() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {

		super("./Level.wav");

	}

}
