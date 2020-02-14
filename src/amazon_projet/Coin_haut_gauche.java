package amazon_projet;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Coin_haut_gauche extends Label {

	public Coin_haut_gauche() {
		this.setText("Vos commandes");
		this.setFont(new Font("Comic sans MS", 30));
		this.setStyle("-fx-font-weight: bold; -fx-background-color: #183152;");
		this.setPadding(new Insets(10));
                this.setTextFill(Color.WHITE);
                this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                
	}
}
