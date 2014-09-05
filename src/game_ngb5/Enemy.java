package game_ngb5;

import java.util.ArrayDeque;
import java.util.Deque;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Enemy extends ImageView {
	String myBossDirection;
	Integer myHP;
	Hero myHero;
	Pane myRoot;
	Integer myLevel;
	boolean myLifeStatus;
	
	public Enemy(Hero hero, Integer xLoc){

		myHero = hero;
		myRoot = hero.myRoot;
		myLevel = myHero.myLevel;
		myLifeStatus = true;
		Image enemyImage = null;
		if(myLevel == 1){
			enemyImage = new Image(this.getClass().getResource("enemy_image.png").toExternalForm());
		}
		if(myLevel == 2){
			enemyImage = new Image(this.getClass().getResource("enemy_image_level_2.gif").toExternalForm());
		}
		if(myLevel == 3){
			enemyImage = new Image(this.getClass().getResource("boss_image.png").toExternalForm());
		}
		this.setImage(enemyImage);
		this.setX(xLoc);
		
		myRoot.getChildren().add(this);
		if(myLevel == 3){
			this.setY(-400);
			myHP = 100;
			myBossDirection = "right";
			bossAttack();
		}
		else{
			this.setY(-100);
			myHP = 10;
			attack();
		}
		
	}
	public void isDead(){
		myLifeStatus = false;
	}
	public void attack(){
		Timeline attackFlight = new Timeline();
		attackFlight.setCycleCount(Animation.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.millis(35), new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				move();
				if(checkAndHandleCollision()){
					attackFlight.stop();
					
				}

				
			}
			
		});
		attackFlight.getKeyFrames().add(kf);
		attackFlight.play();
	}
	public boolean checkAndHandleCollision(){
		for(Missile missile : myHero.myMissiles){
	        if((this.getLayoutBounds().intersects(missile.getBoundsInLocal()) || this.getY() > 1000)) // && myLifeStatus && missile.myLifeStatus)
	                {
	        			enemyHit();
	        			if(myHP <= 0){
	        				myLifeStatus = false;
	        				explode();
	        				return true;
	        				
	        			}
	        			return false;
	                }
		}
        return false;
	}
	public void enemyHit(){
		myHP = myHP - 10;
	}
	public void bossAttack(){
		Timeline bossAttackFlight = new Timeline();
		bossAttackFlight.setCycleCount(Animation.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.millis(35), new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				bossMove();
				if(checkAndHandleCollision()){
					bossAttackFlight.stop();
					
				}

				
			}
			
		});
		bossAttackFlight.getKeyFrames().add(kf);
		bossAttackFlight.play();
		
	}
	public void bossMove(){
		if(this.getY()< -300){
			this.setY(this.getY()+5);
		}
		if(this.getX()>= -300 && this.myBossDirection == "left"){
			this.setX(this.getX() - 5);
		}
		else if(this.getX()< -300 && this.myBossDirection == "left"){
			this.setX(this.getX() + 5);
			this.myBossDirection = "right";
		}
		else if(this.getX()<= 200 && this.myBossDirection == "right"){
			this.setX(this.getX() + 5);
		}
		else if(this.getX()> 200 && this.myBossDirection == "right"){
			this.setX(this.getX() - 5);
			this.myBossDirection = "left";
		}
	}
	public void move(){
		this.setY(this.getY()+5);
	}
	public void explode(){
		Image explosionImage = new Image(this.getClass().getResource("explosion_image.gif").toExternalForm());
		this.setImage(explosionImage);
		this.setY(this.getY()-100);
		FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
		ft.setFromValue(1.0);
		ft.setToValue(0);
		ft.play();
		
	}
}
	

