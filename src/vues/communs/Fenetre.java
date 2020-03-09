package vues.communs;

import java.sql.SQLException;

import amazon_projet.Amazon_Projet;
import controlers.AbstractControler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import observer.Observer;
import vues.page_acceuil.Page_Accueil;

public class Fenetre implements Observer {

	private Scene scene;

	public Fenetre(AbstractControler controler) {
		try {
			Page_Accueil accueil = new Page_Accueil(controler, "");
			update(accueil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@Override
	public void update(GridPane gp) {
		Amazon_Projet.getAmazonStage().setScene(new Scene(gp));
	}
}
