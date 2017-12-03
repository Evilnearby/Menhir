package Moteur;

/**
 * Classe représentant une carte Taupe. Elle est caractérisée par un tableau de
 * int simple.
 */
public class Taupe extends Allie {

	/**
	 * Constructeur de la classe Allie. Il prend en argument de un tableau de
	 * int simple et dex coordonnés.
	 */
	public Taupe(int[] val, int x, int y) {
		super(val, x, y);
	}

	/**
	 * Méthode permettant de détruire les menhirs d'un joueur adverse. On
	 * choisit d'abord un joueur adverse, puis on détruit un certain nombre de
	 * menhir dépendant de la saison en cour. On remet ensuite la carte Taupe
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
