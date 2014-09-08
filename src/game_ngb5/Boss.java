//This entire file is part of my masterpiece
//Nick Balkissoon




package game_ngb5;

import javafx.scene.image.Image;

public class Boss extends Enemy {
	Integer myHP;
	public Boss(Hero hero, Integer xLoc, Integer yLoc, Integer HP) {
		super(hero, xLoc, yLoc, HP);
		myBossDirection = "right";
	}


	@Override
	public void move() {
		if(this.getY()< -300){
			goDown();
		}
		if(this.getX()>= -300 && this.myBossDirection == "left"){
			goLeft();
		}
		else if(this.getX()< -300 && this.myBossDirection == "left"){
			goRight();	
		}
		else if(this.getX()<= 200 && this.myBossDirection == "right"){
			goRight();
		}
		else if(this.getX()> 200 && this.myBossDirection == "right"){
			goLeft();
			
		}
	}
	
	public void goLeft(){
		this.setX(this.getX() - 5);
		this.myBossDirection = "left";
	}
	public void goRight(){
		this.setX(this.getX() + 5);
		this.myBossDirection = "right";
		
	}
	public void goDown(){
		this.setY(this.getY()+5);
	}

	@Override
	public void chooseImage() {
		Image enemyImage = new Image(this.getClass().getResource("boss_image.png").toExternalForm());
		this.setImage(enemyImage);
		
	}





}
