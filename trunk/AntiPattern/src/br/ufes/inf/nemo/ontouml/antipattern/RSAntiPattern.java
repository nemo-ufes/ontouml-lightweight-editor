package br.ufes.inf.nemo.ontouml.antipattern;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.AssociationEndNameGenerator;
import br.ufes.inf.nemo.ontouml.antipattern.util.SourceTargetAssociation;

/*Relation Specialization*/
public class RSAntiPattern {
	private Association general;
	private Association specific;
	private Classifier generalSource;
	private Classifier generalTarget;
	private Classifier specificSource;
	private Classifier specificTarget;
		
	public RSAntiPattern (Association specific, Association general) throws Exception{
						
		this.setGeneral(general);
		this.setSpecific(specific);
		
		/*This methods are only necessary because the provided model may not correctly instantiante the RefOntoUML Metamodel. 
		 * In this case, it is necessary to correct who is the source and who is the target in the association, according to the logic in the metamodel.
		 * For instance, in a mediation, the relator is always the source and the mediated type is always the target*/
		specificSource = (Classifier) SourceTargetAssociation.getSourceAlloy(specific);
		specificTarget = (Classifier) SourceTargetAssociation.getTargetAlloy(specific);
		generalSource = (Classifier) SourceTargetAssociation.getSourceAlloy(general);
		generalTarget = (Classifier) SourceTargetAssociation.getTargetAlloy(general);
				
	}
	
	public String generateSubsetOcl(){
		
		String aes_name = AssociationEndNameGenerator.associationEndName(specific.getMemberEnd().get(1));
		String aeg_name = AssociationEndNameGenerator.associationEndName(general.getMemberEnd().get(1));
		
		return 	"context "+specific.getMemberEnd().get(0).getType().getName()+"\n"+
				"inv : self.oclAsType("+general.getMemberEnd().get(0).getType().getName()+")."+aeg_name+"->includesAll(self."+aes_name+")";
	}
	
	public String generateRedefineOcl(){
		String aes_name = AssociationEndNameGenerator.associationEndName(specific.getMemberEnd().get(1));
		String aeg_name = AssociationEndNameGenerator.associationEndName(general.getMemberEnd().get(1));
			
		return 	"context "+specific.getMemberEnd().get(0).getType().getName()+"\n"+
				"inv : self.oclAsType("+general.getMemberEnd().get(0).getType().getName()+")."+aeg_name+"=self."+aes_name;
	}
	
	public String generateDisjointOcl(){
		String aes_name = AssociationEndNameGenerator.associationEndName(specific.getMemberEnd().get(1));
		String aeg_name = AssociationEndNameGenerator.associationEndName(general.getMemberEnd().get(1));
			
		return 	"context "+specific.getMemberEnd().get(0).getType().getName()+"\n"+
				"inv : self.oclAsType("+general.getMemberEnd().get(0).getType().getName()+")."+aeg_name+"->intersection(self."+aes_name+")->size()=0";
	}
	
	public String generateNotSubsetOcl(){
		String aes_name = AssociationEndNameGenerator.associationEndName(specific.getMemberEnd().get(1));
		String aeg_name = AssociationEndNameGenerator.associationEndName(general.getMemberEnd().get(1));
			
		return 	"context "+specific.getMemberEnd().get(0).getType().getName()+"\n"+
				"inv : !(self.oclAsType("+general.getMemberEnd().get(0).getType().getName()+")."+aeg_name+"->includesAll(self."+aes_name+"))";
	}
	
	/*This method generates an Alloy predicate that states that the specific association is a SUBTYPE of the general association*/
	public String generateSubsetPredicate (OntoUMLParser mapper) {
		String predicate, rules, name;
		
		String generalName, specificName;
		generalName = mapper.getName(general);
		specificName = mapper.getName(specific);
		
		name = "subset_"+specificName+"_"+generalName;
		rules = "some "+specificName+"\n\t";
		rules += "some "+generalName+"\n\t";
		rules += specificName+" in "+generalName;
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	/*This method generates an Alloy predicate that states that the specific association is NOT A SUBTYPE of the general association*/
	public String generateNotSubsetPredicate (OntoUMLParser mapper) {
		String predicate, rules, name;
		
		String generalName, specificName;
		generalName = mapper.getName(general);
		specificName = mapper.getName(specific);
		
		name = "not_subset_"+specificName+"_"+generalName;
		rules = "some "+specificName+"\n\t";
		rules += "some "+generalName+"\n\t";
		rules += specificName+" not in "+generalName;
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	/*This method generates an Alloy predicate that states that the specific association is DISJOINT with the general association*/
	public String generateDisjointPredicate (OntoUMLParser mapper) {
		String predicate, rules, name;
		
		String generalName, specificName;
		generalName = mapper.getName(general);
		specificName = mapper.getName(specific);
		
		name = "disjoint_"+specificName+"_"+generalName;
		rules = "some "+specificName+"\n\t";
		rules += "some "+generalName+"\n\t";
		rules += "no "+specificName+" & "+generalName;
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	/*This method generates an Alloy predicate that states that the specific association is a REDEFINITION of the general association*/
	public String generateRedefinePredicate (OntoUMLParser mapper) {
		String predicate, rules, name;
		
		String generalName, specificName, specificSourceName, specificTargetName;
		generalName = mapper.getName(general);
		specificName = mapper.getName(specific);
		specificSourceName = mapper.getName(specificSource);
		specificTargetName = mapper.getName(specificTarget);
		name = "redefine_"+specificName+"_"+generalName;
				
		if (this.specificSource.allParents().contains(generalSource) || this.specificSource.allParents().contains(generalTarget)){
			rules = "some "+specificSourceName+"\n\t";
			rules += "all w:World, x:w."+specificSourceName+" | x.(w."+specificName+")=x.(w."+generalName+")";
		}
		else{
			rules = "some "+specificTargetName+"\n\t";
			rules += "all w:World, x:w."+specificTargetName+" | (w."+specificName+").x=(w."+generalName+").x";
		}
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public Association getSpecific() {
		return specific;
	}
	public void setSpecific(Association specific) throws Exception {
		/*TODO: Provide a test to ensure that the provided associations characterize indeed this antipattern*/
		if (specific==null)
			throw new Exception("Provided specific association is null");
		else if(specific.equals(this.general))
			throw new Exception("Provided specific association is equal to existing general assocition");
		this.specific = specific;
	}
	
	public Association getGeneral() {
		return general;
	}
	public void setGeneral(Association general) throws Exception {
		/*TODO: Provide a test to ensure that the provided associations characterize indeed this antipattern*/
		if (general==null) 
			throw new Exception("Provided general association is null");
		else if (general.equals(this.specific))
			throw new Exception("Provided general association is equal to existing specific assocition");
		this.general = general;
	}
	public Classifier getGeneralSource() {
		return generalSource;
	}
	public Classifier getGeneralTarget() {
		return generalTarget;
	}
	public Classifier getSpecificSource() {
		return specificSource;
	}
	public Classifier getSpecificTarget() {
		return specificTarget;
	}
	
	@Override
	public String toString() {
		String result;
		
		result = "General: "+generalSource.getName()+" - "+general.getName()+" - "+generalTarget.getName()+"\n";
		result += "Specific: "+specificSource.getName()+" - "+specific.getName()+" - "+specificTarget.getName();
		
		return result;
	}
	
}
