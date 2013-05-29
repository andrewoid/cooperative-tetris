package edu.touro.cooptetris;

import java.util.ArrayList;

import javax.inject.Inject;

import edu.touro.cooptetris.pieces.Piece;

public class GameController {

	private Board board;
	private PiecesList list;
	private PieceFactory pieceFactory;

	@Inject
	public GameController(Board board, PiecesList list,
			PieceFactory pieceFactory) {
		this.board = board;
		this.list = list;
		this.pieceFactory = pieceFactory;
	}

}
