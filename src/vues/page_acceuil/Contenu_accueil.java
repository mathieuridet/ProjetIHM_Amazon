package vues.page_acceuil;

import amazon_projet.Recup_image;
import controlers.AbstractControler;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import vues.communs.Liste_produit;

public class Contenu_accueil extends VBox {

	private Liste_produit selections;
	private Liste_produit propositions;
	private TextField recherche_textuelle;
	private Button loupe;

	public Contenu_accueil(AbstractControler controler, String categorie, boolean first, String rechercheTextuelle)
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

		// Partie de reset quand on est sur une recherche
		Button resetButton = new Button("Annuler ");
		resetButton.setFont(Font.font("Comic sans MS", FontWeight.NORMAL, 15));
		resetButton.setGraphic(new ImageView(new Recup_image("img/reset.png").getImg()));
		resetButton.setContentDisplay(ContentDisplay.RIGHT);
		resetButton.setBackground(Background.EMPTY);
		resetButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				controler.GoPageAccueil();
			}
		});

		barre_du_milieu.getChildren().add(resetButton);

		if (!controler.isSelectionCategorie() && !controler.isSelectionTextuelle()) {
			resetButton.setVisible(false);
			resetButton.setManaged(false);
		} else {
			resetButton.setVisible(true);
			resetButton.setManaged(true);
		}

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
					controler.GoPageAccueil(cat, true, rechercheTextuelle);
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
				controler.GoPageAccueil(categorie, first, recherche_textuelle.getText());
			}
		});
		this.recherche_textuelle.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER)
					controler.GoPageAccueil(categorie, first, recherche_textuelle.getText());
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
		// Partie produits
		Label liste1 = new Label();
		if (controler.isSelectionCategorie() & !controler.isSelectionTextuelle())
			liste1.setText("> Résultat de : \t catégorie = " + categorie);
		else if (!controler.isSelectionCategorie() & controler.isSelectionTextuelle())
			liste1.setText("> Résultat de : \t recherche = " + rechercheTextuelle);
		else if (controler.isSelectionCategorie() & controler.isSelectionTextuelle())
			liste1.setText("> Résultat de : \t catégorie = " + categorie + ", \t recherche = " + rechercheTextuelle);
		else
			liste1.setText("> Découvrez nos produits :");
		liste1.setFont(Font.font("Comic sans MS", FontWeight.NORMAL, 18));
		liste1.setTextAlignment(TextAlignment.LEFT);
		liste1.setAlignment(Pos.CENTER_LEFT);
		HBox contenurListe1 = new HBox(5);
		contenurListe1.getChildren().add(liste1);
		Contenu_accueil.setMargin(contenurListe1, new Insets(20, 0, 0, 10));

		this.selections = new Liste_produit(75, 20, controler, categorie, first, rechercheTextuelle);
		Contenu_accueil.setMargin(this.selections, new Insets(0));
		VBox.setVgrow(this.selections, Priority.ALWAYS);

		Label liste2 = new Label("> Découvrez nos produits :");
		liste2.setFont(Font.font("Comic sans MS", FontWeight.NORMAL, 18));
		liste2.setTextAlignment(TextAlignment.LEFT);
		liste2.setAlignment(Pos.CENTER_LEFT);
		HBox contenurListe2 = new HBox(5);
		contenurListe2.getChildren().add(liste2);
		Contenu_accueil.setMargin(contenurListe2, new Insets(0, 0, 0, 10));

		this.propositions = new Liste_produit(75, 20, controler, categorie, !first, rechercheTextuelle);
		Contenu_accueil.setMargin(this.propositions, new Insets(0));
		VBox.setVgrow(this.propositions, Priority.ALWAYS);

		this.getChildren().addAll(contenurListe1, this.selections, contenurListe2, this.propositions);

		this.setPrefWidth(1500);
	}
}
