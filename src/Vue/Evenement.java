package Vue;

import java.util.ArrayList;

import Moteur.Allie;
import Moteur.Ingredient;
import Moteur.Joueur;

/**
 * Classe repr�sentant un �v�nnement. Un �v�nnement sera lev� par un objet
 * observable du moteur du jeu. Tous les attributs de l'objet ne serons pas
 * utilis�s en m�me temps mais d�pendront de l'�v�nnement lev�.
 */
public class Evenement {

	/** R�f�rence � un joueur. */
	private Joueur joueur;
	/** R�f�rence � un joueur. */
	private Joueur joueur2;
	/** R�f�rence � une liste de joueur. */
	private ArrayList<Joueur> listeJoueur;
	/** R�f�rence � un ingr�dient. */
	private Ingredient ingredient;
	/** R�f�rence � un Allie. */
	private Allie allie;
	/** un int pouvant d�crire certains �v�nements. */
	private int nbr;
	/** Chaine de caract�res pouvant d�crire certains �v�nements. */
	private String descriptif;
	/**
	 * Type de l'�v�nement. Le type ne peut prendre que certaines valeurs dans
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
