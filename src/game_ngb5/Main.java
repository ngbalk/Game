package game_ngb5;
import java.awt.RenderingHints.Key;



import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.css.*;

public class Main extends Application{

	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		setupStage(stage);
		stage.show();
	}
	public Stage setupStage(Stage stage){
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500, Color.BLUE);
		scene.getStylesheets().add(Main.class.getResource("stylesheet.css").toExternalForm());
		stage.setTitle("Back to Earth!");
		addWelcomeText(root);
		stage.setScene(scene);
		return stage;
	}
	public Group addWelcomeText(Group group){
		Text text = new Text();
		text.setText("Back to Earth!");
		text.setId("splash-page");
		text.setY(70);
		text.setX(100);
		group.getChildren().add(text);
		return group;
	}

}
