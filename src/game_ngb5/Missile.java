package game_ngb5;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
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
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
public class Missile extends ImageView{
	Integer myDamage;
	boolean myLifeStatus;
	Pane myRoot;
	public Missile(Hero hero){
		myRoot = hero.myRoot;
		myLifeStatus = true;
		Image missileImage = new Image(this.getClass().getResource("missile_image.gif").toExternalForm());
		this.setImage(missileImage);
		this.setFitWidth(25);
		this.setX(hero.getX());
		this.setY(hero.getY());
		myRoot.getChildren().add(this);
	}
	public void impact(){
		myLifeStatus = false;
	}
	public void move(){
		this.setY(this.getY()-10);
	}

	public void fire(){
		Timeline missileFlight = new Timeline();
		missileFlight.setCycleCount(Animation.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				move();
				
			}
			
		});
		missileFlight.getKeyFrames().add(kf);
		missileFlight.play();
	}

}
