package edu.touro.cooptetris.sound;

import java.io.IOException;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class CompleteLineMusicPlayer extends SoundPlayer {

	private static final long serialVersionUID = 1L;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Clip clip;

	public CompleteLineMusicPlayer() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		super("./Line.wav");

	}

}
