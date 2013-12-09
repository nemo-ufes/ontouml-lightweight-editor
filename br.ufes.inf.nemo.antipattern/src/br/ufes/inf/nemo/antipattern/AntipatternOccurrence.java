package br.ufes.inf.nemo.antipattern;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public abstract class AntipatternOccurrence {
	
	public AntipatternOccurrence(OntoUMLParser parser){
		if (parser==null)
			throw new NullPointerException("AntipatternOccurrence: parser cannot be null.");
		this.parser = parser;
	}
	
	protected OntoUMLParser parser; 
	/**
	 * Sets the elements to be transformed to alloy on the provided parser
	 * 
	 * @param parser
	 */
	public abstract OntoUMLParser setSelected();
	
	
	
}
