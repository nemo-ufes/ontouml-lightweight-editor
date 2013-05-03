package br.ufes.inf.nemo.antipattern;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class SSRAntiPattern extends Antipattern {
		
	/**
	 * Constructor
	 *
	 * @param parser
	 * @throws Exception
	 */
	public SSRAntiPattern(OntoUMLParser refparser) throws Exception 
	{
		
	}

	/**
	 * Select in the OntoUML model only those elements related with this antipattern...
	 */
	@Override
	public void setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);		
	}
	
	/**
	 * To String method...
	 * 
	 */
	@Override
	public String toString() {
		
		String result=new String();
	
		return result;
	}	
}
