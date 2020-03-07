/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import java.sql.SQLException;
import java.util.List;

import Model.AbstractModel;
import vues.communs.Produit;

/**
 *
 * @author daekc
 */
public class Controler extends AbstractControler {

	public Controler(AbstractModel model) throws SQLException {
		super(model);
	}

	@Override
	void control(Produit p) {
		try {
			// On notifie le modèle et la BD quand on modifie un élément du panier
			if (isAjoutPanier()) {
				boolean update = false;
				List<Produit> produits = this.getProductsByCommand(this.idCommande);
				for (Produit prod : produits) {
					if (prod.equals(p)) {
						this.model.updateProductInACommand(p, this.idCommande, true);
						update = true;
						break;
					}
				}
				if (!update)
					this.model.insertProductInACommand(p, this.idCommande);

			} else if (isRemoveProdFromPanier()) {

				this.model.deleteProductInACommand(p, this.idCommande);
				this.removeProdFromPanier = false;

			} else {

				List<Produit> produits = this.getProductsByCommand(this.idCommande);
				for (Produit prod : produits) {
					if (prod.equals(p)) {
						this.model.updateProductInACommand(p, this.idCommande, false);
						break;
					}
				}
			}
			
			GoPageAccueil();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
