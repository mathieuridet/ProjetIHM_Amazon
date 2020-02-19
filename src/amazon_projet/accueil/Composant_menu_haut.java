/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazon_projet.accueil;

import amazon_projet.Recup_image;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author mathieuridet
 */
public class Composant_menu_haut extends Label {
    
    public Composant_menu_haut(String nom, String chemin_img, ContentDisplay contentdisplay, HPos horizontal_position) {
        Recup_image recup = new Recup_image(chemin_img);
        ImageView imageview = new ImageView(recup.getImg());
        imageview.setFitHeight(30);
        imageview.setFitWidth(30);
        this.setText(nom);
        this.setGraphic(imageview);
        this.setContentDisplay(contentdisplay);
        this.setFont(Font.font("Arial", 25));
        this.setTextFill(Color.WHITE);
        
        // On centre les différents composants dans leur espace disponible dans la barre
        GridPane.setValignment(this, VPos.CENTER);
        GridPane.setHalignment(this, horizontal_position);
    }
    
}
