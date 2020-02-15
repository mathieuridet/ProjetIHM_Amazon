package amazon_projet.accueil;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.HBox;

public class Coin_haut_gauche extends HBox {

	public Coin_haut_gauche() {
//		this.setText("Vos commandes");
//		this.setFont(new Font("Comic sans MS", 30));
//		this.setStyle("-fx-font-weight: bold; -fx-background-color: #183152;");
//		this.setPadding(new Insets(10));
//		this.setTextFill(Color.WHITE);
//		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		Composant_menu_haut panier = new Composant_menu_haut("Mon Panier", "../img/img_panier.png", ContentDisplay.LEFT, HPos.CENTER);
		this.getChildren().add(panier);
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-font-weight: bold; -fx-background-color: #183152;");
	}
}
