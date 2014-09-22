package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import java.text.Normalizer;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class HomoFuncAction extends AntiPatternAction<HomoFuncOccurrence>{

	public ArrayList<PartElement> partList;
	public ArrayList<RelationElement> relationList;
	public ArrayList<RefOntoUML.Classifier> elemList;
	
	public HomoFuncAction(HomoFuncOccurrence ap) 
	{
		super(ap);
	}

	public enum Action { CHANGE_NATURE_TO_COLLECTION, CHANGE_TO_COLLECTIVE, CHANGE_TO_MEMBEROF, CHANGE_TO_SUBCOLLECTIONOF, CREATE_NEW_IDENTITY_PROVIDER, 
		  				 CREATE_NEW_PART_TO_WHOLE, CREATE_NEW_SUBPART_TO_WHOLE, 
		  				 CREATE_COMPONENTOF_TO_EXISTING_SUBPART, CREATE_COMPONENTOF_TO_EXISTING_TYPE
		  				 }
	
	@Override
	public void run(){
		if (code ==Action.CHANGE_TO_COLLECTIVE) {
			ap.changeToCollective();
		}
		else if (code ==Action.CHANGE_NATURE_TO_COLLECTION) {
			ap.changeNatureToCollection();
		}
		else if (code ==Action.CHANGE_TO_MEMBEROF) {
			ap.changeToMemberOf();
		}
		else if (code ==Action.CHANGE_TO_SUBCOLLECTIONOF) {
			ap.changeToSubCollectionOf();
		}
		else if (code ==Action.CREATE_NEW_IDENTITY_PROVIDER) {
			ap.createNewIdentityProvider();
		}
		else if(code==Action.CREATE_NEW_PART_TO_WHOLE){
			for(PartElement part: partList){
				ap.createNewPartToWhole(part.partStereotype, part.partName, part.componentOfName, part.isEssential, part.isInseparable,part.isShareable,part.isImmutablePart,part.isImmutableWhole);
			}	
		}
		else if(code==Action.CREATE_NEW_SUBPART_TO_WHOLE){
			for(PartElement part: partList){
				ap.createNewSubPartToWhole(part.partStereotype, part.partName, part.componentOfName, part.isEssential, part.isInseparable,part.isShareable,part.isImmutablePart,part.isImmutableWhole);
			}	
		}
		else if(code==Action.CREATE_COMPONENTOF_TO_EXISTING_SUBPART){
			for(RelationElement relation: relationList){
				ap.createSubComponentOfToExistingSubPart(relation.getType(),relation.componentOfName,relation.isEssential,relation.isInseparable,relation.isShareable,relation.isImmutablePart,relation.isImmutableWhole);
			}
		}
		else if(code==Action.CREATE_COMPONENTOF_TO_EXISTING_TYPE){
			for(RelationElement relation: relationList){
				ap.createComponentOfToExistingType(relation.getType(),relation.componentOfName,relation.isEssential,relation.isInseparable,relation.isShareable,relation.isImmutablePart,relation.isImmutableWhole);
			}
		}
	}
	
	public void setChangeToCollective() {
		code = Action.CHANGE_TO_COLLECTIVE;
	}
	
	public void setChangeNatureToCollection()
	{
		code = Action.CHANGE_NATURE_TO_COLLECTION;
	}
	
	public void setChangeToSubCollectionOf()
	{
		code = Action.CHANGE_TO_SUBCOLLECTIONOF;
	}
	
	public void setChangeToMemberOf()
	{
		code = Action.CHANGE_TO_MEMBEROF;
	}
	
	public void setCreateNewIdentityProvider()
	{
		code = Action.CREATE_NEW_IDENTITY_PROVIDER;
	}
	
	public void setCreateNewPart(ArrayList<PartElement> partList, boolean createNewPartToWhole)
	{
		if(createNewPartToWhole) code = Action.CREATE_NEW_PART_TO_WHOLE;
		else code = Action.CREATE_NEW_SUBPART_TO_WHOLE;
		this.partList=partList;
	}	
	
	public void setCreateSubComponentOfToExistingSubPart(ArrayList<RelationElement> relationList)
	{
		code=Action.CREATE_COMPONENTOF_TO_EXISTING_SUBPART;
		this.relationList=relationList;
	}
	
	public void setCreateComponentOfToExistingType(ArrayList<RelationElement> relationList)
	{
		code=Action.CREATE_COMPONENTOF_TO_EXISTING_TYPE;
		this.relationList=relationList;
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		if (code ==Action.CHANGE_TO_COLLECTIVE) {
			result+="Change "+OntoUMLNameHelper.getTypeAndName(getAp().getWhole(),true,true)+" to «Collective»\n";
		}	
		if (code ==Action.CHANGE_NATURE_TO_COLLECTION) {
			result+="Change nature of "+OntoUMLNameHelper.getTypeAndName(getAp().getWhole(),true,true)+" to Collection\n";
		}	
		if (code ==Action.CHANGE_TO_MEMBEROF) {
			result+="Change "+OntoUMLNameHelper.getTypeAndName(getAp().getPartEnd().getAssociation(),true,true)+" to «memberOf»\n";
		}
		if (code ==Action.CHANGE_TO_SUBCOLLECTIONOF) {		
			result+="Change "+OntoUMLNameHelper.getTypeAndName(getAp().getPartEnd().getAssociation(),true,true)+" to «subCollectionOf»\n";
		}
		if (code ==Action.CREATE_NEW_IDENTITY_PROVIDER) {
			result+="Create new identity provider to "+OntoUMLNameHelper.getTypeAndName(getAp().getWhole(),true,true);
		}
		if(code==Action.CREATE_NEW_PART_TO_WHOLE)
		{			
			for(PartElement part: partList){
				result+="Create new part via componentOf: ("+part+")\n";
			}			
		}
		if(code==Action.CREATE_NEW_SUBPART_TO_WHOLE)
		{
			for(PartElement part: partList){
				result+="Create new sub-part via subsetted-componentOf: ("+part+")\n";
			}						
		}
		if(code==Action.CREATE_COMPONENTOF_TO_EXISTING_SUBPART)
		{
			for(RelationElement relation: relationList){
				result+="Create new subsetted-componentOf to existing sub-part: ("+relation+")\n";
			}		
		}
		
		if(code==Action.CREATE_COMPONENTOF_TO_EXISTING_TYPE)
		{
			for(RelationElement relation: relationList){
				result+="Create new componentOf to existing type: ("+relation+")\n";
			}		
		}
		return result;
	}
}
	
