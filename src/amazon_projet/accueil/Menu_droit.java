/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazon_projet.accueil;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author daekc
 */
class Menu_droit extends GridPane {

	public Menu_droit() {
		String[] cats = { "Amazon Prime", "Amazon Fire TV", "Ebook et Kindle", "Amazon Music" };

		// this.setGridLinesVisible(true);
		
		// La somme des largeurs des 2 colonnes de la page fait 100%
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(80);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(20);
		this.getColumnConstraints().addAll(column1, column2);

		for (int i = 0; i < cats.length; i++) {
			ImageView imageview = new ImageView(new Image(this.getClass().getResourceAsStream("img/img_leftArrow.png")));
			imageview.setFitHeight(20);
			imageview.setFitWidth(20);
			Text txt = new Text(cats[i]);
			txt.setWrappingWidth(180);
			GridPane.setMargin(txt, new Insets(8));
			GridPane.setMargin(imageview, new Insets(8));
			this.add(txt, 0, i);
			this.add(imageview, 1, i);
		}
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0, 0, 0, 30, false), Insets.EMPTY)));
	}

}
