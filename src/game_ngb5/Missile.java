package game_ngb5;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.animation.PathTransition;
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
		this.getTransforms().add(new Rotate(270, 0, 0));
		this.setLayoutX(hero.getLayoutX() + 32);
		this.setLayoutY(hero.getLayoutY() + 15);
	}
	public void impact(){
		myLifeStatus = false;
	}
	public void fire(){
		Path path = new Path();
		path.getElements().add(new MoveTo(35, 30));
	    path.getElements().add(new LineTo(35, -500));
	    Group g = new Group();
	    myRoot.getChildren().add(g); 
	    g.getChildren().add(path);
		g.getChildren().add(this);
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(1000));
		pathTransition.setPath(path);
		pathTransition.setNode(this);
		pathTransition.play();
	}

}
