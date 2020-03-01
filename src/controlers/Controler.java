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
 * @author daekc
 */
public class Controler extends AbstractControler {

	public Controler(AbstractModel model) {
		super(model);
	}

	/**
	 * Retourne l'ensemble des produits appartenant à la commande d'ID 'idCommande'.
	 * 
	 * @param idCommande
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Produit> getProductsByCommand(int idCommande) throws SQLException {
		List<Produit> produits = new ArrayList<>();
		ResultSet rs = this.model.selectProductInCommand(idCommande);
		while (rs.next()) {
			produits.add(new Produit(rs.getString("Image_Produit"), rs.getString("Libelle"), rs.getString("Prix"),
					rs.getString("Description")));
		}
		return produits;
	}

	/**
	 * Retourne le prix total (somme des prix des articles d'une commande) de la
	 * commande d'ID idCommande.
	 * 
	 * @param idCommande
	 * @return
	 * @throws SQLException
	 */
	@Override
	public String getPrixTotalCommande(int idCommande) throws SQLException {
		ResultSet rs = this.model.selectPrixProductInACommand(idCommande);
		float total = 0f;
		while (rs.next()) {
			total += (rs.getFloat("Prix") * rs.getInt("Qte_Produit"));
		}
		total = (float) Math.round(total * 100) / 100;
		return String.valueOf(total);
		
	}

	/**
	 * Retourne un ensemble de produits appartenant à la catégorie passée en
	 * paramètre.
	 * 
	 * @param cat
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Produit> getProductsByCategory(String cat) throws SQLException {
		List<Produit> produits = new ArrayList<>();
		ResultSet rs = this.model.selectProductByCategory(cat);
		while (rs.next()) {
			produits.add(new Produit(rs.getString("Image_Produit"), rs.getString("Libelle"), rs.getString("Prix"),
					rs.getString("Description")));
		}
		return produits;
	}

	@Override
	public String getDateLivraisonCommand(int idCommande) throws SQLException {
		ResultSet rs = this.model.selectDateLivraisonCommand(idCommande);
		if (rs.next()) {
			DateFormat df = new SimpleDateFormat("dd / MM / yyyy");
			return df.format(rs.getDate("DateLivraison"));
		}
		return null;
	}

	@Override
	void control() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

}
