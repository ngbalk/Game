package game_ngb5;
import java.awt.RenderingHints.Key;











import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.event.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.util.Duration;
import javafx.css.*;

public class Main extends Application{
	Scene myCurrentScene;
	Stage myStage;
	Hero myHero;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		myStage = stage;
		setupStage(stage);
		stage.show();
		
		
	}
	public void spawnEnemies(){
		Integer[] firstLevelAttack = {50, 150, 250, 350, 450};
		Queue<Integer> attackPattern = new LinkedList<Integer>();
		loadPattern(firstLevelAttack, attackPattern);
		Timeline spawning = new Timeline();
		spawning.setCycleCount(Animation.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.millis(1500), new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Integer xLoc = attackPattern.poll();
				attackPattern.add(xLoc);
				Enemy enemy = new Enemy(myHero, xLoc);
				myHero.myEnemies.add(enemy);
			}
		});
		spawning.getKeyFrames().add(kf);
		spawning.play();
	}
	public Queue<Integer> loadPattern(Integer[] pattern, Queue<Integer> q){
		for(Integer i : pattern){
			q.add(i);
		}
		return q;
	}
	
	public void cleaningService(){
		Timeline cleaning = new Timeline();
		cleaning.setCycleCount(Animation.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				cleanDeadEnemiesAndMissiles();
			}
		});
		cleaning.getKeyFrames().add(kf);
		cleaning.play();
		
	}
	
	public void cleanDeadEnemiesAndMissiles(){
		for(Enemy enemy : myHero.myEnemies){
			if(!enemy.myLifeStatus){
				enemy.setX(5000);
			}
		}
		for(Missile missile : myHero.myMissiles){
			if(!missile.myLifeStatus){
				missile.setX(5000);
			}
		}
	}
	public Stage setupStage(Stage stage){
		stage.setMaxHeight(500);
		stage.setMaxWidth(500);
		stage.setMinWidth(500);
		stage.setMinHeight(500);
		Pane gp = new Pane();
		//StackPane sp = new StackPane();
		gp.setId("welcome-background");
		//root.getChildren().add(background);
		Scene scene = new Scene(gp, 500, 500);
		scene.getStylesheets().add(Main.class.getResource("stylesheet.css").toExternalForm());
		stage.setTitle("Back to Earth!");
		addWelcomeText(gp);
		addStartButton(gp);
		//root.getChildren().add(group);
		stage.setScene(scene);
		myCurrentScene = scene;
		return stage;
	}
	public void addWelcomeText(Pane gp){
		
		Text text = new Text();
		text.setText("Back to Earth!");
		text.setId("splash-page");
		text.setLayoutX(100);
		text.setLayoutY(250);
		gp.getChildren().add(text);
	}
	
	
	
	public void addStartButton(Pane gp){
		Button startButton = new Button();
		startButton.setText("Start!");
		startButton.setId("start-button");
		startButton.setLayoutX(200);
		startButton.setLayoutY(400);
		startButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				myStage.setScene(buildPlayScene());
				spawnEnemies();
				cleaningService();
				
				
			}
			
		});
		gp.getChildren().add(startButton);
	}
	
	public Scene buildPlayScene(){
		Pane root = new Pane();
		root.setId("play-scene");
		Scene playScene = new Scene(root, 500, 500);
		playScene.getStylesheets().addAll(this.getClass().getResource("stylesheet.css").toExternalForm());
		root.getChildren().add(buildHero(root));
		return playScene;
	}
	
	public Hero buildHero(Pane root){
		Hero hero = new Hero(root);
		myHero = hero;
		hero.requestFocus();
		return hero;
	}

	
	
	

}
