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
	Pane myRoot;
	Scene myCurrentScene;
	Stage myStage;
	Hero myHero;
	Integer myGameMillis;
	Timeline myGameLoop;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		myStage = stage;
		setupStage(stage);
		stage.show();
		
		
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
		gp.setId("welcome-background");
		Scene scene = new Scene(gp, 500, 500);
		scene.getStylesheets().add(Main.class.getResource("stylesheet.css").toExternalForm());
		stage.setTitle("Back to Earth!");
		addWelcomeText(gp);
		addStartButton(gp);
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
	
	public void gameHandler(){
		myHero.myScore = 0;
		myHero.myLevel = 1;
		Spawner spawnEnemies1 = new Spawner(myHero);
		Spawner spawnEnemies2 = new Spawner(myHero);
		myGameMillis = 0;
		cleaningService();
		Timeline gameLoop = new Timeline();
		myGameLoop = gameLoop;
		gameLoop.setCycleCount(Animation.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if(myGameMillis == 0){
					Spawner spawnEnemies1 = new Spawner(myHero);
					spawnEnemies1.playSpawning();
				}
				if(myGameMillis == 45000){
					spawnEnemies1.killSpawning();
					myHero.myLevel++;
					Spawner spawnEnemies2 = new Spawner(myHero);
					spawnEnemies2.playSpawning();
				}
				if(myGameMillis == 90000){
					spawnEnemies2.killSpawning();
					myHero.myLevel ++;
				}
				
				if(myHero.myLevel == 3){
					spawnEnemies1.killSpawning();
					spawnEnemies2.killSpawning();
					endGameHandler();
				}
				if(!myHero.myLifeStatus){
					
					spawnEnemies1.killSpawning();
				
					spawnEnemies2.killSpawning();
					endGameHandler();
				}
				myGameMillis += 10;
			}
		});
		gameLoop.getKeyFrames().add(kf);
		gameLoop.play();
	}
	public void endGameHandler(){
		myGameLoop.stop();
		myGameMillis = 0;
		System.out.println("hero destroyed");
		Timeline endGame = new Timeline();
		endGame.setCycleCount(Animation.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				
				if(myGameMillis == 3010){
					Text finalText;
					Text finalScore;
					if(myHero.myLifeStatus){
						finalText = new Text("Welcome Home, Hero!");
						finalText.setX(155);
						finalText.setY(250);
						finalScore = new Text(10, 10 , "Score: " + myHero.myScore.toString());
						finalScore.setX(155);
						finalScore.setY(280);
					}
					else{
						finalText = new Text("You Lose!");
						finalText.setX(155);
						finalText.setY(250);
						finalScore = new Text("Score: " + myHero.myScore.toString());
						finalScore.setX(155);
						finalScore.setY(300);
					}
					
					finalText.setId("splash-page");
					finalScore.setId("splash-page");
					myRoot.getChildren().add(finalText);
					myRoot.getChildren().add(finalScore);
					
				}
				
				if(myGameMillis == 8010){
					try {
						setupStage(myStage);
						myGameMillis = 0;
						endGame.stop();
						return;
					} catch (Exception e) {

					}
				}
				myGameMillis += 1000;
			}
		});
		endGame.getKeyFrames().add(kf);
		endGame.play();
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
				myCurrentScene = buildPlayScene();
				myStage.setScene(myCurrentScene);
				gameHandler();
				
				
				
			}
			
		});
		gp.getChildren().add(startButton);
	}
	
	public Scene buildPlayScene(){
		Pane root = new Pane();
		myRoot = root;
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
