package vues.page_acceuil;

import java.sql.SQLException;

import controlers.AbstractControler;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import vues.communs.Produit;

public class InfosCommandes extends VBox {

	private int nbreProdPanier = 0;

	public InfosCommandes(AbstractControler controler) {
		try {
			// On récupère l'ID de la commande en cours
			Label commande = new Label("Commande n°" + controler.getIdCommande());
			commande.setFont(new Font("Comic sans MS", 20));
			commande.setStyle("-fx-font-weight: bold");
			commande.setTextFill(Color.MAGENTA);
			commande.setWrapText(true);
			commande.setTextAlignment(TextAlignment.CENTER);
			commande.setPadding(new Insets(10));
			this.getChildren().add(commande);

			// Détails des quantités des produits commandés
			for (Produit prod : controler.getProductsByCommand(controler.getIdCommande())) {
				int qteProd = controler.getQteProductInCommand(prod, controler.getIdCommande());
				this.nbreProdPanier += qteProd;

				Button removeProduct = new Button();
				removeProduct.setText("-");
				removeProduct.setShape(new Circle(5));
				HBox.setMargin(removeProduct, new Insets(3, 5, 0, 5));
				removeProduct.setOnMousePressed(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						if (qteProd == 1) {
							controler.setMajFromPanier(true);
							controler.setRemoveProdFromPanier(true, prod);
							controler.setSelectionCategorie(false);
							controler.GoPageAccueil();
						} else {
							controler.setMajFromPanier(true);
							controler.setAjoutPanier(false, prod);
							controler.setSelectionCategorie(false);
							controler.GoPageAccueil();
						}
					}
				});

				Button addProduct = new Button();
				addProduct.setText("+");
				addProduct.setShape(new Circle(5));
				HBox.setMargin(addProduct, new Insets(3, 5, 0, 5));
				addProduct.setOnMousePressed(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						controler.setMajFromPanier(true);
						controler.setAjoutPanier(true, prod);
						controler.setSelectionCategorie(false);
						controler.GoPageAccueil();
					}
				});

				Label qteArticle = new Label(prod.getArticle().getText() + " \t x" + qteProd);
				qteArticle.setFont(new Font("Arial", 15));
				qteArticle.setStyle("-fx-font-weight: bold");
				qteArticle.setTextFill(Color.WHITE);
				qteArticle.setWrapText(true);
				qteArticle.setTextAlignment(TextAlignment.CENTER);
				qteArticle.setPadding(new Insets(10));
				HBox.setHgrow(qteArticle, Priority.ALWAYS);

				HBox details = new HBox();
				details.setPadding(new Insets(10));
				details.getChildren().addAll(removeProduct, addProduct, qteArticle);
				this.getChildren().add(details);
			}

			// Prix total de la commande
			Label prix_commande = new Label("Total = " + controler.getPrixTotalCommande(controler.getIdCommande()));
			prix_commande.setFont(new Font("Comic sans MS", 20));
			prix_commande.setStyle("-fx-font-weight: bold");
			prix_commande.setTextFill(Color.ORANGE);
			prix_commande.setWrapText(true);
			prix_commande.setTextAlignment(TextAlignment.CENTER);
			prix_commande.setPadding(new Insets(10));

			// Date prévue pour livraison de la commande
			Label datelivraison_commande;
			datelivraison_commande = new Label(
					"Livraison prévue le\n" + controler.getDateLivraisonCommand(controler.getIdCommande()));
			datelivraison_commande.setFont(new Font("Comic sans MS", 20));
			datelivraison_commande.setTextFill(Color.WHITE);
			datelivraison_commande.setWrapText(true);
			datelivraison_commande.setTextAlignment(TextAlignment.CENTER);
			datelivraison_commande.setPadding(new Insets(10));

			// Bouton pour passer la commande
			Button passer_commande = new Button();
			passer_commande.setText("Commander");
			passer_commande.setPadding(new Insets(5, 25, 5, 25));
			VBox.setMargin(passer_commande, new Insets(15));
			passer_commande.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					try {
						controler.setSelectionCategorie(false);
						controler.commandeTerminee();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			
			if (this.nbreProdPanier == 0) {
				passer_commande.setVisible(false);
				passer_commande.setManaged(false);
			} else {
				passer_commande.setVisible(true);
				passer_commande.setManaged(true);
			}
			
			this.getChildren().addAll(prix_commande, datelivraison_commande, passer_commande);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.setAlignment(Pos.CENTER);
		this.setBorder(new Border(
				new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(2), null, new Insets(8))));
		if (controler.isMajFromPanier()) {
			this.setVisible(true);
			this.setManaged(true);
		} else {
			this.setVisible(false);
			this.setManaged(false);
		}

	}

	public int getNbreProdPanier() {
		return nbreProdPanier;
	}
}
