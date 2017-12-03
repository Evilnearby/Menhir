package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.*;
import Moteur.*;
import Widget.*;

/**
 * Classe g�rant les int�ractions avec l'utilisateur avec une interface
 * graphique. Elle est asoci�e � une partie.
 */
public class VueGraphique extends Vue {

	/** Attribut repr�sentant la fen�tre du jeu. */
	private JFrame fenetre;
	/** R�ference sur la partie en cour. */
	private PartieThread partie;

	// affichage des stats
	/** Information concernant la saison en cour. */
	private JLabel statSaison;
	/** Attribut repr�sant la manche en cour. */
	private JLabel statManche;
	/** Attribut permettant l'affichage du nombre de graines du joueur physique. */
	private JLabel statNbGraine;
	/** Attribut permettant l'affichage du nombre de menhir du joueur physique. */
	private JLabel statNbMenhir;
	/** Attribut permettant l'affichage du nombre de point du joueur physique. */
	private JLabel statNbrPoint;

	/**
	 * Attribut repr�sentant la zone inf�rieur de la fen�tre dans laquelle on
	 * affichera les cartes du joueur.
	 */
	private JPanel paneldubas;

	/**
	 * Objet qui servira � bloquer la foncton jouerIngredient tant que le joueur
	 * n'aura pas choisi un ingr�dient.
	 */
	private Object verrou = new Object();
	/** Attribut servant � r�cup�rer l'ingr�dient s�lectionn� par le jouer. */
	private Ingredient ingredientSelectionne;

	// affichage des actions des joueurs vituels
	/** Affichage des actions des joueurs virtuels. */
	private JList logJoueur;
	/**
	 * Attribut definissant une zone scrollable por afficher les actions des
	 * joueurs virtuels sans d�bordemant.
	 */
	private JScrollPane scrollPane;
	/**
	 * Attribut contenant les �l�ments � ajouter dans l'affichage des actions
	 * des joueurs virtuels.
	 */
	private DefaultListModel log = new DefaultListModel();

	/** M�thode servant � rafraichir les informations de la fen�tre. */
	private void rafraichir() {
		// Refresh du score et de la manche

		// Affichage de la saison en cour
		String saison = "";
		if (this.partie.getSaison() == 0) {
			saison = "Printemps";
		} else if (this.partie.getSaison() == 1) {
			saison = "Et�";
		} else if (this.partie.getSaison() == 2) {
			saison = "Autonne";
		} else {
			saison = "Hivers";
		}

		this.statSaison.setText("Saison : " + saison);

		// On r�cup�re la ref�rence du joueur physique, puis on met � jour les
		// stats du joueur
		JoueurPhysique joueurPhysique = null;
		for (Iterator<Joueur> it = this.partie.getListeJoueur().iterator(); it
				.hasNext();) {
			Joueur j = it.next();
			if (j instanceof JoueurPhysique) {
				joueurPhysique = (JoueurPhysique) j;
			}
		}
		this.statNbGraine.setText("Graine : " + joueurPhysique.getNbrGraine());
		this.statNbMenhir.setText("Menhir : " + joueurPhysique.getNbrMenhir());
		this.statNbrPoint.setText("Point : " + joueurPhysique.getNbrPoint());

		// Refresh de l'affichage de l'action des joueurs virtuels

		this.paneldubas.removeAll();

		for (Iterator<Ingredient> it = joueurPhysique.getMainJoueur()
				.iterator(); it.hasNext();) {
			this.paneldubas.add(new CarteWidget(it.next()));
		}

		// Ajout de la carte Allie sur l'interface graphique
		if (joueurPhysique.getAllie() instanceof Allie) {
			this.paneldubas.add(new CarteWidget(joueurPhysique.getAllie()));
		}

		this.paneldubas.repaint();
		this.fenetre.revalidate();
		this.fenetre.repaint();
	}

	/**
	 * Constructeur de la Classe VueGraphique, il prend en argument une
	 * r�f�rence � une partie. L'affichage de la fen�tre se divise en trois
	 * parties. La partie du bas contient les cartes. L'affichage du haut est
	 * redivis� en deux. La partie de gauche sert � afficher les statistiques de
	 * l'utilisateur (nombre de graine, de menhir et de point et la manche en
	 * cour), et la partie de droite sert � afficher les actions des joueurs
	 * virtuels.
	 */
	public VueGraphique(PartieThread p) {
		super(p);
		this.partie = p;
		fenetre = new JFrame("Jeu du menhir");

		this.statSaison = new JLabel();
		this.statManche = new JLabel();
		this.statNbGraine = new JLabel();
		this.statNbMenhir = new JLabel();
		this.statNbrPoint = new JLabel();

		// Affichage des statistiques

		JPanel panelStats = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelStats.setLayout(new BoxLayout(panelStats, BoxLayout.PAGE_AXIS));

		panelStats.setBackground(Color.white);
		panelStats.add(this.statSaison);
		panelStats.add(this.statManche);
		panelStats.add(this.statNbGraine);
		panelStats.add(this.statNbMenhir);
		panelStats.add(this.statNbrPoint);

		panelStats.setPreferredSize(new Dimension(150, 150));

		JPanel panelJoueur = new JPanel();

		// gestion des log de la partie
		this.logJoueur = new JList(this.log);
		this.logJoueur.setVisibleRowCount(10);
		this.scrollPane = new JScrollPane(this.logJoueur);
		panelJoueur.add(this.scrollPane);

		// Layout du haut
		JPanel panelDuHaut = new JPanel();
		panelDuHaut.setLayout(new BoxLayout(panelDuHaut, BoxLayout.LINE_AXIS));
		panelDuHaut.add(panelStats);
		panelDuHaut.add(this.scrollPane);
		panelDuHaut.setBackground(Color.black);

		// Layout du bas
		this.paneldubas = new JPanel();

		// Layout final
		Container reservoir = fenetre.getContentPane();
		reservoir.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.weightx = 0.5;
		c.weighty = 0.5;

		c.gridx = 0;
		c.gridy = 0;
		reservoir.add(panelDuHaut, c);

		c.gridx = 0;
		c.gridy = 1;
		reservoir.add(this.paneldubas, c);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(1300, 700);
		fenetre.setVisible(true);

		this.rafraichir();
	}

	/**
	 * M�thode affichant le joueur en cour ainsi que le nombre de graine et de
	 * menhir qu'il poss�de
	 */
	public void jouerTour(Evenement event) {
		this.log.addElement(event.getJoueur().getNom() + " Graine : "
				+ event.getJoueur().getNbrGraine() + " Menhir : "
				+ event.getJoueur().getNbrMenhir());
		this.rafraichir();
	}

	/** M�thode affichant le vainqueur de la partie. */
	public void finPartie(Evenement event) {
		JFrame frame = new JFrame();
		this.log.addElement("Le vainqueur est : " + event.getJoueur().getNom());
		JOptionPane.showMessageDialog(frame, "Le vainqueur est : "
				+ event.getJoueur().getNom());
	}

	/** Affiche le num�ros de la manche actuelle. */
	public void debutManche(Evenement event) {
		this.statManche.setText("Manche n� " + (event.getNbr()));
	}

	public void afficherListeJoueur(Evenement event) {
	}

	/** M�thode affichant l'ingr�dient qu'a choisi le joueur virtuel. */
	public void choixIngredientJVirtuel(Evenement event) {
		this.log.addElement("Le " + event.getJoueur().getNom()
				+ " joue une carte avec les valeurs : ");
	}

	/** M�thode affichant le joueur qu'a choisi de cibler le joueur virtuel. */
	public void choixJoueurJVirtuel(Evenement event) {
		this.log.addElement("Le " + event.getJoueur().getNom() + " vise : "
				+ event.getJoueur2().getNom());
	}

	/** M�thode affichant l'emplacement qu'a choisi le joueur virtuel. */
	public void choixEmplacementJVirtuel(Evenement event) {
		this.log.addElement("Le " + event.getJoueur().getNom() + " joue : "
				+ event.getDescriptif());
	}

	/**
	 * M�thode affichant si le joueur virtuel choisi les graines ou la carte
	 * alli�.
	 */
	public void choixDebutJVirtuel(Evenement event) {
		this.log.addElement("Le " + event.getJoueur().getNom() + " prend : "
				+ event.getDescriptif());
	}

	/**
	 * M�thode affichant les valeurs de la carte qu'a choisi le joueur virtuel.
	 * L'affichage se fait dans la fen�tre scrollable.
	 */
	public void afficherIngredient(Evenement event) {
		int[][] valeur = event.getIngredient().getValeurs().clone();
		String tmp = "";
		for (int i = 0; i < valeur.length; i++) {
			for (int j = 0; j < valeur[0].length; j++) {
				tmp += valeur[i][j];
			}
			this.log.addElement(tmp);
			tmp = "";
		}

	}

	/** M�thode affichant la carte alli� que poss�de le joueur physique. */
	public void afficherAllie(Evenement event) {
		int[] valeur = event.getAllie().getValeurs().clone();
		String tmp = "";
		for (int i = 0; i < valeur.length; i++) {
			tmp += valeur[i];
		}

		this.log.addElement(tmp);
	}

	/** M�thode affichant combien de graine donne le g�ant. */
	public void actionGeant(Evenement event) {
		this.log.addElement("Le geant donne " + event.getNbr() + " Graine(s)");
	}

	/** M�thode affichant combien de menhir fait pousser l'engrais. */
	public void actionEngrais(Evenement event) {
		this.log.addElement("L'engrais donne " + event.getNbr() + " Menhir(s)");
	}

	/** M�thode affichant combien de graine vole le farfadet. */
	public void actionFarfadet(Evenement event) {
		this.log.addElement("Le farfadet vole : " + event.getNbr()
				+ " Graine(s)");
	}

	/** M�thode affichant combien de menhir d�truit la taupe. */
	public void actionTaupe(Evenement event) {
		this.log.addElement("La taupe d�truit : " + event.getNbr()
				+ " Menhir(s)");
		this.rafraichir();
	}

	/**
	 * M�thode g�rant l'int�raction avec l'utilisateur pour choisir un joueur �
	 * cibler. Une nouvelle fen�tre de dialogue est alors ouverte pour int�ragir
	 * avec l'utilisateur.
	 */
	public Joueur choisirJoueur(Joueur j) {
		JFrame frame = new JFrame();
		String affichage = "Choisissez un joueur Parmis la liste : " + "\n";
		String[] liste = new String[this.partie.getListeJoueur().size() - 1];
		int i = 0;
		for (Iterator<Joueur> it = this.partie.getListeJoueur().iterator(); it
				.hasNext();) {
			Joueur joueur = it.next();
			if (joueur instanceof JoueurVirtuel) {
				affichage += joueur.getNom() + " : " + joueur.getNbrGraine()
						+ " Graine(s) : " + joueur.getNbrMenhir()
						+ " Menhir(s)" + "\n";
				liste[i] = joueur.getNom();
				i++;
			}
		}
		String retour = null;
		while (retour == null) {
			retour = (String) JOptionPane.showInputDialog(frame, affichage,
					"message", JOptionPane.QUESTION_MESSAGE, null, liste,
					"message");
			System.out.println(retour);
		}

		Joueur joueurRetour = null;
		for (Iterator<Joueur> it = this.partie.getListeJoueur().iterator(); it
				.hasNext();) {
			Joueur joueur = it.next();
			if (joueur.getNom() == retour) {
				joueurRetour = joueur;
			}
		}

		return joueurRetour;
	}

	/**
	 * M�thode g�rant l'int�raction avec l'utilisateur pour choisir un
	 * ingr�dient parmis la main du joueur. La s�lection se fait en cliquant sur
	 * l'ingr�dient de ntre choisi. La m�thode est bloqu�e tant que le joueur
	 * n'a pas choisi.
	 */
	public synchronized Ingredient choisirCarte(Joueur joueur) {
		// Autorise le joueur � jouer
		joueur.setAction(false);

		this.paneldubas.removeAll();

		for (Iterator<Ingredient> it = joueur.getMainJoueur().iterator(); it
				.hasNext();) {

			CarteWidget cw = new CarteWidget(it.next());

			this.paneldubas.add(cw);

			VueGraphique that = this;
			MouseAdapter listener = new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (joueur.getAction() == false) {
						that.ingredientSelectionne = cw.getIngredient();
						synchronized (that.verrou) {
							that.verrou.notify();
						}
						joueur.setAction(true);
					} else {
						System.out.println("D�j� jou�");
					}
				}
			};

			cw.addMouseListener(listener);
		}

		if (joueur.getAllie() instanceof Allie) {
			this.paneldubas.add(new CarteWidget(joueur.getAllie()));
		}

		this.paneldubas.repaint();
		this.fenetre.revalidate();
		this.fenetre.repaint();

		// Bloque la fonction tant que le joueur n'a pas jou�
		synchronized (verrou) {
			try {
				this.verrou.wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.fenetre.revalidate();
		this.fenetre.repaint();

		return this.ingredientSelectionne;
	}

	/**
	 * M�thode g�rant l'int�raction avec le joueur physique pour choisir un
	 * emplacement sur lequel joueur une carte. Une nouvelle fen�tre est ouverte
	 * pour g�rer cette int�ration.
	 */
	public Emplacement choisirEmplacement(Joueur joueur) {
		JFrame frame = new JFrame();
		String affichage = "Choisissez un emplacement : ";
		String[] liste = { "Engrais", "Geant", "Farfadet" };
		String retour = null;
		while (retour == null) {
			retour = (String) JOptionPane.showInputDialog(frame, affichage,
					"message", JOptionPane.QUESTION_MESSAGE, null, liste,
					"toto");
		}

		if (retour == "Geant") {
			return this.partie.getGeant();
		} else if (retour == "Engrais") {
			return this.partie.getEngrais();
		} else {
			return this.partie.getFarfadet();
		}
	}

	/**
	 * M�thode donnant le choix � l'utilisateur de choisir de prendre les
	 * graines ou une carte alli�. Pour cela, le choix se fait dans une nouvelle
	 * fen�tre qui apparait.
	 */
	public void choixGraineOuAllie(Joueur joueur) {
		JFrame frame = new JFrame();
		int retour = -1;
		while (retour == -1) {
			retour = JOptionPane.showConfirmDialog(frame,
					"Prendre une carte Allie?", "message",
					JOptionPane.YES_NO_OPTION);
		}
		if (retour == JOptionPane.YES_OPTION) {
			joueur.setNbrGraine(0);
			joueur.setAllie(this.partie.piocherCarteAllie());
		} else {
			joueur.setNbrGraine(2);
		}
	}

	/**
	 * M�thode donnant le choix � l'utilisateur de joueur une carte ingr�dient
	 * ou une carte alli� via une nouvelle fen�tre.
	 */
	public int choixAllieOuIngredient(Joueur joueur) {
		JFrame frame = new JFrame();
		int retour = -1;
		while (retour == -1) {
			retour = JOptionPane.showConfirmDialog(frame,
					"Jouer votre carte Allie?", "message",
					JOptionPane.YES_NO_OPTION);
		}
		if (retour == JOptionPane.YES_OPTION) {
			return 0;
		} else {
			return 1;
		}
	}
}
