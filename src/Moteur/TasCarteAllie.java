package Moteur;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Classe repr�sentant le tas de carte alli�. Elle est d�finie par une
 * collection de carte alli�. On choisit une collection de type Queue car on ne
 * peut prendre que la carte en haut du tas et non une carte au millieu du tas.
 */
public class TasCarteAllie {

	private LinkedList<Allie> tasCarteAllie = new LinkedList<Allie>();

	/**
	 * Constructeur du tas de carte Alli�. Les tas est cr�� une seule fois en
	 * d�but partie , c'est pourquoi on initialise le tas � la main ici.
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

	/** M�thode permettant de d�poser une carte dans le tas. */
	public void deposerAllie(Allie a) {
		this.tasCarteAllie.add(a);
	}

	/** M�thode permettant de piocher une carte dans le tas. */
	public Allie piocherCarteAllie() {
		return this.tasCarteAllie.poll();
	}

	/** M�thode permettant de m�langer le tas. */
	public void trierTas() {
		Collections.shuffle(this.tasCarteAllie);
	}
}
