//This entire file is part of my masterpiece
//Nick Balkissoon




package game_ngb5;

import javafx.scene.image.Image;

public class Elite extends Enemy {

	public Elite(Hero hero, Integer xLoc, Integer yLoc, Integer HP) {
		super(hero, xLoc, yLoc, HP);
	}

	@Override
	public void move() {
		this.setY(this.getY()+5);

	}

	@Override
	public void chooseImage() {
		Image enemyImage = new Image(this.getClass().getResource("enemy_image_level_2.gif").toExternalForm());
		this.setImage(enemyImage);
	}


}
