/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import controlers.AbstractControler;
import vues.communs.Produit;
import vues.page_acceuil.Page_Accueil;
import vues.page_produit.Page_Produit;

/**
 *
 * @author daekc
 */
public class Model extends AbstractModel {

	@Override
	public ResultSet selectProductInCommand(int IDCommande) {
		try {
			return BD_Amazon.execute_select("SELECT p.* " + "FROM Commande c, Achat a, Produit p "
					+ "WHERE p.ID = a.ID_Produit " + "AND a.ID_Commande = c.ID " + "AND c.ID = " + IDCommande);
		} catch (SQLException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public ResultSet selectProductByCategory(String cat) {
		try {
			return BD_Amazon.execute_select("SELECT * " + "FROM Produit " + "WHERE categorie = '" + cat + "'");
		} catch (SQLException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public ResultSet selectPrixProductInACommand(int IDCommande) {
		try {
			return BD_Amazon.execute_select("SELECT p.Prix, a.Qte_Produit " + "FROM Produit p, Achat a, Commande c "
					+ "WHERE p.ID = a.ID_Produit " + "AND a.ID_Commande = c.ID " + "AND c.ID = " + IDCommande);
		} catch (SQLException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public ResultSet selectDateLivraisonCommand(int IDCommande) {
		try {
			return BD_Amazon.execute_select("SELECT DateLivraison " + "FROM Commande " + "WHERE ID = " + IDCommande);
		} catch (SQLException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public void GoVueProduit(Produit p, AbstractControler controler) {
		this.notifyObserver(new Page_Produit(p, controler));
	}

	@Override
	public void GoVueAccueil(AbstractControler controler) {
		try {
			this.notifyObserver(new Page_Accueil(controler));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
