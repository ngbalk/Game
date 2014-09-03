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
	Hero myHero;
	Pane myRoot;
	Integer myHP;
	boolean myLifeStatus;
	
	public Enemy(Hero hero, Integer xLoc){
		myHero = hero;
		myRoot = hero.myRoot;
		myHP = 100;
		myLifeStatus = true;
		Image enemyImage = new Image(this.getClass().getResource("enemy_image.png").toExternalForm());
		this.setImage(enemyImage);
		this.setX(xLoc);
		this.setY(-100);
		myRoot.getChildren().add(this);
		attack();
		
		
	}
	public void isDead(){
		myHP = 0;
		myLifeStatus = false;
	}
	public void isHit(Integer damage){
		myHP -= damage;
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
	        			
	        			explode();
	                    myLifeStatus = false;
	                    return true;
	                }
		}
        return false;
	}
		
	public void move(){
		this.setY(this.getY()+5);
	}
	public void explode(){
		Image explosionImage = new Image(this.getClass().getResource("explosion_image.gif").toExternalForm());
		this.setImage(explosionImage);
		this.setY(this.getY()-50);
		FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
		ft.setFromValue(1.0);
		ft.setToValue(0);
		ft.play();
		
	}
}
	

