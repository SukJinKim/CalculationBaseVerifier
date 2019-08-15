package alinew.cbv;

import java.io.File;
import java.io.FileNotFoundException;
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

public class CalculationBaseVerifier {
	private static final String PATH = "/Users/kimsukjin/Downloads/2019-1 자치회 다은 추가경정안(합숙비 추가한 최종본).xlsx"; //Add Path 
	private static final int WIDTH = 20;
	
	private static List<PriceNCalcBase> PriceNCalcBaseList = new ArrayList<>();
	private static List<Integer> priceOnCalcBaseList = new ArrayList<>();
	
	public static void main(String[] args) {
		try {
			Workbook wb = WorkbookFactory.create(new File(PATH));
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			CalcBaseCalculator calcBaseCalculator = new CalcBaseCalculator();

			String regex = "((\\\\|\\₩)\\s*(\\d*,)*(\\d)+\\s*(\\*\\s*(\\d)+\\s*([가-힣]*))*)";
			Pattern p = Pattern.compile(regex);
			Matcher m = null;
			
			int price;
			String calcBase;
			boolean isPriceNCalcBaseSame;
			PriceNCalcBase priceNCalcBase;
			String priceOnCalcBase;
			
			for(Sheet sheet: wb) {
				//show sheet name
				System.out.println("\n\tVerifying calculation base from " + sheet.getSheetName());
				for (Row row : sheet) {
		            for (Cell cell : row) {
		            	m = p.matcher(cell.toString());
		            	if(m.find()) {
		            		CellReference calcBaseCellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
		            		CellReference priceCellRef = new CellReference(row.getRowNum(), cell.getColumnIndex()-2);
		            		String priceCellPos = priceCellRef.formatAsString();
		            		String calcBaseCellPos = calcBaseCellRef.formatAsString();
		            		
		            		System.out.println("\nExtracting calc data from " + priceCellPos + " and calcBase data from " + calcBaseCellPos);
		            		
		                    calcBase = cell.toString();
		                    
		                    Cell priceCell = row.getCell(cell.getColumnIndex()-2);

		                    if(priceCell.getCellType().equals(CellType.FORMULA)) { 		//A case of type of cell is FORMULA
		                    	CellValue priceCellValue = evaluator.evaluate(priceCell);
		                    	price = (int) priceCellValue.getNumberValue();
		                    }else { 													//A case of type of cell is not FORMULA
		                    	price = (int) priceCell.getNumericCellValue();
		                    }
		                    PriceNCalcBaseList.add(new PriceNCalcBase(price, calcBase, priceCellPos, calcBaseCellPos));
		            	}
		            }
		        }
				//calculate price on calculation base and add into list
				for(PriceNCalcBase pnc: PriceNCalcBaseList) {
					int priceOnCalBase = calcBaseCalculator.calculateCalcBase(pnc.getCalcBase());
					priceOnCalcBaseList.add(priceOnCalBase);
				}
				
				//Compare and print results
				System.out.println(String.format("\n\n\t%-30s %-30s %-30s %-30s %-30s %-30s", "Price Position", "Price", "Calculation Base Position", "Calculation Base", "Price On Calculation Base", "same?"));
				
				if(PriceNCalcBaseList.size() == priceOnCalcBaseList.size()) {
					for(int i = 0; i < PriceNCalcBaseList.size(); i++) {
						priceNCalcBase = PriceNCalcBaseList.get(i);
						priceOnCalcBase = (priceOnCalcBaseList.get(i) != -1) ? priceOnCalcBaseList.get(i).toString() : "Invalid Format" ;
						
						isPriceNCalcBaseSame = (priceNCalcBase.getPrice() == priceOnCalcBaseList.get(i));
						
						System.out.println(String.format("\t%-30s %-30d %-30s %-30s %-30s %-30b",
								priceNCalcBase.getPriceCellPos(), priceNCalcBase.getPrice(), priceNCalcBase.getCalcBaseCellPos(), trim(priceNCalcBase.getCalcBase(), WIDTH), priceOnCalcBase,  isPriceNCalcBaseSame));
					}
				}else {
					System.err.println("...ERROR...");
				}
				//clear all lists
				PriceNCalcBaseList.clear();
				priceOnCalcBaseList.clear();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 10) + "...";
        else
            return s;
    }

}
