package Moteur;

/**
 * Interface que devra implémenter les emplacements. Elle contient la méthode
 * réprésentant l'action d'un emplacement.
 */
public interface Emplacement {

	/** Méthode que devra implémenter Engrais, Géant et Farfadet. */
	public void actionEmplacement(Joueur joueur, Ingredient ingredient);
}
