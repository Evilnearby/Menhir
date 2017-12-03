package Moteur;

/** Classe permettant de lancer une partie dans un thread différent. */
public class PartieThread extends Partie implements Runnable {

	/**
	 * Constructeur de la classe PartirThread. Cette classe permet de lancer une
	 * partie dans un autre thread que le thread principal afin de ne pas
	 * bloquer l'interface graphique
	 */
	public PartieThread(int typePartie, int nbrJoueur) {
		super(typePartie, nbrJoueur);
	}

	public void run() {
		this.lancerPartie();
	}

}
