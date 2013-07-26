package br.ufes.inf.nemo.antipattern;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class SSRAntiPattern extends Antipattern {
	Association a1,a2;
	Classifier a1Source, a1Target, a2Source, a2Target;
		
	/**
	 * Constructor
	 *
	 * @param parser
	 * @throws Exception
	 */
	public SSRAntiPattern(Association a1, Association a2) throws Exception 
	{
		this.a1 = a1;
		this.a2 = a2;
		this.a1Source = (Classifier) a1.getMemberEnd().get(0).getType();
		this.a1Target = (Classifier) a1.getMemberEnd().get(1).getType();
		this.a2Source = (Classifier) a2.getMemberEnd().get(0).getType();
		this.a2Target = (Classifier) a2.getMemberEnd().get(1).getType();
	}

	/**
	 * Select in the OntoUML model only those elements related with this antipattern...
	 */
	@Override
	public OntoUMLParser setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		selection.add(a1);
		selection.add(a2);
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.ALL_ANCESTORS, false);
		
		return parser;		
	}
	
	/**
	 * To String method...
	 * 
	 */
	@Override
	public String toString() {
		
		String result=new String();
		
		result += a1Source.getName() + " - " + a1.getName() + " - " + a1Target.getName() + "\n";
		result += a2Source.getName() + " - " + a2.getName() + " - " + a2Target.getName();
		
		return result;
	}	
}

/*
 context _'Space Traveller'
inv closed : self.destination->forAll( x | x.oclIsTypeOf(_'System') implies self.destination->includesAll(x.oclAsType(_'System').galaxy->asSet()))

context _'Space Traveller'
inv open : self.destination->forAll( x | x.oclIsTypeOf(_'System') implies self.destination->excludesAll(x.oclAsType(_'System').galaxy->asSet()))

context _'Space Traveller'
inv open : self.destination->forAll( x | x.oclIsTypeOf(_'System') implies not self.destination->includesAll(x.oclAsType(_'System').galaxy->asSet()))
 */
