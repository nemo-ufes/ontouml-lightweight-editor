package br.ufes.inf.nemo.ontouml.antipattern;

import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.SourceTargetAssociation;
import RefOntoUML.Association;
import RefOntoUML.Classifier;

/*Relation Specialization*/
public class RSAntiPattern {
	private Association general;
	private Association specific;
	private Classifier generalSource;
	private Classifier generalTarget;
	private Classifier specificSource;
	private Classifier specificTarget;
	private String generalName, specificName, specificSourceName, specificTargetName, generalSourceName, generalTargetName; 
	
	
	public RSAntiPattern (Association specific, Association general, NamesMapper mapper) throws Exception{
						
		this.setGeneral(general);
		this.setSpecific(specific);
		
		/*This methods are only necessary because the provided model may not correctly instantiante the RefOntoUML Metamodel. 
		 * In this case, it is necessary to correct who is the source and who is the target in the association, according to the logic in the metamodel.
		 * For instance, in a mediation, the relator is always the source and the mediated type is always the target*/
		specificSource = (Classifier) SourceTargetAssociation.getSourceAlloy(specific);
		specificTarget = (Classifier) SourceTargetAssociation.getTargetAlloy(specific);
		generalSource = (Classifier) SourceTargetAssociation.getSourceAlloy(general);
		generalTarget = (Classifier) SourceTargetAssociation.getTargetAlloy(general);
		
		/*This provides the names that will be used in the generated Alloy code.
		 * There is a difference between the element name in the model and in the Alloy specification, since there may be repeated names in the model, but not in Alloy*/ 
		generalName = mapper.elementsMap.get(general);
		specificName = mapper.elementsMap.get(specific);
		specificSourceName = mapper.elementsMap.get(specificSource);
		specificTargetName = mapper.elementsMap.get(specificTarget);
		generalSourceName = mapper.elementsMap.get(generalSource);
		generalTargetName = mapper.elementsMap.get(generalTarget);
		
	}
	
	/*This method generates an Alloy predicate that states that the specific association is a SUBTYPE of the general association*/
	public String generateSubsetPredicate () {
		String predicate, rules, name;
					
		name = "subset_"+this.specificName+"_"+this.generalName;
		rules = "some "+this.specificName+"\n\t";
		rules += "some "+this.generalName+"\n\t";
		rules += this.specificName+" in "+this.generalName;
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	/*This method generates an Alloy predicate that states that the specific association is NOT A SUBTYPE of the general association*/
	public String generateNotSubsetPredicate () {
		String predicate, rules, name;
		
		name = "not_subset_"+this.specificName+"_"+this.generalName;
		rules = "some "+this.specificName+"\n\t";
		rules += "some "+this.generalName+"\n\t";
		rules += this.specificName+" not in "+this.generalName;
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	/*This method generates an Alloy predicate that states that the specific association is DISJOINT with the general association*/
	public String generateDisjointPredicate () {
		String predicate, rules, name;
						
		name = "disjoint_"+this.specificName+"_"+this.generalName;
		rules = "some "+this.specificName+"\n\t";
		rules += "some "+this.generalName+"\n\t";
		rules += "no "+this.specificName+" & "+this.generalName;
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	/*This method generates an Alloy predicate that states that the specific association is a REDEFINITION of the general association*/
	public String generateRedefinePredicate () {
		String predicate, rules, name;
				
		name = "redefine_"+specificName+"_"+generalName;
				
		if (this.specificSource.allParents().contains(generalSource) || this.specificSource.allParents().contains(generalTarget)){
			rules = "some "+this.specificSourceName+"\n\t";
			rules += "all w:World, x:w."+this.specificSourceName+" | x.(w."+this.specificName+")=x.(w."+this.generalName+")";
		}
		else{
			rules = "some "+this.specificTargetName+"\n\t";
			rules += "all w:World, x:w."+this.specificTargetName+" | (w."+this.specificName+").x=(w."+this.generalName+").x";
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
		
		result = "General: "+generalSourceName+" - "+generalName+" - "+generalTargetName+"\n";
		result += "Specific: "+specificSourceName+" - "+specificName+" - "+specificTargetName;
		
		return result;
	}
	
}
