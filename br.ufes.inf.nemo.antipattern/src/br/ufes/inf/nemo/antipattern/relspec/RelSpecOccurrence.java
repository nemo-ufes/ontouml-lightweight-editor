package br.ufes.inf.nemo.antipattern.relspec;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.SpecializationType;

/*Relation Specialization*/
public class RelSpecOccurrence extends AntipatternOccurrence{
	private Association general, specific;
	private Classifier generalSource, generalTarget, specificSource, specificTarget;
	private Property generalSourceEnd, generalTargetEnd, specificSourceEnd, specificTargetEnd;
	private boolean isReverse;
	
	public enum OperationType { SUBSET, REDEFINE, DISJOINT }
			
	public RelSpecOccurrence (Association specific, Association general, RelSpecAntipattern ap) throws Exception{
		super(ap);
		this.setGeneral(general);
		this.setSpecific(specific);
				
		setProperties(specific, general);
	}

	private void setProperties(Association specific, Association general){
		specificSourceEnd = specific.getMemberEnd().get(0);
		specificTargetEnd = specific.getMemberEnd().get(1);
		generalSourceEnd = general.getMemberEnd().get(0);
		generalTargetEnd = general.getMemberEnd().get(1);
		
		specificSource = (Classifier) specificSourceEnd.getType();
		specificTarget = (Classifier) specificTargetEnd.getType();
		generalSource = (Classifier) generalSourceEnd.getType();
		generalTarget = (Classifier) generalTargetEnd.getType();
		
		if((specificSource.equals(generalSource) || specificSource.allParents().contains(generalSource))&&(specificTarget.equals(generalTarget) || specificTarget.allParents().contains(generalTarget)))
			isReverse = false;
		else if((specificSource.equals(generalTarget) || specificSource.allParents().contains(generalTarget))&&(specificTarget.equals(generalSource) || specificTarget.allParents().contains(generalSource)))
			isReverse = true;
	}
	
	public Classifier getAlignedSpecificSource(){
		if (isReverse)
			return specificTarget;
		else
			return specificSource;
		
	}
	
	public Property getAlignedSpecificSourceEnd(){
		if (isReverse)
			return specificTargetEnd;
		else
			return specificSourceEnd;
		
	}
	
	public Classifier getAlignedSpecificTarget(){
		if (isReverse)
			return specificSource;
		else
			return specificTarget;
	}
	
	public Property getAlignedSpecificTargetEnd(){
		if (isReverse)
			return specificSourceEnd;
		else
			return specificTargetEnd;
	}
	
	public boolean isVariation1(){
		return (!generalSource.equals(generalTarget) && !generalSource.equals(specificSource) && !generalSource.equals(specificTarget) 
				&& !generalTarget.equals(specificSource) && !generalTarget.equals(specificTarget) && !specificSource.equals(specificTarget));
	}
	
	public boolean isVariation2(){
		return (generalSource.equals(specificSource) && !generalSource.equals(generalTarget) &&  !generalSource.equals(specificTarget) 
				&& !generalTarget.equals(specificTarget));
	}
	
	public boolean isVariation3(){
		return (generalTarget.equals(specificTarget) && !generalTarget.equals(generalSource) &&  !generalTarget.equals(specificSource) 
				&& !generalSource.equals(specificSource));
	}
	
	public boolean isVariation4(){
		return (generalSource.equals(specificSource) && generalTarget.equals(specificTarget) && !generalSource.equals(generalTarget));
	}
	
	public boolean isVariation5(){
		return (generalSource.equals(specificSource) && specificSource.equals(specificTarget) && specificTarget.equals(generalTarget));
	}
	
	public boolean isVariation6(){
		return (generalSource.equals(generalTarget) && specificSource.equals(specificTarget) && !generalSource.equals(specificSource));
	}
	
	public boolean isVariation7(){
		return (generalSource.equals(generalTarget) && !generalSource.equals(specificSource) && !generalSource.equals(specificTarget)
				&& !specificSource.equals(specificTarget));
	}
	
	/*
	public String generateOcl(int type, OntoUMLParser parser) throws Exception{
		String invName = new String(), contextName = new String(), invRule = new String();
		String aes_name, aeg_name;
		
		Classifier specificSource = (Classifier) specific.getMemberEnd().get(0).getType();
		//Classifier specificTarget = (Classifier) specific.getMemberEnd().get(1).getType();
		Classifier generalSource = (Classifier) general.getMemberEnd().get(0).getType();
		//Classifier generalTarget = (Classifier) general.getMemberEnd().get(1).getType();
		
		if (specificSource.equals(generalSource) || specificSource.allParents().contains(generalSource))
		{
			aes_name = specific.getMemberEnd().get(1).getName();
			aeg_name = general.getMemberEnd().get(1).getName();
		}
		else {
			aes_name = specific.getMemberEnd().get(1).getName();
			aeg_name = general.getMemberEnd().get(0).getName();
		}
		
		contextName = specific.getMemberEnd().get(0).getType().getName();
		
		if(type==SUBSET){
			invName = "subset";
			invRule = "self."+aeg_name+"->includesAll(self."+aes_name+"->asSet())";
		}
		else if (type==REDEFINE){
			invName = "redefine";
			invRule = "self."+aeg_name+"=self."+aes_name;
		}
		else if (type==DISJOINT){
			invName = "disjoint";
			invRule = "self."+aeg_name+"->excludesAll(self."+aes_name+"->asSet())";
		}
		else if (type==NONSUBSET){
			invName = "nonSubset";
			invRule = "not (self."+aeg_name+"->includesAll(self."+aes_name+"->asSet()))";
		}
		else
			throw new Exception("The method 'RSAntiPattern::generateOcl' requires that a valid type of predicate is provided");
		
		invName += "_"+specific.getName()+"_"+general.getName();
		
		return 	"context _'"+contextName+"'\n"+
				"inv "+invName+" : \n    "+invRule;
		
	}
	*/
	
	
		
	
	/*This method generates alloy predicates for the RS AntiPattern. */
	/*
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
	}*/
	
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
		
		String s =	"Parent Assoc.: "+OntoUMLNameHelper.getTypeAndName(general, true, true)+"\r\n"+
					"\tSource: "+OntoUMLNameHelper.getNameTypeAndMultiplicity(generalSourceEnd, false, false, true, true, true)+"\r\n"+
					"\tTarget: "+OntoUMLNameHelper.getNameTypeAndMultiplicity(generalTargetEnd, false, false, true, true, true)+
					"\r\n\r\n"+
					"Child Assoc.: "+OntoUMLNameHelper.getTypeAndName(specific, true, true)+"\r\n"+
					"\tSource: "+OntoUMLNameHelper.getNameTypeAndMultiplicity(specificSourceEnd, false, false, true, true, true)+"\r\n"+
					"\tTarget: "+OntoUMLNameHelper.getNameTypeAndMultiplicity(specificTargetEnd, false, false, true, true, true);
		
		if(isReverse)
			s+="\r\n\r\nIs Reversed";
		
		return s;
	}
	
	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(general);
		selection.add(specific);
		selection.add(specificSource);
		selection.addAll(specificSource.allParents());
		selection.add(specificTarget);
		selection.addAll(specificTarget.allParents());
		
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.ALL_ANCESTORS, false);
		return parser;
	}

	@Override
	public String getShortName() {
		return "Parent: "+OntoUMLNameHelper.getTypeAndName(general, true, true)+", Child: "+OntoUMLNameHelper.getTypeAndName(specific, true, true);
	}
	
	////////////////FIX OUTCOMES//////////////
	

	public void generateOCL(OperationType type){
		
		String invRule = "self.";
		String invName = specific.getName()+"_";
		String oclOperation, contextName;
		
		Property context, generalContext;
		
		if (!getAlignedSpecificTarget().equals(getGeneralTarget()) && getAlignedSpecificSource().equals(getGeneralSource())){
			context = getAlignedSpecificTargetEnd();
			generalContext = generalTargetEnd;
		}
		else{
			context = getAlignedSpecificSourceEnd();
			generalContext = generalSourceEnd;
		} 
		
		//include end's name if null or empty
		fixPropertyName(context.getOpposite());
		
		invRule += addQuotes(context.getOpposite().getName())+"->asSet()";
		contextName = context.getType().getName();
		
		switch (type) {
		case SUBSET:
			oclOperation = "->includesAll(";
			invName += "subsets";
			break;

		case REDEFINE:
			oclOperation = "=";
			invName += "redefines";
			break;
			
		case DISJOINT:
			oclOperation = "->excludesAll(";
			invName += "disjointWith";
			break;
		default :
			oclOperation = "ERROR";
			invName += "ERROR";
		}
		
		invRule+=oclOperation+"self";
		
		if(!generalContext.getType().equals(context.getType()))
			invRule += ".oclAsType("+addQuotes(generalContext.getType().getName())+")";
		
		//include end's name if null or empty
		fixPropertyName(generalContext.getOpposite());
		
		invRule += "."+addQuotes(generalContext.getOpposite().getName())+"->asSet()";
		
		if(type==OperationType.SUBSET || type==OperationType.DISJOINT)
			invRule += ")";
		
		invName += "_"+general.getName();
		
		super.fix.addAll(fixer.generateOCLInvariant(contextName, invName, invRule));
	}

	private void fixPropertyName(Property property) {
		if(property.getName()==null || property.getName().trim().isEmpty()){
			if(property.getType()==null || property.getType().getName()==null || property.getType().getName().trim().isEmpty())
				property.setName("property");
			else
				property.setName(property.getType().getName().trim().toLowerCase());
		}
	}
	
	public void subsetRelations(){
		generateOCL(OperationType.SUBSET);
		fix.addAll(fixer.subsetProperty(generalTargetEnd, specificTargetEnd, SpecializationType.SUBSET, true));
	}
	
	public void redefineRelations(){
		generateOCL(OperationType.REDEFINE);
		fix.addAll(fixer.subsetProperty(generalTargetEnd, specificTargetEnd, SpecializationType.REDEFINE, true));
	}
	
	public void createSpecificSourceSubTypeAndRedefine(ClassStereotype stereotype)
	{
		this.fix.addAll(fixer.createSubTypeAsInvolvingLink(specificSource, stereotype, specific));
		setProperties(specific, general);
		generateOCL(OperationType.REDEFINE);
		fix.addAll(fixer.subsetProperty(generalTargetEnd, specificTargetEnd, SpecializationType.REDEFINE, true));
	}
	
	public void createSpecificTargetSubTypeAndRedefine(ClassStereotype stereotype)
	{
		this.fix.addAll(fixer.createSubTypeAsInvolvingLink(specificTarget, stereotype, specific));
		setProperties(specific, general);
		generateOCL(OperationType.REDEFINE);
		fix.addAll(fixer.subsetProperty(generalSourceEnd, specificSourceEnd, SpecializationType.REDEFINE, true));
	}
	
	public void createGeneralSourceSubTypeAndRedefine(ClassStereotype stereotype)
	{
		this.fix.addAll(fixer.createSubTypeAsInvolvingLink(generalSource, stereotype, general));
		setProperties(specific, general);
		generateOCL(OperationType.REDEFINE);
		fix.addAll(fixer.subsetProperty(specificTargetEnd, generalTargetEnd, SpecializationType.REDEFINE, true));
	}
	
	public void createGeneralTargetSubTypeAndRedefine(ClassStereotype stereotype)
	{
		this.fix.addAll(fixer.createSubTypeAsInvolvingLink(generalTarget, stereotype, general));
		setProperties(specific, general);
		generateOCL(OperationType.REDEFINE);
		fix.addAll(fixer.subsetProperty(specificSourceEnd, generalSourceEnd, SpecializationType.REDEFINE, true));
	}
	
	public void createGeneralBothSubTypesAndRedefine(ClassStereotype sourceStereotype, ClassStereotype targetStereotype)
	{
		this.fix.addAll(fixer.createSubTypeAsInvolvingLink(generalSource, sourceStereotype, general));
		this.fix.addAll(fixer.createSubTypeAsInvolvingLink(generalTarget, targetStereotype, general));
		setProperties(specific, general);
		generateOCL(OperationType.REDEFINE);
		fix.addAll(fixer.subsetProperty(specificTargetEnd, generalTargetEnd, SpecializationType.REDEFINE, true));
	}
	
	public void createSpecificBothSubTypesAndRedefine(ClassStereotype sourceStereotype, ClassStereotype targetStereotype)
	{
		this.fix.addAll(fixer.createSubTypeAsInvolvingLink(specificSource, sourceStereotype, general));
		this.fix.addAll(fixer.createSubTypeAsInvolvingLink(specificTarget, targetStereotype, general));
		setProperties(specific, general);
		generateOCL(OperationType.REDEFINE);
		fix.addAll(fixer.subsetProperty(generalTargetEnd, specificTargetEnd, SpecializationType.REDEFINE, true));
	}
	
	public void deleteSpecific(){
		this.fix.addAll(fixer.deleteElement(specific));
	}
	
	public void deleteGeneral(){
		this.fix.addAll(fixer.deleteElement(general));
	}
	
	public boolean isEqual(Association a1, Association a2){
		return (a1.equals(general) && a2.equals(specific)) || (a1.equals(specific) && a2.equals(general)) ;
	}

	public Property getGeneralTargetEnd() {
		return generalTargetEnd;
	}

	public Property getGeneralSourceEnd() {
		return generalSourceEnd;
	}
	
}
