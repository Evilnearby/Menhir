package Moteur;

/**
 * Classe repr�sentant une carte Taupe. Elle est caract�ris�e par un tableau de
 * int simple.
 */
public class Taupe extends Allie {

	/**
	 * Constructeur de la classe Allie. Il prend en argument de un tableau de
	 * int simple et dex coordonn�s.
	 */
	public Taupe(int[] val, int x, int y) {
		super(val, x, y);
	}

	/**
	 * M�thode permettant de d�truire les menhirs d'un joueur adverse. On
	 * choisit d'abord un joueur adverse, puis on d�truit un certain nombre de
	 * menhir d�pendant de la saison en cour. On remet ensuite la carte Taupe
	 * dans le tas de carte Allie.
	 */
	public int detruireMenhir(Joueur j, Partie p) {
		Joueur adversaire = j.choisirJoueur();
		if (this.getValeur(p) > adversaire.getNbrMenhir()) {
			adversaire.setNbrMenhir(0);
			return adversaire.getNbrMenhir();
		} else {
			return this.getValeur(p);
		}
	}
}
