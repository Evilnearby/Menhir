package Moteur;

/**
 * Interface que devra impl�menter les emplacements. Elle contient la m�thode
 * r�pr�sentant l'action d'un emplacement.
 */
public interface Emplacement {

	/** M�thode que devra impl�menter Engrais, G�ant et Farfadet. */
	public void actionEmplacement(Joueur joueur, Ingredient ingredient);
}
