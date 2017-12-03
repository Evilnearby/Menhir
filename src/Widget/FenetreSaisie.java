package Widget;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreSaisie {
	/** La fenêtre qui contient les différents éléments. */
	private JFrame fenetre;
	// JPanel p;
	/**
	 * Le panel qui contient la zone de texte ainsi que le bouton de validation.
	 */
	private JPanel p3;
	/** La zone de texte. */
	private JTextField input;
	/** Le bouton de validation. */
	private JButton enterbutton;
	/** Le message qui servira de titre de la fenêtre. */
	private String message;
	/**
	 * Attribut qui récupère ce que l'utilisateur a entré dans la zone de texte.
	 */
	private String valeur;
	/**
	 * Objet qui sert à bloquer la partie tant que le joueur n'a pas validé sa
	 * réponse.
	 */
	private Object verrou = new Object();

	/**
	 * Constructeur de la classe FenetreSaisie, Il prend en argument un String
	 * qui correspond au message de le fenêtre qui sera affiché.
	 */
	public FenetreSaisie(String message) {
		this.message = message;
	}

	/**
	 * Méthode permettant la création d'une nouvelle fenêtre de saisie. Elle
	 * récupère le texte saisie par l'utilisateur et le place dans l'attribut
	 * valeur. Finalement, elle ferme la fenêtre après exécution.
	 */
	public void execute() {

		JFrame fenetre;
		JPanel p;
		JTextField input;
		JButton enterbutton;

		this.valeur = null;
		FenetreSaisie that = this;

		fenetre = new JFrame();
		p = new JPanel(new GridLayout(1, 3));
		input = new JTextField();
		enterbutton = new JButton("Valider");
		enterbutton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				that.valeur = input.getText();
				synchronized (that.verrou) {
					that.verrou.notify();
				}
			}
		});

		p.add(input);
		p.add(enterbutton);

		fenetre.setContentPane(p);
		fenetre.setTitle(this.message);
		fenetre.setBounds(100, 100, 400, 75);
		fenetre.setVisible(true);
		// Bloque la fonction tant que le joueur n'a pas joué
		synchronized (verrou) {
			try {
				this.verrou.wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		fenetre.dispatchEvent(new WindowEvent(fenetre,
				WindowEvent.WINDOW_CLOSING));
	}
	
	public String getValeur() {
		return this.valeur;
	}
}
