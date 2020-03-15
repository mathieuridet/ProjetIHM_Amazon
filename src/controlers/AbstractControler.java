package controlers;

import Model.AbstractModel;
import Model.BD_Amazon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import vues.communs.Produit;

public abstract class AbstractControler {

	protected AbstractModel model;
	protected boolean ajoutPanier = false;
	protected boolean majFromPanier = false;
	protected boolean removeProdFromPanier = false;
	protected int idCommande = 0;
	protected List<String> categories = new ArrayList<String>();
	private boolean selectionCategorie;
	private boolean selectionTextuelle;

	public AbstractControler(AbstractModel model) throws SQLException {
		this.model = model;
		this.idCommande = getIdCommandeEnCours();
		ResultSet rs = this.model.selectAllCategories();
		while (rs.next()) {
			categories.add(rs.getString("Categorie"));
		}
		String maj = "UPDATE Commande SET DateLivraison = ? WHERE ID = " + this.idCommande;
		PreparedStatement pstmt = BD_Amazon.conn.prepareStatement(maj);
		pstmt.setDate(1, java.sql.Date.valueOf((LocalDate.now()).plusDays(3)));
		pstmt.execute();
		this.selectionCategorie = false;
		this.selectionTextuelle = false;
	}

	public List<Produit> getProductsByCommand(int idCommande) throws SQLException {
		List<Produit> produits = new ArrayList<>();
		ResultSet rs = this.model.selectProductInCommand(idCommande);
		while (rs.next()) {
			produits.add(new Produit(rs.getInt("ID"), rs.getString("Image_Produit"), rs.getString("Libelle"),
					rs.getString("Prix"), rs.getString("Description"), rs.getString("Categorie")));
		}
		return produits;
	}

	public float getPrixTotalCommande(int idCommande) throws SQLException {
		ResultSet rs = this.model.selectPrixProductInACommand(idCommande);
		float total = 0f;
		while (rs.next()) {
			total += (rs.getFloat("Prix") * rs.getInt("Qte_Produit"));
		}
		total = (float) Math.round(total * 100) / 100;
		return total;
	}

	public List<Produit> getProductsByCategory(String cat) throws SQLException {
		List<Produit> produits = new ArrayList<>();
		ResultSet rs = this.model.selectProductByCategory(cat);
		while (rs.next()) {
			produits.add(new Produit(rs.getInt("ID"), rs.getString("Image_Produit"), rs.getString("Libelle"),
					rs.getString("Prix"), rs.getString("Description"), rs.getString("Categorie")));
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

	public int getQteProductInCommand(Produit p, int idCommande) throws SQLException {
		ResultSet rs = this.model.selectQteProductInACommand(p, idCommande);
		if (rs.next()) {
			return rs.getInt("Qte_Produit");
		}
		return 0;
	}

	public int getIdCommandeEnCours() throws SQLException {
		ResultSet rs = this.model.selectIdCommandeEnCours();
		if (rs.next()) {
			return rs.getInt("ID");
		}
		return 0;
	}

	public boolean getStatutActualCommande() throws SQLException {
		ResultSet rs = this.model.selectIdCommandeEnCours();
		if (rs.next()) {
			return rs.getBoolean("Statut");
		}
		return false;
	}

	public void commandeTerminee() throws SQLException {
		this.model.endCommandAndCreateANewOne(this.idCommande);
		this.idCommande = getIdCommandeEnCours();
		this.setSelectionCategorie(false);
		this.setSelectionTextuelle(false);
		this.model.GoVueAccueil(this, "", true, "");
	}

	public void GoPageProduit(Produit p) {
		this.model.GoVueProduit(p, this);
	}

	public void GoPageAccueil() {
		this.setSelectionCategorie(false);
		this.setSelectionTextuelle(false);
		this.model.GoVueAccueil(this, "", true, "");
	}

	public void GoPageAccueil(String categorie, boolean first, String rechercheTextuelle) {
		if (!categorie.equals(""))
			this.setSelectionCategorie(true);
		else
			this.setSelectionCategorie(false);
		if (!rechercheTextuelle.equals(""))
			this.setSelectionTextuelle(true);
		else
			this.setSelectionTextuelle(false);
		this.model.GoVueAccueil(this, categorie, first, rechercheTextuelle);
	}

	public boolean isAjoutPanier() {
		return this.ajoutPanier;
	}

	public void setAjoutPanier(boolean ajoutPanier, Produit p) {
		this.ajoutPanier = ajoutPanier;
		control(p);
	}

	public boolean isMajFromPanier() {
		return this.majFromPanier;
	}

	public void setMajFromPanier(boolean fromPanier) {
		this.majFromPanier = fromPanier;
	}

	public boolean isRemoveProdFromPanier() {
		return removeProdFromPanier;
	}

	public void setRemoveProdFromPanier(boolean removeProdFromPanier, Produit p) {
		this.removeProdFromPanier = removeProdFromPanier;
		control(p);
	}

	public int getIdCommande() {
		return this.idCommande;
	}

	public List<String> getCategories() {
		return categories;
	}

	public boolean isSelectionCategorie() {
		return selectionCategorie;
	}

	public void setSelectionCategorie(boolean selectionCategorie) {
		this.selectionCategorie = selectionCategorie;
	}

	public boolean isSelectionTextuelle() {
		return selectionTextuelle;
	}

	public void setSelectionTextuelle(boolean selectionTextuelle) {
		this.selectionTextuelle = selectionTextuelle;
	}

	// Méthode de contrôle
	abstract void control(Produit p);
}
