package edu.touro.cooptetris.net.message;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.touro.cooptetris.net.client.ClientGameController;
import edu.touro.cooptetris.net.server.ServerGameController;

public class MovePiecesMessage implements Message {

	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(ServerGameController.class.getName());

	@Override
	public void handleByClient(ClientGameController gameController) {

	}

	@Override
	public void handleByServer(ServerGameController gameController) {
		gameController.movePieces();
		//log.log(Level.INFO, "Handling a MovePiecesMessage");
	}

}
