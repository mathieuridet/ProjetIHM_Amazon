package amazon_projet.produit;

import amazon_projet.accueil.Barre_menu_haut;
import amazon_projet.accueil.Coin_haut_gauche;
import amazon_projet.accueil.Produit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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

public class Page_Produit extends GridPane {

	public Page_Produit(Produit product) {

		// -------------------------------------------------------------------------------
		// On donne les bonnes tailles (en pourcentage) au cases du GridPane
		// -------------------------------------------------------------------------------

		// La somme des hauteurs des 2 lignes de la page fait 100%
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(8);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(92);
		this.getRowConstraints().addAll(row1, row2);

		// L largeur de de la page fait bien 100%
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(200);
		this.getColumnConstraints().add(column1);

		// -------------------------------------------------------------------------------
		// Partie relative au coin en haut (à gauche)
		// -------------------------------------------------------------------------------

		Coin_haut_gauche coin_panier = new Coin_haut_gauche();
		coin_panier.setMaxWidth(270);

		// -------------------------------------------------------------------------------
		// Partie relative au menu en haut (et à droite)
		// -------------------------------------------------------------------------------

		Barre_menu_haut menu_haut = new Barre_menu_haut();
		menu_haut.setPadding(new Insets(20, 0, 20, 0));

		// -------------------------------------------------------------------------------
		// On met en place le menu du haut de la page
		// -------------------------------------------------------------------------------

		HBox menu_fixe = new HBox();
		menu_fixe.getChildren().addAll(coin_panier, menu_haut);
		HBox.setHgrow(coin_panier, Priority.ALWAYS);
		HBox.setHgrow(menu_haut, Priority.ALWAYS);

		// -------------------------------------------------------------------------------
		// On gère la partie du GridPane et des informations du produit
		// -------------------------------------------------------------------------------

		GridPane contenant = new GridPane();
		ColumnConstraints colonne_info = new ColumnConstraints();
		colonne_info.setPrefWidth(100);
		colonne_info.setHgrow(Priority.ALWAYS);
		contenant.getColumnConstraints().add(colonne_info);

		// On partage la page en 3 parties horizontales de même hauteur
		for (int i = 0; i < 3; i++) {
			contenant.getRowConstraints().add(new RowConstraints(500));
			if (i == 0) {
				HBox rectangle_haut = new HBox();
				contenant.add(rectangle_haut, 0, 0);
				rectangle_haut.setBackground(
						new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			} else if (i == 1) {
				HBox rectangle_milieu = new HBox();
				contenant.add(rectangle_milieu, 0, 1);
				rectangle_milieu
						.setBackground(new Background(new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY)));
			} else {
				HBox rectangle_bas = new HBox();
				contenant.add(rectangle_bas, 0, 2);
				rectangle_bas.setBackground(
						new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
				rectangle_bas.setAlignment(Pos.CENTER);
				Label commentaires = new Label("COMMENTAIRES");
				HBox.setHgrow(commentaires, Priority.ALWAYS);
				commentaires.setFont(Font.font("Arial", FontWeight.BOLD, 50));
				rectangle_bas.getChildren().add(commentaires);
			}
		}

		contenant.setGridLinesVisible(true);

		// -------------------------------------------------------------------------------
		// On gère la partie du ScrollPane pour pouvoir scroll juste le contenu
		// -------------------------------------------------------------------------------

		ScrollPane scroll_du_contenant = new ScrollPane(contenant);
		scroll_du_contenant.setFitToWidth(true);
		scroll_du_contenant.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroll_du_contenant.setHbarPolicy(ScrollBarPolicy.NEVER);

		VBox.setVgrow(scroll_du_contenant, Priority.ALWAYS);
		this.add(menu_fixe, 0, 0);
		this.add(scroll_du_contenant, 0, 1);
	}
}
