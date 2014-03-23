package br.ufes.inf.nemo.antipattern.wizard.mixiden;

import java.text.Normalizer;
import java.util.ArrayList;

import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class MixIdenAction extends AntiPatternAction<MixIdenOccurrence>{

	public enum Action {CHANGE_MIXIN_STEREOTYPE, ADD_SUBTYPES};
	
	private ArrayList<SortalToAdd> newSubtypes;
	
	public MixIdenAction(MixIdenOccurrence ap) {
		super(ap);
	}

	@Override
	public void run() {
//		if(code==Action.CHANGE_MIXIN_STEREOTYPE){
//			ap.changeMixinStereotype();
//		}
//		else if(code==Action.ADD_SUBTYPES) {
//			if(existingSubtypes!=null && existingSubtypes.size()>0)
//				ap.addExistingSubtypes(existingSubtypes);
//			if(newTypes!=null && newTypes.keySet().size()>0)
//				ap.addNewSubtypes(newTypes);
//		}
	}
	
	public void setChangeMixinStereotype(){
		code = Action.CHANGE_MIXIN_STEREOTYPE;
	}
	
	public void setAddSubtypes(ArrayList<SortalToAdd> newSubtypes){
		code = Action.ADD_SUBTYPES;
		this.newSubtypes = newSubtypes;
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
		}
		else if(code==Action.ADD_SUBTYPES){
			
			for (SortalToAdd subtype : newSubtypes){
				if(subtype.newSortal()){
					result+="Add Class: «"+getStereotypeName(subtype.getSortal().getClass())+"» "+subtype.getSortalName()+"\n";
					result+="Add Generalization: from "+subtype.getSortalName()+" to "+ap.getMixin().getName()+"\n";
				}
				else
					result+="Add Generalization: from "+subtype.getSortalName()+" to "+ap.getMixin().getName()+"\n";
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
