package amazon_projet;

import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Page_Accueil extends GridPane {

	public Page_Accueil() {
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
		// Partie relative au coin en haut à gauche
		// -------------------------------------------------------------------------------

		Coin_haut_gauche titre_menu_gauche = new Coin_haut_gauche();
		// On centre le titre "Vos Commandes" qui ira en haut du menu
		GridPane.setHalignment(titre_menu_gauche, HPos.CENTER);

		// -------------------------------------------------------------------------------
		// Partie relative au menu en haut (et à droite)
		// -------------------------------------------------------------------------------

		Barre_menu_haut menu_haut = new Barre_menu_haut();

		// -------------------------------------------------------------------------------
		// Partie relative au menu à gauche (et en bas)
		// -------------------------------------------------------------------------------

		Barre_menu_gauche menu_gauche = new Barre_menu_gauche();

		// -------------------------------------------------------------------------------
		// Partie relative au contenu central
		// -------------------------------------------------------------------------------

		Contenu contenu_central = new Contenu();

		// -------------------------------------------------------------------------------
		// On ajoute les éléments dans les bonnes cases du GridPane
		// -------------------------------------------------------------------------------

		this.add(titre_menu_gauche, 0, 0);
		this.add(menu_haut, 1, 0);
		this.add(menu_gauche, 0, 1);
		this.add(contenu_central, 1, 1);
	}
}
