package amazon_projet.accueil;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Barre_menu_haut extends GridPane {

	public Barre_menu_haut() {
		// -------------------------------------------------------------------------------
		// On donne les bonnes tailles (en pourcentage) au cases du GridPane
		// -------------------------------------------------------------------------------

		// La somme des hauteurs des 2 lignes de la page fait 100%
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(100);
		this.getRowConstraints().add(row1);

		// La somme des largeurs des 2 colonnes de la page fait 100%
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(20);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(40);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(30);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(10);
//		ColumnConstraints column5 = new ColumnConstraints();
//		column5.setPercentWidth(20);
		this.getColumnConstraints().addAll(column1, column2, column3, column4);

		// On crée nos différents composants du menu
		Composant_menu_haut mon_compte = new Composant_menu_haut("Mon Compte", "../img/img_moncompte.png",
				ContentDisplay.LEFT, HPos.CENTER);

//		Composant_menu_haut panier = new Composant_menu_haut("Mon Panier", "../img/img_panier.png", ContentDisplay.LEFT, HPos.CENTER);

		ImageView img_logo = new ImageView(
				new Image(this.getClass().getResourceAsStream("../img/img_logo_amazon.png")));
		img_logo.setFitHeight(50);
		img_logo.setFitWidth(150);
		GridPane.setValignment(img_logo, VPos.CENTER);
		GridPane.setHalignment(img_logo, HPos.CENTER);

		Composant_menu_haut ventes_speciales = new Composant_menu_haut("Ventes spéciales",
				"../img/img_ventesspeciales.png", ContentDisplay.RIGHT, HPos.CENTER);

		Composant_menu_haut menu_deroulant_droite = new Composant_menu_haut("   ", "../img/img_menuderoulantdroite.png",
				ContentDisplay.LEFT, HPos.RIGHT);

		// On attache tous les composants au conteneur du haut
		// this.getChildren().addAll(mon_compte, panier, img_logo);
		this.add(mon_compte, 0, 0);
//		this.add(panier, 1, 0);
		this.add(img_logo, 1, 0);
		this.add(ventes_speciales, 2, 0);
		this.add(menu_deroulant_droite, 3, 0);
		// this.setGridLinesVisible(true);

		// this.setMaxHeight(Double.MAX_VALUE);
		// this.setMaxWidth(Double.MAX_VALUE);
		this.setStyle("-fx-background-color: #183152;");

	}
}
