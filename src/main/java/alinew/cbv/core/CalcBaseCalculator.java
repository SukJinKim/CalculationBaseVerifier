package alinew.cbv.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalcBaseCalculator {
	private static List<String> operandList = new ArrayList<>();
	private static List<String> operatorList = new ArrayList<>();
	
	public int calculateCalcBase(String calcBase) throws NumberFormatException, ScriptException {
		String formularizedCalcBase = formularizeCalcBase(calcBase);
		int priceOnCalcBase = -1;
		
		if(!formularizedCalcBase.equals("INVALID FORMAT")) {
			ScriptEngineManager factory = new ScriptEngineManager();
			
			// create a JavaScript engine
			ScriptEngine engine = factory.getEngineByName("JavaScript");
			// evaluate JavaScript code from formularized calculation base
			priceOnCalcBase = Integer.parseInt(engine.eval(formularizedCalcBase).toString());
		}
		
		//clear lists
		operandList.clear();
		operatorList.clear();
		
		return priceOnCalcBase;
	}
	
	private static String formularizeCalcBase(String calcBase) {
 		String formula = "";
		
		extractOperands(calcBase);
		extractOperators(calcBase);
			
		if(operandList.size() + 1 != operatorList.size()) {
			formula = "INVALID FORMAT";
		}else {
			for(int i = 0; i < operatorList.size(); i++) { //combine operands and operators to formularize calculation base
				if(i == operatorList.size()-1) {
					formula += operatorList.get(i);
					break;
				}
				formula += operatorList.get(i) + operandList.get(i);
			}
			
			formula = formula.replaceAll("([a-zA-Z]|[가-힣]|\\,|(\\\\|\\₩))", ""); //remove miscellaneous characters such as English, Korean, ',' , '\' and '₩'.
		}
	
		return formula;
	}
	
	private static void extractOperands(String calcBase) {
		String regex = "(\\+|\\-)"; //operand are only + and -
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(calcBase);
		
		while(m.find()) {
			operandList.add(m.group());
		}
	}
	private static void extractOperators(String calcBase) {
		String regex = "((\\\\|\\₩)\\s*(\\d*,)*(\\d)+\\s*(\\*\\s*(\\d*,)*(\\d)+\\s*([가-힣]*))*)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(calcBase);
		
		while(m.find()) {
			operatorList.add(m.group());
		}
	}
}


