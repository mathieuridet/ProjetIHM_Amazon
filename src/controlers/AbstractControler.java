/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import Model.AbstractModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import vues.communs.Produit;

/**
 *
 * @author mathieuridet
 */
public abstract class AbstractControler {

	protected AbstractModel model;

	public AbstractControler(AbstractModel model) {
		this.model = model;
	}

	public List<Produit> getProductsByCommand(int idCommande) throws SQLException {
		List<Produit> produits = new ArrayList<>();
		ResultSet rs = this.model.selectProductInCommand(idCommande);
		while (rs.next()) {
			produits.add(new Produit(rs.getInt("ID"), rs.getString("Image_Produit"), rs.getString("Libelle"),
					rs.getString("Prix"), rs.getString("Description")));
		}
		return produits;
	}

	public String getPrixTotalCommande(int idCommande) throws SQLException {
		ResultSet rs = this.model.selectPrixProductInACommand(idCommande);
		float total = 0f;
		while (rs.next()) {
			total += (rs.getFloat("Prix") * rs.getInt("Qte_Produit"));
		}
		total = (float) Math.round(total * 100) / 100;
		return String.valueOf(total);
	}

	public List<Produit> getProductsByCategory(String cat) throws SQLException {
		List<Produit> produits = new ArrayList<>();
		ResultSet rs = this.model.selectProductByCategory(cat);
		while (rs.next()) {
			produits.add(new Produit(rs.getInt("ID"), rs.getString("Image_Produit"), rs.getString("Libelle"),
					rs.getString("Prix"), rs.getString("Description")));
		}
		return produits;
	}

	public String getDateLivraisonCommand(int idCommande) throws SQLException {
		ResultSet rs = this.model.selectDateLivraisonCommand(idCommande);
		if (rs.next()) {
			DateFormat df = new SimpleDateFormat("dd / MM / yyyy");
			return df.format(rs.getDate("DateLivraison"));
		}
		return null;
	}
	
	public void GoPageProduit(Produit p) {
		this.model.GoVueProduit(p, this);
	}
	
	public void GoPageAccueil() {
		this.model.GoVueAccueil(this);
	}

	// Méthode de contrôle
	abstract void control();

}
