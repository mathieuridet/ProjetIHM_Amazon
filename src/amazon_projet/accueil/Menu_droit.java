/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazon_projet.accueil;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author daekc
 */
public class Menu_droit extends GridPane {

	private boolean menu_droite_visible = false;

	public Menu_droit(Barre_menu_haut topMenu) {
		String[] cats = { "Amazon Prime Video", "Amazon Fire TV", "Ebook et Kindle", "Amazon Music" };

		// La somme des largeurs des 2 colonnes de la page fait 100%
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(80);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(20);
		this.getColumnConstraints().addAll(column1, column2);

		for (int i = 0; i < cats.length; i++) {
			ImageView imageview = new ImageView(
					new Image(this.getClass().getResourceAsStream("img/img_leftArrow.png")));
			imageview.setFitHeight(20);
			imageview.setFitWidth(20);
			Text txt = new Text(cats[i]);
			txt.setFont(Font.font("Arial", 16));
			txt.setWrappingWidth(200);
			GridPane.setMargin(txt, new Insets(8));
			GridPane.setMargin(imageview, new Insets(8));
			this.add(txt, 0, i);
			this.add(imageview, 1, i);
		}

		this.setBackground(
				new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0, 0, 0, 30, false), Insets.EMPTY)));
		this.setPadding(new Insets(20, 0, 0, 0));
		this.setVgap(10);
		// this.setGridLinesVisible(true);
		
		// Event pour faire apparaître et disparaître le menu de droite
		topMenu.getTirets_droite().setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				if (isMenu_droite_visible()) {
					setMenu_droite_visible(false);
					setVisible(false);
					setManaged(false);
				} else {
					setMenu_droite_visible(true);
					setVisible(true);
					setManaged(true);
				}
			}
		});
	}

	public boolean isMenu_droite_visible() {
		return menu_droite_visible;
	}

	public void setMenu_droite_visible(boolean menu_droite_visible) {
		this.menu_droite_visible = menu_droite_visible;
	}

}
