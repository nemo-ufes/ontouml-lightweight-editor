package br.ufes.inf.nemo.ontouml.antipattern;

import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;

import RefOntoUML.Association;
import RefOntoUML.Classifier;

/*Self-Type Relationship AntiPattern*/
public class STRAntiPattern {
	private Association association;
	private Classifier type;
	private String associationName, typeName;
	
	public STRAntiPattern (Association association, NamesMapper mapper) throws Exception{
		this.setAssociation(association, mapper);
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is REFLEXIVE*/
	public String generateReflexivePredicate (int cardinality) {
		String predicate, rules, name;
		
		name = "reflexive_"+this.associationName;
		rules = "#" + this.associationName + ">" + cardinality;
		rules += "\n\tall w:World | reflexive[w."+ this.associationName +", w."+this.typeName+"]";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is IRREFLEXIVE*/
	public String generateIrreflexivePredicate (int cardinality){
		String predicate, rules, name;
		
		name = "irreflexive_"+this.associationName;
		
		rules = "#" + this.associationName + ">" + cardinality; 
		rules += "\n\tall w:World | irreflexive[w."+ this.associationName +"]";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
    	
    	return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is Transitive*/
	public String generateTransitivePredicate (int cardinality){
		String predicate, rules, name;
		
		name = "transitive_"+this.associationName;
		
		rules = "#" + this.associationName + ">" + cardinality; 
		rules += "\n\tall w:World | transitive[w."+ this.associationName +"]";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
    	
    	return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is INTRANSITIVE*/
	public String generateIntransitivePredicate (int cardinality){
		String predicate, rules, name;
		
		name = "instransitive_"+this.associationName;
		rules = "#" + this.associationName + ">" + cardinality; 
		rules += "\n\tall w:World | all disj x,y,z: w."+ this.typeName +" | (y in x.(w."+ this.associationName +") and z in y.(w."+ this.associationName +") ) implies z not in x.(w."+ this.associationName +")";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
		
		return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is SYMMETRIC*/
	public String generateSymmetricPredicate (int cardinality){
		String predicate, rules, name;
		
		name = "symmetric_"+this.associationName;
		
		rules = "#" + this.associationName + ">" + cardinality; 
		rules += "\n\tall w:World | symmetric[w."+ this.associationName +"]";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
    	
    	return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is ANTISYMMETRIC*/
	public String generateAntisymmetricPredicate (int cardinality){
		String predicate, rules, name;
		
		name = "antisymmetric_"+this.associationName;
		rules = "#" + this.associationName + ">" + cardinality;
		rules += "\n\tall w:World | antisymmetric[w."+ this.associationName +"]";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
			
		return predicate;
	}
	
	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association, NamesMapper mapper) throws Exception {
		
		
		if (association == null)
			throw new NullPointerException();
		/*Check if the provided association indeed characterizes a SelfType Relationship AntiPattern*/
		else if (!association.getMemberEnd().get(0).getType().equals(association.getMemberEnd().get(1).getType()))
			throw new Exception("Input association does not characterize a Self-Type Relationship AntiPattern");
		
		this.association = association;
		this.type = (Classifier) association.getMemberEnd().get(0).getType();
		
		associationName = mapper.elementsMap.get(this.association);
		typeName = mapper.elementsMap.get(this.type);
	}

	public Classifier getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return this.typeName+" - "+this.associationName;
	}

}
