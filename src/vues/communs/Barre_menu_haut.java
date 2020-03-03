package vues.communs;

import amazon_projet.Recup_image;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Barre_menu_haut extends GridPane {

	private Composant_menu_haut tirets_droite = new Composant_menu_haut("   ", "img/img_menuderoulantdroite.png",
			ContentDisplay.LEFT, HPos.RIGHT);;
			
	private ImageView logoAmazon;

	public Barre_menu_haut() {
		// -------------------------------------------------------------------------------
		// On donne les bonnes tailles (en pourcentage) au cases du GridPane
		// -------------------------------------------------------------------------------

		// Une unique ligne qui fait 100% de la zone couverte
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(100);
		this.getRowConstraints().add(row1);

		// La somme des largeurs des 2 colonnes fait 100% de la zone couverte
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(15);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(55);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(25);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(5);
//		ColumnConstraints column5 = new ColumnConstraints();
//		column5.setPercentWidth(20);
		this.getColumnConstraints().addAll(column1, column2, column3, column4);

		// On crée nos différents composants du menu

		Composant_menu_haut mon_compte = new Composant_menu_haut(" Mon Compte", "img/img_moncompte.png",
				ContentDisplay.LEFT, HPos.CENTER);

		Recup_image recup = new Recup_image("img/img_logo_amazon.png");
		this.logoAmazon = new ImageView(recup.getImg());

		this.logoAmazon.setFitHeight(45);
		this.logoAmazon.setFitWidth(135);
		GridPane.setValignment(this.logoAmazon, VPos.CENTER);
		GridPane.setHalignment(this.logoAmazon, HPos.CENTER);

		Composant_menu_haut ventes_speciales = new Composant_menu_haut("Ventes spéciales ",
				"img/img_ventesspeciales.png", ContentDisplay.RIGHT, HPos.CENTER);

		// A noter que le composant du menu déroulant de droite a déjà été instancié en
		// variable de classe

		// On attache tous les composants au conteneur du haut
		// this.getChildren().addAll(mon_compte, panier, img_logo);
		this.add(mon_compte, 0, 0);
//		this.add(panier, 1, 0);
		this.add(this.logoAmazon, 1, 0);
		this.add(ventes_speciales, 2, 0);
		this.add(this.tirets_droite, 3, 0);
		// this.setGridLinesVisible(true);

		// this.setMaxHeight(Double.MAX_VALUE);
		// this.setMaxWidth(Double.MAX_VALUE);
		this.setStyle("-fx-background-color: #183152;");

	}

	public Composant_menu_haut getTirets_droite() {
		return tirets_droite;
	}

	public ImageView getLogoAmazon() {
		return logoAmazon;
	}

}
