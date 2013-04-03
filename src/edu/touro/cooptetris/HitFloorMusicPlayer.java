package edu.touro.cooptetris;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class HitFloorMusicPlayer extends JFrame {
	
	private Clip clip;

	public HitFloorMusicPlayer() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setTitle("Hit Floor Song Player");
		this.setVisible(true);

		try {
			// Open an audio input stream.
			File soundFile = new File("./Land.wav");
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
	
	public void play(){
		clip.start();
	}
	
	
	public static void main(String[] args) {
		try {
			HitFloorMusicPlayer test= new HitFloorMusicPlayer();
			test.play();
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
