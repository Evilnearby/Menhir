package Moteur;

/** Classe caract�risant un joueur physique. Elle h�rite de la classe Joueur. */
public class JoueurPhysique extends Joueur {

	/**
	 * Constructeur de l'objet JoueurPhysique, il est associ� � une partie que
	 * l'on passe en argument. On appel ensuite le constructeur de la classe
	 * m�re Joueur.
	 */
	public JoueurPhysique(String nom, Partie p) {
		super(nom, p);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * M�thode permettant de choisir un joueur sur lequel jouer une carte. On
	 * affiche d'abord la liste des joueurs avec la m�thode
	 * afficherListeJoueur(). Puis on retourne le joueur cibl� dans la
	 * collection de l'objet Partie.
	 */
	public Joueur choisirJoueur() {
		return this.partie.getVue().choisirJoueur(this);
	}

	/**
	 * M�thode permettant de choisir une carte � jouer. On affiche d'abord la
	 * main du joueur, puis on retourne la carte correspondant dans la main du
	 * joueur.
	 */
	public Ingredient choisirCarte() {
		return this.partie.vue.choisirCarte(this);
	}

	/**
	 * M�thode levant un nouveau �v�nement pour le choix d'un emplacement pour
	 * le joueur physique.
	 */
	public Emplacement choisirEmplacement() {
		return this.partie.vue.choisirEmplacement(this);
	}

	/**
	 * M�thode levant un nouveau �v�nement pour le choix entre les graines ou
	 * une carte alli� en d�but de manche.
	 */
	public void choixGraineOuAllie() {
		this.partie.vue.choixGraineOuAllie(this);
	}

	/**
	 * M�thode levant un nouveau �v�nement pour le choix entre jouer une carte
	 * alli� ou une carte ingr�dient.
	 */
	public int choixAllieOuIngredient() {
		return this.partie.vue.choixAllieOuIngredient(this);
	}
}
