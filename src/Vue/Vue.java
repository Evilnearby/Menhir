package Vue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import Moteur.*;

/**
 * Classe représentant une vue général. Une vue récupère les évènnements levés
 * par le moteur du jeu. La manière de traiter ces évènnements dépendra des objets
 * héritant de cette classe.
 */
public abstract class Vue implements Observer {

	/** Référence sur une partie. */
	protected Partie partie;
	/** liste des joueurs de la partie. */
	protected ArrayList<Joueur> listJoueur = new ArrayList<Joueur>();
	/** Référence sur l'objet Geant. */
	protected Geant geant;
	/** Référence sur l'objet Engrais. */
	protected Engrais engrais;
	/** Référence surl'objet Farfadet. */
	protected Farfadet farfadet;

	/**
	 * Constructeur de la classe Vue. Il prend en paramètre une référence sur
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
	 * Implémentation de la méthode update. Cette méthode va gérer les
	 * évènnements provoqués par les objets observables.
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
	 * Méthode qui affichera des informations sur le joueur actif. L'affichage
	 * des informations est géré par les classe implémentent cette méthode.
	 */
	public abstract void jouerTour(Evenement event);

	/** Méthode qui affichera le vainqueur de la partie. */
	public abstract void finPartie(Evenement event);

	/** Méthode qui affichera la manche actuelle à chaque nouvelle manche. */
	public abstract void debutManche(Evenement event);

	/** Méthode qui affichera la liste des Joueurs. */
	public abstract void afficherListeJoueur(Evenement event);

	/** Méthode qui intéragira avec l'utilisateur pour choisir un ingrédient. */
	public abstract void choixIngredientJVirtuel(Evenement event);

	/**
	 * Méthode qui affichera le choix du joueur virtuel dans la liste des
	 * joueurs.
	 */
	public abstract void choixJoueurJVirtuel(Evenement event);

	/**
	 * Méthode qui affichera le choix du joueur virtuel concernant
	 * l'emplacement.
	 */
	public abstract void choixEmplacementJVirtuel(Evenement event);

	/**
	 * Méthode qui affichera lechoix du joueur virtuel entre les graines ou la
	 * arte allié.
	 */
	public abstract void choixDebutJVirtuel(Evenement event);

	/** Méthode affihant les carte du joueur physique. */
	public abstract void afficherIngredient(Evenement event);

	/** Méthode affichant la carte allié du joueur physique. */
	public abstract void afficherAllie(Evenement event);

	/** Méthode affichant le nombre de graines que donne le géant. */
	public abstract void actionGeant(Evenement event);

	/** Méthode affichant le nombre de menhir que donne l'engrais. */
	public abstract void actionEngrais(Evenement event);

	/** Méthode affichant le nombre de graine que vole le farfadet. */
	public abstract void actionFarfadet(Evenement event);

	/** Méthode affichant le nombre de menhir que détruit la taupe */
	public abstract void actionTaupe(Evenement event);

	/**
	 * Méthode gérant l'intéraction avec le joueur physique afin de choisir un
	 * joueur sur le quel effectuer une action.
	 */
	public abstract Joueur choisirJoueur(Joueur j);

	/**
	 * Méthode gérant l'intéraction avec le joueur physique afin de choisir un
	 * ingrédient partie ceux disponibles.
	 */
	public abstract Ingredient choisirCarte(Joueur joueur);

	/**
	 * Méthode gérant l'intéraction avec le joueur physique afin de choisir un
	 * emplacement pour jouer un ingrédient.
	 */
	public abstract Emplacement choisirEmplacement(Joueur joueur);

	/**
	 * Gère l'intéraction avec l'utilisateur afin de choisir entre les graines
	 * ou la carte allié en début de manche.
	 */
	public abstract void choixGraineOuAllie(Joueur joueur);

	/**
	 * Gère l'intéraction avec l'utilisateur afin de choisir en jouer un
	 * ingrédient ou jouer une carte allié.
	 */
	public abstract int choixAllieOuIngredient(Joueur joueur);

}
