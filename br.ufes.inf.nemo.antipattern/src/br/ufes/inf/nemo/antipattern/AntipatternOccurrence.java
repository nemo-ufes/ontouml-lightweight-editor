package br.ufes.inf.nemo.antipattern;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public abstract class AntipatternOccurrence {
	
	protected boolean isFixed;
	protected Antipattern<?> antipattern;
	protected OntoUMLParser parser; 
	
	public AntipatternOccurrence(Antipattern<?> antipattern){
		
		if (antipattern==null)
			throw new NullPointerException("AntipatternOccurrence: antipattern cannot be null.");
		
		if (antipattern.parser==null)
			throw new NullPointerException("AntipatternOccurrence: parser cannot be null.");
		
		this.antipattern = antipattern;
		this.parser = antipattern.parser;
		this.isFixed = false;
		
	}
	/**
	 * Sets the elements to be transformed to alloy on the provided parser
	 * 
	 * @param parser
	 */
	public abstract OntoUMLParser setSelected();
	
	public abstract String getShortName();
	
	public boolean isFixed(){
		return isFixed;
	}
	
	public Antipattern<?> getaAntiPatternType(){
		return antipattern;
	}
	
	
	
	
}
