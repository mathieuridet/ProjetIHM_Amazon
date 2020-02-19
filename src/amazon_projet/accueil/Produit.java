/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazon_projet.accueil;

import amazon_projet.Recup_image;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author daekc
 */
public class Produit extends VBox {

	private Label article;
	private ImageView imView;
	private Label prix;
	private Label description;

	public Produit(String img, String nomArticle, String price, String txt) {

		this.article = new Label(nomArticle);
		Recup_image recup = new Recup_image(img);
		this.imView = new ImageView(recup.getImg());
		this.imView.setFitHeight(80);
		this.imView.setFitWidth(80);
		this.article.setGraphic(imView);
		this.article.setContentDisplay(ContentDisplay.TOP);
		this.prix = new Label(price);
		this.description = new Label(txt);
		VBox.setMargin(this.description, new Insets(2, 2, 2, 2));
		VBox.setMargin(this.prix, new Insets(2, 2, 2, 2));
		VBox.setMargin(this.article, new Insets(2, 2, 2, 2));

		this.prix.setFont(new Font("Arial", 25));
		this.prix.setTextFill(Color.ORANGE);
		this.article.setFont(Font.font("Arial", 20));
		this.description.setFont(Font.font("Arial", FontPosture.ITALIC, 10));
		this.description.setWrapText(true);
		this.description.setMaxWidth(100);
		this.description.setTextAlignment(TextAlignment.CENTER);

		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(8, 8, 8, 8));
		this.setAlignment(Pos.CENTER);
		this.getChildren().add(article);
		this.getChildren().add(prix);
		this.getChildren().add(this.description);
	}

	public Label getArticle() {
		return article;
	}

	public ImageView getImView() {
		return imView;
	}

	public Label getPrix() {
		return prix;
	}

	public Label getDescription() {
		return description;
	}
}
