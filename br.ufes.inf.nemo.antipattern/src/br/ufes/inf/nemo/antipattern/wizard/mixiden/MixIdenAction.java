package br.ufes.inf.nemo.antipattern.wizard.mixiden;

import java.text.Normalizer;
import java.util.ArrayList;

import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;
import br.ufes.inf.nemo.antipattern.mixiden.SortalToAdd;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class MixIdenAction extends AntiPatternAction<MixIdenOccurrence>{

	public enum Action {CHANGE_MIXIN_STEREOTYPE, ADD_SUBTYPES, CHANGE_SUBTYPES_IDENTITY};
	
	private ArrayList<SortalToAdd> newSubtypes;
	private ArrayList<SortalToAdd> subtypesToFix;
	
	public MixIdenAction(MixIdenOccurrence ap) {
		super(ap);
	}

	@Override
	public void run() {
		if(code==Action.CHANGE_MIXIN_STEREOTYPE){
			ap.changeMixinStereotype();
		}
		else if(code==Action.CHANGE_SUBTYPES_IDENTITY) {
			ap.changeIdentityProviders(subtypesToFix);
		}
		else if(code==Action.ADD_SUBTYPES) {
			ap.addSortals(newSubtypes);
		}
	}
	
	public void setChangeMixinStereotype(){
		code = Action.CHANGE_MIXIN_STEREOTYPE;
	}
	
	public void setAddSubtypes(ArrayList<SortalToAdd> newSubtypes){
		code = Action.ADD_SUBTYPES;
		this.newSubtypes = newSubtypes;
		this.subtypesToFix = null;
	}
	
	public void setChangeSubtypesIdentity(ArrayList<SortalToAdd> subtypesToFix){
		code = Action.CHANGE_SUBTYPES_IDENTITY;
		this.subtypesToFix = subtypesToFix;
		this.newSubtypes = null;
	}

		
	@Override
	public String toString(){
		String result = new String();
		
		if(code==Action.CHANGE_MIXIN_STEREOTYPE) {
			result += "Change Class: Change stereotype of <"+ap.getMixin().getName()+"> to ";
			if(ap.isHasAntiRigid() && !ap.isHasRigid())
				result += "«Role»";
			else
				result += "«Subkind»";
			result+="\nAdd Generalization: from "+ap.getMixin().getName()+" to "+ap.getIdentityProvider().getName()+"\n";
		}
		else if(code==Action.ADD_SUBTYPES){
			
			for (SortalToAdd subtype : newSubtypes){
				if(subtype.newSortal()){
					result+="Add Class: «"+getStereotypeName(subtype.getSortalStereotype())+"» "+subtype.getSortalName()+"\n";
					result+="Add Generalization: from "+subtype.getSortalName()+" to "+ap.getMixin().getName()+"\n";
				}
				else
					result+="Add Generalization: from "+subtype.getSortalName()+" to "+ap.getMixin().getName()+"\n";
				
				if(subtype.newIdentityProvider()){
					result+="Add Class: «"+getStereotypeName(subtype.getIdentityProviderStereotype())+"» "+subtype.getIdentityProviderName()+"\n";
				}
			}			
		}
		else if (code==Action.CHANGE_SUBTYPES_IDENTITY){
			for (SortalToAdd subtype : subtypesToFix) {
				if(subtype.newIdentityProvider())
					result+="Add Class: «"+getStereotypeName(subtype.getIdentityProviderStereotype())+"» "+subtype.getIdentityProviderName()+"\n";
				
				result+="Modify Class: Change "+subtype.getSortalName()+" identity provider to: "+subtype.getIdentityProviderName()+"\n";
			}
		}
		
		return result;
	}
	
	private String getStereotypeName(Class<?> stereotype){
		String type = stereotype.getName().replaceAll("RefOntoUML.impl.","");
		type = type.replaceAll("class","");
		type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
}
