package vues.page_acceuil;

import controlers.AbstractControler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vues.communs.Produit;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
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

	private List<Produit> produits = new ArrayList<Produit>();

	public Liste_produit(int imgSize, int space, AbstractControler controler, String categorie, boolean chosen)
			throws SQLException {
		RowConstraints row = new RowConstraints();
		row.setPrefHeight(100);
		row.setVgrow(Priority.ALWAYS);

		// int n = 1;
		GridPane productList = new GridPane();
		productList.getRowConstraints().add(row);
		productList.setHgap(space);
		productList.setPadding(new Insets(20));
		// productList.setGridLinesVisible(true);

		if (chosen) {
			// On récupère les articles appartenant à la catégorie spécifiée
			this.produits = controler.getProductsByCategory(categorie);
		} else {
			for (String cat : controler.getCategories()) {
				if (!cat.equals(categorie)) {
					this.produits.addAll(controler.getProductsByCategory(cat));
				}
			}
		}

		int i = 0;
		for (Produit produit : this.produits) {
			productList.getColumnConstraints().add(new ColumnConstraints(200));
			produit.getImView().setFitHeight(imgSize);
			produit.getImView().setFitWidth(imgSize);

			produit.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					controler.GoPageProduit(produit);
				}
			});

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
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setFitToHeight(true);
		this.setFitToWidth(true);
		this.setBackground(new Background(new BackgroundFill(null, new CornerRadii(10), Insets.EMPTY)));
	}

	public Liste_produit(int imgSize, int space, int titleSize, int priceSize, int textSize,
			AbstractControler controler, Produit prod) throws SQLException {
		RowConstraints row = new RowConstraints();
		row.setPrefHeight(100);
		row.setVgrow(Priority.ALWAYS);

		GridPane productList = new GridPane();
		productList.getRowConstraints().add(row);
		productList.setHgap(space);
		productList.setPadding(new Insets(20));
		// productList.setGridLinesVisible(true);

		// On récupère les articles appartenant à la catégorie spécifiée
		this.produits = controler.getProductsByCategory(prod.getCategorieProduct());
		for (Produit p : this.produits) {
			if (p.equals(prod)) {
				this.produits.remove(p);
				break;
			}
		}

		int i = 0;
		for (Produit produit : this.produits) {
			productList.getColumnConstraints().add(new ColumnConstraints(200));
			produit.getImView().setFitHeight(imgSize);
			produit.getImView().setFitWidth(imgSize);

			produit.getArticle().setFont(Font.font(titleSize));
			produit.getArticle().setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			produit.getPrix().setFont(Font.font(priceSize));
			produit.getPrix().setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			produit.getDescription().setFont(Font.font(textSize));
			produit.getDescription().setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			produit.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					controler.GoPageProduit(produit);
				}
			});

			productList.add(produit, i, 0);
			i++;
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

	public List<Produit> getProduits() {
		return produits;
	}
}
