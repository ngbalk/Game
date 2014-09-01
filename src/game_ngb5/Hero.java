package game_ngb5;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
