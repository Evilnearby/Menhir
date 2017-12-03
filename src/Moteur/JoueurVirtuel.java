package Moteur;

import java.util.Iterator;
import java.util.Random;

import Vue.Evenement;
import Vue.EventType;

/** Classe caract�risant un joueur virtuel. Elle h�rite de la classe Joueur. **/
public class JoueurVirtuel extends Joueur implements Strategy {

	/**
	 * Attribut permettant de stocker l'ingr�dient qu'a choisit le joueur
	 * virtuel.
	 */
	private Ingredient ingredientChoisit;

	/**
	 * Constructeur de la classe JoueurVirtuel. Il prend en arguement une
	 * r�f�rence d'un objet Partie et un String repr�sentant le nom du joueur.
	 */
	public JoueurVirtuel(String nom, Partie p) {
		super(nom, p);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * M�thode permettant de choisir un joueur � cibler pour jouer une carte. Le
	 * joueur virtuel va choisir un joueur dans la collection de joueur de
	 * l'objet Partie. Il choisit le joueur ayant le plus de graine. On retourne
	 * un jouer de la liste de joueur.
	 */
	public Joueur choisirJoueur() {
		Joueur joueurRetour = this.partie.getListeJoueur().get(0);
		for (Iterator<Joueur> it = this.partie.getListeJoueur().iterator(); it
				.hasNext();) {
			Joueur j = it.next();
			if (j.getNbrGraine() >= joueurRetour.getNbrGraine()) {
				joueurRetour = j;
			}
		}

		Evenement event = new Evenement(EventType.CHOIX_JOUEUR_JVIRTUEL);
		event.setJoueur(this);
		event.setJoueur2(joueurRetour);
		setChanged();
		notifyObservers(event);

		return joueurRetour;
	}

	/**
	 * M�thode permettant de choisir une carte � jouer. Le joueur virtuel va
	 * choisir al�atoirement une carte parmis la collection de carte qu'il
	 * poss�de.
	 */
	public Ingredient choisirCarte() {
		Random rnd = new Random();
		Evenement event = new Evenement(EventType.CHOIX_INGREDIENT_JVIRTUEL);
		event.setJoueur(this);
		setChanged();
		notifyObservers(event);
		int reponse = rnd.nextInt(this.getMain().size());
		this.afficherValeurIngredient(this.mainJoueur.get(reponse));
		this.ingredientChoisit = this.mainJoueur.get(reponse);
		return this.mainJoueur.get(reponse);
	}

	/**
	 * M�thode permettant de choisir un emplacement � jouer. Le joueur virtuel
	 * va choisir son emplacement � jouer en fonction de la carte qu'il a
	 * choisit avant. Il choisit l'emplacement qui donne la plus grande valeur �
	 * sa carte. On retourne une instance de l'emplacement jou� de l'objet
	 * Partie associ�e � l'objet JoueurVirtuel.
	 */
	public Emplacement choisirEmplacement() {
		Evenement event = new Evenement(EventType.CHOIX_EMPLACEMENT_JVIRTUEL);
		event.setJoueur(this);
		int valeur1 = this.ingredientChoisit.getValeur(this.partie,
				this.partie.getEngrais());
		int valeur2 = this.ingredientChoisit.getValeur(this.partie,
				this.partie.getGeant());
		int valeur3 = this.ingredientChoisit.getValeur(this.partie,
				this.partie.getFarfadet());

		if (valeur1 > valeur2 && valeur1 > valeur3) {
			event.setDescriptif("Engrais");
			setChanged();
			notifyObservers(event);
			return this.partie.getEngrais();
		} else if (valeur2 > valeur1 && valeur2 > valeur3) {
			event.setDescriptif("Geant");
			setChanged();
			notifyObservers(event);
			return this.partie.getGeant();
		} else {
			event.setDescriptif("Farfadet");
			setChanged();
			notifyObservers(event);
			return this.partie.getFarfadet();
		}
	}

	/**
	 * M�thode permettant de choisir entre les graines ou la carte Allie en
	 * d�but de manche. Le joueur virtuel fait son choix al�atoirement.
	 */
	public void choixGraineOuAllie() {
		Random rnd = new Random();
		Evenement event = new Evenement(EventType.CHOIX_DEBUT_JVIRTUEL);
		event.setJoueur(this);
		if (rnd.nextInt(2) == 0) {
			event.setDescriptif("Graine");
			setChanged();
			notifyObservers(event);
			this.nbrGraine = 2;
		} else {
			event.setDescriptif("Allie");
			setChanged();
			notifyObservers(event);
			this.nbrGraine = 0;
			this.carteAllie = this.partie.piocherCarteAllie();
		}
	}

	/**
	 * M�thode permettant de choisir entre jouer la carte Allie ou une carte
	 * ingr�dient. Le choix se fait al�atoirement.
	 */
	public int choixAllieOuIngredient() {
		Random rnd = new Random();
		return rnd.nextInt(2);
	}
}
