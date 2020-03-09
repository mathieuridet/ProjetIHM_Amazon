package vues.page_acceuil;

import amazon_projet.Recup_image;
import controlers.AbstractControler;
import java.sql.SQLException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import vues.communs.Liste_produit;

public class Contenu_accueil extends VBox {

	private String chosenCategorie = "";
	private Liste_produit selections;
	private Liste_produit propositions;
	private TextField recherche_textuelle;
	private Button loupe;

	public Contenu_accueil(AbstractControler controler, String categorie, boolean chosen, String rechercheTextuelle)
			throws SQLException {
		this.setAlignment(Pos.TOP_CENTER);
		this.setPadding(new Insets(10));
		// -------------------------------------------------------------------------------
		// Pub
		Recup_image recup = new Recup_image("img/img_pub.png");
		ImageView imgview_pub = new ImageView(recup.getImg());

		HBox box_pub = new HBox();
		box_pub.getChildren().add(imgview_pub);
		box_pub.setAlignment(Pos.CENTER);
		box_pub.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));

		// -------------------------------------------------------------------------------
		// Partie sur la recherche de produit et sélection de catégories
		HBox barre_du_milieu = new HBox(5);
		barre_du_milieu.setPadding(new Insets(10, 0, 10, 0));

		// Partie sur les catégories
		MenuButton toutes_nos_categories = new MenuButton("Toutes nos catégories");
		toutes_nos_categories.setMaxHeight(Double.MAX_VALUE);
		toutes_nos_categories.setFont(Font.font("Arial", FontWeight.BOLD, 15));

		for (String cat : controler.getCategories()) {
			MenuItem mi = new MenuItem(cat);
			mi.setStyle("-fx-font-size: 18px; -fx-padding: 2 98 2 2;");
			mi.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					controler.setSelectionCategorie(true);
					controler.GoPageAccueil(cat, true, "");
				}
			});
			toutes_nos_categories.getItems().add(mi);
		}

		// Partie sur la barre de recherche
		this.recherche_textuelle = new TextField();
		this.recherche_textuelle.setPromptText("Que recherchez-vous ?");
		this.recherche_textuelle.getText();
		this.recherche_textuelle.setMaxHeight(Double.MAX_VALUE);
		this.recherche_textuelle.setFont(Font.font("Arial", 20));

		this.loupe = new Button("", new ImageView(new Recup_image("img/img_loupe.png").getImg()));
		this.loupe.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(5), Insets.EMPTY)));
		this.loupe.setPadding(new Insets(5, 30, 5, 30));
		this.loupe.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controler.GoPageAccueil(categorie, chosen, recherche_textuelle.getText());
			}
		});

		HBox.setHgrow(toutes_nos_categories, Priority.ALWAYS);
		HBox.setHgrow(this.recherche_textuelle, Priority.ALWAYS);
		barre_du_milieu.getChildren().addAll(toutes_nos_categories, this.recherche_textuelle, this.loupe);

		// -------------------------------------------------------------------------------
		// On attache la pub et la barre de recherche au contenant principal
		this.getChildren().addAll(box_pub, barre_du_milieu);
		Contenu_accueil.setMargin(barre_du_milieu, new Insets(15, 0, 0, 0));

		// -------------------------------------------------------------------------------
		this.chosenCategorie = categorie;

		if (this.chosenCategorie.equals("")) {
			// On génère aléatoirement une catégorie pour afficher des produits différents
			Random rand = new Random();
			int randomIndex = rand.nextInt(controler.getCategories().size());
			this.chosenCategorie = controler.getCategories().get(randomIndex);
		}

		// -------------------------------------------------------------------------------
		// Partie produits
		this.selections = new Liste_produit(75, 20, controler, this.chosenCategorie, chosen, rechercheTextuelle);
		Contenu_accueil.setMargin(this.selections, new Insets(15, 0, 0, 0));
		VBox.setVgrow(this.selections, Priority.ALWAYS);

		this.propositions = new Liste_produit(75, 20, controler, this.chosenCategorie, !chosen, rechercheTextuelle);
		Contenu_accueil.setMargin(this.propositions, new Insets(15, 0, 0, 0));
		VBox.setVgrow(this.propositions, Priority.ALWAYS);

		this.getChildren().addAll(this.selections, this.propositions);

		this.setPrefWidth(1500);
	}
}
