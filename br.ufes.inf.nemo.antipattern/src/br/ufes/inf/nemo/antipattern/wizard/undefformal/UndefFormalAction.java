package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import java.util.HashMap;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class UndefFormalAction  extends AntiPatternAction< UndefFormalOccurrence>{

	public Association assoc;
	public Classifier source;
	public Classifier target;
	public HashMap<String,String> sourceMapType = new HashMap<String,String>();
	public HashMap<String,String> targetMapType = new HashMap<String,String>();
	public HashMap<String,String> sourceMapStereo = new HashMap<String,String>();
	public HashMap<String,String> targetMapStereo = new HashMap<String,String>();
	public String constraints;
	public int upper;
	public HashMap<String,String> newMediatedMap  = new HashMap<String,String>();
	
	public UndefFormalAction( UndefFormalOccurrence ap) {
		super(ap);
	}

	public enum Action {
		CHANGE_TO_MEDIATION, 
		CHANGE_TO_CHARACTERIZATION,
		CHANGE_TO_CHARACTERIZATION_INVERTING_SIDES,
		CHANGE_TO_MATERIAL,
		CHANGE_TO_SUBCOLLECTIONOF_SRC_WHOLE, 
		CHANGE_TO_SUBCOLLECTIONOF_TGT_WHOLE,
		CHANGE_TO_MEMBEROF_SRC_WHOLE, 
		CHANGE_TO_MEMBEROF_TGT_WHOLE,
		CHANGE_TO_SUBQUANTITYOF_SRC_WHOLE, 
		CHANGE_TO_SUBQUANTITYOF_TGT_WHOLE,
		CREATE_DATATYPES_ATTRIBUTES_AND_RULES,
		SET_UPPER_MULTP,
		CREATE_NEW_MEDIATED_TYPES,
	}
	
	public void setChangeToMediation(Association assoc, Classifier source, Classifier target){
		code = Action.CHANGE_TO_MEDIATION;
		this.assoc = assoc;
		this.source=source;
		this.target=target;
	}
	
	public void setUpperMult(Association assoc, Classifier source, Classifier target, int upper)
	{
		code = Action.SET_UPPER_MULTP;
		this.assoc=assoc;
		this.source=source;
		this.target=target;
		this.upper=upper;
	}
	
	public void setCreateMediatedTypes(HashMap<String,String> newMediatedMap)
	{
		code = Action.CREATE_NEW_MEDIATED_TYPES;
		this.newMediatedMap=newMediatedMap;
	}
	
	public void setCreateDatatypesAttributesAndRules(Association assoc, Classifier source, Classifier target, 
		HashMap<String,String> sourceMapType, HashMap<String,String> targetMapType,	HashMap<String,String> sourceMapStereo, HashMap<String,String> targetMapStereo,
		String constraints){
		code = Action.CREATE_DATATYPES_ATTRIBUTES_AND_RULES;
		this.assoc = assoc;
		this.source=source;
		this.target=target;
		this.sourceMapType=sourceMapType;
		this.sourceMapStereo=sourceMapStereo;
		this.targetMapStereo=targetMapStereo;
		this.targetMapType=targetMapType;
		this.constraints=constraints;
	}
	
	public void setChangeToCharacterization(Association assoc, Classifier source, Classifier target){
		code = Action.CHANGE_TO_CHARACTERIZATION;
		this.assoc = assoc;
		this.source=source;
		this.target=target;
	}
	
	public void setChangeToCharacterizationInvertingSides(Association assoc, Classifier source, Classifier target){
		code = Action.CHANGE_TO_CHARACTERIZATION_INVERTING_SIDES;
		this.assoc = assoc;
		this.source=source;
		this.target=target;		
	}
	public void setChangeToSubQuantityOfSrcWhole(Association assoc, Classifier source, Classifier target){
		code = Action.CHANGE_TO_SUBQUANTITYOF_SRC_WHOLE;
		this.assoc = assoc;
		this.source=source;
		this.target=target;
	}
	
	public void setChangeToSubQuantityOfTgtWhole(Association assoc, Classifier source, Classifier target){
		code = Action.CHANGE_TO_SUBQUANTITYOF_TGT_WHOLE;
		this.assoc = assoc;
		this.source=source;
		this.target=target;
	}
	
	public void setChangeToMemberOfSrcWhole(Association assoc, Classifier source, Classifier target){
		code = Action.CHANGE_TO_MEMBEROF_SRC_WHOLE;
		this.assoc = assoc;
		this.source=source;
		this.target=target;
	}

	public void setChangeToMemberOfTgtWhole(Association assoc, Classifier source, Classifier target){
		code = Action.CHANGE_TO_MEMBEROF_TGT_WHOLE;
		this.assoc = assoc;
		this.source=source;
		this.target=target;
	}

	public void setChangeToSubCollectionOfSrcWhole(Association assoc, Classifier source, Classifier target){
		code = Action.CHANGE_TO_SUBCOLLECTIONOF_SRC_WHOLE;
		this.assoc = assoc;
		this.source=source;
		this.target=target;
	}
	
	public void setChangeToSubCollectionOfTgtWhole(Association assoc, Classifier source, Classifier target){
		code = Action.CHANGE_TO_SUBCOLLECTIONOF_TGT_WHOLE;
		this.assoc = assoc;
		this.source=source;
		this.target=target;
	}
	
	public void setChangeToMaterial(Association assoc, Classifier source, Classifier target){	
		code = Action.CHANGE_TO_MATERIAL;
		this.assoc = assoc;
		this.source = source;
		this.target = target;
	}
	
	@Override
	public void run() {
		if(code==Action.CHANGE_TO_MEDIATION) ap.changeToMediation(assoc,source,target);		
		else if(code==Action.CHANGE_TO_CHARACTERIZATION) ap.changeToCharacterization(assoc,source,target); 
		else if(code==Action.CHANGE_TO_MATERIAL) ap.changeToMaterial(assoc,source,target);			
		else if(code==Action.CHANGE_TO_SUBCOLLECTIONOF_SRC_WHOLE) ap.changeToSubCollectionOfSrcWhole(assoc,source,target);
		else if(code==Action.CHANGE_TO_SUBCOLLECTIONOF_TGT_WHOLE) ap.changeToSubCollectionOfTgtWhole(assoc,source,target);
		else if(code==Action.CHANGE_TO_MEMBEROF_SRC_WHOLE) ap.changeToMemberOfSrcWhole(assoc,source,target);			
		else if(code==Action.CHANGE_TO_MEMBEROF_TGT_WHOLE) ap.changeToMemberOfTgtWhole(assoc,source,target);			
		else if(code==Action.CHANGE_TO_SUBQUANTITYOF_SRC_WHOLE) ap.changeToSubQuantityOfSrcWhole(assoc,source,target);			
		else if(code==Action.CHANGE_TO_SUBQUANTITYOF_TGT_WHOLE) ap.changeToSubQuantityOfTgtWhole(assoc,source,target);	
		else if(code==Action.CREATE_DATATYPES_ATTRIBUTES_AND_RULES) ap.createDatatypesAttributesAndConstraint(assoc,source,target,sourceMapType,targetMapType,sourceMapStereo,targetMapStereo, constraints);
		else if(code==Action.SET_UPPER_MULTP) ap.setUpperMult(assoc,source,target,upper);
		else if(code==Action.CREATE_NEW_MEDIATED_TYPES) ap.createNewMediatedTypes(newMediatedMap);
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		if(code==Action.CHANGE_TO_MEDIATION) {
			result = "Change formal association "+assoc.getName()+" to <<mediation>> "; 
		
		}else if(code==Action.CHANGE_TO_CHARACTERIZATION){
			result = "Change formal association "+assoc.getName()+" to <<characterization>> ";
					
		}else if(code==Action.CHANGE_TO_MATERIAL){
			result = "Change formal association "+assoc.getName()+" to <<material>> ";
			
		}else if(code==Action.CHANGE_TO_SUBCOLLECTIONOF_SRC_WHOLE){
			result = "Change formal association "+assoc.getName()+" to <<subCollectionOf>> with "+target.getName()+" as subcollection";

		}else if(code==Action.CHANGE_TO_SUBCOLLECTIONOF_TGT_WHOLE){
			result = "Change formal association "+assoc.getName()+" to <<subCollectionOf>> with "+source.getName()+" as subcollection";

		}else if(code==Action.CHANGE_TO_MEMBEROF_SRC_WHOLE){
			result = "Change formal association "+assoc.getName()+" to <<memberOf>> with "+target.getName()+" as member";
			
		}else if(code==Action.CHANGE_TO_MEMBEROF_TGT_WHOLE){
			result = "Change formal association "+assoc.getName()+" to <<memberOf>> with "+source.getName()+" as member";
			
		}else if(code==Action.CHANGE_TO_SUBQUANTITYOF_SRC_WHOLE){
			result = "Change formal association "+assoc.getName()+" to <<subQuantityOf>> with "+source.getName()+" as whole";
			
		}else if(code==Action.CHANGE_TO_SUBQUANTITYOF_TGT_WHOLE){
			result = "Change formal association "+assoc.getName()+" to <<subQuantityOf>> with "+target.getName()+" as whole";			 
				
		}else if(code==Action.CREATE_DATATYPES_ATTRIBUTES_AND_RULES){
			for(String name: sourceMapType.keySet()){
				result+= "Create attribute "+name+": "+sourceMapType.get(name)+" ("+sourceMapStereo.get(name)+") on class "+source.getName()+"\n";
			}
			for(String name: targetMapType.keySet()){
				result+= "Create attribute "+name+": "+targetMapType.get(name)+" ("+targetMapStereo.get(name)+") on class "+target.getName()+"\n";
			}
			result += "Set Formal relation as isDerived"+"\n";
			result += "Create OCL constraint";			 
		
		}else if(code==Action.SET_UPPER_MULTP){
			result = "Set upper cardinality on mediated side "+target.getName()+" to "+upper;
		
		}else if(code==Action.CREATE_NEW_MEDIATED_TYPES){
			for(String name: newMediatedMap.keySet()){
				result += "Create new mediated type: "+name +" ("+newMediatedMap.get(name)+")"+", and a mediation connected from the relator to it "+"\n";	
			}			
		}
				
		return result; 
	}
	
}
