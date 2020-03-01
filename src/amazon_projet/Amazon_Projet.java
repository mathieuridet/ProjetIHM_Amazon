/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazon_projet;

import Model.AbstractModel;
import Model.BD_Amazon;
import Model.Model;
import controlers.AbstractControler;
import controlers.Controler;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vues.page_acceuil.Page_Accueil;
import vues.communs.Produit;
import vues.page_produit.Page_Produit;

public class Amazon_Projet extends Application {

	@Override
	public void start(Stage primaryStage) throws SQLException {
		// On setup la BD
		BD_Amazon.connexionToBd("amazon", "jdbc:mariadb://176.158.51.172:3300/amazon", "jdbcUser", "AmazonIHM");

		// Instanciation de notre modèle
		AbstractModel model = new Model();
		// Création du contrôleur
		AbstractControler controler = new Controler(model);
		// La scène de la page d'accueil
		Page_Accueil accueil = new Page_Accueil(controler);

		Scene scene = new Scene(accueil);

		primaryStage.setTitle("Amazon");
		primaryStage.setScene(scene);
		primaryStage.setHeight(1250);
		primaryStage.setWidth(1750);
		primaryStage.show();

		// La scène de la page produit
		Page_Produit test = new Page_Produit(new Produit("img/img_product1.png", "TITRE", "PRIX €",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio"),
				controler);

		Stage test_produit = new Stage();

		Scene scene1 = new Scene(test);

		test_produit.setTitle("Amazon");
		test_produit.setScene(scene1);
		test_produit.setHeight(1250);
		test_produit.setWidth(1750);
		test_produit.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}
