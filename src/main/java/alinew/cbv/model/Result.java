package alinew.cbv.model;

import javafx.beans.property.SimpleStringProperty;

public class Result {
	private SimpleStringProperty sheet;
	private SimpleStringProperty pricePos;
	private SimpleStringProperty price;
	private SimpleStringProperty calcBasePos;
	private SimpleStringProperty calcBase;
	private SimpleStringProperty priceOnCalcBase;
	private SimpleStringProperty same;
	
	public Result(String sheet, String pricePos, String price,
			String calcBasePos, String calcBase, String priceOnCalcBase,
			String same) {
		super();
		this.sheet = new SimpleStringProperty(sheet);
		this.pricePos = new SimpleStringProperty(pricePos);
		this.price = new SimpleStringProperty(price);
		this.calcBasePos = new SimpleStringProperty(calcBasePos);
		this.calcBase = new SimpleStringProperty(calcBase);
		this.priceOnCalcBase = new SimpleStringProperty(priceOnCalcBase);
		this.same = new SimpleStringProperty(same);
	}
	
	public String getSheet() {
		return sheet.get();
	}
	
	public void setSheet(String sheet) {
		this.sheet.set(sheet);
	}
	
	public String getPricePos() {
		return pricePos.get();
	}
	public void setPricePos(String pricePos) {
		this.pricePos.set(pricePos);
	}
	public String getPrice() {
		return price.get();
	}
	public void setPrice(String price) {
		this.price.set(price);
	}
	public String getCalcBasePos() {
		return calcBasePos.get();
	}
	public void setCalcBasePos(String calcBasePos) {
		this.calcBasePos.set(calcBasePos);
	}
	public String getCalcBase() {
		return calcBase.get();
	}
	public void setCalcBase(String calcBase) {
		this.calcBase.set(calcBase);
	}
	public String getPriceOnCalcBase() {
		return priceOnCalcBase.get();
	}
	public void setPriceOnCalcBase(String priceOnCalcBase) {
		this.priceOnCalcBase.set(priceOnCalcBase);
	}
	public String getSame() {
		return same.get();
	}
	public void setSame(String same) {
		this.same.set(same);
	}
}
