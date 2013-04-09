package edu.touro.cooptetris;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

	protected Clip clip;

	public SoundPlayer(String soundFileName)
			throws UnsupportedAudioFileException, IOException,
			LineUnavailableException {

		try {
			// Open an audio input stream.
			// URL url =
			// this.getClass().getClassLoader().getResource("tetristheme.wav");
			File soundFile = new File(soundFileName);
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);

		} catch (UnsupportedAudioFileException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (LineUnavailableException e) {
			throw e;
		}

	}

	public void play() {
		clip.start();
	}

}
