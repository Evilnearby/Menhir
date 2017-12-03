package Vue;

import java.util.ArrayList;

import Moteur.Allie;
import Moteur.Ingredient;
import Moteur.Joueur;

/**
 * Classe représentant un évènnement. Un évènnement sera levé par un objet
 * observable du moteur du jeu. Tous les attributs de l'objet ne serons pas
 * utilisés en même temps mais dépendront de l'évènnement levé.
 */
public class Evenement {

	/** Référence à un joueur. */
	private Joueur joueur;
	/** Référence à un joueur. */
	private Joueur joueur2;
	/** Référence à une liste de joueur. */
	private ArrayList<Joueur> listeJoueur;
	/** Référence à un ingrédient. */
	private Ingredient ingredient;
	/** Référence à un Allie. */
	private Allie allie;
	/** un int pouvant décrire certains événements. */
	private int nbr;
	/** Chaine de caractères pouvant décrire certains événements. */
	private String descriptif;
	/**
	 * Type de l'événement. Le type ne peut prendre que certaines valeurs dans
	 * le enum EventType.
	 */
	private EventType eventType;

	/** Constructeur de la classe Evenement. Il prend en arguement un EventType */
	public Evenement(EventType eventType) {
		this.eventType = eventType;
	}

	public EventType getEvent() {
		return this.eventType;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

	public Joueur getJoueur2() {
		return this.joueur2;
	}

	public Joueur getJoueur() {
		return this.joueur;
	}

	public int getNbr() {
		return this.nbr;
	}

	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

	public void setDescriptif(String d) {
		this.descriptif = d;
	}

	public String getDescriptif() {
		return this.descriptif;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Ingredient getIngredient() {
		return this.ingredient;
	}

	public void setAllie(Allie a) {
		this.allie = a;
	}

	public Allie getAllie() {
		return this.allie;
	}

	public void setListeJoueur(ArrayList<Joueur> liste) {
		this.listeJoueur = liste;
	}

	public ArrayList<Joueur> getListeJoueur() {
		return this.listeJoueur;
	}
}
