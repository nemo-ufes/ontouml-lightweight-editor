package br.ufes.inf.nemo.antipattern.relcomp;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RelCompOccurrence extends AntipatternOccurrence {
	Association a1,a2;
	Classifier a1Source, a1Target, a2Source, a2Target;
		
	/**
	 * Constructor
	 *
	 * @param parser
	 * @throws Exception
	 */
	public RelCompOccurrence(Association a1, Association a2, RelCompAntipattern ap) throws Exception 
	{
		super(ap);
		
		verifyInputs(a1, a2);
		
		this.a1 = a1;
		this.a2 = a2;
		this.a1Source = (Classifier) a1.getMemberEnd().get(0).getType();
		this.a1Target = (Classifier) a1.getMemberEnd().get(1).getType();
		this.a2Source = (Classifier) a2.getMemberEnd().get(0).getType();
		this.a2Target = (Classifier) a2.getMemberEnd().get(1).getType();
	}

	private void verifyInputs(Association a1, Association a2) throws Exception {
		if(a1==null || a2==null)
			throw new NullPointerException("RelComp: associations can't be null.");
		
		if (a1.equals(a2))
			throw new Exception("RelCom: associations can't be the same.");
		
		if(a1.getMemberEnd().size()!=2 || a2.getMemberEnd().size()!=2)
			throw new Exception("RelComp: associations must exactly 2 properties.");
		
		if(a1.getMemberEnd().get(0).getType()==null || a1.getMemberEnd().get(1).getType()==null)
			throw new Exception("RelComp: Association 1 has one of its end types as null.");
		
		if(a2.getMemberEnd().get(0).getType()==null || a2.getMemberEnd().get(1).getType()==null)
			throw new Exception("RelComp: Association 2 has one of its end types as null.");
	}

	public Association getA1() {
		return a1;
	}

	public Association getA2() {
		return a2;
	}

	public Classifier getA1Source() {
		return a1Source;
	}

	public Classifier getA1Target() {
		return a1Target;
	}

	public Classifier getA2Source() {
		return a2Source;
	}

	public Classifier getA2Target() {
		return a2Target;
	}
	
	/**
	 * Select in the OntoUML model only those elements related with this antipattern...
	 */
	@Override
	public OntoUMLParser setSelected() {
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
		
		String result = "A1: "+parser.getStringRepresentation(a1.getMemberEnd().get(0)) + 
						" - " + parser.getStringRepresentation(a1) + 
						" - " + parser.getStringRepresentation(a1.getMemberEnd().get(1)) + "\n"+
						"A2: "+parser.getStringRepresentation(a2.getMemberEnd().get(0))+ 
						" - " + parser.getStringRepresentation(a2) + 
						" - " + parser.getStringRepresentation(a2.getMemberEnd().get(1));
		return result;
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(a1)+" & "+parser.getStringRepresentation(a2);
	}
	
	public boolean a2EndsSpecializeA1Target(){
		if((a2Source.equals(a1Target) || a2Source.allParents().contains(a1Target)) && (a2Target.equals(a1Target)||a2Target.allParents().contains(a1Target)))
			return true;
		return false;
	}
	
	public boolean a2EndsSpecializeA1Source(){
		return !a2EndsSpecializeA1Target();
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
