package Moteur;

/**
 * Classe représentant une carte Allié. Elle est caractérisée par un tableau de
 * int simple représentant les valeurs de la carte et des coordonnés.
 */
public class Allie {
	/** Attribut repésentant les valeurs possibles d'une carte allié. */
	private int[] valeur;
	/**
	 * Attribut représentant la coordonnée en coordonné en ordonnée, cette
	 * coordonné sert à savoir où couper l'image contenant les carte.
	 */
	private int x;
	/**
	 * Attribut représentant la coordonnée en coordonné en abcisse, cette
	 * coordonné sert à savoir où couper l'image contenant les carte.
	 */
	private int y;

	/**
	 * Constructeur de la classe Allie. Il prend en argument un tableau de int
	 * simple ainsi qu'une coordoné en ordonné et une en abcisse.
	 */
	public Allie(int[] val, int x, int y) {
		this.x = x;
		this.y = y;
		this.valeur = new int[4];
		if (val.length > 4) {
			System.out.println("Valeurs non valide pour une carte");
		} else {
			for (int i = 0; i < this.valeur.length; i++) {
				this.valeur[i] = val[i];
			}
		}
	}

	/** Retourne la valeur de la carte en fonction de la saison en cours. */
	public int getValeur(Partie p) {
		return this.valeur[p.getSaison()];
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int[] getValeurs() {
		return this.valeur;
	}
}
