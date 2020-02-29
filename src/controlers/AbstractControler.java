/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import Model.AbstractModel;
import amazon_projet.Recup_image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vues.communs.Produit;

/**
 *
 * @author mathieuridet
 */
public abstract class AbstractControler {
    protected AbstractModel model;
  

    public AbstractControler(AbstractModel model){
        this.model = model;
        
    }

    /*
    public ResultSet selectProductsByCommand(int idCommande) {
        return this.model.selectProductInCommand(idCommande);
    }
    
    public ResultSet selectProductsByCategory(String cat) {
        return this.model.selectProductByCategory(cat);
    }
    */
    
    /**
     * Retourne l'ensemble des produits appartenant à la commande d'ID 'idCommande'.
     * @param idCommande
     * @return
     * @throws SQLException
     */
    public List<Produit> getProductsByCommand(int idCommande) throws SQLException {
        List<Produit> produits = new ArrayList<>();
        ResultSet rs = this.model.selectProductInCommand(idCommande);
        while(rs.next()) {
            //if(attrToGet.equals("Prix")) attr_product.add(rs.getDouble(attrToGet));
            produits.add(new Produit(rs.getString("Image_Produit"), rs.getString("Libelle"), rs.getString("Prix"), rs.getString("Description")));
        }
        return produits;
    }  
    
    /**
     * Retourne un ensemble de produits appartenant à la catégorie passée en paramètre.
     * @param cat
     * @return
     * @throws SQLException 
     */
    public List<Produit> getProductsByCategory(String cat) throws SQLException {
        List<Produit> produits = new ArrayList<>();
        ResultSet rs = this.model.selectProductByCategory(cat);
        while(rs.next()) {
            produits.add(new Produit(rs.getString("Image_Produit"), rs.getString("Libelle"), rs.getString("Prix"), rs.getString("Description")));
        }
        return produits;
    }
    
    //Méthode de contrôle
    abstract void control();

}
