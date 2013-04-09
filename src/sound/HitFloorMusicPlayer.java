package sound;

import java.io.IOException;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class HitFloorMusicPlayer extends SoundPlayer {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		try {
			HitFloorMusicPlayer test = new HitFloorMusicPlayer();
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

	public HitFloorMusicPlayer() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		super("./Land.wav");
	}

}