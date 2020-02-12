/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazon_projet;

import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author mathieuridet
 */
public class Amazon_Projet extends Application {
    
    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
        // Principal conteneur
        BorderPane root = new BorderPane();
        // Scene principale (page d'accueil)
        Scene scene = new Scene(root, 300, 250);
        
// -------------------------------------------------------------------------------
        // Barre tout en haut
        HBox menu_haut = new HBox();
        
       // menu_haut.setStyle("-fx-background-color: red;");
        // On veut que la barre au dessus prenne toujours toute la largeur de la scene
        menu_haut.maxWidth(scene.getWidth());
        menu_haut.minWidth(scene.getWidth());
        
        // On crée nos différents "boutons" composants le menu
        Label mon_compte = new Label("Mon Compte");
        //mon_compte.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("img/img_moncompte.png"))));
        //mon_compte.setContentDisplay(ContentDisplay.LEFT);
        mon_compte.setPadding(new Insets(20));
        
        Label panier = new Label("Mon Panier");
        //panier.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("img/img_panier.png"))));
        //panier.setContentDisplay(ContentDisplay.LEFT);
        panier.setPadding(new Insets(20));
        
        ImageView img_logo = new ImageView(new Image(this.getClass().getResourceAsStream("img/img_logo.jpeg")));
        img_logo.setFitHeight(30);
        img_logo.setFitWidth(75);
        
        // On attache tous les composants au conteneur du haut
        menu_haut.getChildren().add(mon_compte);
        menu_haut.getChildren().add(panier);
        menu_haut.getChildren().add(img_logo);
        
        
// -------------------------------------------------------------------------------        
        // Menu de gauche
        BorderPane menu_gauche = new BorderPane();
        menu_gauche.maxHeight(scene.getHeight());
        menu_gauche.minHeight(scene.getHeight());
        menu_haut.setTranslateX(menu_gauche.getWidth());
        
        // Titre du menu
        Label titre_menugauche = new Label("Vos commandes");
        titre_menugauche.setFont(new Font("Comic sans MS", 20));
        titre_menugauche.setStyle("-fx-font-weight: bold");
        titre_menugauche.setPadding(new Insets(10));
        
        //Creation des articles
        Produit produit1 = new Produit("img/img_product1.png", "Article 1", "19.99€", "Description article 1");
        Produit produit2 = new Produit("img/img_product2.png", "Article 2", "39.99€", "Description article 2");
        Produit produit3 = new Produit("img/img_product3.png", "Article 3", "99.99€", "Description article 3");
        Produit produit4 = new Produit("img/img_product1.png", "Article 4", "159.99€", "Description article 4");
        Produit produit5 = new Produit("img/img_product2.png", "Article 5", "209.99€", "Description article 5");
        
        // Liste d'articles (dans le menu de gauche)
        ObservableList<VBox> commandeList = FXCollections.<VBox>observableArrayList(produit1, produit2, produit3, produit4, produit5);
        ListView<VBox> articles_commande = new ListView<>(commandeList);
        articles_commande.setOrientation(Orientation.VERTICAL);
        
        // Prix et date livraison commande
        Label prix_commande= new Label("Prix");
        prix_commande.setFont(new Font("Comic sans MS", 20));
        prix_commande.setStyle("-fx-font-weight: bold");
        prix_commande.setPadding(new Insets(10));
        Label datelivraison_commande = new Label ("Date Livraison");
        datelivraison_commande.setFont(new Font("Comic sans MS", 20));
        datelivraison_commande.setStyle("-fx-font-weight: bold");
        datelivraison_commande.setPadding(new Insets(10));
              
        // On attache le prix et la date de livraison dans une VBox
        VBox prixEtDate = new VBox();
        prixEtDate.getChildren().add(prix_commande);
        prixEtDate.getChildren().add(datelivraison_commande);
        prixEtDate.setAlignment(Pos.CENTER);
        
        // Résumé commande (regroupe liste d'articles, prix et date)
        BorderPane commande = new BorderPane();
        commande.setCenter(articles_commande);
        commande.setBottom(prixEtDate);
        
        
        //Icone en bas du menu de gauche
        Image img_icone = new Image(this.getClass().getResourceAsStream("img/img_icone.png"));
        ImageView imgview_icone = new ImageView(img_icone);
        imgview_icone.setFitHeight(75);
        imgview_icone.setFitWidth(75);
        
        
        // On attache le titre "Vos Commandes" en haut du menu
        menu_gauche.setTop(titre_menugauche);
        menu_gauche.setAlignment(titre_menugauche, Pos.CENTER);
        // On attache la commande au centre du menu de gauche
        menu_gauche.setCenter(commande);
        // On attache l'icone en bas du menu
        menu_gauche.setBottom(imgview_icone);
        menu_gauche.setAlignment(imgview_icone, Pos.CENTER);
        
// -------------------------------------------------------------------------------
        // Contenu au centre
        VBox contenu_central = new VBox();
        contenu_central.setAlignment(Pos.CENTER);
        
        // Pub
        Image img_pub = new Image(this.getClass().getResourceAsStream("img/img_pub.png"));
        ImageView imgview_pub = new ImageView(img_pub);
        
        
        
        // On attache tous les composants au contenant principal
        contenu_central.getChildren().add(imgview_pub);
        
// -------------------------------------------------------------------------------        
        
        root.setTop(menu_haut);
        root.setCenter(contenu_central);
        root.setLeft(menu_gauche);


        primaryStage.setTitle("Amazon");
        primaryStage.setScene(scene);
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1750);
        //primaryStage.setFullScreen(true);
        //primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
