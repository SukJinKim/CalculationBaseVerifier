package alinew.cbv;

import java.io.IOException;

import alinew.cbv.view.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Home.fxml"));
		Parent root = loader.load();
		HomeController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		
		
		Scene scene = new Scene(root);
		
		
		primaryStage.setTitle("Thanos");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false); 
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
