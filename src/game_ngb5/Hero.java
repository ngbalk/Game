package game_ngb5;


import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Hero extends ImageView{
	boolean myLifeStatus;
	Integer myAmmo;

	public Hero(){
		myLifeStatus = true;
		myAmmo = 100;
		Image heroImage = new Image(this.getClass().getResource("hero_sprite.png").toExternalForm());
		this.setImage(heroImage);
		this.setLayoutX(200);
		this.setLayoutY(400);
		buildEventListeners();
		
	}
	public void buildEventListeners(){
		System.out.println("Building events");
		EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				System.out.println("event detected");
				if(event.getCode() == KeyCode.RIGHT){
					System.out.println("Going Right...");
					moveRight();
				}
				if(event.getCode() == KeyCode.LEFT){
					System.out.println("Going Left...");
					moveLeft();
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
		this.setLayoutX(this.getLayoutX() + 5);
	}
	public void moveLeft(){
		this.setLayoutX(this.getLayoutX() - 5);
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
}
