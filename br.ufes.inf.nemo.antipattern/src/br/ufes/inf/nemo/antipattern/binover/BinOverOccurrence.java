package br.ufes.inf.nemo.antipattern.binover;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.antipattern.overlapping.CommonMixinSubtype;
import br.ufes.inf.nemo.antipattern.overlapping.CommonMixinSupertype;
import br.ufes.inf.nemo.antipattern.overlapping.CommonSortalSupertype;
import br.ufes.inf.nemo.antipattern.overlapping.GeneralizationLine;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingGroup;
import br.ufes.inf.nemo.antipattern.overlapping.SameType;
import br.ufes.inf.nemo.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class BinOverOccurrence extends AntipatternOccurrence {

	public enum BinaryPropertyValue {TRANSITIVE, NON_TRANSITIVE, INTRANSITIVE, 
									 REFLEXIVE, IRREFLEXIVE, NON_REFLEXIVE, 
									 SYMMETRIC, ASYMMETRIC, NON_SYMMETRIC,
									 CYCLIC, ACYCLIC, NON_CYCLIC, 
									 NONE};
									 
	public enum BinaryProperty {Reflexivity, Symmetry, Transitivity, Cyclicity};
	
	Association association;
	Classifier source,target;
	Property sourceEnd, targetEnd;
	OverlappingGroup overlappingGroup;
		
	public BinOverOccurrence(Association a, Antipattern<?> ap) throws Exception {
		super(ap);
		
		this.association = a;
		this.source = (Classifier) a.getMemberEnd().get(0).getType();
		this.target= (Classifier) a.getMemberEnd().get(1).getType();
		this.sourceEnd = a.getMemberEnd().get(0);
		this.targetEnd = a.getMemberEnd().get(1);
		
		overlappingGroup = identifyOverlappingGroup();
		if(overlappingGroup == null)
			throw new Exception(BinOverAntipattern.getAntipatternInfo().getAcronym()+": invalid occurrence");
		
	}
	
	public OverlappingGroup identifyOverlappingGroup(){
		ArrayList<Property> ends = new ArrayList<Property>(association.getMemberEnd());
		
		try { return new SameType(ends); } catch(Exception e){}
		try { return new GeneralizationLine(ends); } catch(Exception e){}
		try { return new CommonSortalSupertype(ends); } catch(Exception e){}
		try { return new CommonMixinSupertype(ends); } catch(Exception e){}
		try { return new CommonMixinSubtype(ends); } catch(Exception e){}
		
		return null;

	}

	public OverlappingGroup getOverlappingGroup() {
		return overlappingGroup;
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
		return 	"Association: "+parser.getStringRepresentation(association)+
				"\nSource: "+parser.getStringRepresentation(source)+
				"\nTarget: "+parser.getStringRepresentation(target);
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
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.COMPLETE_HIERARCHY, false);
		return parser;
	}
	
	public void makeEndsDisjoint(){
		overlappingGroup.makeEndsDisjoint(this, new ArrayList<Property>(association.getMemberEnd()));
	}
		
	public String generateReflexivityOCL(BinaryPropertyValue value){
		
		String contextName = source.getName();
		String invName = value.toString().toLowerCase();
		
		fix.addAll(fixer.fixPropertyName(targetEnd));
		String targetEndName = addQuotes(targetEnd.getName()); 
		
		String invRule = "self."+targetEndName+"->asSet()";
		
		if(value==BinaryPropertyValue.REFLEXIVE)
			invRule+="includes";
		else if(value==BinaryPropertyValue.IRREFLEXIVE)
			invRule+="excludes";
		
		invRule += "(self)";

		fix.addAll(fixer.generateOCLInvariant(contextName, invName, invRule));
		
		return fix.getAddedRules().get(0);
	}
	
	public String generateSymmetryOCL(BinaryPropertyValue value){
		
		String contextName = source.getName();
		String invName = value.toString().toLowerCase();
		
		fix.addAll(fixer.fixPropertyName(targetEnd));
		String targetEndName = addQuotes(targetEnd.getName()); 
		
		String invRule = "self."+targetEndName+"->asSet()->forAll( x | x.oclIsTypeOf("+addQuotes(contextName)+") implies x.oclAsType("+addQuotes(contextName)+")."+targetEndName+"->";
		
		if(value==BinaryPropertyValue.SYMMETRIC)
			invRule+="includes";
		else if(value==BinaryPropertyValue.ASYMMETRIC)
			invRule+="excludes";
		
		invRule += "(self))";

		fix.addAll(fixer.generateOCLInvariant(contextName, invName, invRule));
		
		return fix.getAddedRules().get(0);
	}
	
	public String generateTransitivivityOCL(BinaryPropertyValue value){
		
		String contextName = source.getName();
		String invName = value.toString().toLowerCase();
		
		fix.addAll(fixer.fixPropertyName(targetEnd));
		String targetEndName = addQuotes(targetEnd.getName()); 
		
		String invRule = "self."+targetEndName+"->asSet()->";
		
		if(value==BinaryPropertyValue.TRANSITIVE)
			invRule+="includesAll";
		else if(value==BinaryPropertyValue.INTRANSITIVE)
			invRule+="excludesAll";
		
		invRule+="( self->asSet()->closure( x | if x.oclIsTypeOf("+addQuotes(contextName)+") " +
												"then x.oclAsType("+addQuotes(contextName)+")."+targetEndName+
												"else Set{}))";
		
		fix.addAll(fixer.generateOCLInvariant(contextName, invName, invRule));
		
		return fix.getAddedRules().get(0);
	}
	
	public String generateCyclicityOCL(BinaryPropertyValue value){
		
		String contextName = source.getName();
		String invName = value.toString().toLowerCase();
		
		fix.addAll(fixer.fixPropertyName(targetEnd));
		String targetEndName = addQuotes(targetEnd.getName()); 
		
		String invRule = "self->asSet()->closure( x | x.oclAsType("+addQuotes(contextName)+")."+targetEndName+"->asSet())->";
		
		if(value==BinaryPropertyValue.CYCLIC)
			invRule+="includes";
		else if(value==BinaryPropertyValue.ACYCLIC)
			invRule+="excludes";
		
		invRule+="(self))";
		
		fix.addAll(fixer.generateOCLInvariant(contextName, invName, invRule));
		
		return fix.getAddedRules().get(0);
	}
		
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
