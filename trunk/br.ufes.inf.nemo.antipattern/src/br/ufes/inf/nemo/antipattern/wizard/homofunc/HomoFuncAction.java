package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import java.text.Normalizer;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.NamedElement;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class HomoFuncAction extends AntiPatternAction<HomoFuncOccurrence>{

	public ArrayList<PartElement> partList;
	public ArrayList<RelationElement> relationList;
	public RefOntoUML.Element element;
	
	public HomoFuncAction(HomoFuncOccurrence ap) 
	{
		super(ap);
	}

	public enum Action { CHANGE_TO_COLLECTIVE, CHANGE_TO_MEMBEROF, CHANGE_TO_SUBCOLLECTIONOF, CREATE_NEW_IDENTITY_PROVIDER, 
		  				 CREATE_NEW_PART_TO_WHOLE, CREATE_NEW_SUBPART_TO_WHOLE, 
		  				 CREATE_SUBCOMPONENTOF_TO_EXISTING_SUBPART }
	
	@Override
	public void run(){
		if (code ==Action.CHANGE_TO_COLLECTIVE) {
			ap.changeToCollective(element);
		}
		if (code ==Action.CHANGE_TO_MEMBEROF) {
			ap.changeToMemberOf(element);
		}
		if (code ==Action.CHANGE_TO_SUBCOLLECTIONOF) {
			ap.changeToSubCollectionOf(element);
		}
		if (code ==Action.CREATE_NEW_IDENTITY_PROVIDER) {
			ap.createNewIdentityProvider(element);
		}
		if(code==Action.CREATE_NEW_PART_TO_WHOLE){
			for(PartElement part: partList){
				ap.createNewPartToWhole(part.partStereotype, part.partName, part.componentOfName, part.isEssential, part.isInseparable,part.isShareable,part.isImmutablePart,part.isImmutableWhole);
			}	
		}
		if(code==Action.CREATE_NEW_SUBPART_TO_WHOLE){
			for(PartElement part: partList){
				ap.createNewSubPartToWhole(part.partStereotype, part.partName, part.componentOfName, part.isEssential, part.isInseparable,part.isShareable,part.isImmutablePart,part.isImmutableWhole);
			}	
		}
		if(code==Action.CREATE_SUBCOMPONENTOF_TO_EXISTING_SUBPART){
			for(RelationElement relation: relationList){
				ap.createSubComponentOfToExistingSubPart(relation.getType(),relation.componentOfName,relation.isEssential,relation.isInseparable,relation.isShareable,relation.isImmutablePart,relation.isImmutableWhole);
			}
		}
	}
	
	public void setChangeToCollective(RefOntoUML.Element element)
	{
		code = Action.CHANGE_TO_COLLECTIVE;
		this.element = element;
	}
	
	public void setChangeToSubCollectionOf(RefOntoUML.Element element)
	{
		code = Action.CHANGE_TO_MEMBEROF;
		this.element = element;
	}
	
	public void setChangeToMemberOf(RefOntoUML.Element element)
	{
		code = Action.CHANGE_TO_MEMBEROF;
		this.element = element;
	}
	
	public void setCreateNewIdentityProvider(RefOntoUML.Element element)
	{
		code = Action.CREATE_NEW_IDENTITY_PROVIDER;
		this.element = element;
	}
	
	public void setCreateNewPart(ArrayList<PartElement> partList, boolean createNewPartToWhole)
	{
		if(createNewPartToWhole) code = Action.CREATE_NEW_PART_TO_WHOLE;
		else code = Action.CREATE_NEW_SUBPART_TO_WHOLE;
		this.partList=partList;
	}	
	
	public void setCreateSubComponentOfToExistingSubPart(ArrayList<RelationElement> relationList)
	{
		code=Action.CREATE_SUBCOMPONENTOF_TO_EXISTING_SUBPART;
		this.relationList=relationList;
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		if (code ==Action.CHANGE_TO_COLLECTIVE) {
			result+="Change <<"+getStereotype(element)+">> "+((NamedElement)element).getName()+" to <<Collective>>";
		}	
		if (code ==Action.CHANGE_TO_MEMBEROF) {
			result+="Change <<"+getStereotype(element)+">> "+((NamedElement)element).getName()+" to <<memberOf>>";
		}
		if (code ==Action.CHANGE_TO_SUBCOLLECTIONOF) {		
			result+="Change <<"+getStereotype(element)+">> "+((NamedElement)element).getName()+" to <<subCollectionOf>>";
		}
		if (code ==Action.CREATE_NEW_IDENTITY_PROVIDER) {
			result+="Create new identity provider to <<"+getStereotype(element)+">> "+((NamedElement)element).getName()+"";
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
		if(code==Action.CREATE_SUBCOMPONENTOF_TO_EXISTING_SUBPART)
		{
			for(RelationElement relation: relationList){
				result+="Create new subsetted-componentOf to existing sub-part: ("+relation+")\n";
			}		
		}
		return result;
	}
}