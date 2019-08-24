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
	


	public Result(SimpleStringProperty sheet, SimpleStringProperty pricePos, SimpleStringProperty price,
			SimpleStringProperty calcBasePos, SimpleStringProperty calcBase, SimpleStringProperty priceOnCalcBase,
			SimpleStringProperty same) {
		super();
		this.sheet = sheet;
		this.pricePos = pricePos;
		this.price = price;
		this.calcBasePos = calcBasePos;
		this.calcBase = calcBase;
		this.priceOnCalcBase = priceOnCalcBase;
		this.same = same;
	}
	
	public SimpleStringProperty getSheet() {
		return sheet;
	}
	public void setSheet(SimpleStringProperty sheet) {
		this.sheet = sheet;
	}
	public SimpleStringProperty getPricePos() {
		return pricePos;
	}
	public void setPricePos(SimpleStringProperty pricePos) {
		this.pricePos = pricePos;
	}
	public SimpleStringProperty getPrice() {
		return price;
	}
	public void setPrice(SimpleStringProperty price) {
		this.price = price;
	}
	public SimpleStringProperty getCalcBasePos() {
		return calcBasePos;
	}
	public void setCalcBasePos(SimpleStringProperty calcBasePos) {
		this.calcBasePos = calcBasePos;
	}
	public SimpleStringProperty getCalcBase() {
		return calcBase;
	}
	public void setCalcBase(SimpleStringProperty calcBase) {
		this.calcBase = calcBase;
	}
	public SimpleStringProperty getPriceOnCalcBase() {
		return priceOnCalcBase;
	}
	public void setPriceOnCalcBase(SimpleStringProperty priceOnCalcBase) {
		this.priceOnCalcBase = priceOnCalcBase;
	}
	public SimpleStringProperty getSame() {
		return same;
	}
	public void setSame(SimpleStringProperty same) {
		this.same = same;
	}
}
