package edu.touro.cooptetris.net.message;

import java.io.Serializable;

import edu.touro.cooptetris.GameController;
import edu.touro.cooptetris.net.client.ClientGameController;

public interface Message extends Serializable {

	public void handleByClient(ClientGameController gameController);

	public void handleByServer(GameController gameController);

}
