package game_ngb5;
import java.awt.RenderingHints.Key;











import javafx.event.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
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
import javafx.css.*;

public class Main extends Application{
	Scene myCurrentScene;
	Stage myStage;
	
	
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
				
				
			}
			
		});
		gp.getChildren().add(startButton);
	}
	
	public Scene buildPlayScene(){
		Pane root = new Pane();
		root.setId("play-scene");
		Scene playScene = new Scene(root, 500, 500);
		playScene.getStylesheets().addAll(this.getClass().getResource("stylesheet.css").toExternalForm());
		root.getChildren().add(buildHero());
		return playScene;
	}
	
	public Hero buildHero(){
		Hero hero = new Hero();
		return hero;
	}
	
	
	

}
