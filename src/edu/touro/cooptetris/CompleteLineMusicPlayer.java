package edu.touro.cooptetris;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class CompleteLineMusicPlayer extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		try {
			CompleteLineMusicPlayer test = new CompleteLineMusicPlayer();
			test.play();

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	private Clip clip;

	public CompleteLineMusicPlayer() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(0, 0);
		this.setTitle("Complete Line Player");
		this.setVisible(false);

		try {
			// Open an audio input stream.
			File soundFile = new File("./Line.wav");
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
