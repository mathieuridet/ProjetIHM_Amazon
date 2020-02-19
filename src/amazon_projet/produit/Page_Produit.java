package amazon_projet.produit;

import amazon_projet.accueil.Barre_menu_haut;
import amazon_projet.accueil.Coin_haut_gauche;
import amazon_projet.accueil.Menu_droit;
import amazon_projet.accueil.Produit;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class Page_Produit extends GridPane {

	public Page_Produit(Produit product) {

		// -------------------------------------------------------------------------------
		// On donne les bonnes tailles (en pourcentage) au cases du GridPane
		// -------------------------------------------------------------------------------

		// La somme des hauteurs des 2 lignes de la page fait 100%
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(8);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(92);
		this.getRowConstraints().addAll(row1, row2);

		// L largeur de de la page fait bien 100%
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(100);
		this.getColumnConstraints().add(column1);

		// -------------------------------------------------------------------------------
		// Partie relative au coin en haut (à gauche)
		// -------------------------------------------------------------------------------

		Coin_haut_gauche coin_panier = new Coin_haut_gauche();
		coin_panier.setMaxWidth(270);

		// -------------------------------------------------------------------------------
		// Partie relative au menu en haut (et à droite)
		// -------------------------------------------------------------------------------

		Barre_menu_haut menu_haut = new Barre_menu_haut();
		menu_haut.setPadding(new Insets(20, 0, 20, 0));

		// -------------------------------------------------------------------------------
		// On met en place le menu du haut de la page
		// -------------------------------------------------------------------------------

		HBox menu_fixe = new HBox();
		menu_fixe.getChildren().addAll(coin_panier, menu_haut);
		HBox.setHgrow(coin_panier, Priority.ALWAYS);
		HBox.setHgrow(menu_haut, Priority.ALWAYS);

		// -------------------------------------------------------------------------------
		// On gère la partie des informations du produit
		// -------------------------------------------------------------------------------

		Contenu_produit contenu = new Contenu_produit();

		// Partie menu de droite
		Menu_droit menu_droit = new Menu_droit(menu_haut);
		menu_droit.setVisible(false);
		menu_droit.setManaged(false);

		// Contenant des 2 éléments précédents
		HBox contenant_central = new HBox();
		HBox.setHgrow(contenu, Priority.ALWAYS);
		contenant_central.getChildren().addAll(contenu, menu_droit);

		// -------------------------------------------------------------------------------
		// On utilise un ScrollPane pour pouvoir scroll juste le contenu
		// -------------------------------------------------------------------------------

		ScrollPane contenant = new ScrollPane(contenant_central);
		contenant.setFitToWidth(true);
		contenant.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		contenant.setHbarPolicy(ScrollBarPolicy.NEVER);

		VBox.setVgrow(contenant, Priority.ALWAYS);
		this.add(menu_fixe, 0, 0);
		this.add(contenant, 0, 1);
	}
}
