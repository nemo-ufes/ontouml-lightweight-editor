package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;

/*Self-Type Relationship AntiPattern*/
public class STRAntiPattern extends Antipattern{
	private Association association;
	private Classifier type;
	
	
	public STRAntiPattern (Association association) throws Exception{
		this.setAssociation(association);
		
	}
	
	public String generateIrreflexiveOcl(OntoUMLParser parser){
		String aet_name = parser.getAlias(association.getMemberEnd().get(1));
		
		return 	"context "+parser.getAlias(association.getMemberEnd().get(0).getType())+"\n"+
				"inv irreflexive_"+parser.getAlias(association)+" : not (self."+aet_name+"->includes(self))";
	}
	
	public String generateReflexiveOcl(OntoUMLParser parser){
		String aet_name = parser.getAlias(association.getMemberEnd().get(1));
		
		return 	"context "+parser.getAlias(association.getMemberEnd().get(0).getType())+"\n"+
				"inv reflexive_"+parser.getAlias(association)+" : self."+aet_name+"->includes(self)";
	}
	
	public String generateSymmetricOcl(OntoUMLParser parser){
		String aet_name = parser.getAlias(association.getMemberEnd().get(1));
		String aes_name = parser.getAlias(association.getMemberEnd().get(0));
		
		return 	"context "+parser.getAlias(association.getMemberEnd().get(0).getType())+"\n"+
				"inv symmetric_"+parser.getAlias(association)+"_source : self."+aet_name+"->forAll(x : "+parser.getAlias(association.getMemberEnd().get(1).getType())+" | x."+aes_name+"->includes(self))"+
				
				"context "+parser.getAlias(association.getMemberEnd().get(1).getType())+"\n"+
				"inv symmetric_"+parser.getAlias(association)+"_target : self."+aes_name+"->forAll(x : "+parser.getAlias(association.getMemberEnd().get(0).getType())+" | x."+aet_name+"->includes(self))";
	}
	
	public String generateAntiSymmetricOcl(OntoUMLParser parser){
		String aet_name = parser.getAlias(association.getMemberEnd().get(1));
		String aes_name = parser.getAlias(association.getMemberEnd().get(0));
		
		return 	"context "+parser.getAlias(association.getMemberEnd().get(0).getType())+"\n"+
				"inv antisymmetric_"+parser.getAlias(association)+"_source : self."+aet_name+"->forAll(x : "+parser.getAlias(association.getMemberEnd().get(1).getType())+" | !(x."+aes_name+"->includes(self)))"+
				
				"context "+parser.getAlias(association.getMemberEnd().get(1).getType())+"\n"+
				"inv antisymmetric_"+parser.getAlias(association)+"_target : self."+aes_name+"->forAll(x : "+parser.getAlias(association.getMemberEnd().get(0).getType())+" | !(x."+aet_name+"->includes(self)))";
	}
	
	public String generateTransitiveOcl(OntoUMLParser parser){
		String aet_name = parser.getAlias(association.getMemberEnd().get(1));		
		
		return 	"context "+parser.getAlias(association.getMemberEnd().get(0).getType())+"\n"+
				"inv transitive_" + association.getName().trim() + " : self."+aet_name+"->asSet()->includesAll(self."+aet_name+"."+aet_name+"->asSet())";				
	}
	
	public String generateIntransitiveOcl(OntoUMLParser parser){
		String aet_name = parser.getAlias(association.getMemberEnd().get(1));		
		
		return 	"context "+parser.getAlias(association.getMemberEnd().get(0).getType())+"\n"+
				"inv intransitive_"+association.getName().trim()+ " : self."+aet_name+"->asSet()->excludesAll(self."+aet_name+"."+aet_name+"->asSet())";
				
	}
	
	/*TODO criar as regras para transitive*/
	
	/*This method returns an Alloy predicate which only generates model instances in which the association that characterizes the antipattern is REFLEXIVE*/
	public String generateReflexivePredicate (int cardinality, OntoUMLParser parser) {
		String predicate, rules, name;
		
		String associationName, typeName;
		associationName = parser.getAlias(this.association);
		typeName = parser.getAlias(this.type);
		
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
		typeName = parser.getAlias(this.type);
		
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

	@Override
	public void setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(type);
		selection.add(association);
		
		parser.setSelection(selection);
	}
	

}
