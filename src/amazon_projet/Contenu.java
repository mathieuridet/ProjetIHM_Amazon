package amazon_projet;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Contenu extends VBox {

	public Contenu() {
		this.setAlignment(Pos.TOP_CENTER);
		this.setPadding(new Insets(20, 10, 10, 10));

		// -------------------------------------------------------------------------------
		// Pub
		Image img_pub = new Image(this.getClass().getResourceAsStream("img/img_pub.png"));
		ImageView imgview_pub = new ImageView(img_pub);

		HBox box_pub = new HBox();
		box_pub.getChildren().add(imgview_pub);
		box_pub.setAlignment(Pos.CENTER);
		box_pub.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));

		// -------------------------------------------------------------------------------
		// Barre de recherche centrale
		HBox barre_du_milieu = new HBox(5);
		barre_du_milieu.setPadding(new Insets(5, 0, 5, 0));

		MenuButton toutes_nos_categories = new MenuButton("Toutes nos catégories");
		toutes_nos_categories.setMaxHeight(Double.MAX_VALUE);
		toutes_nos_categories.setFont(Font.font("Arial", FontWeight.BOLD, 15));

		TextField recherche_textuelle = new TextField();
		recherche_textuelle.setMaxHeight(Double.MAX_VALUE);

		Button loupe = new Button("", new ImageView(new Image(getClass().getResourceAsStream("img/img_loupe.png"))));
		loupe.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(5), Insets.EMPTY)));
		loupe.setPadding(new Insets(5, 25, 5, 25));

		HBox.setHgrow(toutes_nos_categories, Priority.ALWAYS);
		HBox.setHgrow(recherche_textuelle, Priority.ALWAYS);
		barre_du_milieu.getChildren().addAll(toutes_nos_categories, recherche_textuelle, loupe);

		// -------------------------------------------------------------------------------
		// Partie produits
		GridPane conteneur_produits = new GridPane();
		conteneur_produits.setHgap(10);
		conteneur_produits.setVgap(10);
		// conteneur_produits.setGridLinesVisible(true);
		for (int i = 0; i < 8; i++) {
			ColumnConstraints rowi = new ColumnConstraints();
			rowi.setPercentWidth(12.5);
			conteneur_produits.getColumnConstraints().add(rowi);
		}

		int n = 1;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 8; j++) {
				Produit produitn = new Produit("img/img_product1.png", "Article " + n, "19.99€",
						"Description article " + n, "#FFFFFF");
				conteneur_produits.add(produitn, j, i);
				n++;
			}
		}

		HBox.setHgrow(conteneur_produits, Priority.ALWAYS);
		
		// Pour lister et scroll les produits
		ScrollPane scrollable = new ScrollPane();
		scrollable.setContent(conteneur_produits);
		scrollable.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollable.setFitToHeight(true);
		scrollable.setFitToWidth(true);

		// -------------------------------------------------------------------------------
		// On attache tous les composants au contenant principal
		this.getChildren().addAll(box_pub, barre_du_milieu, scrollable);
		Contenu.setMargin(barre_du_milieu, new Insets(10, 0, 10, 0));
	}
}
