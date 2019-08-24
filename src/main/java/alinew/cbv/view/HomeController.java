package alinew.cbv.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.script.ScriptException;

import org.apache.poi.EncryptedDocumentException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;

public class HomeController implements Initializable {
	@FXML
    private TextField textField;

    @FXML
    private Button browseBtn;

    @FXML
    private Button snapBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	private Stage primaryStage;
	private File selectedFile;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}	
	
	
	@FXML
	void handleOpenFileChooser(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel File", "*.xls", "*.xlsx"));
		selectedFile = fileChooser.showOpenDialog(primaryStage);
		if (selectedFile != null) {
			textField.setText(selectedFile.getAbsolutePath());
		}
	}

	@FXML
	void showTableResult(ActionEvent event) throws IOException, EncryptedDocumentException, NumberFormatException, ScriptException {
		if(selectedFile != null) {
			//dialog setting
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			dialog.setTitle("Result");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Table.fxml"));
			Parent root = loader.load();
			
			TableController controller = loader.getController();
			controller.getResults(selectedFile);
			
			Scene scene = new Scene(root);
			
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
		}
	}
}
