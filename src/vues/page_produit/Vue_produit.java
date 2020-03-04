package vues.page_produit;

import amazon_projet.Recup_image;
import vues.communs.Produit;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class Vue_produit extends GridPane {

	private Button addProduct = new Button();
	private Button notAddProduct = new Button();

	public Vue_produit(Produit prod) {
		// Partie relative aux principales informations et visuels du produit
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		// Une unique ligne qui fait 100% de la zone couverte
		RowConstraints row = new RowConstraints();
		row.setPercentHeight(100);
		this.getRowConstraints().add(row);

		// La somme des largeurs des 4 colonnes fait 100% de la zone couverte
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(30);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(5);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(35);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(30);
		this.getColumnConstraints().addAll(column1, column2, column3, column4);

		// -------------------------------------------------------------------------------
		// 1ère partie en partant de la gauche
		this.addProduct.setText("Je prends !");
		this.addProduct.setFont(Font.font("Comic sans MS", FontWeight.NORMAL, 15));
		this.addProduct.setGraphic(new ImageView(new Recup_image("img/plus.png").getImg()));
		this.addProduct.setContentDisplay(ContentDisplay.TOP);
		this.addProduct.setBackground(Background.EMPTY);

		this.notAddProduct.setText("Non merci.");
		this.notAddProduct.setFont(Font.font("Comic sans MS", FontWeight.NORMAL, 15));
		this.notAddProduct.setGraphic(new ImageView(new Recup_image("img/croix.png").getImg()));
		this.notAddProduct.setContentDisplay(ContentDisplay.TOP);
		this.notAddProduct.setBackground(Background.EMPTY);

		HBox conteneur_boutons = new HBox();
		HBox.setHgrow(this.addProduct, Priority.ALWAYS);
		HBox.setHgrow(this.notAddProduct, Priority.ALWAYS);
		conteneur_boutons.setAlignment(Pos.TOP_CENTER);
		HBox.setMargin(this.addProduct, new Insets(5, 30, 5, 30));
		HBox.setMargin(this.notAddProduct, new Insets(5, 30, 5, 30));
		conteneur_boutons.getChildren().addAll(this.addProduct, this.notAddProduct);

		Label choix_options = new Label("Choix des options");
		choix_options
				.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		choix_options.setWrapText(true);
		choix_options.setMaxSize(400, 300);
		choix_options.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
		choix_options.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), null)));
		choix_options.setAlignment(Pos.CENTER);
		choix_options.setTextAlignment(TextAlignment.CENTER);

		VBox conteneur_vertical = new VBox();
		VBox.setVgrow(choix_options, Priority.ALWAYS);
		VBox.setMargin(choix_options, new Insets(5, 0, 30, 0));
		conteneur_vertical.setAlignment(Pos.CENTER);
		conteneur_vertical.getChildren().addAll(conteneur_boutons, choix_options);
		GridPane.setHalignment(conteneur_vertical, HPos.CENTER);

		this.add(conteneur_vertical, 0, 0);

		// -------------------------------------------------------------------------------
		// 2e partie
		VBox vues = new VBox();
		for (int j = 1; j <= 4; j++) {
			int n = j;
			if (j > 3)
				n = j - 2;
			Recup_image recup = new Recup_image("img/img_product" + n + ".png");
			ImageView vuei = new ImageView(recup.getImg());
			VBox.setMargin(vuei, new Insets(20, 0, 20, 0));
			vues.getChildren().add(vuei);
		}
		vues.setAlignment(Pos.CENTER);

		this.add(vues, 1, 0);

		// -------------------------------------------------------------------------------
		// 3e partie
		ImageView img_produit = prod.getImView();
		img_produit.setFitHeight(300);
		img_produit.setFitWidth(300);
		GridPane.setHalignment(img_produit, HPos.CENTER);

		this.add(img_produit, 2, 0);

		// -------------------------------------------------------------------------------
		// 4e partie
		VBox infos = new VBox();
		infos.setAlignment(Pos.CENTER);
		infos.setPadding(new Insets(50));

		Label titre = new Label(prod.getArticle().getText());
		titre.setFont(Font.font("Arial", FontWeight.BOLD, 50));
		titre.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		titre.setAlignment(Pos.CENTER);
		VBox.setVgrow(titre, Priority.ALWAYS);

		Label valeur = prod.getPrix();
		valeur.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		valeur.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		valeur.setAlignment(Pos.CENTER);
		VBox.setVgrow(valeur, Priority.ALWAYS);

		Label details = prod.getDescription();
		details.setFont(Font.font("Arial", 20));
		details.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		details.setAlignment(Pos.CENTER);
		details.setWrapText(true);
		VBox.setVgrow(details, Priority.ALWAYS);

		infos.getChildren().addAll(titre, valeur, details);

		this.add(infos, 3, 0);

		// this.setGridLinesVisible(true);
	}

	public Button getAddProduct() {
		return addProduct;
	}

	public Button getNotAddProduct() {
		return notAddProduct;
	}

}
