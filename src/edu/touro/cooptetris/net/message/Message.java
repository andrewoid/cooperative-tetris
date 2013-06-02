package edu.touro.cooptetris.net.message;

import java.io.Serializable;

import edu.touro.cooptetris.GameController;
import edu.touro.cooptetris.GameStateListener;

public interface Message extends Serializable {

	public void handleByClient(GameController gameController,
			GameStateListener gameStateListener);

	public void handleByServer(GameController gameController);

}
