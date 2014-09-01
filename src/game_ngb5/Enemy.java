package game_ngb5;

import java.util.ArrayDeque;
import java.util.Deque;

import javafx.animation.Animation;
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
	Integer myLevel;
	boolean myLifeStatus;
	
	public Enemy(Hero hero, Integer level){
		myHero = hero;
		myRoot = hero.myRoot;
		myHP = 100;
		myLevel = level;
		myLifeStatus = true;
		Image enemyImage = new Image(this.getClass().getResource("enemy_image.png").toExternalForm());
		this.setImage(enemyImage);
		this.setX(250);
		this.setY(250);
		//this.setLayoutX(250);
		//this.setLayoutY(250);
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
		KeyFrame kf = new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>(){

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
        if(this.getLayoutBounds().intersects(myHero.myMissiles.peek().getBoundsInLocal()) || this.getY() > 500)
                {
        			System.out.println("collision detected");
        			System.out.println("  Enemy: " + this.getBoundsInLocal().toString());
        			System.out.println("Missile: " + myHero.myMissiles.peek().getBoundsInLocal().toString());
                    this.setVisible(false);
                    myRoot.getChildren().remove(this);
                    return true;
                }
        return false;
	}
		
	public void move(){
		this.setY(this.getY()+10);
	}
}
	

