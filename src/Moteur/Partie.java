package Moteur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;
import java.util.Scanner;

import Vue.Evenement;
import Vue.EventType;
import Vue.Vue;
import Vue.VueConsole;
import Vue.VueGraphique;
import Widget.FenetreSaisie;

/**
 * Classe caract�risant une partie. Elle est caract�ris�e par une saison, un
 * type (rapide ou complexe), une liste de joueur et un nombre de joueur. Elle
 * est assici� � un tas de carte ingr�dient et un tas de carte alli�.
 */
public class Partie extends Observable {

	// Constantes d�finissant les saison possibles;
	/** Attribut d�finissant la saison "printemps". */
	public final static int PRINTEMPS = 0;
	/** Attribut d�finissant la saison "�t�". */
	public final static int ETE = 1;
	/** Attribut d�finissant la saison "automne". */
	public final static int AUTOMNE = 2;
	/** Attribut d�finissant la saison "hivers". */
	public final static int HIVER = 3;
	/** Collection repr�sentant la liste des joueurs de la partie. */
	private ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
	// Constantes repr�sentant le type de la partie possible
	/** Attribut d�finissant le type de partie "rapide". */
	public static final int TYPE_RAPIDE = 0;
	/** Attribut d�finissant le type de partie "complexe". */
	public static final int TYPE_COMPLEXE = 1;
	/**
	 * Attribut d�finissant le type de la partie. Il vaut 0 pour une partie
	 * rapide et 1 pour une partie complexe
	 */
	private int typePartie;
	/**
	 * Attribut d�finissant la saison en cour. Il vaut 0 � 3 pour respecgivement
	 * les saison printemps, �t�, automne et hivers.
	 */
	private int saison;
	/** Attribut d�finissant le tas de carte Allie. */
	private TasCarteAllie tasCarteAllie;
	/** Attribut d�finissant le tas de carte ingredient. */
	private TasDeCarte tasDeCarte;
	// Les instances des emplacements associ�s � la partie.
	/**
	 * Attribut repr�sentant le nombre de joueur virtuel de la partie. Ne peut
	 * pas �tre sup�rieur � 5.
	 */
	private int nbrJoueur;
	/** R�f�rence sur l'objet Geant. */
	public final Geant geant;
	/** R�f�rence sur l'objet Farfadet. */
	public final Farfadet farfadet;
	/** R�f�rence sur l'objet Engrais. */
	public final Engrais engrais;

	/**
	 * R�f�rence sur l'objet Vue qu'elle soit une VueConsole ou une
	 * VueGraphique.
	 */
	Vue vue;

	/**
	 * Constructeur de la classe Partie. Elle prend en param�tre un type de
	 * partie (0/1) et un nombre de joueurs virtuels (<5).
	 */
	public Partie(int typePartie, int nbrJoueur) {
		// On initialise la saison � printemps
		this.saison = Partie.PRINTEMPS;
		// On cr�er les instances de Geant, Engrai et Farfadet
		this.geant = new Geant(this);
		this.engrais = new Engrais(this);
		this.farfadet = new Farfadet(this);
		this.typePartie = typePartie;
		this.nbrJoueur = nbrJoueur;

		// On cr�er le tas de carte ingr�dient et le tas de carte alli�s
		// associ�s � la partie
		this.tasDeCarte = new TasDeCarte();
		this.tasCarteAllie = new TasCarteAllie();
	}

	public static void main(String[] args) {
		try {
			if (Integer.parseInt(args[0]) == 0) {
				int type;
				Scanner sc = new Scanner(System.in);
				int reponse = -1;
				String tmpReponse = null;
				while (reponse != 0 && reponse != 1) {
					System.out
							.println("Quel type de partie voulez-vous lancer? 0 = basique, 1 = complexe");
					tmpReponse = sc.nextLine();
					// On v�rifie l'entr� utilisateur
					if (tmpReponse.isEmpty()) {
						reponse = 10;
					} else {
						tmpReponse = tmpReponse.replaceAll("[^\\d]", "10");
						reponse = Integer.parseInt(tmpReponse);
					}
				}
				if (reponse == 0) {
					type = 0;
				} else {
					type = 1;
				}
				int reponse2 = -1;
				String tmpReponse2 = null;
				while (reponse2 < 1 || reponse2 > 5) {
					System.out
							.println("Combien de joueur voulez-vous? :  (5 maxi)");
					tmpReponse2 = sc.nextLine();
					// On v�rifie l'entr� utilisateur
					if (tmpReponse2.isEmpty()) {
						reponse2 = 10;
					} else {
						tmpReponse2 = tmpReponse2.replaceAll("[^\\d]", "10");
						reponse2 = Integer.parseInt(tmpReponse2);
					}
				}

				Partie partie = new Partie(type, reponse2);

				// Scanner sc = new Scanner(System.in);
				System.out.println("Entrez votre nom : ");
				String nomJoueur = sc.nextLine();
				partie.ajoutJoueurPhysique(nomJoueur);
				int i = 0;
				while (i != partie.getNbrJoueur()) {
					partie.ajoutJoueurVirtuel("joueur" + (i + 1));
					i++;
				}

				VueConsole vueConsole = new VueConsole(partie);
				partie.setVue(vueConsole);

				partie.lancerPartie();
			} else {
				FenetreSaisie nomJoueur = new FenetreSaisie("Entrez votre nom");
				boolean breaker = true;
				while (breaker) {
					nomJoueur.execute();
					if (nomJoueur.getValeur().isEmpty()) {
						continue;
					} else {
						breaker = false;
					}
				}

				FenetreSaisie nbrJoueur = new FenetreSaisie(
						"Combien de joueur virtuel voulez-vous?");
				int nbrJ = 10;
				while (nbrJ < 1 || nbrJ > 5) {
					nbrJoueur.execute();

					if (nbrJoueur.getValeur().isEmpty()) {
						nbrJ = 10;
					} else {
						nbrJ = Integer.parseInt(nbrJoueur.getValeur()
								.replaceAll("[^\\d]", "10"));
					}
				}

				FenetreSaisie typePartie = new FenetreSaisie(
						"Quel type de partie voulez-vous? (0 = simple et 1 = complexe");
				int typeP = 10;
				while (typeP != 0 && typeP != 1) {
					typePartie.execute();

					if (typePartie.getValeur().isEmpty()) {
						typeP = 10;
						;
					} else {
						typeP = Integer.parseInt(typePartie.getValeur()
								.replaceAll("[^\\d]", "10"));
					}
				}

				PartieThread partie = new PartieThread(typeP, nbrJ);
				int i = 0;
				while (i != partie.getNbrJoueur()) {
					partie.ajoutJoueurVirtuel("joueur" + (i + 1));
					i++;
				}

				partie.ajoutJoueurPhysique(nomJoueur.getValeur());

				VueGraphique vg = new VueGraphique(partie);
				partie.setVue(vg);

				Thread t = new Thread(partie);
				t.start();
			}
		} catch (Exception e) {
			System.out
					.println("Entrez un parametre valide : 0 = jouer en console, 1 = jouer en graphique");
		}
	}

	public ArrayList<Joueur> getListeJoueur() {
		return this.listeJoueur;
	}

	public int getTypePartie() {
		return this.typePartie;
	}

	public int getNbrJoueur() {
		return this.nbrJoueur;
	}

	public void setType(int typePartie) {
		this.typePartie = typePartie;
	}

	/**
	 * M�thode permettant de lancer une partie. Si l'attribut typePartie vaut 0
	 * alors on lance une partie rapide, si il vaut 1, on lance une partie
	 * complexe.
	 */
	public void lancerPartie() {
		if (this.typePartie == Partie.TYPE_COMPLEXE) {
			this.lancerPartieComplexe();
		} else if (this.typePartie == Partie.TYPE_RAPIDE) {
			this.lancerPartieBasique();
		}
	}

	/**
	 * M�thode pour changer de saison. La saison est un int valant 0,1,2 ou 3.
	 * Donc un modulo 4 � la saison en cour +1 permet de trouver la nouvelle
	 * aison.
	 */
	public void changerSaison() {
		this.saison = (this.saison + 1) % 4;
	}

	public Vue getVue() {
		return this.vue;
	}

	public void setVue(Vue vue) {
		this.vue = vue;
	}

	public int getSaison() {
		return this.saison;
	}

	public Geant getGeant() {
		return this.geant;
	}

	public Farfadet getFarfadet() {
		return this.farfadet;
	}

	public Engrais getEngrais() {
		return this.engrais;
	}

	/**
	 * M�thode pour ajouter un joueur virtuel � la collection de Joueur de la
	 * partie.
	 */
	public void ajoutJoueurVirtuel(String nomJoueur) {
		JoueurVirtuel nouveauJoueurVirtuel = new JoueurVirtuel(nomJoueur, this);
		this.listeJoueur.add(nouveauJoueurVirtuel);
	}

	/**
	 * M�thode pour ajouter un joueur physique � la collection de Joueur de la
	 * partie
	 */
	public void ajoutJoueurPhysique(String nomJoueur) {
		JoueurPhysique nouveauJoueur = new JoueurPhysique(nomJoueur, this);
		this.listeJoueur.add(nouveauJoueur);
	}

	/**
	 * M�thode permettant le lancement d'une partie basique. Un partie basique
	 * n'est qu'une seule manche. Le type de la partie lui est entr� dans le
	 * constructuer de Partie.A la fin, on d�termine le vainqueur
	 */
	public void lancerPartieBasique() {
		this.lancerManche();

		Evenement event = new Evenement(EventType.FIN_PARTIE);
		event.setJoueur(this.calculVainqueur());
		setChanged();
		notifyObservers(event);

	}

	/**
	 * M�thode permettant le lancement d'une partie complexe. Une partie
	 * complexe dure autant de manche qu'il y a de joueur.A la fin, on d�termine
	 * le vainqueur.
	 */
	public void lancerPartieComplexe() {
		int i = 0;
		// On lance autant de manche qu'il y a de jouer dans la partie.
		while (i != this.listeJoueur.size()) {

			Evenement event = new Evenement(EventType.DEBUT_MANCHE);
			event.setNbr((i + 1));
			setChanged();
			notifyObservers(event);

			this.lancerManche();
			i++;
		}

		// On calcule le vainqueur de la partie.

		Evenement event = new Evenement(EventType.FIN_PARTIE);
		event.setJoueur(this.calculVainqueur());
		setChanged();
		notifyObservers(event);
	}

	/**
	 * Lancement d'une manche : On commence par m�langer les tas de cartes.
	 * Ensuite on distribue les cartes � l'ensemble des joueurs de la
	 * collections. Une manche dure 4 tours. Si la partie est complexe, on
	 * propose au joueur de jouer une carte alli� ou un ingredient. Si la partie
	 * est basique, on joue obligatoirement un ingredient. A la fin de chaque
	 * tour, on change de saison avec la m�thode changerSaison() Une fois les 4
	 * tours termin�s, on rammases les cartes alli� et les ingr�dients. On ompte
	 * aussi le nombre de point obtenue pendant la manche.
	 */
	public void lancerManche() {
		// On distribue les graines et les cartes apr�s avoir m�lang� les tas de
		// cartes.
		this.tasCarteAllie.trierTas();
		this.tasDeCarte.trierTas();
		this.distribuerCarte();
		this.distribuerGraine();

		// On m�lange la liste des joueurs pour avoir un joueur diff�rent qui
		// commence � chaque tour.
		Collections.shuffle(this.listeJoueur);

		for (int i = 0; i < 4; i++) {
			for (Iterator<Joueur> it = this.listeJoueur.iterator(); it
					.hasNext();) {
				Joueur j = it.next();

				Evenement event = new Evenement(EventType.JOUER_TOUR);
				event.setJoueur(j);
				setChanged();
				notifyObservers(event);

				// on v�rifie si le joueur a une carte Taupe SI on joue une
				// partie complexe.
				if (this.typePartie == 1 && j.getAllie() instanceof Taupe) {
					// Le joueur choisit si il veut jouer une carte Allie ou un
					// Ingredient.
					int choix = j.choixAllieOuIngredient();
					if (choix == 0) {
						j.jouerAllie();
					} else {
						j.jouerIngredient();
					}
				} else {
					// Si on joue une partie basique, on joue syst�matiquement
					// un Ingredient.
					j.jouerIngredient();
				}
			}
			// A la fin de la manche, on change de saison.
			this.changerSaison();
		}

		// A la fin de la manche, on compte le nombre de point de chaque joueur,
		// et on reprend les cartes.
		for (Iterator<Joueur> it = this.listeJoueur.iterator(); it.hasNext();) {
			Joueur j = it.next();
			j.compterNbrPoint();
			this.deposerAllie(j.getAllie());
			j.setAllie(null);
		}

		this.ramasserCarte();
	}

	/**
	 * M�thode pour distribuer les graines � l'ensemble des joueurs de la
	 * collection. Si la partie est complexe, on donne le choix au joueur de
	 * choisir les graines ou une carte Allie.
	 */
	public void distribuerGraine() {
		for (Iterator<Joueur> it = this.listeJoueur.iterator(); it.hasNext();) {
			Joueur j = it.next();
			if (this.getTypePartie() == 1) {
				j.choixGraineOuAllie();
			} else {
				j.setNbrGraine(2);
			}
		}
	}

	/**
	 * M�thide pour distribuer les cartes. On fait un iterator sur la collection
	 * de Joueur, et chaque joueur doit piocher 4 cartes dans le tas
	 * d'ingr�dients.
	 */

	public void distribuerCarte() {
		for (Iterator<Joueur> it = this.listeJoueur.iterator(); it.hasNext();) {
			Joueur v = it.next();
			for (int i = 0; i < 4; i++) {
				v.setCarte(this.tasDeCarte.piocherCarte());
			}
		}
	}

	/**
	 * M�thode pour rammaser les cartes ingr�dient. Chaque joueur d�pose sa
	 * carte dans le tas. On veille bien � retirer la carte dans la main du
	 * joueur.
	 */
	public void ramasserCarte() {
		for (Iterator<Joueur> it = this.listeJoueur.iterator(); it.hasNext();) {
			Joueur j = it.next();
			for (Iterator<Ingredient> it2 = j.mainJoueur.iterator(); it2
					.hasNext();) {
				Ingredient c = it2.next();
				this.tasDeCarte.deposerCarte(c);
				it2.remove();
			}
		}
	}

	/** M�thode qui initialise le nombre de graine de tous les joueurs � 0. */
	public void ramasserGraine() {
		for (Iterator<Joueur> it = this.listeJoueur.iterator(); it.hasNext();) {
			it.next().setNbrGraine(0);
		}
	}

	/**
	 * M�thode pour afficher la liste des joueur de la collection. On affiche
	 * �galement de nombre de graine et de menhir du joueur
	 */
	public void afficherListeJoueur() {

		Evenement event = new Evenement(EventType.AFFICHAGE_LISTE_JOUEUR);
		event.setListeJoueur(this.listeJoueur);
		setChanged();
		notifyObservers(event);
	}

	/** M�thode pour piocher une carte dans le tas de carte alli� */
	public Allie piocherCarteAllie() {
		return this.tasCarteAllie.getTas().poll();
	}

	/** M�thode pour d�poser une carte dans le tas de carte ingr�dient */
	public void deposerIngredient(Ingredient c) {
		this.tasDeCarte.getTas().add(c);
	}

	/** M�thode pour d�poser une carte dans le tas de carte alli� */
	public void deposerAllie(Allie a) {
		this.tasCarteAllie.getTas().add(a);
	}

	/** M�thode pour ramasser les cartes alli� des joueurs en fin de manche */
	/*
	public void ramasserAllie() {
		for (Iterator<Joueur> it = this.listeJoueur.iterator(); it.hasNext();) {
			Joueur j = it.next();
			this.tasCarteAllie.deposerAllie(j.getAllie());
		}
	}
	*/

	/**
	 * M�thode pour d�terminer le vainqueur de la partie. Le vainqueur est
	 * d�termin� en fonction du nombre de menhir. En cas d'�galit�, le vainqueur
	 * est le joueur avec le plus de graines.
	 */
	public Joueur calculVainqueur() {
		Joueur joueurRetour = this.listeJoueur.get(0);
		int scoreMaxi = this.listeJoueur.get(0).getNbrPoint();
		for (Iterator<Joueur> it = this.listeJoueur.iterator(); it.hasNext();) {
			Joueur j = it.next();
			if (j.getNbrPoint() > scoreMaxi) {
				scoreMaxi = j.getNbrPoint();
				joueurRetour = j;
			} else if (j.getNbrPoint() == scoreMaxi
					&& j.getNbrGraine() > joueurRetour.getNbrGraine()) {
				joueurRetour = j;
			}
		}

		return joueurRetour;
	}
}
