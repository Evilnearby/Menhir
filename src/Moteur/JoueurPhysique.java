package Moteur;

/** Classe caractérisant un joueur physique. Elle hérite de la classe Joueur. */
public class JoueurPhysique extends Joueur {

	/**
	 * Constructeur de l'objet JoueurPhysique, il est associé à une partie que
	 * l'on passe en argument. On appel ensuite le constructeur de la classe
	 * mère Joueur.
	 */
	public JoueurPhysique(String nom, Partie p) {
		super(nom, p);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Méthode permettant de choisir un joueur sur lequel jouer une carte. On
	 * affiche d'abord la liste des joueurs avec la méthode
	 * afficherListeJoueur(). Puis on retourne le joueur ciblé dans la
	 * collection de l'objet Partie.
	 */
	public Joueur choisirJoueur() {
		return this.partie.getVue().choisirJoueur(this);
	}

	/**
	 * Méthode permettant de choisir une carte à jouer. On affiche d'abord la
	 * main du joueur, puis on retourne la carte correspondant dans la main du
	 * joueur.
	 */
	public Ingredient choisirCarte() {
		return this.partie.vue.choisirCarte(this);
	}

	/**
	 * Méthode levant un nouveau événement pour le choix d'un emplacement pour
	 * le joueur physique.
	 */
	public Emplacement choisirEmplacement() {
		return this.partie.vue.choisirEmplacement(this);
	}

	/**
	 * Méthode levant un nouveau événement pour le choix entre les graines ou
	 * une carte allié en début de manche.
	 */
	public void choixGraineOuAllie() {
		this.partie.vue.choixGraineOuAllie(this);
	}

	/**
	 * Méthode levant un nouveau événement pour le choix entre jouer une carte
	 * allié ou une carte ingrédient.
	 */
	public int choixAllieOuIngredient() {
		return this.partie.vue.choixAllieOuIngredient(this);
	}
}
