/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazon_projet;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author daekc
 */
public class Produit extends VBox{
    
    public Produit(String img, String nomArticle, String p, String description) {
       
        Label article = new Label(nomArticle);
        ImageView imView = new ImageView(new Image(this.getClass().getResourceAsStream(img)));
        imView.setFitHeight(80);
        imView.setFitWidth(80);
        article.setGraphic(imView);
        article.setContentDisplay(ContentDisplay.TOP);
        Label prix = new Label(p);
        Label txt = new Label(description);
        this.setMargin(txt, new Insets(2,2,2,2)); 
        this.setMargin(prix, new Insets(2,2,2,2));
        this.setMargin(article, new Insets(2,2,2,2));
        
        prix.setFont(new Font("Arial", 25));
        prix.setTextFill(Color.ORANGE);
        article.setFont(Font.font("Arial", 20));
        txt.setFont(Font.font("Arial", FontPosture.ITALIC, 10));
        txt.setWrapText(true);
        txt.setMaxWidth(100);
        txt.setTextAlignment(TextAlignment.CENTER);

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(8,8,8,8));
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(article);
        this.getChildren().add(prix);
        this.getChildren().add(txt);
    }

}
