package Moteur;

import java.util.ArrayList;
import java.util.Observable;

import Vue.Evenement;
import Vue.EventType;

/**
 * Classe caractérisant un joueur de manière général. Un joueur est caractérisé
 * par un nom, un nombre de graine, menhir et point. Elle est aussi associé à
 * une collection d'ingrédient, représentant sa main, et une carte allié. Un
 * joueur est également associé à une partie.
 */
public abstract class Joueur extends Observable {

	/** Chaine de caractère représentant le nom du joueur. */
	protected String nom;
	/** Attribut représentant le nombre de graine que possède le joueur. */
	protected int nbrGraine;
	/** Attribut représentant le nombre de menhir que possède le joueur. */
	protected int nbrMenhir;
	/** Attribut représentant le nombre de point que possède le joueur. */
	protected int nbrPoint;
	/** Référence sur un objet Partie. */
	protected Partie partie;
	/** Liste des ingrédient que possède le joueur. */
	protected ArrayList<Ingredient> mainJoueur = new ArrayList<Ingredient>();
	/** Carte allié associé au joueur. */
	protected Allie carteAllie;
	/** Attribut indiquant si le joueur à déjà effectué son action. */
	boolean action = false;

	/**
	 * Constructeur de la classe Joueur : on initalise les arguments et on
	 * associe le joueur à une partie.
	 */
	public Joueur(String nom, Partie partie) {
		this.nom = nom;
		this.nbrGraine = 0;
		this.nbrMenhir = 0;
		this.nbrPoint = 0;
		this.partie = partie;
	}

	public boolean getAction() {
		return this.action;
	}

	public void setAction(boolean a) {
		this.action = a;
	}

	public String getNom() {
		return this.nom;
	}

	public int getNbrGraine() {
		return this.nbrGraine;
	}

	public int getNbrMenhir() {
		return this.nbrMenhir;
	}

	public int getNbrPoint() {
		return this.nbrPoint;
	}

	public ArrayList<Ingredient> getMainJoueur() {
		return this.mainJoueur;
	}

	public void setAllie(Allie a) {
		this.carteAllie = a;
	}

	public void setNbrGraine(int nbrGraine) {
		this.nbrGraine = nbrGraine;
	}

	public void setNbrMenhir(int nbrMenhir) {
		this.nbrMenhir = nbrMenhir;
	}

	public Allie getAllie() {
		return this.carteAllie;
	}

	/**
	 * Méthode comptant le nombre de point du joueur. Le nombre de menhir est
	 * ajouté au nonmbre de point puis est réinitialisé à 0.
	 */
	public void compterNbrPoint() {
		this.nbrPoint += this.nbrMenhir;
		this.nbrMenhir = 0;
	}

	public void setCarte(Ingredient c) {
		this.mainJoueur.add(c);
	}

	public ArrayList<Ingredient> getMain() {
		return this.mainJoueur;
	}

	/**
	 * Méthode permettant à un joueur (physique ou virtuel) de jouer une carte
	 * sur un emplacement. Les méthodes choisirCarte() et choisirEmplacement()
	 * permettent de définir quelle carte et quel emplacement seront joués. Une
	 * fois la carte jouée, on la dépose dans le tas de carte.
	 */
	public void jouerIngredient() {
		Ingredient carteAJouer = this.choisirCarte();
		Emplacement emplaJouer = this.choisirEmplacement();
		emplaJouer.actionEmplacement(this, carteAJouer);
		partie.deposerIngredient(carteAJouer);
		this.mainJoueur.remove(this.mainJoueur.indexOf(carteAJouer));
	}

	/**
	 * Méthode permettant de jouer une carte Allie. Seul les cartes Taupe
	 * peuvent être jouée. Une fois jouée, on dépose la carte dans le tas de
	 * carte. On retire bien la carte Allie de l'emplacement de la carte Allie
	 * du joueur.
	 */
	public void jouerAllie() {
		Evenement event = new Evenement(EventType.ACTION_TAUPE);
		event.setNbr(((Taupe) this.getAllie())
				.detruireMenhir(this, this.partie));
		setChanged();
		notifyObservers(event);
		partie.deposerAllie(this.carteAllie);
		this.carteAllie = null;
	}

	public void afficherValeurIngredient(Ingredient ingredient) {
		Evenement event = new Evenement(EventType.AFFICHAGE_INGREDIENT);
		event.setJoueur(this);
		event.setIngredient(ingredient);
		setChanged();
		notifyObservers(event);
	}

	public void afficherValeurAllie(Allie a) {
		Evenement event = new Evenement(EventType.AFFICHAGE_ALLIE);
		event.setJoueur(this);
		event.setAllie(a);
		setChanged();
		notifyObservers(event);
	}

	// Méthodes abtraites que les objets JoueurPhysique et JoueurVirtuel devront
	// implémenter.

	/**
	 * Méthode intéragissant avec le joueur physique pour choisir une carte à
	 * jouer. L'implémentation de la méthode se fera dans les classes filles.
	 */
	public abstract Ingredient choisirCarte();

	/**
	 * Méthode intéragissant avec le joueur physique pour choisir une
	 * emplacement à jouer. L'implémentation de la méthode se fera dans les
	 * classes filles.
	 */
	public abstract Emplacement choisirEmplacement();

	/**
	 * Méthode intéragissant avec le joueur physique pour choisir un joueur.
	 * L'implémentation de la méthode se fera dans les classes filles.
	 */
	public abstract Joueur choisirJoueur();

	/**
	 * Méthode intéragissant avec le joueur physique pour choisir entre les
	 * graines ou une carte allié. L'implémentation de la méthode se fera dans
	 * les classes filles.
	 */
	public abstract void choixGraineOuAllie();

	/**
	 * Méthode intéragissant avec le joueur physique pour choisir entre jouer
	 * une carte allié ou un ingrédient. L'implémentation de la méthode se fera
	 * dans les classes filles.
	 */
	public abstract int choixAllieOuIngredient();
}
