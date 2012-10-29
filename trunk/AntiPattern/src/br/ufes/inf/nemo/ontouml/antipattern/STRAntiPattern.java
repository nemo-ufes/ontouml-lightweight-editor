package br.ufes.inf.nemo.ontouml.antipattern;

import br.ufes.inf.nemo.common.ontouml.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.AssociationEndNameGenerator;

import RefOntoUML.Association;
import RefOntoUML.Classifier;

/*Self-Type Relationship AntiPattern*/
public class STRAntiPattern {
	private Association association;
	private Classifier type;
	
	
	public STRAntiPattern (Association association) throws Exception{
		this.setAssociation(association);
	}
	
	public String generateIrreflexiveOcl(){
		String aet_name = AssociationEndNameGenerator.associationEndName(association.getMemberEnd().get(1));
		return 	"context "+association.getMemberEnd().get(0).getType().getName()+"\n"+
				"inv : !(self."+aet_name+"->includes(self))";
	}
	
	public String generateReflexiveOcl(){
		String aet_name = AssociationEndNameGenerator.associationEndName(association.getMemberEnd().get(1));
		return 	"context "+association.getMemberEnd().get(0).getType().getName()+"\n"+
				"inv : self."+aet_name+"->includes(self)";
	}
	
	public String generateSymmetricOcl(){
		String aet_name = AssociationEndNameGenerator.associationEndName(association.getMemberEnd().get(1));
		String aes_name = AssociationEndNameGenerator.associationEndName(association.getMemberEnd().get(0));
		return 	"context "+association.getMemberEnd().get(0).getType().getName()+"\n"+
				"inv : self."+aet_name+"->forAll(x : "+association.getMemberEnd().get(1).getType().getName()+" | x."+aes_name+"->includes(self))"+
				"context "+association.getMemberEnd().get(1).getType().getName()+"\n"+
				"inv : self."+aes_name+"->forAll(x : "+association.getMemberEnd().get(0).getType().getName()+" | x."+aet_name+"->includes(self))";
	}
	
	public String generateAntiSymmetricOcl(){
		String aet_name = AssociationEndNameGenerator.associationEndName(association.getMemberEnd().get(1));
		String aes_name = AssociationEndNameGenerator.associationEndName(association.getMemberEnd().get(0));
		return 	"context "+association.getMemberEnd().get(0).getType().getName()+"\n"+
				"inv : self."+aet_name+"->forAll(x : "+association.getMemberEnd().get(1).getType().getName()+" | !(x."+aes_name+"->includes(self)))"+
				"context "+association.getMemberEnd().get(1).getType().getName()+"\n"+
				"inv : self."+aes_name+"->forAll(x : "+association.getMemberEnd().get(0).getType().getName()+" | !(x."+aet_name+"->includes(self)))";
	}
	
	/*TODO criar as regras para transitive*/
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is REFLEXIVE*/
	public String generateReflexivePredicate (int cardinality, OntoUMLParser mapper) {
		String predicate, rules, name;
		
		String associationName, typeName;
		associationName = mapper.getName(this.association);
		typeName = mapper.getName(this.type);
		
		name = "reflexive_"+associationName;
		rules = "#" + associationName + ">" + cardinality;
		rules += "\n\tall w:World | reflexive[w."+ associationName +", w."+typeName+"]";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is IRREFLEXIVE*/
	public String generateIrreflexivePredicate (int cardinality, OntoUMLParser mapper){
		String predicate, rules, name;
		
		String associationName;
		associationName = mapper.getName(this.association);
		
		name = "irreflexive_"+associationName;
		
		rules = "#" + associationName + ">" + cardinality; 
		rules += "\n\tall w:World | irreflexive[w."+ associationName +"]";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
    	
    	return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is Transitive*/
	public String generateTransitivePredicate (int cardinality, OntoUMLParser mapper){
		String predicate, rules, name;
		
		String associationName;
		associationName = mapper.getName(this.association);
		name = "transitive_"+associationName;
		
		rules = "#" + associationName + ">" + cardinality; 
		rules += "\n\tall w:World | transitive[w."+ associationName +"]";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
    	
    	return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is INTRANSITIVE*/
	public String generateIntransitivePredicate (int cardinality, OntoUMLParser mapper){
		String predicate, rules, name;
		
		String associationName, typeName;
		associationName = mapper.getName(this.association);
		typeName = mapper.getName(this.type);
		
		name = "instransitive_"+associationName;
		rules = "#" + associationName + ">" + cardinality; 
		rules += "\n\tall w:World | all disj x,y,z: w."+ typeName +" | (y in x.(w."+ associationName +") and z in y.(w."+ associationName +") ) implies z not in x.(w."+ associationName +")";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
		
		return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is SYMMETRIC*/
	public String generateSymmetricPredicate (int cardinality, OntoUMLParser mapper){
		String predicate, rules, name;
		
		String associationName;
		associationName = mapper.getName(this.association);
		name = "symmetric_"+associationName;
		
		rules = "#" + associationName + ">" + cardinality; 
		rules += "\n\tall w:World | symmetric[w."+ associationName +"]";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
    	
    	return predicate;
	}
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is ANTISYMMETRIC*/
	public String generateAntisymmetricPredicate (int cardinality, OntoUMLParser mapper){
		String predicate, rules, name;
		
		String associationName;
		associationName = mapper.getName(this.association);
		name = "antisymmetric_"+associationName;
		rules = "#" + associationName + ">" + cardinality;
		rules += "\n\tall w:World | antisymmetric[w."+ associationName +"]";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
			
		return predicate;
	}
	
	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) throws Exception {
		
		
		if (association == null)
			throw new NullPointerException();
		/*Check if the provided association indeed characterizes a SelfType Relationship AntiPattern*/
		else if (!association.getMemberEnd().get(0).getType().equals(association.getMemberEnd().get(1).getType()))
			throw new Exception("Input association does not characterize a Self-Type Relationship AntiPattern");
		
		this.association = association;
		this.type = (Classifier) association.getMemberEnd().get(0).getType();
	}

	public Classifier getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return type.getName()+" - "+association.getName();
	}

}
