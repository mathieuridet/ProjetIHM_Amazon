/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import Model.AbstractModel;
import java.sql.SQLException;
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
        
    public abstract List<Produit> getProductsByCommand(int idCommande) throws SQLException;
    public abstract String getPrixTotalCommande(int idCommande) throws SQLException;
    public abstract List<Produit> getProductsByCategory(String cat) throws SQLException;
    public abstract String getDateLivraisonCommand(int idCommande) throws SQLException;
    
    //Méthode de contrôle
    abstract void control();

}
