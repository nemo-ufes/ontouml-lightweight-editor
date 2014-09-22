package br.ufes.inf.nemo.antipattern.relcomp;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

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
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.ALL_ANCESTORS, false);
		
		return parser;		
	}
	
	/**
	 * To String method...
	 * 
	 */
	@Override
	public String toString() {
		
//		String result = "A1: "+parser.getStringRepresentation(a1.getMemberEnd().get(0)) + 
//						" - " + parser.getStringRepresentation(a1) + 
//						" - " + parser.getStringRepresentation(a1.getMemberEnd().get(1)) + "\n"+
//						"A2: "+parser.getStringRepresentation(a2.getMemberEnd().get(0))+ 
//						" - " + parser.getStringRepresentation(a2) + 
//						" - " + parser.getStringRepresentation(a2.getMemberEnd().get(1));
		String result = OntoUMLNameHelper.getCompleteName(a1)+"\n"+OntoUMLNameHelper.getCompleteName(a2);
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
	
	private String generateOCL(Property contextProperty, int type, int value){
		
		String context, oppositeProperty, oppositeType, generalOppositePropertyName, rule;
		String castContext = "", castOpposite = "";
		Property generalOppositeProperty;
		
		fix.addAll(fixer.fixPropertyName(contextProperty.getOpposite()));
		
		context = contextProperty.getType().getName();
		oppositeProperty = addQuotes(contextProperty.getOpposite().getName());
		oppositeType = addQuotes(contextProperty.getOpposite().getType().getName());
		
		if (a2EndsSpecializeA1Source())
			generalOppositeProperty = a1.getMemberEnd().get(1);
		else
			generalOppositeProperty = a1.getMemberEnd().get(0);
		
		fix.addAll(fixer.fixPropertyName(generalOppositeProperty));
		
		generalOppositePropertyName = addQuotes(generalOppositeProperty.getName());
		rule = "self."+oppositeProperty+"->asSet()->forAll( x: "+oppositeType+" | self";
		
		if(!contextProperty.getType().equals(generalOppositeProperty.getOpposite().getType())){
			castContext = ".oclAsType("+addQuotes(generalOppositeProperty.getOpposite().getType().getName())+")";
		}
		
		rule += castContext+"."+generalOppositePropertyName+"->asSet()->";
		
		if(!contextProperty.getOpposite().getType().equals(generalOppositeProperty.getOpposite().getType())){
			castOpposite = ".oclAsType("+addQuotes(generalOppositeProperty.getOpposite().getType().getName())+")";
		}
		
		if(type==1)
			rule += "includesAll(x"+castOpposite+"."+generalOppositePropertyName+"->asSet())";
		else if(type==2)
			rule += "excludesAll(x"+castOpposite+"."+generalOppositePropertyName+"->asSet())";
		else if (type==3)
			rule += "intersection(x"+castOpposite+"."+generalOppositePropertyName+"->asSet())->size()>="+value;
		
		rule +=")";
		
		Fix auxFix = fixer.generateOCLInvariant(context, "", rule);
		fix.addAll(auxFix);
		
		return auxFix.getAddedRules().get(0);
	}
	
	public String generateOCLIncludesAll(Property p){
		if(a2.getMemberEnd().contains(p))
			return generateOCL(p, 1, 0);
		return null;
	}
	
	public String generateOCLExcludesAll(Property p){
		if(a2.getMemberEnd().contains(p))
			return generateOCL(p, 2, 0);
		return null;
	}
	
	public String generateOCLAtLeast(Property p, int value){
		if(a2.getMemberEnd().contains(p))
			return generateOCL(p, 3, value);
		return null;
	}
	

	
}

/*
context BSource
inv : self.btarget->asSet()->forAll( x : BTarget | self.asource->asSet()->includesAll(x.asource->asSet()))

context BSource
inv : self.btarget->asSet()->forAll( x : BTarget | self.asource->excludesAll(x.asource))

context BSource
inv : self.btarget->asSet()->forAll( x : BTarget | self.asource->intersection(x.asource)->size()>=n)



context BTarget
inv : self.bsource->asSet()->forAll( x : BSource | self.asource->includesAll(x.asource))

context BSource
inv : self.btarget->asSet()->forAll( x : BTarget | self.atarget->includesAll(x.atarget))

context BTarget
inv : self.bsource->asSet()->forAll( x : BSource | self.atarget->includesAll(x.atarget))

 context _'Space Traveller'
inv closed : self.destination->forAll( x | x.oclIsTypeOf(_'System') implies self.destination->includesAll(x.oclAsType(_'System').galaxy->asSet()))

context _'Space Traveller'
inv open : self.destination->forAll( x | x.oclIsTypeOf(_'System') implies self.destination->excludesAll(x.oclAsType(_'System').galaxy->asSet()))

context _'Space Traveller'
inv open : self.destination->forAll( x | x.oclIsTypeOf(_'System') implies not self.destination->includesAll(x.oclAsType(_'System').galaxy->asSet()))
 */
