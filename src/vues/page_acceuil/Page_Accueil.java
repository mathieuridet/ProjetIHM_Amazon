package vues.page_acceuil;

import controlers.AbstractControler;
import java.sql.SQLException;
import vues.communs.Barre_menu_haut;
import vues.communs.Menu_droit;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import observer.Observer;

public class Page_Accueil extends GridPane implements Observer {

	// L'instance de notre objet contrôleur
	@SuppressWarnings("unused")
	private AbstractControler controler;

	public Page_Accueil(AbstractControler controler) throws SQLException {
		this.controler = controler;
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

		Barre_menu_gauche menu_gauche = new Barre_menu_gauche(controler);

		// -------------------------------------------------------------------------------
		// Partie relative au centre de la page
		// -------------------------------------------------------------------------------

		Contenu_accueil contenu = new Contenu_accueil(controler);

		// Partie menu de droite
		Menu_droit menu_droit = new Menu_droit(menu_haut);
		menu_droit.setVisible(false);
		menu_droit.setManaged(false);

		// Contenant des 2 éléments précédents
		HBox contenant_central = new HBox();
		contenant_central.getChildren().addAll(contenu, menu_droit);

		// -------------------------------------------------------------------------------
		// On ajoute les éléments dans les bonnes cases du GridPane
		// -------------------------------------------------------------------------------

		this.add(coin_panier, 0, 0);
		this.add(menu_haut, 1, 0);
		this.add(menu_gauche, 0, 1);
		this.add(contenant_central, 1, 1);
	}

	/*
	 * class OperateurListener implements ActionListener{ public void
	 * actionPerformed(ActionEvent e) {
	 * controler.setOperateur(((JButton)e.getSource()).getText()); } }
	 */

	@Override
	public void update(String str) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

}
