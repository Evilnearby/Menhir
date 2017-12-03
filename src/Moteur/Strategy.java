package Moteur;

/**
 * Interface du patron de concept Strategy. Le joueur virtuel impl�mentera ces
 * m�thodes celon une strat�gie particuli�re. Le joueur choisit al�atoirement
 * entre les graines et la carte alli� ainsi qu'entre jouer une carte alli� ou une
 * carte ingr�dient. Il choisit al�atoirement une carte puis choisi l'emplacement
 * qui lui donne la plus grande valeur � sa carte. Il choisit �galement le joueur
 * avec le plus de graine.
 */
public interface Strategy {

	public Ingredient choisirCarte();

	public Joueur choisirJoueur();

	public Emplacement choisirEmplacement();

	public int choixAllieOuIngredient();

	public void choixGraineOuAllie();
}
