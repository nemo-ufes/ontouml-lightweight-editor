package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class UndefFormalAction  extends AntiPatternAction< UndefFormalOccurrence>{

	public Association assoc;
	public Classifier source;
	public Classifier target;
	
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
		CHANGE_TO_SUBQUANTITYOF_TGT_WHOLE
	}
	
	public void setChangeToMediation(Association assoc, Classifier source, Classifier target){
		code = Action.CHANGE_TO_MEDIATION;
		this.assoc = assoc;
		this.source=source;
		this.target=target;
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
		}
				
		return result; 
	}
	
}
