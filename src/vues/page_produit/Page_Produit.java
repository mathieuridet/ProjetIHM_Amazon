package vues.page_produit;

import controlers.AbstractControler;
import vues.communs.Barre_menu_haut;
import vues.page_acceuil.Coin_haut_gauche;
import vues.communs.Menu_droit;
import vues.communs.Produit;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class Page_Produit extends GridPane {

	public Page_Produit(Produit prod, AbstractControler controler) {

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
		coin_panier.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				controler.setMajFromPanier(true);
				controler.GoPageAccueil();
			}
		});

		// -------------------------------------------------------------------------------
		// Partie relative au menu en haut (et à droite)
		// -------------------------------------------------------------------------------

		Barre_menu_haut menu_haut = new Barre_menu_haut();
		menu_haut.setPadding(new Insets(20, 0, 20, 0));
		menu_haut.getLogoAmazon().setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				controler.setMajFromPanier(false);
				controler.GoPageAccueil();
			}
		});

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

		GridPane infos_produit = new GridPane();
		ColumnConstraints colonne_infos = new ColumnConstraints();
		colonne_infos.setPrefWidth(100);
		colonne_infos.setHgrow(Priority.ALWAYS);
		infos_produit.getColumnConstraints().add(colonne_infos);
		for (int i = 0; i < 4; i++) {
			infos_produit.getRowConstraints().add(new RowConstraints(500));
		}

		Vue_produit productView = new Vue_produit(prod);
		infos_produit.add(productView, 0, 0);

		productView.getAddProduct().setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				controler.setMajFromPanier(false);
				controler.setAjoutPanier(true, prod);
			}
		});
		productView.getNotAddProduct().setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				controler.GoPageAccueil();
			}
		});

		Suggestions suggest = new Suggestions(prod, controler);
		infos_produit.add(suggest, 0, 1);
		Commentaires coms = new Commentaires();
		infos_produit.add(coms, 0, 2);
		Fiche_technique tech = new Fiche_technique();
		infos_produit.add(tech, 0, 3);

		// Partie menu de droite
		Menu_droit menu_droit = new Menu_droit(menu_haut);
		menu_droit.setVisible(false);
		menu_droit.setManaged(false);

		// Contenant des 2 éléments précédents
		HBox contenant_central = new HBox();
		HBox.setHgrow(infos_produit, Priority.ALWAYS);
		contenant_central.getChildren().addAll(infos_produit, menu_droit);

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
