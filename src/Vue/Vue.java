package Vue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import Moteur.*;

/**
 * Classe repr�sentant une vue g�n�ral. Une vue r�cup�re les �v�nnements lev�s
 * par le moteur du jeu. La mani�re de traiter ces �v�nnements d�pendra des objets
 * h�ritant de cette classe.
 */
public abstract class Vue implements Observer {

	/** R�f�rence sur une partie. */
	protected Partie partie;
	/** liste des joueurs de la partie. */
	protected ArrayList<Joueur> listJoueur = new ArrayList<Joueur>();
	/** R�f�rence sur l'objet Geant. */
	protected Geant geant;
	/** R�f�rence sur l'objet Engrais. */
	protected Engrais engrais;
	/** R�f�rence surl'objet Farfadet. */
	protected Farfadet farfadet;

	/**
	 * Constructeur de la classe Vue. Il prend en param�tre une r�f�rence sur
	 * l'objet Partie.
	 */
	public Vue(Partie p) {
		this.partie = p;
		for (Iterator<Joueur> it = this.partie.getListeJoueur().iterator(); it
				.hasNext();) {
			Joueur j = it.next();
			this.listJoueur.add(j);
		}

		for (Iterator<Joueur> it = this.listJoueur.iterator(); it.hasNext();) {
			it.next().addObserver(this);
		}

		this.engrais = this.partie.getEngrais();
		this.geant = this.partie.getGeant();
		this.farfadet = this.partie.getFarfadet();

		this.engrais.addObserver(this);
		this.geant.addObserver(this);
		this.farfadet.addObserver(this);
		this.partie.addObserver(this);
	}

	/**
	 * Impl�mentation de la m�thode update. Cette m�thode va g�rer les
	 * �v�nnements provoqu�s par les objets observables.
	 */
	public void update(Observable o, Object arg) {
		Evenement event = null;
		if (arg instanceof Evenement) {
			event = (Evenement) arg;
			switch (event.getEvent()) {
			case JOUER_TOUR:
				this.jouerTour(event);
				break;
			case FIN_PARTIE:
				this.finPartie(event);
				break;
			case DEBUT_MANCHE:
				this.debutManche(event);
				break;
			case AFFICHAGE_LISTE_JOUEUR:
				this.afficherListeJoueur(event);
				break;
			case CHOIX_INGREDIENT_JVIRTUEL:
				this.choixIngredientJVirtuel(event);
				break;
			case CHOIX_JOUEUR_JVIRTUEL:
				this.choixJoueurJVirtuel(event);
				break;
			case CHOIX_EMPLACEMENT_JVIRTUEL:
				this.choixEmplacementJVirtuel(event);
				break;
			case CHOIX_DEBUT_JVIRTUEL:
				this.choixDebutJVirtuel(event);
				break;
			case AFFICHAGE_INGREDIENT:
				this.afficherIngredient(event);
				break;
			case AFFICHAGE_ALLIE:
				this.afficherAllie(event);
				break;
			case ACTION_TAUPE:
				this.actionTaupe(event);
				break;
			case ACTION_GEANT:
				this.actionGeant(event);
				break;
			case ACTION_FARFADET:
				this.actionFarfadet(event);
				break;
			case ACTION_ENGRAIS:
				this.actionEngrais(event);
				break;
			}
		}
	}

	/**
	 * M�thode qui affichera des informations sur le joueur actif. L'affichage
	 * des informations est g�r� par les classe impl�mentent cette m�thode.
	 */
	public abstract void jouerTour(Evenement event);

	/** M�thode qui affichera le vainqueur de la partie. */
	public abstract void finPartie(Evenement event);

	/** M�thode qui affichera la manche actuelle � chaque nouvelle manche. */
	public abstract void debutManche(Evenement event);

	/** M�thode qui affichera la liste des Joueurs. */
	public abstract void afficherListeJoueur(Evenement event);

	/** M�thode qui int�ragira avec l'utilisateur pour choisir un ingr�dient. */
	public abstract void choixIngredientJVirtuel(Evenement event);

	/**
	 * M�thode qui affichera le choix du joueur virtuel dans la liste des
	 * joueurs.
	 */
	public abstract void choixJoueurJVirtuel(Evenement event);

	/**
	 * M�thode qui affichera le choix du joueur virtuel concernant
	 * l'emplacement.
	 */
	public abstract void choixEmplacementJVirtuel(Evenement event);

	/**
	 * M�thode qui affichera lechoix du joueur virtuel entre les graines ou la
	 * arte alli�.
	 */
	public abstract void choixDebutJVirtuel(Evenement event);

	/** M�thode affihant les carte du joueur physique. */
	public abstract void afficherIngredient(Evenement event);

	/** M�thode affichant la carte alli� du joueur physique. */
	public abstract void afficherAllie(Evenement event);

	/** M�thode affichant le nombre de graines que donne le g�ant. */
	public abstract void actionGeant(Evenement event);

	/** M�thode affichant le nombre de menhir que donne l'engrais. */
	public abstract void actionEngrais(Evenement event);

	/** M�thode affichant le nombre de graine que vole le farfadet. */
	public abstract void actionFarfadet(Evenement event);

	/** M�thode affichant le nombre de menhir que d�truit la taupe */
	public abstract void actionTaupe(Evenement event);

	/**
	 * M�thode g�rant l'int�raction avec le joueur physique afin de choisir un
	 * joueur sur le quel effectuer une action.
	 */
	public abstract Joueur choisirJoueur(Joueur j);

	/**
	 * M�thode g�rant l'int�raction avec le joueur physique afin de choisir un
	 * ingr�dient partie ceux disponibles.
	 */
	public abstract Ingredient choisirCarte(Joueur joueur);

	/**
	 * M�thode g�rant l'int�raction avec le joueur physique afin de choisir un
	 * emplacement pour jouer un ingr�dient.
	 */
	public abstract Emplacement choisirEmplacement(Joueur joueur);

	/**
	 * G�re l'int�raction avec l'utilisateur afin de choisir entre les graines
	 * ou la carte alli� en d�but de manche.
	 */
	public abstract void choixGraineOuAllie(Joueur joueur);

	/**
	 * G�re l'int�raction avec l'utilisateur afin de choisir en jouer un
	 * ingr�dient ou jouer une carte alli�.
	 */
	public abstract int choixAllieOuIngredient(Joueur joueur);

}
