/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazon_projet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import amazon_projet.accueil.Page_Accueil;

public class Amazon_Projet extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Principal conteneur
		Page_Accueil accueil = new Page_Accueil();
		
		Scene scene = new Scene(accueil);

		primaryStage.setTitle("Amazon");
		primaryStage.setScene(scene);
		primaryStage.setHeight(1000);
		primaryStage.setWidth(1500);
		// primaryStage.setFullScreen(true);
		// primaryStage.initStyle(StageStyle.DECORATED);		
		primaryStage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}
