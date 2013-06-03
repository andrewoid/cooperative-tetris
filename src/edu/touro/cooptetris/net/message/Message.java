package edu.touro.cooptetris.net.message;

import java.io.Serializable;

import edu.touro.cooptetris.ClientGameController;
import edu.touro.cooptetris.GameController;

public interface Message extends Serializable {

	public void handleByClient(ClientGameController gameController);

	public void handleByServer(GameController gameController);

}
