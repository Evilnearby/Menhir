package Moteur;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Classe représentant le tas de carte ingrédient. Elle est définie par une
 * collection de carte ingrédient. On choisit une collection de type Queue car
 * on ne peut prendre que la carte en haut du tas et non une carte au millieu du
 * tas.
 */
public class TasDeCarte {

	/**
	 * Liste des cartes qi font parties du tas. On prend une collection
	 * linkedList car le tas de carte se comporte comme une file FIFO.
	 */
	private LinkedList<Ingredient> tasDeCarte = new LinkedList<Ingredient>();

	/**
	 * Constructeur du tas de carte d'ingrédient. Les tas est créé une seule
	 * fois en début partie , c'est pourquoi on initialise le tas à la main ici.
	 */
	public TasDeCarte() {

		this.tasDeCarte.add(new Ingredient(new int[][] { { 1, 1, 1, 1 },
				{ 2, 0, 1, 1 }, { 2, 0, 2, 0 } }, 0, 0));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 2, 0, 1, 1 },
				{ 1, 3, 0, 0 }, { 0, 1, 2, 1 } }, 1, 0));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 0, 0, 4, 0 },
				{ 0, 2, 2, 0 }, { 0, 0, 1, 3 } }, 2, 0));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 1, 3, 1, 0 },
				{ 1, 2, 1, 1 }, { 0, 1, 4, 0 } }, 0, 1));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 2, 1, 1, 1 },
				{ 1, 0, 2, 2 }, { 3, 0, 0, 2 } }, 1, 1));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 1, 2, 2, 0 },
				{ 1, 1, 2, 1 }, { 2, 0, 1, 2 } }, 2, 1));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 2, 1, 1, 2 },
				{ 1, 1, 1, 3 }, { 2, 0, 2, 2 } }, 0, 2));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 0, 3, 0, 3 },
				{ 2, 1, 3, 0 }, { 1, 1, 3, 1 } }, 1, 2));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 1, 2, 1, 2 },
				{ 1, 0, 1, 4 }, { 2, 4, 0, 0 } }, 2, 2));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 1, 3, 1, 2 },
				{ 2, 1, 2, 2 }, { 0, 0, 3, 4 } }, 0, 3));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 2, 2, 0, 3 },
				{ 1, 1, 4, 1 }, { 1, 2, 1, 3 } }, 1, 3));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 2, 2, 3, 1 },
				{ 2, 3, 0, 3 }, { 1, 1, 3, 3 } }, 2, 3));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 2, 2, 3, 1 },
				{ 2, 3, 0, 3 }, { 1, 1, 3, 3 } }, 0, 4));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 2, 2, 2, 2 },
				{ 0, 4, 4, 0 }, { 1, 3, 2, 2 } }, 1, 4));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 3, 1, 3, 1 },
				{ 1, 4, 2, 1 }, { 2, 4, 1, 1 } }, 2, 4));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 4, 1, 1, 1 },
				{ 1, 2, 1, 3 }, { 1, 2, 2, 2 } }, 0, 5));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 2, 3, 2, 0 },
				{ 0, 4, 3, 0 }, { 2, 1, 1, 3 } }, 1, 5));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 2, 2, 3, 0 },
				{ 1, 1, 1, 4 }, { 2, 0, 3, 2 } }, 2, 5));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 3, 1, 4, 1 },
				{ 2, 1, 3, 3 }, { 2, 3, 2, 2 } }, 0, 6));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 2, 4, 1, 2 },
				{ 2, 2, 2, 3 }, { 1, 4, 3, 1 } }, 1, 6));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 3, 3, 3, 0 },
				{ 1, 3, 3, 2 }, { 2, 3, 1, 3 } }, 2, 6));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 1, 2, 2, 1 },
				{ 1, 2, 3, 0 }, { 0, 2, 2, 2 } }, 0, 7));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 4, 0, 1, 1 },
				{ 1, 1, 3, 1 }, { 0, 0, 3, 3 } }, 1, 7));
		this.tasDeCarte.add(new Ingredient(new int[][] { { 2, 0, 1, 3 },
				{ 0, 3, 0, 3 }, { 1, 2, 2, 1 } }, 2, 7));
	}

	public LinkedList<Ingredient> getTas() {
		return this.tasDeCarte;
	}

	/** Méthode permettant de piocher une carte dans le tas. */
	public Ingredient piocherCarte() {
		return this.tasDeCarte.poll();
	}

	/** Méthode permettant de déposer une carte dans le tas. */
	public void deposerCarte(Ingredient e) {
		this.tasDeCarte.add(e);
	}

	/** Méthode permettant de mélanger le tas. */
	public void trierTas() {
		Collections.shuffle(this.tasDeCarte);
	}
}
