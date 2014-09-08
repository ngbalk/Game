//This entire file is part of my masterpiece
//Nick Balkissoon



package game_ngb5;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.util.Duration;

abstract public class Enemy extends ImageView {
	Timeline myAttackFlight;
	String myBossDirection;
	Integer myHP;
	Hero myHero;
	Pane myRoot;
	Integer myLevel;
	boolean myLifeStatus;
	
	abstract public void chooseImage();
	abstract public void move();
	
	public Enemy(Hero hero, Integer xLoc, Integer yLoc, Integer HP){

		myHero = hero;
		myRoot = hero.myRoot;
		myLevel = hero.myLevel;
		myHP = HP;
		myLifeStatus = true;
		chooseImage();
		setLayout(xLoc, yLoc);
		
		
	}
	

	public void attack(){
		Timeline attackFlight = new Timeline();
		myAttackFlight = attackFlight;
		attackFlight.setCycleCount(Animation.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.millis(35), new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				move();
			}
			
		});
		attackFlight.getKeyFrames().add(kf);
		attackFlight.play();
	}

	public void enemyHit(){
		myHP = myHP - 10;
		if(myHP<=0){
			die();
			explode();
		}
		else{
			explode();
		}
	}
	public void die(){
		myLifeStatus = false;
		myRoot.getChildren().remove(this);
		
	}
	public void explode(){
		double currentX = this.getX();
		double currentY = this.getY();
		Image explosionImage = new Image(this.getClass().getResource("explosion_image.gif").toExternalForm());
		ImageView explosion = new ImageView();
		myRoot.getChildren().add(explosion);
		explosion.setImage(explosionImage);
		explosion.setX(currentX);
		explosion.setY(currentY - 100);
		FadeTransition ft = new FadeTransition(Duration.millis(1000), explosion);
		ft.setFromValue(1.0);
		ft.setToValue(0);
		ft.play();	
	}
	public void setLayout(Integer xLoc, Integer yLoc){
		this.setX(xLoc);
		this.setY(yLoc);
		myRoot.getChildren().add(this);
	}
}
	

