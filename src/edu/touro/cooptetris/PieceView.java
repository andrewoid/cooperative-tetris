package edu.touro.cooptetris;

// TODO: get rid of the warnings in here
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JComponent;

public class PieceView extends JComponent {

	private Piece p;
	private LinePiece line;
	private ArrayList<Piece> pieces;
	private int x;
	private int y;
	private DropTimer timer;
	private ArrayList<Level> levels;
	private int score;
	private Level currLevel;


	public PieceView() {
		levels=new ArrayList<Level>();
		for(int i=0; i<10; i++){
			levels.add(new Level(i, 1000-(i*100)));
		}
		currLevel=levels.get(0);
		timer=new DropTimer(300);
		setSize(800, 600);
		pieces = new ArrayList<Piece>();
		x = getWidth() / 2;
		y = 0;
		
		
		p=new LPiece(x, y);
		pieces.add(p);
		for (int i=0; i<5; i++){
			p.moveDown();
		}
		
		// pieces.add(new TPiece());
		// pieces.add(new LinePiece());
		// pieces.add(new BoxPiece());
		// pieces.add(new ZPiece());
		// pieces.add(new SPiece());
		// pieces.add(new LPiece());
		KeyboardListener keyListener = new KeyboardListener();
		addKeyListener(keyListener);
		keyListener.setPiece(p);
		setFocusable(true);

	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		for (Piece p : pieces) {
			p.drawPiece(g);
		}

		if (timer.isTimeToDrop()){
			pieces.get(0).moveDown();
		}
		
		repaint();
	}
	
	public void lineCompleted(int numLines){
		switch(numLines){
		case 1:
			score+=100;
			break;
		case 2:
			score+=250;
			break;
		case 3:
			score+=500;
			break;
		case 4:
			score+=1000;
			break;
		}
	}
}
