package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.SourceTargetAssociation;

/*Relation Specialization*/
public class RSAntiPattern extends Antipattern{
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
	
	public String generateOcl(int type, OntoUMLParser parser) throws Exception{
		String invName = new String(), contextName = new String(), invRule = new String();
		String aes_name, aeg_name;
		
		Classifier specificSource = (Classifier) specific.getMemberEnd().get(0).getType();
		//Classifier specificTarget = (Classifier) specific.getMemberEnd().get(1).getType();
		Classifier generalSource = (Classifier) general.getMemberEnd().get(0).getType();
		//Classifier generalTarget = (Classifier) general.getMemberEnd().get(1).getType();
		
		if (specificSource.equals(generalSource) || specificSource.allParents().contains(generalSource)){
			aes_name = parser.getAlias(specific.getMemberEnd().get(1));
			aeg_name = parser.getAlias(general.getMemberEnd().get(1));
		}
		else {
			aes_name = parser.getAlias(specific.getMemberEnd().get(1));
			aeg_name = parser.getAlias(general.getMemberEnd().get(0));
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
	public String generatePredicate (OntoUMLParser parser, int type) throws Exception {
		String predicate, rules, name = new String();
		String generalName, specificName, specificSourceName, specificTargetName, generalSourceName, generalTargetName;
		
		generalName = parser.getAlias(general);
		specificName = parser.getAlias(specific);
		specificSourceName = parser.getAlias(specificSource);
		specificTargetName = parser.getAlias(specificTarget);
		generalSourceName = parser.getAlias(generalSource);
		generalTargetName = parser.getAlias(generalTarget);
		
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
			if ((specificSource.equals(generalSource) && specificTarget.equals(generalTarget)) || (specificSource.equals(generalTarget) && specificTarget.equals(generalSource)))
				throw new Exception("A association may not redefine another if the related types are the same.");
			
			if (specificSource.allParents().contains(generalSource) && specificTarget.allParents().contains(generalTarget))
				rules += specificSourceName+"!="+generalSourceName+"\n\t";
			else if (specificSource.allParents().contains(generalTarget) && specificTarget.allParents().contains(generalSource))
				rules += specificSourceName+"!="+generalTargetName+"\n\t";
			
			//Adds to the redefine predicate a rule to make the supertype different from the subtype, so the analyzer better exemplify a redefinition
			if (specificSource.allParents().contains(generalSource)){
				//check if the source in the alloy transformation is the same as the one in the model, since it may change if it is wrongly defined.
				if(specific.getMemberEnd().get(0).getType().equals(specificSource)){
					if(specific.getMemberEnd().get(0).getLower()==0)
						rules += specificSourceName+"!="+generalSourceName+"\n\t";
				}
				else{
					if(specific.getMemberEnd().get(1).getLower()==0)
						rules += specificSourceName+"!="+generalSourceName+"\n\t";
				}
			}
			else if (specificTarget.allParents().contains(generalTarget)) {			
				//check if the source in the alloy transformation is the same as the one in the model, since it may change if it is wrongly defined.
				if(specific.getMemberEnd().get(1).getType().equals(specificTarget)){
					if(specific.getMemberEnd().get(1).getLower()==0)
						rules += specificTargetName+"!="+generalTargetName+"\n\t";
				}
				else{
					if(specific.getMemberEnd().get(0).getLower()==0)
						rules += specificTargetName+"!="+generalTargetName+"\n\t";
				}
			}
			else if (specificTarget.allParents().contains(generalSource)) {
				
				//check if the source in the alloy transformation is the same as the one in the model, since it may change if it is wrongly defined.
				if(specific.getMemberEnd().get(1).getType().equals(specificTarget)){
					if(specific.getMemberEnd().get(1).getLower()==0)
						rules += specificTargetName+"!="+generalSourceName+"\n\t";
				}
				else{
					if(specific.getMemberEnd().get(0).getLower()==0)
						rules += specificTargetName+"!="+generalSourceName+"\n\t";
				}
			}
			else if (specificSource.allParents().contains(generalTarget)) {
				
				//check if the source in the alloy transformation is the same as the one in the model, since it may change if it is wrongly defined.
				if(specific.getMemberEnd().get(0).getType().equals(specificSource)){
					if(specific.getMemberEnd().get(0).getLower()==0)
						rules += specificSourceName+"!="+generalTargetName+"\n\t";
				}
				else{
					if(specific.getMemberEnd().get(1).getLower()==0)
						rules += specificSourceName+"!="+generalTargetName+"\n\t";
				}
			}
			else {
				throw new Exception("Undefined Relation Specialization AntiPattern");
			}
		}
				
		//Case in which the SOURCE of the SPECIFIC association is SUBTYPE_OF or EQUAL_TO the SOURCE of the GENERAL association
		if (specificSource.equals(generalSource) || specificSource.allParents().contains(generalSource)){
			if (type==SUBSET){
				if(general.getMemberEnd().get(0).getUpper()!=1 && general.getMemberEnd().get(1).getUpper()!=1)
					rules += "all w:World |  w."+specificName+"!=w."+generalName+" and w."+specificName+" in "+"w."+generalName;
				else
					rules += "all w:World |  w."+specificName+" in "+"w."+generalName;
			}
			else if (type==DISJOINT)
				rules += "all w:World | all x:w."+specificSourceName+", y:w."+specificTargetName+" | no (x.(w."+specificName+") & "+"x.(w."+generalName+")) and no ((w."+specificName+").y & "+"(w."+generalName+").y)";
			else if (type==NONSUBSET)
				rules += "all w:World | all x:w."+specificSourceName+", y:w."+specificTargetName+" | x.(w."+specificName+") not in "+"x.(w."+generalName+") and (w."+specificName+").y not in "+"(w."+generalName+").y";

			
			else if (type==REDEFINE){
				//flag to check if the first if was true; 
				boolean second_rule = false;
				//the source is specialized
				if(!specificSource.equals(generalSource)){
					rules += "all w:World | all x:w."+specificSourceName+" | x.(w."+specificName+") = "+"x.(w."+generalName+")";
					second_rule=true;
				}
				//the target is specialized
				if(!specificTarget.equals(generalTarget)){
					if(second_rule)
						rules+="\n\t";
						
					rules += "all w:World | all x:w."+specificTargetName+" | (w."+specificName+").x = "+"(w."+generalName+").x";
				}
			}
		}
		//Case in which the SOURCE of the SPECIFIC association is SUBTYPE_OF or EQUAL_TO the TARGET of the GENERAL association
		else {
			if (type==SUBSET){
				if(general.getMemberEnd().get(0).getUpper()!=1 && general.getMemberEnd().get(1).getUpper()!=1)
					rules += "all w:World |  w."+specificName+"!=~(w."+generalName+") and w."+specificName+" in "+"~(w."+generalName+")";
				else
					rules += "all w:World |  w."+specificName+" in "+"~(w."+generalName+")";
			}
			else if (type==DISJOINT)
				rules += "all w:World | all x:w."+specificSourceName+", y:w."+specificTargetName+" | no (x.(w."+specificName+") & "+"x.(~(w."+generalName+"))) and no ((w."+specificName+").y & "+"(~(w."+generalName+")).y)";
			else if (type==NONSUBSET)
				rules += "all w:World | all x:w."+specificSourceName+", y:w."+specificTargetName+" | x.(w."+specificName+") not in "+"x.(~(w."+generalName+")) and (w."+specificName+").y not in "+"(~(w."+generalName+")).y";

			
			else if (type==REDEFINE){
				//flag to check if the first if was true; 
				boolean second_rule = false;
				//the source is specialized
				if(!specificSource.equals(generalTarget)){
					rules += "all w:World | all x:w."+specificSourceName+"| x.(w."+specificName+") = "+"x.(~(w."+generalName+"))";
					second_rule = true;
				}
				//the target is specialized
				if(!specificTarget.equals(generalSource)){
					if(second_rule)
						rules+="\n\t";
					rules += "all w:World | all x:w."+specificTargetName+"| (w."+specificName+").x = "+"(~(w."+generalName+")).x";
				}
			}
					
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
	
	@Override
	public void setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(general);
		selection.add(specific);
		selection.add(specificSource);
		selection.addAll(specificSource.allParents());
		selection.add(specificTarget);
		selection.addAll(specificTarget.allParents());
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.ALL_ANCESTORS, false);
	}
}
