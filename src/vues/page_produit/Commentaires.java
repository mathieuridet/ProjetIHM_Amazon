package vues.page_produit;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Commentaires extends Label {

	public Commentaires() {
		// Partie relative aux commentaires et avis clients concernant le produit
		this.setText("COMMENTAIRES");
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setAlignment(Pos.CENTER);
		GridPane.setHalignment(this, HPos.CENTER);
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.setFont(Font.font("Arial", FontWeight.BOLD, 50));
	}
}
