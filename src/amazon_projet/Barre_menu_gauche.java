package amazon_projet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Barre_menu_gauche extends BorderPane {
    
	public Barre_menu_gauche() {
		// menu_gauche.maxHeight(scene.getHeight());
		// menu_gauche.minHeight(scene.getHeight());
		// menu_haut.setTranslateX(menu_gauche.getWidth());

		// Creation des articles
		Produit produit1 = new Produit("img/img_product1.png", "Article 1", "19.99€", "Description article 1", "#183152");
		Produit produit2 = new Produit("img/img_product2.png", "Article 2", "39.99€", "Description article 2", "#183152");
		Produit produit3 = new Produit("img/img_product3.png", "Article 3", "99.99€", "Description article 3", "#183152");
		Produit produit4 = new Produit("img/img_product1.png", "Article 4", "159.99€", "Description article 4", "#183152");
		Produit produit5 = new Produit("img/img_product2.png", "Article 5", "209.99€", "Description article 5", "#183152");

		// Liste d'articles (dans le menu de gauche)
		ObservableList<VBox> commandeList = FXCollections.<VBox>observableArrayList(produit1, produit2, produit3,
				produit4, produit5);
		ListView<VBox> articles_commande = new ListView<>(commandeList);
		articles_commande.setOrientation(Orientation.VERTICAL);
                articles_commande.setStyle("-fx-control-inner-background: #183152; -fx-background-insets: 0; -fx-padding: 0;");

		// Prix et date livraison commande
		Label prix_commande = new Label("Prix");
		prix_commande.setFont(new Font("Comic sans MS", 20));
		prix_commande.setStyle("-fx-font-weight: bold");
                prix_commande.setTextFill(Color.WHITE);
		prix_commande.setPadding(new Insets(10));
		Label datelivraison_commande = new Label("Date Livraison");
		datelivraison_commande.setFont(new Font("Comic sans MS", 20));
		datelivraison_commande.setStyle("-fx-font-weight: bold");
                datelivraison_commande.setTextFill(Color.WHITE);
		datelivraison_commande.setPadding(new Insets(10));

		// On attache le prix et la date de livraison dans une VBox
		VBox prixEtDate = new VBox();
		prixEtDate.getChildren().add(prix_commande);
		prixEtDate.getChildren().add(datelivraison_commande);
		prixEtDate.setAlignment(Pos.CENTER);

		// Résumé commande (regroupe liste d'articles, prix et date)
		BorderPane commande = new BorderPane();
                commande.setStyle("-fx-background-color: #183152;");
		commande.setCenter(articles_commande);
		commande.setBottom(prixEtDate);

		// Icône en bas du menu de gauche
		Image img_icone = new Image(this.getClass().getResourceAsStream("img/img_icone.png"));
		ImageView imgview_icone = new ImageView(img_icone);
		imgview_icone.setFitHeight(75);
		imgview_icone.setFitWidth(75);

		// On attache la commande au centre du menu de gauche
		this.setCenter(commande);
		// On attache l'icone en bas du menu
		this.setBottom(imgview_icone);
                this.setStyle("-fx-background-color: #183152;");
		this.setAlignment(imgview_icone, Pos.CENTER);
	}
}
