package br.ufes.inf.nemo.antipattern.wizard.relspec;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;

public class RelSpecAction extends AntiPatternAction <RelSpecOccurrence> {

	public RelSpecAction(RelSpecOccurrence ap) 
	{
		super(ap);	
	}
	
	public enum Action {
		SUBSET, REDEFINE, DISJOINT, SPEC_SPECIFIC_SOURCE_REDEFINE, SPEC_SPECIFIC_TARGET_REDEFINE,
		SPEC_GENERAL_SOURCE_REDEFINE, SPEC_GENERAL_TARGET_REDEFINE, DELETE_SPECIFIC, DELETE_GENERAL, 
		SPEC_GENERAL_BOTH_REDEFINE, SPEC_SPECIFIC_BOTH_REDEFINE 
	}
	
	public ClassStereotype source;
	public ClassStereotype target;
	
	@Override
	public void run()
	{
		if(code==Action.SUBSET)
			ap.subsetRelations(); 
		else if(code==Action.REDEFINE)
			ap.redefineRelations();
		else if(code==Action.DISJOINT)
			ap.generateOCL(RelSpecOccurrence.OperationType.DISJOINT);		
		else if(code==Action.DELETE_GENERAL)
			ap.deleteGeneral();
		else if(code==Action.DELETE_SPECIFIC)		
			ap.deleteSpecific();
		else if(code==Action.SPEC_GENERAL_SOURCE_REDEFINE)		
			ap.createGeneralSourceSubTypeAndRedefine(source);
		else if(code==Action.SPEC_GENERAL_TARGET_REDEFINE)
			ap.createGeneralTargetSubTypeAndRedefine(target);
		else if(code==Action.SPEC_GENERAL_BOTH_REDEFINE)
			ap.createGeneralBothSubTypesAndRedefine(source,target);
		else if(code==Action.SPEC_SPECIFIC_SOURCE_REDEFINE)
			ap.createSpecificSourceSubTypeAndRedefine(source);
		else if(code==Action.SPEC_SPECIFIC_SOURCE_REDEFINE)
			ap.createSpecificTargetSubTypeAndRedefine(target);
		else if(code==Action.SPEC_SPECIFIC_BOTH_REDEFINE)
			ap.createSpecificBothSubTypesAndRedefine(source,target);
	}
	
	public void setSubset(){
		code=Action.SUBSET;
	}
	public void setRedefine(){
		code=Action.REDEFINE;
	}
	public void setDisjoint(){
		code=Action.DISJOINT;
	}
	public void setDeleteGeneral(){
		code=Action.DELETE_GENERAL;		
	}
	public void setDeleteSpecific(){
		code=Action.DELETE_SPECIFIC;		
	}	
	public void setSpec_General_Source_Redefine(ClassStereotype source)
	{
		code=Action.SPEC_GENERAL_SOURCE_REDEFINE;
		this.source = source;
	}
	public void setSpec_General_Target_Redefine(ClassStereotype target)
	{
		code=Action.SPEC_GENERAL_TARGET_REDEFINE;
		this.target=target;
	}
	public void setSpec_General_Both_Redefine(ClassStereotype source, ClassStereotype target)
	{
		code=Action.SPEC_GENERAL_BOTH_REDEFINE;
		this.source = source;
		this.target=target;
	}
	public void setSpec_Specific_Source_Redefine(ClassStereotype source)
	{
		code=Action.SPEC_SPECIFIC_SOURCE_REDEFINE;
		this.source = source;
	}
	public void setSpec_Specific_Target_Redefine(ClassStereotype target)
	{
		code=Action.SPEC_SPECIFIC_TARGET_REDEFINE;
		this.target=target;
	}
	public void setSpec_Specific_Both_Redefine(ClassStereotype source, ClassStereotype target)
	{
		code=Action.SPEC_SPECIFIC_BOTH_REDEFINE;
		this.source = source;
		this.target=target;
	}
	
	@Override
	public String toString(){
		String result = new String();
		if(code==Action.SUBSET)
			result="Create OCL constraint: Subsetting";		
		if(code==Action.REDEFINE)
			result="Create OCL constraint: Redefinition";
		if(code==Action.DISJOINT)
			result="Create OCL constraint: Disjointness";		
		if(code==Action.DELETE_GENERAL)
			result="Delete Association: "+ap.getParser().getStringRepresentation(ap.getGeneral());
		if(code==Action.DELETE_SPECIFIC)		
			result="Delete Association: "+ap.getParser().getStringRepresentation(ap.getSpecific());
		if(code==Action.SPEC_GENERAL_SOURCE_REDEFINE)		
			result="Create Class: Subtype of "+ap.getParser().getStringRepresentation(ap.getGeneralSource())+
				   "\nCreate OCL constraint: Redefinition";
		if(code==Action.SPEC_GENERAL_TARGET_REDEFINE)
			result="Create Class: Subtype of "+ap.getParser().getStringRepresentation(ap.getGeneralTarget())+
				   "\nCreate OCL constraint: Redefinition";
		if(code==Action.SPEC_GENERAL_BOTH_REDEFINE)
			result="Create Class: Subtype of "+ap.getParser().getStringRepresentation(ap.getGeneralSource())+
				   "\nCreate Class: Subtype of "+ap.getParser().getStringRepresentation(ap.getGeneralTarget())+
			       "\nCreate OCL constraint: Redefinition";
		if(code==Action.SPEC_SPECIFIC_SOURCE_REDEFINE)
			result="Create Class: Subtype of "+ap.getParser().getStringRepresentation(ap.getSpecificSource())+
			   	   "\nCreate OCL constraint: Redefinition";
		if(code==Action.SPEC_SPECIFIC_TARGET_REDEFINE)
			result="Create Class: Subtype of "+ap.getParser().getStringRepresentation(ap.getSpecificTarget())+
			       "\nCreate OCL constraint: Redefinition";
		if(code==Action.SPEC_SPECIFIC_BOTH_REDEFINE)
			result="Create Class: Subtype of "+ap.getParser().getStringRepresentation(ap.getSpecificSource())+
			   	   "\nCreate Class: Subtype of "+ap.getParser().getStringRepresentation(ap.getSpecificTarget())+
		       	   "\nCreate OCL constraint: Redefinition";
		return result; 
	}
}
