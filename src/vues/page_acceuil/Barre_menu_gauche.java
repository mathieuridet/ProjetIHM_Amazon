package vues.page_acceuil;

import controlers.AbstractControler;
import java.sql.SQLException;
import java.util.List;
import vues.communs.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Barre_menu_gauche extends BorderPane {

	private boolean infos_panier_visible = false;
	private InfosCommandes informations;

	public Barre_menu_gauche(AbstractControler controler) throws SQLException {
		// menu_gauche.maxHeight(scene.getHeight());
		// menu_gauche.minHeight(scene.getHeight());
		// menu_haut.setTranslateX(menu_gauche.getWidth());

		// On récupère les articles constituant la commande d'ID 1 dans la BD
		List<Produit> produits = controler.getProductsByCommand(controler.getIdCommande());
		for (Produit prod : produits) {
			prod.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					controler.GoPageProduit(prod);
				}
			});
		}

		// Liste d'articles (dans le menu de gauche)
		ObservableList<VBox> commandeList = FXCollections.<VBox>observableArrayList(produits);
		ListView<VBox> articles_commande = new ListView<>(commandeList);
		articles_commande.setOrientation(Orientation.VERTICAL);
		articles_commande.setStyle("-fx-control-inner-background: #183152; -fx-background-insets: 0; -fx-padding: 0;");

		// Partie sur les détails des commandes
		this.informations = new InfosCommandes(controler);

		// Résumé commande (regroupe liste d'articles, prix et date)
		BorderPane commande = new BorderPane();
		commande.setStyle("-fx-background-color: #183152;");
		commande.setCenter(articles_commande);
		commande.setTop(this.informations);

		// On attache la commande au centre du menu de gauche
		this.setCenter(commande);
		// On attache l'icone en bas du menu
		this.setStyle("-fx-background-color: #183152;");
	}

	public boolean isInfos_panier_visible() {
		return infos_panier_visible;
	}

	public void setInfos_panier_visible(boolean infos_panier_visible) {
		this.infos_panier_visible = infos_panier_visible;
	}

	public InfosCommandes getInformations() {
		return informations;
	}
}
