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
import javafx.application.Application;
import javafx.stage.Stage;
import vues.communs.Fenetre;

public class Amazon_Projet extends Application {

	private static Stage AmazonStage;

	@Override
	public void start(Stage primaryStage) {

		AmazonStage = primaryStage;
		// On setup la BD
		BD_Amazon.connexionToBd("amazon", "jdbc:mariadb://176.158.51.172:3300/amazon", "jdbcUser", "AmazonIHM");

		// Instanciation de notre modèle
		AbstractModel model = new Model();

		// Création du contrôleur
		AbstractControler controler = new Controler(model);

		AmazonStage.setTitle("Amazon");

		// La fenêtre qui voit tout
		Fenetre fen = new Fenetre(controler);

		model.addObserver(fen);

		AmazonStage.setHeight(1250);
		AmazonStage.setWidth(1750);
		AmazonStage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	public static Stage getAmazonStage() {
		return AmazonStage;
	}
}
