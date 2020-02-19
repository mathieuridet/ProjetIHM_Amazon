package amazon_projet.produit;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Fiche_technique extends Label {

	public Fiche_technique() {
		// Partie relative à la description technique du produit
		this.setText("Description technique");
		this.setAlignment(Pos.CENTER);
		GridPane.setHalignment(this, HPos.CENTER);
		this.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 50));
	}
}
