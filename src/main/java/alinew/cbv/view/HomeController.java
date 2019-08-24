package alinew.cbv.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}	
	
	
	@FXML
	void handleOpenFileChooser(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel File", "*.xls", "*.xlsx"));
		File selectedFile = fileChooser.showOpenDialog(primaryStage);
		if (selectedFile != null) {
			textField.setText(selectedFile.getAbsolutePath());
		}
	}

	@FXML
	void showTableResult(ActionEvent event) {

	}
}
