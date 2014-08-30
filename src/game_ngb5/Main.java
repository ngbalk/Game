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

public class Main extends Application{

	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500, Color.BLUE);
		stage.setTitle("Back to Earth!");
		stage.setScene(scene);
		stage.show();
		
	}

}
