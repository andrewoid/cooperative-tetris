package edu.touro.cooptetris;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class ThemeMusicPlayer extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		try {
			ThemeMusicPlayer testPlayer = new ThemeMusicPlayer();
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

	private Clip clip;

	public ThemeMusicPlayer() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(0, 0);
		this.setTitle("Theme Song Player");
		this.setVisible(false);

		try {
			// Open an audio input stream.
			// URL url =
			// this.getClass().getClassLoader().getResource("tetristheme.wav");
			File soundFile = new File("./tetristheme.wav");
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

	public void pause() {
		clip.stop();
	}

	public void restart() {
		clip.setMicrosecondPosition(0);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void resume() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void play(){
		clip.start();
	}

}
