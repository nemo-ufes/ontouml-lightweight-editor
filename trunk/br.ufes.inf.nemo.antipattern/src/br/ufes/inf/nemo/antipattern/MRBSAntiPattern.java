package br.ufes.inf.nemo.antipattern;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class MRBSAntiPattern extends Antipattern {
		
	/**
	 * Constructor
	 *
	 * @param parser
	 * @throws Exception
	 */
	public MRBSAntiPattern(OntoUMLParser refparser) throws Exception 
	{
		
	}

	/**
	 * Select in the OntoUML model only those elements related with this antipattern...
	 */
	@Override
	public OntoUMLParser setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
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
