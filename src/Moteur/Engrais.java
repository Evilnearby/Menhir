package Moteur;

import java.util.Observable;

import Vue.Evenement;
import Vue.EventType;

/** Classe caract�risant l'emplacement Engrais. Il impl�mente l'interface Emplacement. */
public class Engrais extends Observable implements Emplacement {

	/** R�f�rence sur un objet Partie. */
	private Partie partie;

	/** Constructeur de la classe Engrais. */
	public Engrais(Partie p) {
		this.partie = p;
	}

	/**
	 * M�thode faisant pousser les graines du joueur en menhir. Le nombre de
	 * graine transform�e en menhir d�pendant de la saison en cour. L'engrais
	 * correspond � la deuxi�me ligne des valeurs d'une carte ingr�dient.
	 */
	public void actionEmplacement(Joueur j, Ingredient e) {
		int valeur = e.getValeur(this.partie, this);
		int enStock = j.getNbrGraine();

		Evenement event = new Evenement(EventType.ACTION_ENGRAIS);
		if (enStock >= valeur) {
			event.setNbr(valeur);
			setChanged();
			notifyObservers(event);
			j.setNbrMenhir(j.getNbrMenhir() + valeur);
			j.setNbrGraine(enStock - valeur);
		} else {
			event.setNbr(enStock);
			setChanged();
			notifyObservers(event);
			j.setNbrMenhir(j.getNbrMenhir() + enStock);
			j.setNbrGraine(0);
		}
	}
}
