package amazon_projet;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

public class Contenu extends VBox {

	public Contenu() {
		this.setAlignment(Pos.CENTER);

		// Pub
		Image img_pub = new Image(this.getClass().getResourceAsStream("img/img_pub.png"));
		ImageView imgview_pub = new ImageView(img_pub);

		HBox box_pub = new HBox();
		box_pub.getChildren().add(imgview_pub);
		box_pub.setAlignment(Pos.CENTER);
		box_pub.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));

		// Barre de recherche centrale
		HBox barre_du_milieu = new HBox(5);
		barre_du_milieu.setPadding(new Insets(5));

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
		
		// Le scroll des produits
		// à faire ... (by Abdel)

		// On attache tous les composants au contenant principal
		this.getChildren().addAll(box_pub, barre_du_milieu);
	}
}
