package amazon_projet.produit;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Contenu_produit extends GridPane {

	public Contenu_produit() {

		ColumnConstraints colonne_info = new ColumnConstraints();
		colonne_info.setPrefWidth(100);
		colonne_info.setHgrow(Priority.ALWAYS);
		this.getColumnConstraints().add(colonne_info);

		// On partage la page en 4 parties horizontales de même hauteur
		for (int i = 0; i < 4; i++) {
			this.getRowConstraints().add(new RowConstraints(500));

			if (i == 0) {
				// Partie aux principales informations et visuels du produit
				GridPane contenant1 = new GridPane();
				contenant1.setBackground(
						new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
				// Une unique ligne qui fait 100% de la zone couverte
				RowConstraints row_contenant1 = new RowConstraints();
				row_contenant1.setPercentHeight(100);
				contenant1.getRowConstraints().add(row_contenant1);

				// La somme des largeurs des 4 colonnes fait 100% de la zone couverte
				ColumnConstraints column1_contenant1 = new ColumnConstraints();
				column1_contenant1.setPercentWidth(30);
				ColumnConstraints column2_contenant1 = new ColumnConstraints();
				column2_contenant1.setPercentWidth(5);
				ColumnConstraints column3_contenant1 = new ColumnConstraints();
				column3_contenant1.setPercentWidth(35);
				ColumnConstraints column4_contenant1 = new ColumnConstraints();
				column4_contenant1.setPercentWidth(30);
				contenant1.getColumnConstraints().addAll(column1_contenant1, column2_contenant1, column3_contenant1,
						column4_contenant1);

				// 1ère partie tout à gauche de la première ligne
				Label choix_options = new Label("Choix des options");
				choix_options.setBackground(
						new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
				choix_options.setAlignment(Pos.CENTER);
				GridPane.setHalignment(choix_options, HPos.CENTER);
				choix_options.setMaxSize(400, 400);
				choix_options.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
				choix_options.setBorder(
						new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), null)));
				contenant1.add(choix_options, 0, 0);

				// 2e partie à gauche de la première ligne
				VBox vues = new VBox();
				for (int j = 1; j <= 4; j++) {
					int n = j;
					if (j > 3)
						n = j - 2;
					ImageView vuei = new ImageView(
							new Image(this.getClass().getResourceAsStream("../accueil/img/img_product" + n + ".png")));
					VBox.setMargin(vuei, new Insets(20, 0, 20, 0));
					vues.getChildren().add(vuei);
				}
				vues.setAlignment(Pos.CENTER);
				contenant1.add(vues, 1, 0);
				
				// 3e partie en partant de la gauche de la première ligne
				ImageView img_produit = new ImageView(
						new Image(this.getClass().getResourceAsStream("../accueil/img/img_product1.png")));
				img_produit.setFitHeight(400);
				img_produit.setFitWidth(400);
				GridPane.setHalignment(img_produit, HPos.CENTER);
				contenant1.add(img_produit, 2, 0);

				// contenant1.setGridLinesVisible(true);

				this.add(contenant1, 0, 0);

			} else if (i == 1) {
				// partie relative aux suggestions du site en adéquation avec le produit
				GridPane contenant2 = new GridPane();

				// Une unique ligne qui fait 100% de la zone couverte
				RowConstraints row_contenant2 = new RowConstraints();
				row_contenant2.setPercentHeight(100);
				contenant2.getRowConstraints().add(row_contenant2);

				// La somme des largeurs des 2 colonnes fait 100% de la zone couverte
				ColumnConstraints colum1_contenant2 = new ColumnConstraints();
				colum1_contenant2.setPercentWidth(30);
				ColumnConstraints column2_contenant2 = new ColumnConstraints();
				column2_contenant2.setPercentWidth(70);
				contenant2.getColumnConstraints().addAll(colum1_contenant2, column2_contenant2);

				// contenant2.setGridLinesVisible(true);

				this.add(contenant2, 0, 1);

			} else if (i == 2) {
				// Partie relative aux commentaires et avis clients concernant le produit
				Label commentaires = new Label("COMMENTAIRES");
				commentaires.setBackground(
						new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
				commentaires.setAlignment(Pos.CENTER);
				GridPane.setHalignment(commentaires, HPos.CENTER);
				commentaires.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				commentaires.setFont(Font.font("Arial", FontWeight.BOLD, 50));

				this.add(commentaires, 0, 2);

			} else {
				// Partie relative à la description technique du produit
				Label tech = new Label("Description technique");
				tech.setAlignment(Pos.CENTER);
				GridPane.setHalignment(tech, HPos.CENTER);
				tech.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 50));

				this.add(tech, 0, 3);

			}
		}

		this.setGridLinesVisible(true);
	}
}
