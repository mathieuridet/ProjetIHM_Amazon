package amazon_projet;

import javafx.scene.image.Image;

public class Recup_image {

	private Image img;
	
	public Recup_image(String chemin) {
            this.setImg(new Image(getClass().getResourceAsStream(chemin)));
	}

	public Image getImg() {
            return this.img;
	}

	public void setImg(Image img) {
            this.img = img;
	}

}
