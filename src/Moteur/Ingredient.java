package Moteur;

/**
 * Classe repr�sentant une carte Ingr�dient. Elle est caract�ris�e par un tableau de
 * int � double entr�s et des coordonn�s.
 */
public class Ingredient {

	/**
	 * Valeurs de la carte ingr�dient. Elles sont repr�sent�e dans un tableau de
	 * int � double entr�s.
	 */
	private int[][] valeur;
	/**
	 * Coordonn�e en abcisse de la carte pour la retrouver via l'image contenant
	 * toutes les autres cartes.
	 */
	private int x;
	/**
	 * Coordonn�e en ordonn�e de la carte pour la retrouver via l'image
	 * contenant toutes les autres cartes.
	 */
	private int y;

	/**
	 * Constructeur de la classe Ingredient, il prend en argument un tableau de
	 * int a double entr�es.
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
	 * M�thode permettent de r�cup�rer la valeur de la carte � une saison et
	 * pour un emplacement donn�. La saison est donn�e par le l'argument saison
	 * de l'objet Partie. Le type l'emplacement pass� en argument d�termine
	 * qu'elle ligne de la carte va �tre prise en compte.
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
