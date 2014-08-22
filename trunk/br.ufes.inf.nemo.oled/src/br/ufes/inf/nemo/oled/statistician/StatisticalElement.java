package br.ufes.inf.nemo.oled.statistician;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLModelStatistic.TypeDetail;


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
	
	public StatisticalElement(TypeDetail detail)
	{
		this.measure = detail.getMeasure();
		this.count = detail.getCount();
		this.typePercentage = detail.getTypePercentage();
		this.allPercentage = detail.getAllPercentage();
	}
	
	protected String getMeasure() { return measure; }
	protected int getCount() { return count; }	
	protected String getAllPercentage(){ return allPercentage; }
	protected String getTypePercentage(){ return typePercentage; }	
	protected double getAllPercentageValue(){ return Double.parseDouble(allPercentage.replace("%","").replace(",",".")); } 
	protected double getTypePercentageValue(){ return Double.parseDouble(typePercentage.replace("%","").replace(",",".")); }
}
