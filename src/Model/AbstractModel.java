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
	
	public abstract ResultSet selectQteProductInACommand(Produit p, int IDCommande);
	
	public abstract ResultSet selectIdCommandeEnCours();
	
	public abstract ResultSet selectStatutActualCommande(int IDCommande);
	
	public abstract ResultSet selectAllCategories();
	
	// Actions sur les produits et les commandes 
	public abstract void insertProductInACommand(Produit p, int IDCommande);

	public abstract void updateProductInACommand(Produit p, int IDCommande, boolean add);
	
	public abstract void deleteProductInACommand(Produit p, int IDCommande);
	
	public abstract void endCommandAndCreateANewOne(int IDCommande);
	
	// Changement de la vue accueil à la vue produit et vice-versa
	public abstract void GoVueProduit(Produit p, AbstractControler controler);
	
	public abstract void GoVueAccueil(AbstractControler controler);

	// Implementation du pattern observer
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void notifyObserver(GridPane gp) {

		for (Observer obs : listObserver)
			obs.update(gp);
	}

	public void removeObserver() {
		this.listObserver = new ArrayList<Observer>();
	}

}
