package Moteur;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Classe représentant le tas de carte allié. Elle est définie par une
 * collection de carte allié. On choisit une collection de type Queue car on ne
 * peut prendre que la carte en haut du tas et non une carte au millieu du tas.
 */
public class TasCarteAllie {

	private LinkedList<Allie> tasCarteAllie = new LinkedList<Allie>();

	/**
	 * Constructeur du tas de carte Allié. Les tas est créé une seule fois en
	 * début partie , c'est pourquoi on initialise le tas à la main ici.
	 */
	public TasCarteAllie() {
		this.tasCarteAllie.add(new Taupe(new int[] { 1, 1, 1, 1 }, 0, 0));
		this.tasCarteAllie.add(new Taupe(new int[] { 0, 2, 2, 0 }, 1, 0));
		this.tasCarteAllie.add(new Taupe(new int[] { 0, 1, 2, 1 }, 2, 0));
		this.tasCarteAllie
				.add(new ChienDeGarde(new int[] { 2, 0, 2, 0 }, 0, 1));
		this.tasCarteAllie
				.add(new ChienDeGarde(new int[] { 1, 2, 0, 1 }, 1, 1));
		this.tasCarteAllie
				.add(new ChienDeGarde(new int[] { 0, 1, 3, 0 }, 2, 1));
	}

	public LinkedList<Allie> getTas() {
		return this.tasCarteAllie;
	}

	/** Méthode permettant de déposer une carte dans le tas. */
	public void deposerAllie(Allie a) {
		this.tasCarteAllie.add(a);
	}

	/** Méthode permettant de piocher une carte dans le tas. */
	public Allie piocherCarteAllie() {
		return this.tasCarteAllie.poll();
	}

	/** Méthode permettant de mélanger le tas. */
	public void trierTas() {
		Collections.shuffle(this.tasCarteAllie);
	}
}
