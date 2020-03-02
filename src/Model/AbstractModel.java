/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import controlers.AbstractControler;
import javafx.scene.layout.GridPane;
import observer.Observable;
import observer.Observer;
import vues.communs.Produit;

/**
 *
 * @author mathieuridet
 */
public abstract class AbstractModel implements Observable {

	private List<Observer> listObserver = new ArrayList<Observer>();

	// Récupération des informations sur les produits et le panier
	public abstract ResultSet selectProductInCommand(int IDCommande);

	public abstract ResultSet selectProductByCategory(String cat);

	public abstract ResultSet selectPrixProductInACommand(int IDCommande);

	public abstract ResultSet selectDateLivraisonCommand(int IDCommande);

	// Changement de la vue accueil à la vue produit et vice-versa
	public abstract void GoVueProduit(Produit p, AbstractControler controler);

	// Implementation du pattern observer
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void notifyObserver(GridPane gp) {

		for (Observer obs : listObserver)
			obs.update(gp);
	}

	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}

}
