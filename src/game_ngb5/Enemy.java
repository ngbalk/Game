package game_ngb5;

import javafx.animation.PathTransition;
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
	Pane myRoot;
	Integer myHP;
	Integer myLevel;
	boolean myLifeStatus;
	
	public Enemy(Hero hero, Integer level){
		myRoot = hero.myRoot;
		myHP = 100;
		myLevel = level;
		myLifeStatus = true;
		Image enemyImage = new Image(this.getClass().getResource("enemy_image.gif").toExternalForm());
		this.setImage(enemyImage);
		this.setLayoutX(250);
		this.setLayoutY(-100);
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
		Path path = new Path();
		path.getElements().add(new MoveTo(0, 30));
	    //path.getElements().add(new LineTo(0, 700));
		CubicCurveTo cc1 = new CubicCurveTo();
		cc1.setControlX1(600);
		cc1.setControlY1(300);
		cc1.setControlX2(-600);
		cc1.setControlY2(400);
		cc1.setX(0);
		cc1.setY(650);
	    path.getElements().add(cc1);
	    //path.getElements().add(new CubicCurveTo(250, 600, 0, 240, 380, 240));
	    path.setOpacity(0);
	    Group g = new Group();
	    myRoot.getChildren().add(g); 
	    g.getChildren().add(path);
		g.getChildren().add(this);
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(6000));
		pathTransition.setPath(path);
		pathTransition.setNode(this);
		pathTransition.play();
	}
}
