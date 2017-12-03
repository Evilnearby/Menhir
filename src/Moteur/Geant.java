package Moteur;

import java.util.Observable;

import Vue.Evenement;
import Vue.EventType;

/** Classe caract�risant l'emplacement G�ant. Il impl�mente l'interface Emplacement. */
public class Geant extends Observable implements Emplacement {

	/** R�f�rence � un objet Partie. */
	private Partie partie;

	/** Constructeur de la classe Geant. */
	public Geant(Partie p) {
		this.partie = p;
	}

	/**
	 * M�thode donnant les graines au joueur en fonction de la saison (obtenue
	 * gr�ce � la aprtie associ�e) et de l'emplcament G�ant jou�. G�ant
	 * correspond � la premiers ligne des valeurs de la carte.
	 */
	public void actionEmplacement(Joueur j, Ingredient e) {
		Evenement event = new Evenement(EventType.ACTION_GEANT);
		event.setNbr(e.getValeur(this.partie, this));
		setChanged();
		notifyObservers(event);
		j.setNbrGraine(j.getNbrGraine() + e.getValeur(this.partie, this));
	}
}
