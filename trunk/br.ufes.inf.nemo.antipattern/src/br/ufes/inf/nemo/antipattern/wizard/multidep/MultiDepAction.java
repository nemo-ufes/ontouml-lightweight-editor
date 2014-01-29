package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.Property;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class MultiDepAction extends AntiPatternAction<MultiDepOccurrence>{

	public ArrayList<ArrayList<Property>> binFormalCombo = new ArrayList<ArrayList<Property>>();
	public Type type;
	public Association m;
	
	public MultiDepAction(MultiDepOccurrence ap) {
		super(ap);
	}

	@Override
	public void run() {
		if(code==Action.CREATE_FORMAL_ASSOCIATIONS) ap.createFormalAssociations(binFormalCombo);
		else if(code==Action.ADD_SUBTYPE_INVOLVING_MEDIATION) ap.addSubTypeInvolvingMediation(type,m);
		else if(code==Action.ADD_SUBTYPE_WITH_INTERMEDIATE_TYPE) ap.addSubTypeWithIntermediate(type,m);
	}
	
	public enum Action {CREATE_FORMAL_ASSOCIATIONS, ADD_SUBTYPE_INVOLVING_MEDIATION, ADD_SUBTYPE_WITH_INTERMEDIATE_TYPE, CREATE_GENERALIZATION_SET}
	
	public void setCreateFormalAssocs(ArrayList<ArrayList<Property>> binFormalCombo){
		code = Action.CREATE_FORMAL_ASSOCIATIONS;
		this.binFormalCombo = binFormalCombo;
	}
	
	public void setAddSubTypeInvolvingMediation(Type type, Association m){
		code = Action.ADD_SUBTYPE_INVOLVING_MEDIATION;
		this.type = type;
		this.m = m;
	}
	
	public void setAddSubTypeWithIntermediate(Type type, Association m){
		code = Action.ADD_SUBTYPE_WITH_INTERMEDIATE_TYPE;
		this.type = type;
		this.m = m;
	}
		
	@Override
	public String toString(){
		String result = new String();
		
		if(code==Action.CREATE_FORMAL_ASSOCIATIONS) {
			//result = "Modifify the upper cardinality of relator's side in the mediation "+m.getName()+" to "+n; 
					
		} else if(code==Action.ADD_SUBTYPE_INVOLVING_MEDIATION){
//			for(ArrayList<Mediation> mList: mMatrix){
//				result += "Create OCL invariant limiting to "+nList.get(mMatrix.indexOf(mList))+" the instances of the current relator that mediates the pair: (";
//				int i =0; for(Mediation m: mList) { if(i==mList.size()-1) result += m.mediated().getName()+")"; else result += m.mediated().getName()+","; i++; }
//				result+="\n";
//			}		
					
		}else if(code==Action.ADD_SUBTYPE_WITH_INTERMEDIATE_TYPE){
//			result = "Create two DataTypes namely startTime and endTime"+"\n";
//			for(ArrayList<Mediation> mList: mMatrix){
//				result += "Create OCL invariant limiting to "+nqList.get(mMatrix.indexOf(mList))+" the instances of the historical relator that mediates the pair (";
//				int i =0; for(Mediation m: mList) { if(i==mList.size()-1) result += m.mediated().getName()+")"; else result += m.mediated().getName()+",";  i++; }
//				result+="\n";
//			}
			
		} 
		
		return result;
	}
}
