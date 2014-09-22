package br.ufes.inf.nemo.antipattern;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;

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
	
	public Antipattern<?> getAntipattern() {
		return antipattern;
	}

	public OutcomeFixer getFixer() {
		return fixer;
	}
	
	/**
	 * Sets the elements to be transformed to alloy on the provided parser
	 * 
	 * @param parser
	 */
	public abstract OntoUMLParser setSelected();
	
	public abstract String getShortName();
	
	public boolean isFixed(){ return isFixed; }
	
	public void setIsFixed(boolean value) { isFixed=value; }
	
	public OntoUMLParser getParser(){
		return antipattern.getParser();
	}
	
	public String addQuotes (String name){
		return "_'"+name+"'";
	}
}
