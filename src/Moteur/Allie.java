package Moteur;

/**
 * Classe repr�sentant une carte Alli�. Elle est caract�ris�e par un tableau de
 * int simple repr�sentant les valeurs de la carte et des coordonn�s.
 */
public class Allie {
	/** Attribut rep�sentant les valeurs possibles d'une carte alli�. */
	private int[] valeur;
	/**
	 * Attribut repr�sentant la coordonn�e en coordonn� en ordonn�e, cette
	 * coordonn� sert � savoir o� couper l'image contenant les carte.
	 */
	private int x;
	/**
	 * Attribut repr�sentant la coordonn�e en coordonn� en abcisse, cette
	 * coordonn� sert � savoir o� couper l'image contenant les carte.
	 */
	private int y;

	/**
	 * Constructeur de la classe Allie. Il prend en argument un tableau de int
	 * simple ainsi qu'une coordon� en ordonn� et une en abcisse.
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
