package alinew.cbv;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Main.fxml"));
		Parent root = loader.load();
		
		
		Scene scene = new Scene(root);
		
		
		primaryStage.setTitle("CBV (a.k.a. Thanos)");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false); //make unresizable
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
