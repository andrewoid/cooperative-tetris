package edu.touro.cooptetris.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

	protected Clip clip;
	protected AudioInputStream audioIn;

	public SoundPlayer(String soundFileName)
			throws UnsupportedAudioFileException, IOException,
			LineUnavailableException {

		// Open an audio input stream.
		// URL url =
		// this.getClass().getClassLoader().getResource("tetristheme.wav");
		File soundFile = new File(soundFileName);
		audioIn = AudioSystem.getAudioInputStream(soundFile);
		// Get a sound clip resource.
		clip = AudioSystem.getClip();
		clip.open(audioIn);

	}

	public void play() {

		clip.start();

	}
}
