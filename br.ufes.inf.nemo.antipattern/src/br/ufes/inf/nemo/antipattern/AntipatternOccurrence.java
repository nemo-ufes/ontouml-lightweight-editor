package br.ufes.inf.nemo.antipattern;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public abstract class AntipatternOccurrence {
	
	protected boolean isFixed;
	protected Antipattern<?> antipattern;
	protected OntoUMLParser parser; 
	
	protected OutcomeFixer fixer;
	protected Fix fix;
	
	public AntipatternOccurrence(Antipattern<?> antipattern){
		
		if (antipattern==null)
			throw new NullPointerException("AntipatternOccurrence: antipattern cannot be null.");
		
		if (antipattern.parser==null)
			throw new NullPointerException("AntipatternOccurrence: parser cannot be null.");
		
		this.antipattern = antipattern;
		this.parser = antipattern.parser;
		this.fixer = new OutcomeFixer(parser.getModel());
		this.fix = new Fix();
		this.isFixed = false;		
	}
	
	public Fix getFix()
	{
		return fix;
	}
	
	/**
	 * Sets the elements to be transformed to alloy on the provided parser
	 * 
	 * @param parser
	 */
	public abstract OntoUMLParser setSelected();
	
	public abstract String getShortName();
	
	public boolean isFixed(){ return isFixed; }
	
}
