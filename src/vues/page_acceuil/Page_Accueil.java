package vues.page_acceuil;

import controlers.AbstractControler;
import java.sql.SQLException;
import vues.communs.Barre_menu_haut;
import vues.communs.Menu_droit;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;

public class Page_Accueil extends GridPane {

	private Barre_menu_gauche menu_gauche;
	private HBox contenant_central;
	private Contenu_accueil contenu;
	private Menu_droit menu_droit;

	public Page_Accueil(AbstractControler controler, String categorie, boolean chosen, String rechercheTextuelle)
			throws SQLException {
		// this.setGridLinesVisible(true);

		// -------------------------------------------------------------------------------
		// On donne les bonnes tailles (en pourcentage) au cases du GridPane
		// -------------------------------------------------------------------------------

		// La somme des hauteurs des 2 lignes de la page fait 100%
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(8);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(92);
		this.getRowConstraints().addAll(row1, row2);

		// La somme des largeurs des 2 colonnes de la page fait 100%
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(16);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(84);
		this.getColumnConstraints().addAll(column1, column2);

		// -------------------------------------------------------------------------------
		// Partie relative au coin en haut (à gauche)
		// -------------------------------------------------------------------------------

		Coin_haut_gauche coin_panier = new Coin_haut_gauche();
		GridPane.setHalignment(coin_panier, HPos.CENTER);

		// -------------------------------------------------------------------------------
		// Partie relative au menu en haut (et à droite)
		// -------------------------------------------------------------------------------

		Barre_menu_haut menu_haut = new Barre_menu_haut();

		// -------------------------------------------------------------------------------
		// Partie relative au menu à gauche (et en bas)
		// -------------------------------------------------------------------------------

		this.menu_gauche = new Barre_menu_gauche(controler);
		coin_panier.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				if (menu_gauche.isInfos_panier_visible()) {
					menu_gauche.setInfos_panier_visible(false);
					menu_gauche.getInformations().setVisible(false);
					menu_gauche.getInformations().setManaged(false);
				} else {
					menu_gauche.setInfos_panier_visible(true);
					menu_gauche.getInformations().setVisible(true);
					menu_gauche.getInformations().setManaged(true);
				}
			}
		});

		// -------------------------------------------------------------------------------
		// Partie relative au centre de la page
		// -------------------------------------------------------------------------------
		this.contenu = new Contenu_accueil(controler, categorie, chosen, rechercheTextuelle);

		// Partie menu de droite
		this.menu_droit = new Menu_droit(menu_haut, controler, rechercheTextuelle);
		this.menu_droit.setVisible(false);
		this.menu_droit.setManaged(false);

		// Contenant des 2 éléments précédents
		this.contenant_central = new HBox();
		this.contenant_central.getChildren().addAll(this.contenu, this.menu_droit);

		// -------------------------------------------------------------------------------
		// On ajoute les éléments dans les bonnes cases du GridPane
		// -------------------------------------------------------------------------------

		this.add(coin_panier, 0, 0);
		this.add(menu_haut, 1, 0);
		this.add(this.menu_gauche, 0, 1);
		this.add(this.contenant_central, 1, 1);
	}

	public Barre_menu_gauche getMenu_gauche() {
		return menu_gauche;
	}

	public Contenu_accueil getContenu() {
		return contenu;
	}
}
