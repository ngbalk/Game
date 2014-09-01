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
		Image enemyImage = new Image(this.getClass().getResource("enemy_image.gif").toExternalForm());
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
        if(this.getLayoutBounds().intersects(myHero.myMissiles.peek().getLayoutBounds()) || this.getLayoutY() > 500)
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
		this.setLayoutY(this.getLayoutY()+10);
	}
//		Path path = new Path();
//		path.getElements().add(new MoveTo(0, 30));
//	    //path.getElements().add(new LineTo(0, 700));
//		CubicCurveTo cc1 = new CubicCurveTo();
//		cc1.setControlX1(600);
//		cc1.setControlY1(300);
//		cc1.setControlX2(-600);
//		cc1.setControlY2(400);
//		cc1.setX(0);
//		cc1.setY(650);
//	    path.getElements().add(cc1);
//	    //path.getElements().add(new CubicCurveTo(250, 600, 0, 240, 380, 240));
//	    path.setOpacity(0);
//	    Group g = new Group();
//	    myRoot.getChildren().add(g); 
//	    g.getChildren().add(path);
//		g.getChildren().add(this);
//		PathTransition pathTransition = new PathTransition();
//		pathTransition.setDuration(Duration.millis(6000));
//		pathTransition.setPath(path);
//		pathTransition.setNode(this);
//		pathTransition.play();
	}
	

