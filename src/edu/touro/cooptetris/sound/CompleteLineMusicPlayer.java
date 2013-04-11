package edu.touro.cooptetris.sound;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class CompleteLineMusicPlayer extends SoundPlayer {

	public static void main(String[] args) {
		try {
			CompleteLineMusicPlayer test = new CompleteLineMusicPlayer();
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

	public CompleteLineMusicPlayer() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		super("./Line.wav");

	}

}
