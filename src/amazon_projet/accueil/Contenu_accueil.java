package amazon_projet.accueil;

import amazon_projet.Recup_image;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Contenu_accueil extends VBox {

	public Contenu_accueil() {
		this.setAlignment(Pos.TOP_CENTER);
		this.setPadding(new Insets(10));

		// -------------------------------------------------------------------------------
		// Pub
		Recup_image recup = new Recup_image("img/img_pub.png");
		ImageView imgview_pub = new ImageView(recup.getImg());

		HBox box_pub = new HBox();
		box_pub.getChildren().add(imgview_pub);
		box_pub.setAlignment(Pos.CENTER);
		box_pub.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));

		// -------------------------------------------------------------------------------
		// Barre de recherche centrale
		HBox barre_du_milieu = new HBox(5);
		barre_du_milieu.setPadding(new Insets(10, 0, 10, 0));

		MenuButton toutes_nos_categories = new MenuButton("Toutes nos catégories");
		toutes_nos_categories.setMaxHeight(Double.MAX_VALUE);
		toutes_nos_categories.setFont(Font.font("Arial", FontWeight.BOLD, 15));

		TextField recherche_textuelle = new TextField();
		recherche_textuelle.setMaxHeight(Double.MAX_VALUE);

		Recup_image recup2 = new Recup_image("img/img_loupe.png");
		Button loupe = new Button("", new ImageView(recup2.getImg()));
		loupe.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(5), Insets.EMPTY)));
		loupe.setPadding(new Insets(5, 25, 5, 25));

		HBox.setHgrow(toutes_nos_categories, Priority.ALWAYS);
		HBox.setHgrow(recherche_textuelle, Priority.ALWAYS);
		barre_du_milieu.getChildren().addAll(toutes_nos_categories, recherche_textuelle, loupe);

		// -------------------------------------------------------------------------------
		// On attache la pub et la barre de recherche au contenant principal
		this.getChildren().addAll(box_pub, barre_du_milieu);
		Contenu_accueil.setMargin(barre_du_milieu, new Insets(15, 0, 0, 0));

		// -------------------------------------------------------------------------------
		// Partie produits
		Liste_produit propositions1 = new Liste_produit(75, 20);
		Contenu_accueil.setMargin(propositions1, new Insets(15, 0, 0, 0));
		VBox.setVgrow(propositions1, Priority.ALWAYS);
		Liste_produit propositions2 = new Liste_produit(75, 20);
		Contenu_accueil.setMargin(propositions2, new Insets(15, 0, 0, 0));
		VBox.setVgrow(propositions2, Priority.ALWAYS);
		
		this.getChildren().addAll(propositions1, propositions2);
	}
}
