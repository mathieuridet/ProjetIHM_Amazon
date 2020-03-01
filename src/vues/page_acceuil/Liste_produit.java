package vues.page_acceuil;

import controlers.AbstractControler;
import java.sql.SQLException;
import java.util.List;
import vues.communs.Produit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class Liste_produit extends ScrollPane {

	// L'instance de notre objet contrôleur
	private AbstractControler controler;

	public Liste_produit(int imgSize, int space, AbstractControler controler) throws SQLException {
		this.controler = controler;
		RowConstraints row = new RowConstraints();
		row.setPrefHeight(100);
		row.setVgrow(Priority.ALWAYS);

		// int n = 1;
		GridPane productList = new GridPane();
		productList.getRowConstraints().add(row);
		productList.setHgap(space);
		productList.setPadding(new Insets(20));
		// productList.setGridLinesVisible(true);

		// On récupère les articles appartenant à la catégorie 'Sport'
		List<Produit> produits = this.controler.getProductsByCategory("Sport");
		int i = 0;
		for (Produit produit : produits) {
			productList.getColumnConstraints().add(new ColumnConstraints(200));
			produit.getImView().setFitHeight(imgSize);
			produit.getImView().setFitWidth(imgSize);
			productList.add(produit, i, 0);
			i++;
		}
		/*
		 * for (int i = 0; i < 18; i++) { productList.getColumnConstraints().add(new
		 * ColumnConstraints(200)); Produit produitn = new
		 * Produit("img/img_product1.png", "Article " + n, "19.99€",
		 * "Description article " + n); produitn.getImView().setFitHeight(imgSize);
		 * produitn.getImView().setFitWidth(imgSize); productList.add(produitn, i, 0);
		 * n++; }
		 */
		HBox.setHgrow(productList, Priority.ALWAYS);

		// Pour parcourir les produits
		this.setContent(productList);
		this.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		this.setFitToHeight(true);
		this.setFitToWidth(true);
		this.setBackground(new Background(new BackgroundFill(null, new CornerRadii(10), Insets.EMPTY)));
	}

	public Liste_produit(int imgSize, int space, int titleSize, int priceSize, int textSize,
			AbstractControler controler) {
		this.controler = controler;
		RowConstraints row = new RowConstraints();
		row.setPrefHeight(100);
		row.setVgrow(Priority.ALWAYS);

		int n = 1;
		GridPane productList = new GridPane();
		productList.getRowConstraints().add(row);
		productList.setHgap(space);
		productList.setPadding(new Insets(20));
		// productList.setGridLinesVisible(true);
		for (int i = 0; i < 18; i++) {
			productList.getColumnConstraints().add(new ColumnConstraints(200));
			Produit produitn = new Produit("img/img_product1.png", "Article " + n, "19.99€",
					"Description article " + n);
			produitn.getImView().setFitHeight(imgSize);
			produitn.getImView().setFitWidth(imgSize);
			produitn.getArticle().setFont(Font.font(titleSize));
			produitn.getArticle().setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			produitn.getArticle().setAlignment(Pos.CENTER);
			produitn.getArticle().setWrapText(true);
			produitn.getPrix().setFont(Font.font(priceSize));
			produitn.getPrix().setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			produitn.getPrix().setAlignment(Pos.CENTER);
			produitn.getPrix().setWrapText(true);
			produitn.getDescription().setFont(Font.font(textSize));
			produitn.getDescription().setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			produitn.getDescription().setAlignment(Pos.CENTER);
			produitn.getDescription().setWrapText(true);
			productList.add(produitn, i, 0);
			n++;
		}
		HBox.setHgrow(productList, Priority.ALWAYS);

		// Pour parcourir les produits
		this.setContent(productList);
		this.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setFitToHeight(true);
		this.setFitToWidth(true);
		this.setBackground(new Background(new BackgroundFill(null, new CornerRadii(10), Insets.EMPTY)));
	}
}
