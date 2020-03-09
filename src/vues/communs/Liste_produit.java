package vues.communs;

import controlers.AbstractControler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	public Liste_produit(int imgSize, int space, AbstractControler controler, String categorie, boolean first,
			String rechercheTextuelle) throws SQLException {
		RowConstraints row = new RowConstraints();
		row.setPrefHeight(100);
		row.setVgrow(Priority.ALWAYS);

		// int n = 1;
		GridPane productList = new GridPane();
		productList.getRowConstraints().add(row);
		productList.setHgap(space);
		productList.setPadding(new Insets(20));
		// productList.setGridLinesVisible(true);

		List<Produit> tousLesArticles = new ArrayList<Produit>();
		for (String cat : controler.getCategories()) {
			tousLesArticles.addAll(controler.getProductsByCategory(cat));
		}
		Random random = new Random();

		if (controler.isSelectionCategorie()) {
			if (first) {
				for (Produit prod : controler.getProductsByCategory(categorie)) {
					this.produits.add(prod);
				}

			} else {
				for (Produit prod : tousLesArticles) {
					int aleatoire = random.nextInt(this.produits.size() + 1);
					if (!prod.getCategorieProduct().equals(categorie))
						this.produits.add(aleatoire, prod);
				}
			}
		} else {
			for (Produit prod : tousLesArticles) {
				int aleatoire = random.nextInt(this.produits.size() + 1);
				this.produits.add(aleatoire, prod);
			}
		}

		int i = 0;
		for (Produit produit : this.produits) {
			productList.getColumnConstraints().add(new ColumnConstraints(210));
			produit.getImView().setFitHeight(imgSize);
			produit.getImView().setFitWidth(imgSize);

			produit.getArticle().setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			produit.getPrix().setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
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

	// -----------------------------------------------------------------------------------------------------------
	// Ci-dessus le constructeur pour les listes de la page d'accueil
	// -----------------------------------------------------------------------------------------------------------
	// Ci-dessous le constructeur pour les listes de la page produit
	// -----------------------------------------------------------------------------------------------------------

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
