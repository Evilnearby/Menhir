package Vue;

import java.util.Iterator;
import java.util.Scanner;

import Moteur.*;

/**
 * Classe g�rant les it�ractions avec l'utilisateur en ligne de commande. Elle
 * est associ�e � un partie.
 */
public class VueConsole extends Vue {

	/** Constructeur de la classe VueConsole */
	public VueConsole(Partie p) {
		super(p);
	}

	/**
	 * Impl�mentation de la m�thode choisirJoueur. Si on lance le jeu avec une
	 * vue console, alors l'int�raction avec le joueur se fait en ligne de
	 * commande.
	 */
	public Joueur choisirJoueur(Joueur j) {
		this.partie.afficherListeJoueur();

		Scanner sc = new Scanner(System.in);
		int reponse = -1;
		String tmpReponse = null;
		while (reponse > this.partie.getListeJoueur().size() || reponse < 0
				|| reponse == this.partie.getListeJoueur().indexOf(j)) {
			System.out
					.println("Quel joueur voulez-vous viser ? (Entrez le numeros affich� devant le nom)");
			tmpReponse = sc.nextLine();
			// On v�rifie l'entr� utilisateur.
			if (tmpReponse.isEmpty()) {
				reponse = 10;
			} else {
				tmpReponse = tmpReponse.replaceAll("[^\\d]", "10");
				reponse = Integer.parseInt(tmpReponse);
			}
		}

		return this.partie.getListeJoueur().get(reponse);
	}

	/**
	 * M�thode affichant la main du joueur, permet de montrer au joueur quelle
	 * carte il peut jouer.
	 */
	public void afficherMain(Joueur joueur) {
		int i = 1;
		for (Iterator<Ingredient> it = joueur.getMainJoueur().iterator(); it
				.hasNext();) {
			System.out.println(" ");
			System.out.println("Carte " + i + " : " + i);
			joueur.afficherValeurIngredient(it.next());
			i++;
		}
		if (joueur.getAllie() instanceof Taupe) {
			System.out.println("Carte Taupe : 5 ");
			joueur.afficherValeurAllie(joueur.getAllie());
		} else if (joueur.getAllie() instanceof ChienDeGarde) {
			System.out.println("Chien de garde (non jouable)");
		}
	}

	/**
	 * M�thode permettant de choisir entre jouer la carte alli� ou une carte
	 * ingr�dient. Elle retourne 0 si on veut jouer la carte Allie et 0 sinon.
	 */
	public int choixAllieOuIngredient(Joueur joueur) {
		this.afficherMain(joueur);

		Scanner sc = new Scanner(System.in);
		int reponse = -1;
		String tmpReponse = null;
		while (reponse != 0 && reponse != 1) {
			System.out
					.println("Voulez vous jouer votre carte Allie ? 0 = oui, 1 = non");
			tmpReponse = sc.nextLine();
			// On v�rifie l'entr� utilisateur.
			if (tmpReponse.isEmpty()) {
				reponse = 10;
			} else {
				tmpReponse = tmpReponse.replaceAll("[^\\d]", "10");
				reponse = Integer.parseInt(tmpReponse);
			}
		}
		if (reponse == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * M�thode permettant de choisir entre les graines ou la carte alli� en
	 * d�but de manche. Soit on pioche une carte alli� avec la m�thode
	 * piocherCarteAllie(), soit on initialise le nombre de graine du joueur �
	 * 4.
	 */
	public void choixGraineOuAllie(Joueur joueur) {
		Scanner sc = new Scanner(System.in);
		int reponse = -1;
		String tmpReponse = null;
		while (reponse != 0 && reponse != 1) {
			System.out
					.println("Voulez-vous les graines ou la carte Allie ? 0 = graines, 1 = Allie");
			// On v�rifie l'entr� utilisateur.
			tmpReponse = sc.nextLine();
			if (tmpReponse.isEmpty()) {
				reponse = 10;
			} else {
				tmpReponse = tmpReponse.replaceAll("[^\\d]", "10");
				reponse = Integer.parseInt(tmpReponse);
			}
		}

		if (reponse == 1) {
			joueur.setNbrGraine(0);
			joueur.setAllie(this.partie.piocherCarteAllie());
		} else if (reponse == 0) {
			joueur.setNbrGraine(2);
		}
	}

	/**
	 * Impl�mentation de la m�thode choisirCarte. Elle g�re l'int�raction avec
	 * l'utilisateur pour lui demander quel ingr�dient jouer. L'int�raction se
	 * fait en ligne de commande.
	 */
	public Ingredient choisirCarte(Joueur joueur) {
		this.afficherMain(joueur);

		Scanner sc = new Scanner(System.in);
		int reponse = 10;
		String tmpReponse = null;
		while ((reponse - 1) > joueur.getMain().size() - 1) {
			System.out.println("Quelle carte voulez-vous choisir?");
			tmpReponse = sc.nextLine();
			// On v�rifie l'entr� utilisateur.
			if (tmpReponse.isEmpty()) {
				reponse = 10;
			} else {
				tmpReponse = tmpReponse.replaceAll("[^\\d]", "10");
				reponse = Integer.parseInt(tmpReponse);
			}
		}

		return joueur.getMainJoueur().get(reponse - 1);
	}

	/**
	 * M�thode permettant de choisir un emplacement � jouer. On revoie une
	 * instance de l'emplacement jou� de l'objet Partie. L'int�raction avec le
	 * joueur physique se fait en ligne de commande
	 */
	public Emplacement choisirEmplacement(Joueur joueur) {
		Scanner sc = new Scanner(System.in);
		int reponse = -1;
		String tmpReponse = null;
		while (reponse < 0 || reponse > 4) {
			System.out
					.println("Choissez un emplacement (Geant = 1, Engrais = 2 ou Farfadet = 3) : ");
			tmpReponse = sc.nextLine();
			// On v�rifie l'entr� utilisateur.
			if (tmpReponse.isEmpty()) {
				reponse = 10;
			} else {
				tmpReponse = tmpReponse.replaceAll("[^\\d]", "10");
				reponse = Integer.parseInt(tmpReponse);
			}
		}
		Emplacement e = null;
		switch (reponse) {
		case 3:
			e = partie.getFarfadet();
			break;
		case 2:
			e = partie.getEngrais();
			break;
		case 1:
			e = partie.getGeant();
			break;
		default:
			System.out.println("Entrez un emplacement valide");
		}
		return e;
	}

	/**
	 * M�thode affichant la manche en cour, dans le vue console, l'affichage se
	 * fait en ligne de commande.
	 */
	public void debutManche(Evenement event) {
		System.out.println("Manche n� " + (event.getNbr()));
	}

	/** M�thode affichant le vainqueur de la partie � la fin. */
	public void finPartie(Evenement event) {
		System.out.println("Le vainqueur est :");
		System.out.println(event.getJoueur().getNom());
	}

	/**
	 * M�thode affichant le joueur en train de jouer. L'affichage se fait en
	 * ligne de commande.
	 */
	public void jouerTour(Evenement event) {
		System.out.println(event.getJoueur().getNom() + " Graine : "
				+ event.getJoueur().getNbrGraine() + " Menhir : "
				+ event.getJoueur().getNbrMenhir());
		System.out.println("");
	}

	/** M�thode affichant la liste des joueurs en ligne de commande. */
	public void afficherListeJoueur(Evenement event) {
		int i = 0;
		for (Iterator<Joueur> it = event.getListeJoueur().iterator(); it
				.hasNext();) {
			Joueur j = it.next();
			System.out.println(j.getNom() + " : " + i + " " + j.getNbrGraine()
					+ " Graines " + j.getNbrMenhir() + " Menhir ");
			i++;
		}
	}

	/** M�thode affichant quel ingr�dient le joueur virtuel a choisit de jouer. */
	public void choixIngredientJVirtuel(Evenement event) {
		System.out.println("Le " + event.getJoueur().getNom()
				+ " joue la carte : ");
	}

	/** M�thode affichant quel joueur le joueur virtuel � cibl�. */
	public void choixJoueurJVirtuel(Evenement event) {
		System.out.println("Le " + event.getJoueur().getNom() + " a choisit : "
				+ event.getJoueur2().getNom());
	}

	/** M�thode affichant quel emplacement le joueur virtuel � choisit. */
	public void choixEmplacementJVirtuel(Evenement event) {
		System.out.println("Le " + event.getJoueur().getNom() + " a choisit "
				+ event.getDescriptif());
	}

	/**
	 * M�thode affichant si le joueur virtuel ) choisit les graines ou la carte
	 * alli� en d�but de partie.
	 */
	public void choixDebutJVirtuel(Evenement event) {
		System.out.println(event.getJoueur().getNom() + " prend "
				+ event.getDescriptif());
	}

	/**
	 * M�thode affichant les ingr�dient que le joueur physique poss�de dans sa
	 * main.
	 */
	public void afficherIngredient(Evenement event) {
		System.out.println("");
		for (int i = 0; i < event.getIngredient().getValeurs().length; i++) {
			if (i == 0) {
				System.out.print("Geant    ");
			} else if (i == 1) {
				System.out.print("Engrais  ");
			} else {
				System.out.print("Farfadet ");
			}

			for (int j = 0; j < event.getIngredient().getValeurs()[0].length; j++) {
				System.out.print(event.getIngredient().getValeurs()[i][j]);
			}
			System.out.println(" ");
		}
	}

	/** M�thode affichant les valeurs de la carte alli�. */
	public void afficherAllie(Evenement event) {
		System.out.println("");
		for (int i = 0; i < event.getAllie().getValeurs().length; i++) {
			System.out.print(event.getAllie().getValeurs()[i]);
		}
		System.out.println(" ");
	}

	/** M�thode affichant combien de graine donne le g�ant. */
	public void actionGeant(Evenement event) {
		System.out.println("Le Geant donne : " + event.getNbr() + " Graine(s)");
	}

	/** M�thode affichant combien de menhir donne l'engrais. */
	public void actionEngrais(Evenement event) {
		System.out.println("L'Engrais fait pousser : " + event.getNbr()
				+ " Graine(s)");
	}

	/** M�thode affichant combien de graine vole le farfadet. */
	public void actionFarfadet(Evenement event) {
		System.out.println("Le Farfadet vole : " + event.getNbr()
				+ " Graine(s)");
	}

	/** M�thode affichant combien de menhir d�truit la taupe. */
	public void actionTaupe(Evenement event) {
		System.out.println("La Taupe d�truit : " + event.getNbr()
				+ " Menhir(s)");
	}
}