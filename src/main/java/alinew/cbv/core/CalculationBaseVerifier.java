package alinew.cbv.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

import alinew.cbv.model.Result;

public class CalculationBaseVerifier {
	private List<Result> resultList = new ArrayList<>();
	
	public List<Result> run(File file) throws EncryptedDocumentException, IOException, NumberFormatException, ScriptException{
		Workbook wb = WorkbookFactory.create(file);
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		CalcBaseCalculator calcBaseCalculator = new CalcBaseCalculator();

		String calcBaseRegex = "((\\\\|\\₩)\\s*(\\d+,)*(\\d)+([가-힣]*)\\s*(\\*\\s*(\\d+,)*(\\d)+\\s*([가-힣]*))*)";
		Pattern p = Pattern.compile(calcBaseRegex);
		Matcher m = null;
		
		String sheet; //sheet name
		String pricePos; //position where price is written
		int price;
		String calcBasePos; //position where calculation base is written
		String calcBase; 
		int priceOnCalcBase;
		boolean isSame;
		
		for(Sheet sh: wb) {
			for (Row row : sh) {
	            for (Cell cell : row) {
	            	m = p.matcher(cell.toString());
	            	if(m.find()) {
	            		sheet = sh.getSheetName(); //get sheet name
	            		
	            		//get position of calcBase and price
	            		CellReference calcBaseRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
	            		CellReference priceRef = new CellReference(row.getRowNum(), cell.getColumnIndex()-2); //Generally, the column index of price cell position is 2 less than the column index of calculation base cell position.
	           
	            		calcBasePos = calcBaseRef.formatAsString(); 
	            		pricePos = priceRef.formatAsString();
	            		
	            		calcBase = cell.toString(); //get calcBase
	            		
	            		//get price
	            		Cell priceCell = row.getCell(cell.getColumnIndex()-2); 
	            		
	                    if(priceCell.getCellType().equals(CellType.FORMULA)) { 		//A case of type of cell is FORMULA
	                    	CellValue priceCellValue = evaluator.evaluate(priceCell);
	                    	price = (int) priceCellValue.getNumberValue();
	                    }else if(priceCell.getCellType().equals(CellType.NUMERIC)){ //A case of type of cell is NUMERIC
	                    	price = (int) priceCell.getNumericCellValue();
	                    }else {														//A case of type of cell is STRING
	                    	continue;
	                    }
	                    
	                    //get priceOnCalcBase
	                    priceOnCalcBase = calcBaseCalculator.calculateCalcBase(calcBase);
	                    
	                    //get same (priceOnCalcBase is -1 if it contains invalid format)
	                    isSame = (priceOnCalcBase != -1 && price == priceOnCalcBase);
	                    
	                    //add into resultList
	                    if(priceOnCalcBase == -1) {	//A case of price on calcBase has invalid format
	                    	resultList.add(new Result(sheet, pricePos, Integer.toString(price), calcBasePos, calcBase, "Invalid format", Boolean.toString(isSame)));
	                    }else { 					//A case of price on calcBase has invalid format
	                    	resultList.add(new Result(sheet, pricePos, Integer.toString(price), calcBasePos, calcBase, Integer.toString(priceOnCalcBase), Boolean.toString(isSame)));
	                    }
	            	}
	            }
	        }
		}
		
		return resultList;
	}
}