package Widget;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Moteur.Allie;
import Moteur.Ingredient;
import Moteur.Partie;
import Moteur.PartieThread;
import Vue.VueConsole;
import Vue.VueGraphique;

public class CarteWidget extends JLabel {

	/** Localisation de l'image contenant toutes les cartes ingrédients du jeu. */
	String IMG_PATH = "/files/cartes-ingredients.png";
	/** Localisation de l'image contenant toutes les cartes alliés du jeu. */
	String IMG2_PATH = "/files/cartes-alliés.png";
	/** Référence sur une carte ingrédient lié à une image. */
	Ingredient ingredient;
	/** Rférence sur une carte allié lié à une image. */
	Allie allie;

	/**
	 * Constructeur de la classe CarteWidget. Il prend en paramètre une
	 * référence sur un objet Ingredient.
	 */
	public CarteWidget(Ingredient ingredient) {
		this.ingredient = ingredient;
		this.dessinerIngredient();
	}

	/**
	 * Surcharge du constructeur de la classe CarteWidget. Cette fois-ci, on
	 * prend en paramètre une référence sur un objet Allie.
	 */
	public CarteWidget(Allie allie) {
		this.allie = allie;
		this.dessinerAllie();
	}
	
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }
	    // Créer un buffer d'image
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Déssine l'image dans le buffer
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    return bimage;
	}

	/**
	 * Méthode permettant d'obtenir l'image de la carte ingrédient à partir de
	 * l'image contenant toutes les cartes ingrédients du jeu. Pour cela, on
	 * utilise les coordonné en attribut de l'ingrédient liée à cet objet.
	 */
	public void dessinerIngredient() {
		try {
			
			java.net.URL url = getClass().getResource(IMG_PATH);
			Image image = ImageIO.read(url);
			
			BufferedImage img = this.toBufferedImage(image);
			Image croppedImage = img.getSubimage(this.ingredient.getX() * 730,
					this.ingredient.getY() * 730, 640, 640);
			ImageIcon icon = new ImageIcon(croppedImage);
			Image scaledImage = icon.getImage().getScaledInstance(150, 150,
					Image.SCALE_SMOOTH);
			this.setIcon(new ImageIcon(scaledImage));

			this.setPreferredSize(new Dimension(200, 200));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode permettant d'obtenir l'image de la carte allié à partir de
	 * l'image contenant toutes les cartes alliés du jeu. Pour cela, on utilise
	 * les coordonné en attribut de la carte allié liée à cet objet.
	 */
	public void dessinerAllie() {
		try {
			java.net.URL url = getClass().getResource(IMG2_PATH);
			Image image = ImageIO.read(url);
			
			BufferedImage img = this.toBufferedImage(image);
			Image croppedImage = img.getSubimage(this.allie.getX() * 730,
					this.allie.getY() * 730, 640, 640);
			ImageIcon icon = new ImageIcon(croppedImage);
			Image scaledImage = icon.getImage().getScaledInstance(150, 150,
					Image.SCALE_SMOOTH);
			this.setIcon(new ImageIcon(scaledImage));

			this.setPreferredSize(new Dimension(300, 300));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Ingredient getIngredient() {
		return this.ingredient;
	}

}