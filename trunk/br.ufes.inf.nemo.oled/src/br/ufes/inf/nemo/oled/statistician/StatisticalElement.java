package br.ufes.inf.nemo.oled.statistician;


/**
 * @author John Guerson
 */
public class StatisticalElement {
	
	protected String measure;
	protected int count;	
	protected String typePercentage;
	protected String allPercentage;
	
	public StatisticalElement(String measure, int count, String typePercentage, String allPercentage)
	{
		this.measure = measure;
		this.count = count;
		this.typePercentage = typePercentage;
		this.allPercentage = allPercentage;
	}
	
	protected String getMeasure() { return measure; }
	protected int getCount() { return count; }	
	protected String getAllPercentage(){ return allPercentage; }
	protected String getTypePercentage(){ return typePercentage; }	
	protected double getAllPercentageValue(){ return Double.parseDouble(allPercentage.replace("%","").replace(",",".")); } 
	protected double getTypePercentageValue(){ return Double.parseDouble(typePercentage.replace("%","").replace(",",".")); }
}
