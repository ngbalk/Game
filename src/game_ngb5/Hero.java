package game_ngb5;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Hero extends ImageView{
	ArrayList<Missile> myMissiles;
	ArrayList<Enemy> myEnemies;
	Pane myRoot;
	boolean myLifeStatus;
	Integer myAmmo;

	public Hero(Pane root){
		myRoot = root;
		myLifeStatus = true;
		myAmmo = 100;
		myMissiles = new ArrayList<Missile>();
		myEnemies = new ArrayList<Enemy>();
		Image heroImage = new Image(this.getClass().getResource("hero_sprite.png").toExternalForm());
		this.setImage(heroImage);
		this.setX(200);
		this.setY(400);
		buildEventListeners();
		
	}
	public void buildEventListeners(){
		EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.RIGHT){
					
					moveRight();
				}
				if(event.getCode() == KeyCode.LEFT){
					
					moveLeft();
				}
				if(event.getCode() == KeyCode.SPACE){
					fireMissile();
					
				}
					
			}
		};
		this.setFocusTraversable(true);
		this.requestFocus();
		this.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
		System.out.println(this.isFocused());
		System.out.println(this.focusedProperty().toString());
		System.out.println("handler created");
	}
	

		
	
	public void moveRight(){
		if(this.getX() < 420){
			System.out.println("Going Right...");
			this.setX(this.getX() + 15);
		}
		System.out.println(this.getX());
	}
	public void moveLeft(){
		if(this.getX() > -5){
			System.out.println("Going Left...");
			this.setX(this.getX() - 15);
		}	
		System.out.println(this.getX());
	}
	public void isDead(){
		myLifeStatus = false;
	}
	public void shotFired(){
		myAmmo = myAmmo - 1;
	}
	public boolean hasAmmo(){
		return myAmmo != 0;
	}
	public void fireMissile(){
		System.out.println("Firing missile...");
		Missile missile = new Missile(this);
		myMissiles.add(missile);
		missile.fire();
		
		
	}
}
