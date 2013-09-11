package br.ufes.inf.nemo.antipattern.str;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

/*Self-Type Relationship AntiPattern*/
public class STRAntiPattern extends Antipattern{
	private Association association;
	private Classifier type;
	
	
	public STRAntiPattern (Association association) throws Exception{
		this.setAssociation(association);
		
	}
	
	public String generateIrreflexiveOcl(OntoUMLParser parser)
	{
		String aet_name = association.getMemberEnd().get(1).getName();
		
		return 	"context _'"+association.getMemberEnd().get(0).getType().getName()+"'\n"+
				"inv irreflexive_"+association.getName()+" : \n    not (self."+aet_name+"->includes(self))";
	}
	
	public String generateReflexiveOcl(OntoUMLParser parser)
	{
		String aet_name = association.getMemberEnd().get(1).getName();
		
		return 	"context _'"+association.getMemberEnd().get(0).getType().getName()+"'\n"+
				"inv reflexive_"+association.getName()+" : \n    self."+aet_name+"->includes(self)";
	}
	
	public String generateSymmetricOcl(OntoUMLParser parser)
	{
		String aet_name = association.getMemberEnd().get(1).getName();
		String aes_name = association.getMemberEnd().get(0).getName();
		
		return 	"context _'"+association.getMemberEnd().get(0).getType().getName()+"'\n"+
				"inv symmetric_"+association.getName()+"_source : \n    "+
				"self."+aet_name+"->forAll(x : "+association.getMemberEnd().get(1).getType().getName()+" | x."+aes_name+"->includes(self))"+
				
				"\n\ncontext _'"+association.getMemberEnd().get(1).getType().getName()+"'\n"+
				"inv symmetric_"+association.getName()+"_target : \n    "+
				"self."+aes_name+"->forAll(x : "+association.getMemberEnd().get(0).getType().getName()+" | x."+aet_name+"->includes(self))";
	}
	
	public String generateAntiSymmetricOcl(OntoUMLParser parser)
	{
		String aet_name = association.getMemberEnd().get(1).getName();
		String aes_name = association.getMemberEnd().get(0).getName();
		
		return 	"context _'"+association.getMemberEnd().get(0).getType().getName()+"'\n"+
				"inv antisymmetric_"+association.getName()+"_source : \n    "+
				"self."+aet_name+"->forAll(x : "+association.getMemberEnd().get(1).getType().getName()+" | !(x."+aes_name+"->includes(self)))"+
				
				"\n\ncontext _'"+association.getMemberEnd().get(1).getType().getName()+"'\n"+
				"inv antisymmetric_"+association.getName()+"_target : \n    "+
				"self."+aes_name+"->forAll(x : "+association.getMemberEnd().get(0).getType().getName()+" | !(x."+aet_name+"->includes(self)))";
	}
	
	public String generateTransitiveOcl(OntoUMLParser parser)
	{
		String aet_name = association.getMemberEnd().get(1).getName();		
		
		return 	"context _'"+association.getMemberEnd().get(0).getType().getName()+"'\n"+
				"inv transitive_" + association.getName() + " : \n    "+
				"self."+aet_name+"->asSet()->includesAll(self."+aet_name+"."+aet_name+"->asSet())";				
	}
	
	public String generateIntransitiveOcl(OntoUMLParser parser)
	{
		String aet_name = association.getMemberEnd().get(1).getName();		
		
		return 	"context _'"+association.getMemberEnd().get(0).getType().getName()+"'\n"+
				"inv intransitive_"+association.getName()+ " : \n    "+
				"self."+aet_name+"->asSet()->excludesAll(self."+aet_name+"."+aet_name+"->asSet())";
				
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
		if (!(association instanceof Association))
			throw new Exception("Provided object for STRAntiPattern creation is not an Association.");
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
	public OntoUMLParser setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(type);
		selection.add(association);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.NO_HIERARCHY, false);
		return parser;
	}
	

}
