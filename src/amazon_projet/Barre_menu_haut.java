package amazon_projet;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Barre_menu_haut extends HBox {

	public Barre_menu_haut() {
		// menu_haut.setStyle("-fx-background-color: red;");

		// On crée nos différents "boutons" composants le menu
		Menu mon_compte = new Menu("Mon Compte");
		// mon_compte.setGraphic(new ImageView(new
		// Image(this.getClass().getResourceAsStream("img/img_moncompte.png"))));
		// mon_compte.setContentDisplay(ContentDisplay.LEFT);
		// mon_compte.setPadding(new Insets(20));

		Menu panier = new Menu("Mon Panier");
		// panier.setGraphic(new ImageView(new
		// Image(this.getClass().getResourceAsStream("img/img_panier.png"))));
		// panier.setContentDisplay(ContentDisplay.LEFT);
		// panier.setPadding(new Insets(20));

		MenuBar menu_haut_gauche = new MenuBar();
		menu_haut_gauche.setMaxHeight(Double.MAX_VALUE);

		ImageView img_logo = new ImageView(new Image(this.getClass().getResourceAsStream("img/img_logo_amazon.png")));
		img_logo.setFitHeight(25);
		img_logo.setFitWidth(150);

		HBox box_logo = new HBox();
		box_logo.getChildren().add(img_logo);
		box_logo.setAlignment(Pos.CENTER);
		box_logo.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(10), Insets.EMPTY)));
		GridPane.setHalignment(box_logo, HPos.CENTER);

		// On attache tous les composants au conteneur du haut
		menu_haut_gauche.getMenus().addAll(mon_compte, panier);
		this.getChildren().addAll(menu_haut_gauche, box_logo);
	}
}
