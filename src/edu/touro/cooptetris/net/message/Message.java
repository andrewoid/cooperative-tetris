package edu.touro.cooptetris.net.message;

import java.io.Serializable;

import edu.touro.cooptetris.net.client.ClientGameController;
import edu.touro.cooptetris.net.server.ServerGameController;

public interface Message extends Serializable {

	public void handleByClient(ClientGameController gameController);

	public void handleByServer(ServerGameController gameController);

}
