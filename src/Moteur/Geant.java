package Moteur;

import java.util.Observable;

import Vue.Evenement;
import Vue.EventType;

/** Classe caractérisant l'emplacement Géant. Il implémente l'interface Emplacement. */
public class Geant extends Observable implements Emplacement {

	/** Référence à un objet Partie. */
	private Partie partie;

	/** Constructeur de la classe Geant. */
	public Geant(Partie p) {
		this.partie = p;
	}

	/**
	 * Méthode donnant les graines au joueur en fonction de la saison (obtenue
	 * grâce à la aprtie associée) et de l'emplcament Géant joué. Géant
	 * correspond à la premiers ligne des valeurs de la carte.
	 */
	public void actionEmplacement(Joueur j, Ingredient e) {
		Evenement event = new Evenement(EventType.ACTION_GEANT);
		event.setNbr(e.getValeur(this.partie, this));
		setChanged();
		notifyObservers(event);
		j.setNbrGraine(j.getNbrGraine() + e.getValeur(this.partie, this));
	}
}
