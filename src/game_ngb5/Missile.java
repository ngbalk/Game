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
import javafx.scene.transform.Rotate;
public class Missile extends ImageView{
	Integer myDamage;
	boolean myLifeStatus;
	public Missile(Hero hero){
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
	public void move(){
		this.setLayoutY(this.getLayoutY() - 1);
	}

}
