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
	Stage myStage;
	Game myGame;
	Scene myCurrentScene;
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
	public void addStartButton(Pane gp){
		Button startButton = new Button();
		startButton.setText("Start!");
		startButton.setId("start-button");
		startButton.setLayoutX(200);
		startButton.setLayoutY(300);
		startButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				myGame = new Game(myStage);
				myGame.startGame();
			}
			
		});
		gp.getChildren().add(startButton);
	}
	
	
	


	
	
	

}
