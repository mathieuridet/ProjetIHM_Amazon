/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
			return BD_Amazon.execute_select("SELECT * " + "FROM Produit " + "WHERE Categorie = '" + cat + "'");
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
	public ResultSet selectQteProductInACommand(Produit p, int IDCommande) {
		try {
			return BD_Amazon.execute_select("SELECT Qte_Produit " + "FROM Achat " + "WHERE ID_Commande = " + IDCommande
					+ " AND ID_Produit = " + p.getIdProduct());
		} catch (SQLException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public ResultSet selectIdCommandeEnCours() {
		try {
			return BD_Amazon.execute_select("SELECT ID FROM Commande WHERE Statut = 0");
		} catch (SQLException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public ResultSet selectStatutActualCommande(int IDCommande) {
		try {
			return BD_Amazon.execute_select("SELECT Statut FROM Commande WHERE ID = " + IDCommande);
		} catch (SQLException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public ResultSet selectAllCategories() {
		try {
			return BD_Amazon.execute_select("SELECT DISTINCT Categorie FROM Produit");
		} catch (SQLException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public void insertProductInACommand(Produit p, int IDCommande) {
		try {
			BD_Amazon.execute_insertOrUpdate("INSERT INTO Achat (ID_Commande, ID_Produit, Qte_Produit) " + "VALUES ("
					+ IDCommande + ", " + p.getIdProduct() + ", 1)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProductInACommand(Produit p, int IDCommande, boolean add) {
		if (add) {
			try {
				BD_Amazon.execute_insertOrUpdate("UPDATE Achat " + "SET Qte_Produit = Qte_Produit+1"
						+ " WHERE ID_Commande = " + IDCommande + " AND ID_Produit = " + p.getIdProduct());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				BD_Amazon.execute_insertOrUpdate("UPDATE Achat " + "SET Qte_Produit = Qte_Produit-1"
						+ " WHERE ID_Commande = " + IDCommande + " AND ID_Produit = " + p.getIdProduct());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteProductInACommand(Produit p, int IDCommande) {
		try {
			BD_Amazon.execute_insertOrUpdate("DELETE FROM Achat " + "WHERE ID_Commande = " + IDCommande
					+ " AND ID_Produit = " + p.getIdProduct());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endCommandAndCreateANewOne(int IDCommande) {
		String endCommande = "UPDATE Commande SET Statut = 1 WHERE ID = " + IDCommande;
		LocalDate dateDeLivraison = LocalDate.now();
		String createANewOne = "INSERT INTO Commande (Statut, DateLivraison) " + "VALUES (0, ?)";
		try {
			BD_Amazon.execute_insertOrUpdate(endCommande);
			PreparedStatement pstmt = BD_Amazon.conn.prepareStatement(createANewOne);
			pstmt.setDate(1, java.sql.Date.valueOf(dateDeLivraison.plusDays(3)));
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void GoVueProduit(Produit p, AbstractControler controler) {
		this.notifyObserver(new Page_Produit(p, controler));
	}

	@Override
	public void GoVueAccueil(AbstractControler controler, String categorie, boolean chosen, String rechercheTextuelle) {
		try {
			this.notifyObserver(new Page_Accueil(controler, categorie, chosen, rechercheTextuelle));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
