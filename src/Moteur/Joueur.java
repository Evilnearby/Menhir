package Moteur;

import java.util.ArrayList;
import java.util.Observable;

import Vue.Evenement;
import Vue.EventType;

/**
 * Classe caract�risant un joueur de mani�re g�n�ral. Un joueur est caract�ris�
 * par un nom, un nombre de graine, menhir et point. Elle est aussi associ� �
 * une collection d'ingr�dient, repr�sentant sa main, et une carte alli�. Un
 * joueur est �galement associ� � une partie.
 */
public abstract class Joueur extends Observable {

	/** Chaine de caract�re repr�sentant le nom du joueur. */
	protected String nom;
	/** Attribut repr�sentant le nombre de graine que poss�de le joueur. */
	protected int nbrGraine;
	/** Attribut repr�sentant le nombre de menhir que poss�de le joueur. */
	protected int nbrMenhir;
	/** Attribut repr�sentant le nombre de point que poss�de le joueur. */
	protected int nbrPoint;
	/** R�f�rence sur un objet Partie. */
	protected Partie partie;
	/** Liste des ingr�dient que poss�de le joueur. */
	protected ArrayList<Ingredient> mainJoueur = new ArrayList<Ingredient>();
	/** Carte alli� associ� au joueur. */
	protected Allie carteAllie;
	/** Attribut indiquant si le joueur � d�j� effectu� son action. */
	boolean action = false;

	/**
	 * Constructeur de la classe Joueur : on initalise les arguments et on
	 * associe le joueur � une partie.
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
	 * M�thode comptant le nombre de point du joueur. Le nombre de menhir est
	 * ajout� au nonmbre de point puis est r�initialis� � 0.
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
	 * M�thode permettant � un joueur (physique ou virtuel) de jouer une carte
	 * sur un emplacement. Les m�thodes choisirCarte() et choisirEmplacement()
	 * permettent de d�finir quelle carte et quel emplacement seront jou�s. Une
	 * fois la carte jou�e, on la d�pose dans le tas de carte.
	 */
	public void jouerIngredient() {
		Ingredient carteAJouer = this.choisirCarte();
		Emplacement emplaJouer = this.choisirEmplacement();
		emplaJouer.actionEmplacement(this, carteAJouer);
		partie.deposerIngredient(carteAJouer);
		this.mainJoueur.remove(this.mainJoueur.indexOf(carteAJouer));
	}

	/**
	 * M�thode permettant de jouer une carte Allie. Seul les cartes Taupe
	 * peuvent �tre jou�e. Une fois jou�e, on d�pose la carte dans le tas de
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

	// M�thodes abtraites que les objets JoueurPhysique et JoueurVirtuel devront
	// impl�menter.

	/**
	 * M�thode int�ragissant avec le joueur physique pour choisir une carte �
	 * jouer. L'impl�mentation de la m�thode se fera dans les classes filles.
	 */
	public abstract Ingredient choisirCarte();

	/**
	 * M�thode int�ragissant avec le joueur physique pour choisir une
	 * emplacement � jouer. L'impl�mentation de la m�thode se fera dans les
	 * classes filles.
	 */
	public abstract Emplacement choisirEmplacement();

	/**
	 * M�thode int�ragissant avec le joueur physique pour choisir un joueur.
	 * L'impl�mentation de la m�thode se fera dans les classes filles.
	 */
	public abstract Joueur choisirJoueur();

	/**
	 * M�thode int�ragissant avec le joueur physique pour choisir entre les
	 * graines ou une carte alli�. L'impl�mentation de la m�thode se fera dans
	 * les classes filles.
	 */
	public abstract void choixGraineOuAllie();

	/**
	 * M�thode int�ragissant avec le joueur physique pour choisir entre jouer
	 * une carte alli� ou un ingr�dient. L'impl�mentation de la m�thode se fera
	 * dans les classes filles.
	 */
	public abstract int choixAllieOuIngredient();
}
