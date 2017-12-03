package Moteur;

/**
 * Interface du patron de concept Strategy. Le joueur virtuel implémentera ces
 * méthodes celon une stratégie particulière. Le joueur choisit aléatoirement
 * entre les graines et la carte allié ainsi qu'entre jouer une carte allié ou une
 * carte ingrédient. Il choisit aléatoirement une carte puis choisi l'emplacement
 * qui lui donne la plus grande valeur à sa carte. Il choisit également le joueur
 * avec le plus de graine.
 */
public interface Strategy {

	public Ingredient choisirCarte();

	public Joueur choisirJoueur();

	public Emplacement choisirEmplacement();

	public int choixAllieOuIngredient();

	public void choixGraineOuAllie();
}
