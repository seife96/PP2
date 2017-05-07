package game;

import java.util.Vector;

import objects.*;

public class GameController {

	/** Singleton */
	private static GameController instance;

	/** Haelt Informationen ueber den Zustand des Spiels */
	private GameState gameState;

	/**
	 * Zustaendig fuer die Darstellung und Entgegennahme von
	 * Benutzerinteraktionen
	 */
	private GameFrame gameFrame;
	
	/**
	 * Enthaelt die eigentliche Logik (Kollisions-, Score- und
	 * Schadensberechnung sowie Levelverwaltung)
	 */
	private GameManagementThread gameManagementThread = new GameManagementThread();

	/**
	 * Thread, der dafuer sorgt, dass bewegliche Objekte sich bewegen.
	 */
	private MoveObjects moveObjects = new MoveObjects();
	
	private Shape[] shapeArray = {Shape.I, Shape.J, Shape.L, Shape.O, Shape.S, Shape.Z, Shape.T};


	/** Enthaelt Zustand des Spiels bzgl. gameOver */
	public boolean gameOver = true;

	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}

		return instance;
	}

	/**
	 * Initialisiert das Spiel. Legt GameState sowie GameFrame an, fuellt und
	 * zeichnet die objectList mit saemtlichen Objekten, die zum Spielstart
	 * noetig sind.
	 */
	public void initiate() {

		this.gameState = new GameState();
		this.gameFrame = new GameFrame(gameState);
		
		while(gameState.nextList.size()<10){
			gameState.nextList.add(new Piece(shapeArray[(int)(Math.random()*shapeArray.length)],gameFrame));
		}
	}

	/** Startet Spiel zum ersten Mal. */
	public void startGame() {
		gameState.setGameState(true);
		gameState.setGameOver(false);
		this.gameOver = false;
		gameManagementThread.start();

	}

	/** Startet das Spiel neu. */
	public void restartGame() {
		//TODO
	}

	/**
	 * Pausiert das Spiel und ruft den GameOver-Frame auf und berechnet Punkte.
	 */
	public void endGame() {
		//TODO
	}

	/** Holt vordersten Spielstein aus Vektor, wenn moeglich */
	public synchronized void newPiece() {
		gameState.setCurrent(gameState.nextList.remove(0));
		while(gameState.nextList.size()<10){
			gameState.nextList.add(new Piece(shapeArray[(int)(Math.random()*shapeArray.length)],gameFrame));
		}
	}

	/** versucht Reihen ab Zeile y zu loeschen, indem er sie Schritt fuer Schritt ueberprueft */
	public void removeLinesIfPossible(int y) {
		
	}
	
	/** Loescht Zeile y, erhoeht Lines */
	public void removeOneLine(int y) {
		//TODO
	}

	/** ueberprueft ob Zeile y vollstaendig guefllt ist */
	public boolean checkLineOfCompleteness(int y) {
		//TODO
		return false;
	}

	public GameState getGameState() {
		return this.gameState;
	}

	public GameFrame getGameFrame() {
		return this.gameFrame;
	}

	/**
	 * 
	 * Bewegt in jedem Schleifendurchlauf die Objekte
	 *
	 */
	class MoveObjects extends Thread {

		public void run() {
			//TODO
		}
	}

	class GameManagementThread extends Thread {

		/**
		 * Die Run-Methode soll in jeder Iteration �berpr�fen, ob genug Reihen gel�scht
		 * wurden, um dann das Level zu erh�hen.
		 */
		public void run() {
			while(true){
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(gameState.getCurrent()==null){
					GameController.this.newPiece();
				}
			}
		}
	}

}