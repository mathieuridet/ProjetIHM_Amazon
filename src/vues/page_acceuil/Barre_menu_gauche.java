package vues.page_acceuil;

import controlers.AbstractControler;
import java.sql.SQLException;
import java.util.List;
import vues.communs.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Barre_menu_gauche extends BorderPane {

	private boolean infos_panier_visible = false;
	// L'instance de notre objet contrôleur
	private AbstractControler controler;

	// Pour l'instant on va choisir manuellement l'id de la commande qu'on veut
	private int tmpIdCommande = 2;

	public Barre_menu_gauche(AbstractControler controler) throws SQLException {
		this.controler = controler;
		// menu_gauche.maxHeight(scene.getHeight());
		// menu_gauche.minHeight(scene.getHeight());
		// menu_haut.setTranslateX(menu_gauche.getWidth());

		// On récupère les articles constituant la commande d'ID 1 dans la BD
		List<Produit> produits = this.controler.getProductsByCommand(this.tmpIdCommande);

		// Liste d'articles (dans le menu de gauche)
		ObservableList<VBox> commandeList = FXCollections.<VBox>observableArrayList(produits);
		ListView<VBox> articles_commande = new ListView<>(commandeList);
		articles_commande.setOrientation(Orientation.VERTICAL);
		articles_commande.setStyle("-fx-control-inner-background: #183152; -fx-background-insets: 0; -fx-padding: 0;");

		// Prix et date livraison de la commande d'ID 1
		Label prix_commande = new Label("Total = " + this.controler.getPrixTotalCommande(this.tmpIdCommande));
		prix_commande.setFont(new Font("Comic sans MS", 20));
		prix_commande.setStyle("-fx-font-weight: bold");
		prix_commande.setTextFill(Color.ORANGE);
		prix_commande.setWrapText(true);
		prix_commande.setTextAlignment(TextAlignment.CENTER);
		prix_commande.setPadding(new Insets(10));
		Label datelivraison_commande = new Label(
				"Livraison prévue le\n" + this.controler.getDateLivraisonCommand(this.tmpIdCommande));
		datelivraison_commande.setFont(new Font("Comic sans MS", 20));
		datelivraison_commande.setStyle("-fx-font-weight: bold");
		datelivraison_commande.setTextFill(Color.WHITE);
		datelivraison_commande.setWrapText(true);
		datelivraison_commande.setTextAlignment(TextAlignment.CENTER);
		datelivraison_commande.setPadding(new Insets(10));

		// Icône en bas du menu de gauche
//		Recup_image recup = new Recup_image("img/img_icone.png");
//		Image img_icone = recup.getImg();
//		ImageView imgview_icone = new ImageView(img_icone);
//		imgview_icone.setFitHeight(75);
//		imgview_icone.setFitWidth(75);

		// On attache le prix et la date de livraison dans une VBox
		VBox prixEtDate = new VBox();
		prixEtDate.getChildren().addAll(prix_commande, datelivraison_commande); // Il n'y a plus les trois petits points
																				// de imgview_icone
		prixEtDate.setAlignment(Pos.CENTER);
		prixEtDate.setBorder(new Border(
				new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(2), null, new Insets(8))));
		prixEtDate.setVisible(false);
		prixEtDate.setManaged(false);

		// Résumé commande (regroupe liste d'articles, prix et date)
		BorderPane commande = new BorderPane();
		commande.setStyle("-fx-background-color: #183152;");
		commande.setCenter(articles_commande);
		commande.setBottom(prixEtDate);

		// partie pour afficher et cacher la partie basse
		Button infosPanier = new Button("Infos supplémentaires");
		infosPanier.setAlignment(Pos.CENTER);
		infosPanier.setMaxWidth(Double.MAX_VALUE);
		infosPanier.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				if (isInfos_panier_visible()) {
					setInfos_panier_visible(false);
					prixEtDate.setVisible(false);
					prixEtDate.setManaged(false);
				} else {
					setInfos_panier_visible(true);
					prixEtDate.setVisible(true);
					prixEtDate.setManaged(true);
				}
			}
		});

		// On attache la commande au centre du menu de gauche
		this.setCenter(commande);
		this.setBottom(infosPanier);
		// On attache l'icone en bas du menu
		// this.setBottom(imgview_icone);
		this.setStyle("-fx-background-color: #183152;");
		// BorderPane.setAlignment(imgview_icone, Pos.CENTER);
	}

	public boolean isInfos_panier_visible() {
		return infos_panier_visible;
	}

	public void setInfos_panier_visible(boolean infos_panier_visible) {
		this.infos_panier_visible = infos_panier_visible;
	}
}
