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
import javafx.stage.Stage;
import vues.communs.Fenetre;

public class Amazon_Projet extends Application {

	@Override
	public void start(Stage primaryStage) throws SQLException {
		// On setup la BD
		BD_Amazon.connexionToBd("amazon", "jdbc:mariadb://176.158.51.172:3300/amazon", "jdbcUser", "AmazonIHM");

		// Instanciation de notre modèle
		AbstractModel model = new Model();

		// Création du contrôleur
		AbstractControler controler = new Controler(model);

		// La fenêtre qui voit tout
		Fenetre fen = new Fenetre(controler);
		
		model.addObserver(fen);

		primaryStage.setTitle("Amazon");
		primaryStage.setScene(fen.getScene());
		primaryStage.setHeight(1250);
		primaryStage.setWidth(1750);
		primaryStage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}
