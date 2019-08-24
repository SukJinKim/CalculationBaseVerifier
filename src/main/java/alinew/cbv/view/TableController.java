package alinew.cbv.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.script.ScriptException;

import org.apache.poi.EncryptedDocumentException;

import alinew.cbv.core.CalculationBaseVerifier;
import alinew.cbv.model.Result;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController implements Initializable {
	@FXML TableView<Result> tableView;
	
	private List<Result> results;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Result> resultList = FXCollections.observableArrayList(results);
		
		TableColumn tcSheet = tableView.getColumns().get(0);
		tcSheet.setCellValueFactory(
			new PropertyValueFactory("sheet")
		);
		tcSheet.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcPricePos = tableView.getColumns().get(1);
		tcPricePos.setCellValueFactory(
			new PropertyValueFactory("pricePos")
	    );
		tcPricePos.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcPrice = tableView.getColumns().get(2);
		tcPrice.setCellValueFactory(
			new PropertyValueFactory("pricePos")
	    );
		tcPrice.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcCalcBasePos = tableView.getColumns().get(3);
		tcCalcBasePos.setCellValueFactory(
			new PropertyValueFactory("pricePos")
	    );
		tcCalcBasePos.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcCalcBase = tableView.getColumns().get(4);
		tcCalcBase.setCellValueFactory(
			new PropertyValueFactory("pricePos")
	    );
		tcCalcBase.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcPriceOnCalcBase = tableView.getColumns().get(5);
		tcPriceOnCalcBase.setCellValueFactory(
			new PropertyValueFactory("pricePos")
	    );
		tcPriceOnCalcBase.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcSame = tableView.getColumns().get(6);
		tcSame.setCellValueFactory(
			new PropertyValueFactory("pricePos")
	    );
		tcSame.setStyle("-fx-alignment: CENTER;");
		
		tableView.setItems(resultList);
	}
	
	public void getResults(File file) throws EncryptedDocumentException, NumberFormatException, IOException, ScriptException{
		CalculationBaseVerifier cbv = new CalculationBaseVerifier();
		results = cbv.run(file);
	}

}
