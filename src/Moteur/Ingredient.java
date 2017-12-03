package Moteur;

/**
 * Classe représentant une carte Ingrédient. Elle est caractérisée par un tableau de
 * int à double entrés et des coordonnés.
 */
public class Ingredient {

	/**
	 * Valeurs de la carte ingrédient. Elles sont représentée dans un tableau de
	 * int à double entrés.
	 */
	private int[][] valeur;
	/**
	 * Coordonnée en abcisse de la carte pour la retrouver via l'image contenant
	 * toutes les autres cartes.
	 */
	private int x;
	/**
	 * Coordonnée en ordonnée de la carte pour la retrouver via l'image
	 * contenant toutes les autres cartes.
	 */
	private int y;

	/**
	 * Constructeur de la classe Ingredient, il prend en argument un tableau de
	 * int a double entrées.
	 */
	public Ingredient(int[][] val, int x, int y) {
		if (val.length != 3 || val[0].length != 4) {
			System.out.println("Valeurs non valide pour une carte");
		}
		this.valeur = val.clone();
		this.x = x;
		this.y = y;
	}

	public int[][] getValeurs() {
		return this.valeur;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	/**
	 * Méthode permettent de récupérer la valeur de la carte à une saison et
	 * pour un emplacement donné. La saison est donnée par le l'argument saison
	 * de l'objet Partie. Le type l'emplacement passé en argument détermine
	 * qu'elle ligne de la carte va être prise en compte.
	 */
	public int getValeur(Partie p, Emplacement e) {
		int i = p.getSaison();
		int j = 0;

		if (e instanceof Geant) {
			j = 0;
		} else if (e instanceof Engrais) {
			j = 1;
		} else {
			j = 2;
		}

		return this.valeur[j][i];
	}
}
