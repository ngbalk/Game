package game_ngb5;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game {
	Enemy myBoss; 
	Timeline myGameCycler;
	Spawner myCurrentSpawner;
	Integer myGameTimer;
	Text myClock;
	Text myScoreboard;
	Hero myHero;
	Pane myRoot;
	Stage myStage;
	
	public Game(Stage stage){
		myStage = stage;
		myGameTimer = 0;
		prepareGameScene();
		

	}
	public void startGame(){
		generateGameClock();
		generateScoreboard();
		beginSpawningEnemies();
		cleaningService();
		beginGameCycle();
	}
	public void beginGameCycle(){
		myGameCycler = new Timeline();
		myGameCycler.setCycleCount(Animation.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				handleLevelUp();
				manageGameClock();
				manageScoreboard();
				if(myHero.myLevel == 4 || !myHero.myLifeStatus){
						endGameHandler();
				}
				myGameTimer += 10;	
			}	
		});
		myGameCycler.getKeyFrames().add(kf);
		myGameCycler.play();
		
	}
	public void beginSpawningEnemies(){
		myCurrentSpawner = new Spawner(myHero);
		myCurrentSpawner.playSpawning();
	}
	
	public void prepareGameScene(){
		Scene toSetScene = buildGameScene();
		buildHero();
		myStage.setScene(toSetScene);
	}
	
	public void handleLevelUp(){
		if(myGameTimer == 20000){
			myCurrentSpawner.killSpawning();
			myHero.myLevel++;
			myCurrentSpawner = new Spawner(myHero);
			myCurrentSpawner.playSpawning();
		}
		if(myGameTimer == 40000){
			myCurrentSpawner.killSpawning();
			myHero.myLevel ++;
			Enemy boss = new Enemy(myHero, 100);
			myHero.myEnemies.add(boss);
			myBoss = boss;
			
		}
		if(myGameTimer > 40000){
			if(!myBoss.myLifeStatus){
				myCurrentSpawner.killSpawning();
				myHero.myLevel++;
			}
		}
		
	}
	public void cleaningService(){
		Timeline cleaning = new Timeline();
		cleaning.setCycleCount(Animation.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.millis(5), new EventHandler<ActionEvent>(){
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
	public void endGameHandler() {
		myGameCycler.stop();
		System.out.println("hero destroyed");
		setFinalScene();
		addReplayButton();

	}
	public void setFinalScene(){
		Text finalText = new Text();
		finalText.setX(130);
		finalText.setY(250);
		finalText.setId("splash-page");
		Text finalScore = new Text("Score: " + myHero.myScore.toString());
		finalScore.setX(155);
		finalScore.setY(300);
		finalScore.setId("splash-page");
		
		if(myHero.myLifeStatus){
			finalText.setText("Welcome Home!");
		}
		else{
			finalText.setText("You Lose!");
		}
		myHero.myRoot.getChildren().add(finalText);
		myHero.myRoot.getChildren().add(finalScore);
	}
	
	public void buildHero(){
		myHero = new Hero(myRoot);
		myHero.myScore = 0;
		myHero.myLevel = 1;
		myHero.requestFocus();
		myRoot.getChildren().add(myHero);
	}
	public Scene buildGameScene(){
		myRoot = new Pane();
		myRoot.setId("play-scene");
		Scene gameScene = new Scene(myRoot, 500, 500);
		gameScene.getStylesheets().addAll(this.getClass().getResource("stylesheet.css").toExternalForm());
		return gameScene;
	}
	
	public void addReplayButton(){
		Button replayButton = new Button();
		replayButton.setText("Play Again!");
		replayButton.setId("start-button");
		replayButton.setLayoutX(200);
		replayButton.setLayoutY(100);
		replayButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				Game newGame = new Game(myStage);
				newGame.startGame();
			}
			
		});
		myRoot.getChildren().add(replayButton);
	}
	public void generateGameClock(){
		Integer res = myGameTimer / new Integer(1000);
		myClock = new Text(res.toString());
		myClock.setX(30);
		myClock.setY(50);
		myClock.setId("clock");
		myClock.setFill(Color.WHITE);
		myRoot.getChildren().add(myClock);	
	}
	public void manageGameClock(){
		Integer res = myGameTimer / new Integer(1000);
		myClock.setText(res.toString());
	}
	public void manageScoreboard(){
		myScoreboard.setText(myHero.myScore.toString());
	}
	public void generateScoreboard(){
		myScoreboard = new Text(myHero.myScore.toString());
		myScoreboard.setX(350);
		myScoreboard.setY(50);
		myScoreboard.setId("scoreboard");
		myScoreboard.setFill(Color.WHITE);
		myRoot.getChildren().add(myScoreboard);
	}
}
