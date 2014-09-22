package br.ufes.inf.nemo.antipattern.binover;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.antipattern.overlapping.CommonMixinSubtype;
import br.ufes.inf.nemo.antipattern.overlapping.CommonMixinSupertype;
import br.ufes.inf.nemo.antipattern.overlapping.CommonSortalSupertype;
import br.ufes.inf.nemo.antipattern.overlapping.GeneralizationLine;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingGroup;
import br.ufes.inf.nemo.antipattern.overlapping.SameType;
import br.ufes.inf.nemo.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;

public class BinOverOccurrence extends AntipatternOccurrence {

	public enum BinaryPropertyValue{REFLEXIVE, CO_REFLEXIVE, SHIFT_REFLEXIVE, QUASI_REFLEXIVE, ANTI_REFLEXIVE, NON_REFLEXIVE,
									SYMMETRIC, ASYMMETRIC, ANTI_SYMMETRIC, NON_SYMMETRIC,
									TRANSITIVE, CO_TRANSITIVE, OP_TRANSITIVE, NON_TRANSITIVE, ANTI_TRANSITIVE, NEGATIVELY_TRANSITIVE, SEMI_TRANSITIVE, QUASI_TRANSITIVE,
									EUCLIDEAN, LEFT_EUCLIDEAN, WEAK_COMPLETE, STRONG_COMPLETE, WEAK_CONNECTED, STRONG_CONNECTED,
									TRICHOTOMOUS, FERRERS, CYCLIC, ACYCLIC, NON_CYCLIC, 
									NONE};
									 
	public enum BinaryProperty {Reflexivity, Symmetry, Transitivity, Cyclicity};
	
	Association association;
	Classifier source,target;
	Property sourceEnd, targetEnd;
	OverlappingGroup group;
		
	public BinOverOccurrence(Association a, Antipattern<?> ap) throws Exception {
		super(ap);
		
		this.association = a;
		this.source = (Classifier) a.getMemberEnd().get(0).getType();
		this.target= (Classifier) a.getMemberEnd().get(1).getType();
		this.sourceEnd = a.getMemberEnd().get(0);
		this.targetEnd = a.getMemberEnd().get(1);
		
		group = identifyOverlappingGroup();
		if(group == null)
			throw new Exception(BinOverAntipattern.getAntipatternInfo().getAcronym()+": invalid occurrence");
		
	}
	
	public OverlappingGroup identifyOverlappingGroup(){
		ArrayList<Property> ends = new ArrayList<Property>(association.getMemberEnd());
		
		try { return new SameType(ends, antipattern); } catch(Exception e){}
		try { return new GeneralizationLine(ends, antipattern); } catch(Exception e){}
		try { return new CommonSortalSupertype(ends, antipattern); } catch(Exception e){}
		try { return new CommonMixinSupertype(ends, antipattern); } catch(Exception e){}
		try { return new CommonMixinSubtype(ends, antipattern); } catch(Exception e){}
		
		return null;

	}

	public OverlappingGroup getOverlappingGroup() {
		return group;
	}

	public Association getAssociation() {
		return association;
	}

	public Classifier getSource() {
		return source;
	}

	public Classifier getTarget() {
		return target;
	}

	@Override
	public String toString(){
		return 	group.getType()+
				"\nAssociation: "+OntoUMLNameHelper.getTypeAndName(association,true,false)+
				"\nSource: "+OntoUMLNameHelper.getNameTypeAndMultiplicity(sourceEnd,true,false,true,true,true)+
				"\nTarget: "+OntoUMLNameHelper.getNameTypeAndMultiplicity(targetEnd,true,false,true,true,true);
	}
	
	@Override
	public String getShortName() {
		return parser.getStringRepresentation(association);
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(source);
		selection.add(target);
		selection.add(association);
		
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.COMPLETE_HIERARCHY, false);
		return parser;
	}
	
	public void makeEndsDisjoint(){
		group.makeEndsDisjoint(this, new ArrayList<Property>(association.getMemberEnd()));
	}
	
	public void changeStereotype(Class<? extends Association> newStereotype){
		fix.addAll(fixer.changeRelationStereotypeTo(association, OutcomeFixer.getRelationStereotype(newStereotype)));		
	}
		
	public String generateOCL(BinaryPropertyValue value){
		String context = "", invRule ="", property="", quotedContext="";
		
		if(group instanceof SameType){
			context = source.getName();
			quotedContext = addQuotes(context);
			fix.addAll(fixer.fixPropertyName(targetEnd));
			property = addQuotes(targetEnd.getName())+"->asSet()"; 
		}
		else if (group instanceof GeneralizationLine){
			GeneralizationLine gl = (GeneralizationLine) group;
			context = gl.getParent().getType().getName();
			quotedContext = addQuotes(context);
			fix.addAll(fixer.fixPropertyName(gl.getChild()));
			property = addQuotes(gl.getChild().getName())+"->asSet().oclAsType("+quotedContext+")";
		}
		else if(group instanceof CommonSortalSupertype || group instanceof CommonMixinSupertype){
			
			if(group instanceof CommonMixinSupertype)
				context = ((CommonMixinSupertype) group).getClosestSupertpe().getName();
			else if (group instanceof CommonSortalSupertype)
				context = ((CommonSortalSupertype) group).getClosestSupertpe().getName();
			
			quotedContext = addQuotes(context);
			fix.addAll(fixer.fixPropertyName(targetEnd));
			property = "oclAsType("+addQuotes(source.getName())+")."+addQuotes(targetEnd.getName())+"->asSet().oclAsType("+quotedContext+")"; 

		}
		else if(group instanceof CommonMixinSubtype){

			ArrayList<Classifier> types = new ArrayList<Classifier>();
			types.add(source);
			types.add(target);
			Fix aux = fixer.createSuperTypeInCommonTo(types, "commonSupertype");
			fix.addAll(aux);
			
			context = fix.getAddedByType(RefOntoUML.Class.class).get(0).getName();
			quotedContext = addQuotes(context);
			
			fix.addAll(fixer.fixPropertyName(targetEnd));
			property = "oclAsType("+addQuotes(source.getName())+")."+addQuotes(targetEnd.getName())+"->asSet().oclAsType("+quotedContext+")"; 
		}
		
		if(value==BinaryPropertyValue.REFLEXIVE)
			invRule = quotedContext+".allInstances()->forAll(x : "+quotedContext+" | x."+property+"->includes(x))";

		else if(value==BinaryPropertyValue.CO_REFLEXIVE)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | x."+property+"->includes(y) implies x=y)";
		
		else if(value==BinaryPropertyValue.SHIFT_REFLEXIVE)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | x."+property+"->includes(y) implies y."+property+"->includes(y))";
		
		else if(value==BinaryPropertyValue.QUASI_REFLEXIVE)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | (x."+property+"->includes(y) or y."+property+"->includes(x)) implies y."+property+"->includes(y))";
		
		else if(value==BinaryPropertyValue.ANTI_REFLEXIVE)
			invRule = quotedContext+".allInstances()->forAll(x : "+quotedContext+" | not x."+property+"->includes(x))";
		
		else if(value==BinaryPropertyValue.NON_REFLEXIVE)
			invRule = "not ("+quotedContext+".allInstances()->forAll(x : "+quotedContext+" | x."+property+"->includes(x)) )";
		
		else if(value==BinaryPropertyValue.SYMMETRIC)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | x."+property+"->includes(y) implies y."+property+"->includes(x))";
		
		else if(value==BinaryPropertyValue.ASYMMETRIC)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | x."+property+"->includes(y) implies not y."+property+"->includes(x))";

		else if(value==BinaryPropertyValue.ANTI_SYMMETRIC)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | (x."+property+"->includes(y) and y."+property+"->includes(x)) implies x=y)";
		
		else if(value==BinaryPropertyValue.NON_SYMMETRIC)
			invRule = "not ("+quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | x."+property+"->includes(y) implies y."+property+"->includes(x)))";
		
		else if(value==BinaryPropertyValue.TRANSITIVE)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | "+quotedContext+".allInstances()->forAll (z: "+quotedContext+" | " +
					"(x."+property+"->includes(y) and y."+property+"->includes(z)) implies x."+property+"->includes(z)) )";
		
		else if(value==BinaryPropertyValue.CO_TRANSITIVE)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | "+quotedContext+".allInstances()->forAll (z: "+quotedContext+" | " +
					"(x."+property+"->includes(z) implies (x."+property+"->includes(y) or y."+property+"->includes(z)) )";
		
		else if(value==BinaryPropertyValue.OP_TRANSITIVE)
			invRule = quotedContext+".allInstances()->forAll(x,z : "+quotedContext+" | x."+property+"->includes(z) implies "+quotedContext+".allInstances()->exists(y: "+quotedContext+" | x."+property+"->includes(y) and y."+property+"->includes(z) ))";
		
		else if(value==BinaryPropertyValue.NEGATIVELY_TRANSITIVE)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | "+quotedContext+".allInstances()->forAll (z: "+quotedContext+" | " +
					"(not x."+property+"->includes(y) and not y."+property+"->includes(z)) implies not x."+property+"->includes(z)) )";
		
		else if(value==BinaryPropertyValue.SEMI_TRANSITIVE)
			invRule = quotedContext+".allInstances()->forAll(w,x : "+quotedContext+" | "+quotedContext+".allInstances()->forAll (y,z: "+quotedContext+" | " +
					"(w."+property+"->includes(x) and x."+property+"->includes(y)) implies (w."+property+"->includes(z) or z."+property+"->includes(y)) ))";	
		
		else if(value==BinaryPropertyValue.QUASI_TRANSITIVE)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | "+quotedContext+".allInstances()->forAll (z: "+quotedContext+" | " +
					"(x."+property+"->includes(y) and y."+property+"->includes(z) and not y."+property+"->includes(x) and not z."+property+"->includes(y)) implies (x."+property+"->includes(z) and not z."+property+"->includes(x))) )";
		
		else if(value==BinaryPropertyValue.ANTI_TRANSITIVE)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | "+quotedContext+".allInstances()->forAll (z: "+quotedContext+" | " +
					"(x."+property+"->includes(y) and y."+property+"->includes(z)) implies not x."+property+"->includes(z)) )";
		
		else if(value==BinaryPropertyValue.NON_TRANSITIVE)
			invRule = "not ("+quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | "+quotedContext+".allInstances()->forAll (z: "+quotedContext+" | " +
					"(x."+property+"->includes(y) and y."+property+"->includes(z)) implies x."+property+"->includes(z)) ) )";
		
		else if(value==BinaryPropertyValue.EUCLIDEAN)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | "+quotedContext+".allInstances()->forAll (z: "+quotedContext+" | " +
					"(x."+property+"->includes(y) and x."+property+"->includes(z)) implies (y."+property+"->includes(z) and z."+property+"->includes(y)) ) )";
		
		else if(value==BinaryPropertyValue.LEFT_EUCLIDEAN)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | "+quotedContext+".allInstances()->forAll (z: "+quotedContext+" | " +
					"(y."+property+"->includes(x) and z."+property+"->includes(x)) implies (y."+property+"->includes(z) and z."+property+"->includes(y)) ) )";

		else if(value==BinaryPropertyValue.WEAK_COMPLETE)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | x<>y implies (x."+property+"->includes(y) or y."+property+"->includes(x)))";
		
		else if(value==BinaryPropertyValue.STRONG_COMPLETE)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | x."+property+"->includes(y) or y."+property+"->includes(x))";
		
		else if(value==BinaryPropertyValue.WEAK_CONNECTED)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | not (x."+property+"->includes(y) or y."+property+"->includes(x)) implies x=y)";
		
		else if(value==BinaryPropertyValue.STRONG_CONNECTED)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | x<>y implies (x."+property+"->includes(y) or y."+property+"->includes(x)) )";
		
		else if(value==BinaryPropertyValue.TRICHOTOMOUS)
			invRule = quotedContext+".allInstances()->forAll(x,y : "+quotedContext+" | x."+property+"->includes(y) or y."+property+"->includes(x) or x=y)";
		
		else if(value==BinaryPropertyValue.ACYCLIC)
			invRule = quotedContext+".allInstances()->forAll(x : "+quotedContext+" | not x->closure("+property+")->includes(x))";
		
		else if(value==BinaryPropertyValue.CYCLIC)
			invRule = "not ("+quotedContext+".allInstances()->forAll(x : "+quotedContext+" | not x->closure("+property+")->includes(x)))";
		
		else if(value==BinaryPropertyValue.FERRERS)
			invRule = quotedContext+".allInstances()->forAll(w,x : "+quotedContext+" | "+quotedContext+".allInstances()->forAll (y,z: "+quotedContext+" | " +
					"(w."+property+"->includes(x) and y."+property+"->includes(z)) implies (w."+property+"->includes(z) or x."+property+"->includes(y)) ) )";	
		else
			return null;
		
		fix.addAll(fixer.generateOCLInvariant(context, value.toString().toLowerCase(), invRule));
		return fix.getAddedRules().get(0);
	}
	
	public static boolean validCombination(BinaryPropertyValue reflexivity, BinaryPropertyValue symmetry, BinaryPropertyValue transitivity, BinaryPropertyValue cyclicity){
		
		boolean reflexiveAcyclic = reflexivity==BinaryPropertyValue.REFLEXIVE && cyclicity==BinaryPropertyValue.ACYCLIC;
		boolean reflexiveIntransitive =  reflexivity==BinaryPropertyValue.REFLEXIVE && transitivity==BinaryPropertyValue.ANTI_TRANSITIVE;
		boolean symmetricAcyclic = symmetry==BinaryPropertyValue.SYMMETRIC && cyclicity==BinaryPropertyValue.ACYCLIC;
		boolean reflexiveNonCyclic = reflexivity==BinaryPropertyValue.REFLEXIVE && cyclicity==BinaryPropertyValue.NON_CYCLIC;
		boolean irreflexiveSymmetricTransitive = reflexivity==BinaryPropertyValue.ANTI_REFLEXIVE && symmetry==BinaryPropertyValue.SYMMETRIC && transitivity==BinaryPropertyValue.TRANSITIVE;
		boolean irreflexiveTransitiveCyclic = reflexivity==BinaryPropertyValue.ANTI_REFLEXIVE && transitivity==BinaryPropertyValue.TRANSITIVE && cyclicity==BinaryPropertyValue.CYCLIC;
		boolean nonReflexiveTransitiveCyclic = reflexivity==BinaryPropertyValue.NON_REFLEXIVE && transitivity==BinaryPropertyValue.TRANSITIVE && cyclicity==BinaryPropertyValue.CYCLIC;
		
		return !(reflexiveAcyclic || reflexiveIntransitive || symmetricAcyclic || reflexiveNonCyclic || irreflexiveSymmetricTransitive || irreflexiveTransitiveCyclic || nonReflexiveTransitiveCyclic);
	}

	
/****************************************************************************************************************************************************************************
 * 																																											|
 * 																																											| 
 * 																																											|
 * 																																											|
 * 	OLD CODE	OLD CODE	OLD CODE	OLD CODE	OLD CODE	OLD CODE 	OLD CODE	OLD CODE	OLD CODE	OLD CODE	OLD CODE	OLD CODE	OLD CODE	OLD CODE	|
 * 																																											| 
 * 																																											|
 * 																																											| 
 * 																																											|
 * 																																											|  
 ***************************************************************************************************************************************************************************/ 
		
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is REFLEXIVE*/
	public String generateReflexivePredicate (int cardinality, OntoUMLParser parser) {
		String predicate, rules, name;
		
		String associationName, typeName;
		associationName = parser.getAlias(this.association);
		typeName = parser.getAlias(this.source);
		
		name = "reflexive_"+associationName;
		rules = "#" + associationName + ">" + cardinality;
		rules += "\n\tall w:World | reflexive[w."+ associationName +", w."+typeName+"]";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is IRREFLEXIVE*/
	public String generateIrreflexivePredicate (int cardinality, OntoUMLParser parser){
		String predicate, rules, name;
		
		String associationName;
		associationName = parser.getAlias(this.association);
		
		name = "irreflexive_"+associationName;
		
		rules = "#" + associationName + ">" + cardinality; 
		rules += "\n\tall w:World | irreflexive[w."+ associationName +"]";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
    	
    	return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is Transitive*/
	public String generateTransitivePredicate (int cardinality, OntoUMLParser parser){
		String predicate, rules, name;
		
		String associationName;
		associationName = parser.getAlias(this.association);
		name = "transitive_"+associationName;
		
		rules = "#" + associationName + ">" + cardinality; 
		rules += "\n\tall w:World | transitive[w."+ associationName +"]";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
    	
    	return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is INTRANSITIVE*/
	public String generateIntransitivePredicate (int cardinality, OntoUMLParser parser){
		String predicate, rules, name;
		
		String associationName, typeName;
		associationName = parser.getAlias(this.association);
		typeName = parser.getAlias(this.source);
		
		name = "instransitive_"+associationName;
		rules = "#" + associationName + ">" + cardinality; 
		rules += "\n\tall w:World | all disj x,y,z: w."+ typeName +" | (y in x.(w."+ associationName +") and z in y.(w."+ associationName +") ) implies z not in x.(w."+ associationName +")";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
		
		return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is SYMMETRIC*/
	public String generateSymmetricPredicate (int cardinality, OntoUMLParser parser){
		String predicate, rules, name;
		
		String associationName;
		associationName = parser.getAlias(this.association);
		name = "symmetric_"+associationName;
		
		rules = "#" + associationName + ">" + cardinality; 
		rules += "\n\tall w:World | symmetric[w."+ associationName +"]";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
    	
    	return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is ANTISYMMETRIC*/
	public String generateAntisymmetricPredicate (int cardinality, OntoUMLParser parser){
		String predicate, rules, name;
		
		String associationName;
		associationName = parser.getAlias(this.association);
		name = "antisymmetric_"+associationName;
		rules = "#" + associationName + ">" + cardinality;
		rules += "\n\tall w:World | antisymmetric[w."+ associationName +"]";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
			
		return predicate;
	}
	
	
}
