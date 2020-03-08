package vues.page_produit;

import java.sql.SQLException;

import controlers.AbstractControler;
import vues.communs.Liste_produit;
import vues.communs.Produit;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class Suggestions extends GridPane {

	public Suggestions(Produit prod, AbstractControler controler) {
		// partie relative aux suggestions du site en adéquation avec le produit

		// Une unique ligne qui fait 100% de la zone couverte
		RowConstraints row = new RowConstraints();
		row.setPercentHeight(100);
		this.getRowConstraints().add(row);

		// La somme des largeurs des 2 colonnes fait 100% de la zone couverte
		ColumnConstraints colum1 = new ColumnConstraints();
		colum1.setPercentWidth(30);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(70);
		this.getColumnConstraints().addAll(colum1, column2);

		// -------------------------------------------------------------------------------
		// 1ère partie en partant de la gauche
		Label proposition = new Label("Proposition produit complémentaire");
		proposition.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		proposition.setMaxSize(400, 400);
		proposition.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
		proposition.setWrapText(true);
		proposition.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), null)));
		proposition.setAlignment(Pos.CENTER);
		proposition.setTextAlignment(TextAlignment.CENTER);
		GridPane.setHalignment(proposition, HPos.CENTER);

		this.add(proposition, 0, 0);

		// -------------------------------------------------------------------------------
		// 2e partie
		Liste_produit recommandations;
		try {
			recommandations = new Liste_produit(150, 90, 30, 20, 15, controler, prod);
			this.add(recommandations, 1, 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// this.setGridLinesVisible(true);
	}
}
