package game_ngb5;


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
	Pane myRoot;
	boolean myLifeStatus;
	Integer myAmmo;

	public Hero(Pane root){
		myRoot = root;
		myLifeStatus = true;
		myAmmo = 100;
		Image heroImage = new Image(this.getClass().getResource("hero_sprite.png").toExternalForm());
		this.setImage(heroImage);
		this.setLayoutX(200);
		this.setLayoutY(400);
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
					dispatchEnemy();
					
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
			
		
	public void dispatchEnemy(){
		Enemy enemy = new Enemy(this, 1);
	}
	
	public void moveRight(){
		if(this.getLayoutX() < 420){
			System.out.println("Going Right...");
			this.setLayoutX(this.getLayoutX() + 5);
		}
		System.out.println(this.getLayoutX());
	}
	public void moveLeft(){
		if(this.getLayoutX() > -5){
			System.out.println("Going Left...");
			this.setLayoutX(this.getLayoutX() - 5);
		}	
		System.out.println(this.getLayoutX());
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
		missile.fire();
		
	}
}
