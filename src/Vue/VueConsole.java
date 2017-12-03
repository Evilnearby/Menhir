package Vue;

import java.util.Iterator;
import java.util.Scanner;

import Moteur.*;

/**
 * Classe gérant les itéractions avec l'utilisateur en ligne de commande. Elle
 * est associée à un partie.
 */
public class VueConsole extends Vue {

	/** Constructeur de la classe VueConsole */
	public VueConsole(Partie p) {
		super(p);
	}

	/**
	 * Implémentation de la méthode choisirJoueur. Si on lance le jeu avec une
	 * vue console, alors l'intéraction avec le joueur se fait en ligne de
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
					.println("Quel joueur voulez-vous viser ? (Entrez le numeros affiché devant le nom)");
			tmpReponse = sc.nextLine();
			// On vérifie l'entré utilisateur.
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
	 * Méthode affichant la main du joueur, permet de montrer au joueur quelle
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
	 * Méthode permettant de choisir entre jouer la carte allié ou une carte
	 * ingrédient. Elle retourne 0 si on veut jouer la carte Allie et 0 sinon.
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
			// On vérifie l'entré utilisateur.
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
	 * Méthode permettant de choisir entre les graines ou la carte allié en
	 * début de manche. Soit on pioche une carte allié avec la méthode
	 * piocherCarteAllie(), soit on initialise le nombre de graine du joueur à
	 * 4.
	 */
	public void choixGraineOuAllie(Joueur joueur) {
		Scanner sc = new Scanner(System.in);
		int reponse = -1;
		String tmpReponse = null;
		while (reponse != 0 && reponse != 1) {
			System.out
					.println("Voulez-vous les graines ou la carte Allie ? 0 = graines, 1 = Allie");
			// On vérifie l'entré utilisateur.
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
	 * Implémentation de la méthode choisirCarte. Elle gère l'intéraction avec
	 * l'utilisateur pour lui demander quel ingrédient jouer. L'intéraction se
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
			// On vérifie l'entré utilisateur.
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
	 * Méthode permettant de choisir un emplacement à jouer. On revoie une
	 * instance de l'emplacement joué de l'objet Partie. L'intéraction avec le
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
			// On vérifie l'entré utilisateur.
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
	 * Méthode affichant la manche en cour, dans le vue console, l'affichage se
	 * fait en ligne de commande.
	 */
	public void debutManche(Evenement event) {
		System.out.println("Manche n° " + (event.getNbr()));
	}

	/** Méthode affichant le vainqueur de la partie à la fin. */
	public void finPartie(Evenement event) {
		System.out.println("Le vainqueur est :");
		System.out.println(event.getJoueur().getNom());
	}

	/**
	 * Méthode affichant le joueur en train de jouer. L'affichage se fait en
	 * ligne de commande.
	 */
	public void jouerTour(Evenement event) {
		System.out.println(event.getJoueur().getNom() + " Graine : "
				+ event.getJoueur().getNbrGraine() + " Menhir : "
				+ event.getJoueur().getNbrMenhir());
		System.out.println("");
	}

	/** Méthode affichant la liste des joueurs en ligne de commande. */
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

	/** Méthode affichant quel ingrédient le joueur virtuel a choisit de jouer. */
	public void choixIngredientJVirtuel(Evenement event) {
		System.out.println("Le " + event.getJoueur().getNom()
				+ " joue la carte : ");
	}

	/** Méthode affichant quel joueur le joueur virtuel à ciblé. */
	public void choixJoueurJVirtuel(Evenement event) {
		System.out.println("Le " + event.getJoueur().getNom() + " a choisit : "
				+ event.getJoueur2().getNom());
	}

	/** Méthode affichant quel emplacement le joueur virtuel à choisit. */
	public void choixEmplacementJVirtuel(Evenement event) {
		System.out.println("Le " + event.getJoueur().getNom() + " a choisit "
				+ event.getDescriptif());
	}

	/**
	 * Méthode affichant si le joueur virtuel ) choisit les graines ou la carte
	 * allié en début de partie.
	 */
	public void choixDebutJVirtuel(Evenement event) {
		System.out.println(event.getJoueur().getNom() + " prend "
				+ event.getDescriptif());
	}

	/**
	 * Méthode affichant les ingrédient que le joueur physique possède dans sa
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

	/** Méthode affichant les valeurs de la carte allié. */
	public void afficherAllie(Evenement event) {
		System.out.println("");
		for (int i = 0; i < event.getAllie().getValeurs().length; i++) {
			System.out.print(event.getAllie().getValeurs()[i]);
		}
		System.out.println(" ");
	}

	/** Méthode affichant combien de graine donne le géant. */
	public void actionGeant(Evenement event) {
		System.out.println("Le Geant donne : " + event.getNbr() + " Graine(s)");
	}

	/** Méthode affichant combien de menhir donne l'engrais. */
	public void actionEngrais(Evenement event) {
		System.out.println("L'Engrais fait pousser : " + event.getNbr()
				+ " Graine(s)");
	}

	/** Méthode affichant combien de graine vole le farfadet. */
	public void actionFarfadet(Evenement event) {
		System.out.println("Le Farfadet vole : " + event.getNbr()
				+ " Graine(s)");
	}

	/** Méthode affichant combien de menhir détruit la taupe. */
	public void actionTaupe(Evenement event) {
		System.out.println("La Taupe détruit : " + event.getNbr()
				+ " Menhir(s)");
	}
}