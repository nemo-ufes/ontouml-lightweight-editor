package br.ufes.inf.nemo.ontouml.antipattern;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
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
	public static int SUBSET = 1, REDEFINE = 2, NONSUBSET = 3, DISJOINT = 4;
		
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
	
	public String generateOcl(int type) throws Exception{
		String invName = new String(), contextName = new String(), invRule = new String();
		String aes_name, aeg_name;
		
		Classifier specificSource = (Classifier) specific.getMemberEnd().get(0).getType();
		//Classifier specificTarget = (Classifier) specific.getMemberEnd().get(1).getType();
		Classifier generalSource = (Classifier) general.getMemberEnd().get(0).getType();
		//Classifier generalTarget = (Classifier) general.getMemberEnd().get(1).getType();
		
		if (specificSource.equals(generalSource) || specificSource.allParents().contains(generalSource)){
			aes_name = AssociationEndNameGenerator.associationEndName(specific.getMemberEnd().get(1));
			aeg_name = AssociationEndNameGenerator.associationEndName(general.getMemberEnd().get(1));
		}
		else {
			aes_name = AssociationEndNameGenerator.associationEndName(specific.getMemberEnd().get(1));
			aeg_name = AssociationEndNameGenerator.associationEndName(general.getMemberEnd().get(0));
		}
		
		contextName = specific.getMemberEnd().get(0).getType().getName();
		
		if(type==SUBSET){
			invName = "subset";
			invRule = "self."+aeg_name+"->includesAll(self."+aes_name+")";
		}
		else if (type==REDEFINE){
			invName = "redefine";
			invRule = "self."+aeg_name+"=self."+aes_name;
		}
		else if (type==DISJOINT){
			invName = "disjoint";
			invRule = "self."+aeg_name+"->excludesAll(self."+aes_name+")";
		}
		else if (type==NONSUBSET){
			invName = "nonSubset";
			invRule = "not (self."+aeg_name+"->includesAll(self."+aes_name+"))";
		}
		else
			throw new Exception("The method 'RSAntiPattern::generateOcl' requires that a valid type of predicate is provided");
		
		invName += "_"+specific.getName().trim()+"_"+general.getName().trim();
		
		return 	"context "+contextName+"\n"+
				"inv "+invName+" : "+invRule;
		
	}
	
		
	/*This method generates alloy predicates for the RS AntiPattern. */
	public String generatePredicate (OntoUMLParser mapper, int type) throws Exception {
		String predicate, rules, name = new String();
		String generalName, specificName, specificSourceName, specificTargetName, generalSourceName, generalTargetName;
		
		generalName = mapper.getName(general);
		specificName = mapper.getName(specific);
		specificSourceName = mapper.getName(specificSource);
		specificTargetName = mapper.getName(specificTarget);
		generalSourceName = mapper.getName(generalSource);
		generalTargetName = mapper.getName(generalTarget);
		
		if(type==SUBSET)
			name = "subset";
		else if (type==REDEFINE)
			name = "redefine";
		else if (type==DISJOINT)
			name = "disjoint";
		else if (type==NONSUBSET)
			name = "nonSubset";
		else
			throw new Exception("The method 'RSAntiPattern::generatePredicate' requires that a valid type of predicate is provided");
		
		name += "_"+specificName+"_"+generalName;
		rules = "some "+specificName+"\n\t" +
				"some "+generalName+"\n\t";
		
			if (type==REDEFINE){
				if (specificSource.allParents().contains(generalSource))
					rules += specificSourceName+"!="+generalSourceName+"\n\t";
				if (specificTarget.allParents().contains(generalTarget))			
					rules += specificTargetName+"!="+generalTargetName+"\n\t";
				if (specificTarget.allParents().contains(generalSource))
					rules += specificTargetName+"!="+generalSourceName+"\n\t";
				if (specificSource.allParents().contains(generalTarget))
					rules += specificSourceName+"!="+generalTargetName+"\n\t";
			}
		
		
		
		rules += "all w:World | ";
		
		if (specificSource.equals(generalSource) || specificSource.allParents().contains(generalSource)){
			if (type==SUBSET){
				if(general.getMemberEnd().get(0).getUpper()!=1 || general.getMemberEnd().get(1).getUpper()!=1)
					rules += " all x:w."+specificSourceName+", y:w."+specificTargetName+" | x.(w."+specificName+")!=x.(w."+generalName+") and x.(w."+specificName+") in "+"x.(w."+generalName+") and (w."+specificName+").y in "+"(w."+generalName+").y";
				else
					rules += " all x:w."+specificSourceName+", y:w."+specificTargetName+" | x.(w."+specificName+") in "+"x.(w."+generalName+") and (w."+specificName+").y in "+"(w."+generalName+").y";
			}
			if (type==DISJOINT)
				rules += " all x:w."+specificSourceName+", y:w."+specificTargetName+" | no (x.(w."+specificName+") & "+"x.(w."+generalName+")) and no ((w."+specificName+").y & "+"(w."+generalName+").y)";
			if (type==NONSUBSET)
				rules += " all x:w."+specificSourceName+", y:w."+specificTargetName+" | x.(w."+specificName+") not in "+"x.(w."+generalName+") and (w."+specificName+").y not in "+"(w."+generalName+").y";
			if (type==REDEFINE)
				rules += " all x:w."+specificSourceName+", y:w."+specificTargetName+" | x.(w."+specificName+") = "+"x.(w."+generalName+") and (w."+specificName+").y = "+"(w."+generalName+").y";
		}
		
		else {
			if (type==SUBSET){
				if(general.getMemberEnd().get(0).getUpper()!=1 || general.getMemberEnd().get(1).getUpper()!=1)
					rules += " all x:w."+specificSourceName+", y:w."+specificTargetName+" | x.(w."+specificName+")!=x.(~(w."+generalName+")) and x.(w."+specificName+") in "+"x.(~(w."+generalName+")) and (w."+specificName+").y in "+"(~(w."+generalName+")).y";
				else
					rules += " all x:w."+specificSourceName+", y:w."+specificTargetName+" | x.(w."+specificName+") in "+"x.(~(w."+generalName+")) and (w."+specificName+").y in "+"(~(w."+generalName+")).y";
			}
			if (type==DISJOINT)
				rules += " all x:w."+specificSourceName+", y:w."+specificTargetName+" | no (x.(w."+specificName+") & "+"x.(~(w."+generalName+"))) and no ((w."+specificName+").y & "+"(~(w."+generalName+")).y)";
			if (type==NONSUBSET)
				rules += " all x:w."+specificSourceName+", y:w."+specificTargetName+" | x.(w."+specificName+") not in "+"x.(~(w."+generalName+")) and (w."+specificName+").y not in "+"(~(w."+generalName+")).y";
			if (type==REDEFINE)
				rules += " all x:w."+specificSourceName+", y:w."+specificTargetName+" | x.(w."+specificName+") = "+"x.(~(w."+generalName+")) and (w."+specificName+").y = "+"(~(w."+generalName+")).y";
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
