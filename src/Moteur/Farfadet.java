package Moteur;

import java.util.Observable;

import Vue.Evenement;
import Vue.EventType;

/** Classe caract�risant l'emplacement Farfadet. Il impl�mente l'interface Emplacement. */
public class Farfadet extends Observable implements Emplacement {

	/** Rf�rence � un objet Partie. */
	private Partie partie;

	/** Constructeur de la classe Farfadet. */
	public Farfadet(Partie p) {
		this.partie = p;
	}

	/**
	 * M�thode permattant de voler les graines d'un joueur choisit. On choisit
	 * le joueur cibl�, ensuite on lui vole les graines correpondant � la valeur
	 * de la carte. Si le joueur cibl� a un chien de garde, on soustrait la
	 * valeur de la carte par la valeur de la carte chien de garde. On veille �
	 * ne pas mettre un nombre de graine n�gatif au joueur.
	 */
	public void actionEmplacement(Joueur j, Ingredient e) {
		Joueur adversaire = j.choisirJoueur();
		int valeur = e.getValeur(this.partie, this);
		int stockAdverse = adversaire.getNbrGraine();

		Evenement event = new Evenement(EventType.ACTION_FARFADET);
		if (adversaire.getAllie() instanceof ChienDeGarde) {
			int valeurChienDeGarde = adversaire.getAllie().getValeur(
					this.partie);
			if ((valeur - valeurChienDeGarde) >= stockAdverse) {

				event.setNbr(stockAdverse);
				setChanged();
				notifyObservers(event);
				j.setNbrGraine(j.getNbrGraine() + adversaire.getNbrGraine());
				adversaire.setNbrGraine(0);
			} else if ((valeur - valeurChienDeGarde) < 0) {

				event.setNbr(0);
				setChanged();
				notifyObservers(event);
			} else {

				event.setNbr((valeur - valeurChienDeGarde));
				setChanged();
				notifyObservers(event);
				j.setNbrGraine(j.getNbrGraine() + (valeur - valeurChienDeGarde));
				adversaire.setNbrGraine(adversaire.getNbrGraine()
						- (valeur - valeurChienDeGarde));
			}
			this.partie.deposerAllie(adversaire.getAllie());
			adversaire.setAllie(null);
		} else {
			if (valeur >= stockAdverse) {

				event.setNbr(stockAdverse);
				setChanged();
				notifyObservers(event);
				j.setNbrGraine(j.getNbrGraine() + adversaire.getNbrGraine());
				adversaire.setNbrGraine(0);
			} else {

				event.setNbr(valeur);
				setChanged();
				notifyObservers(event);
				j.setNbrGraine(j.getNbrGraine() + valeur);
				adversaire.setNbrGraine(adversaire.getNbrGraine() - valeur);
			}
		}
	}
}
