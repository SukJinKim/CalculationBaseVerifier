package alinew.cbv;


public class PriceNCalcBase {
	private int price;
	private String calcBase;
	private String priceCellPos;
	private String calcBaseCellPos;
	

	public PriceNCalcBase(int price, String calcBase, String priceCellPos, String calcBaseCellPos) {
		super();
		this.price = price;
		this.calcBase = calcBase;
		this.priceCellPos = priceCellPos;
		this.calcBaseCellPos = calcBaseCellPos;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCalcBase() {
		return calcBase;
	}
	public void setCalcBase(String calcBase) {
		this.calcBase = calcBase;
	}
	public String getPriceCellPos() {
		return priceCellPos;
	}
	public void setPriceCellPos(String priceCellPos) {
		this.priceCellPos = priceCellPos;
	}
	public String getCalcBaseCellPos() {
		return calcBaseCellPos;
	}
	public void setCalcBaseCellPos(String calcBaseCellPos) {
		this.calcBaseCellPos = calcBaseCellPos;
	}
	
	@Override
	public String toString() {
		return "PriceNCalcBase [price=" + price + ", calcBase=" + calcBase + ", priceCellPos=" + priceCellPos
				+ ", calcBaseCellPos=" + calcBaseCellPos + "]";
	}
}

