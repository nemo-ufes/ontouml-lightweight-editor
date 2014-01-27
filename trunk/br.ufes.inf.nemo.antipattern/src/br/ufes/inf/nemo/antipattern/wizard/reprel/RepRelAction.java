package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.util.ArrayList;

import RefOntoUML.Mediation;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class RepRelAction extends AntiPatternAction<RepRelOccurrence>{

	public RepRelAction(RepRelOccurrence ap) {
		super(ap);
	}
	
	int n;
	Mediation m;	
	ArrayList<ArrayList<Mediation>> mMatrix = new ArrayList<ArrayList<Mediation>>();
	ArrayList<Integer> nqList = new ArrayList<Integer>();
	ArrayList<Integer> nList = new ArrayList<Integer>();
	
	public enum Action {CHANGE_UPPER_MULT, CREATE_INVARIANT_WITH_QUALITIES, CREATE_INVARIANT}

	@Override
	public void run() {
		
		if(code==Action.CHANGE_UPPER_MULT) ap.changeUpperMult(m,n);
		else if(code==Action.CREATE_INVARIANT_WITH_QUALITIES) ap.createInvariantWithQualities(mMatrix,nqList);
		else if(code==Action.CREATE_INVARIANT) ap.createInvariant(mMatrix,nList);
	}
	
	public void setChangeUpperMult(Mediation m, Integer n){
		code = Action.CHANGE_UPPER_MULT;
		this.n = n;
		this.m=m;
	}
	
	public void setCreateInvariantWithQualities(ArrayList<ArrayList<Mediation>> mMatrix, ArrayList<Integer> NQ){
		code = Action.CREATE_INVARIANT_WITH_QUALITIES;
		this.mMatrix = mMatrix;
		this.nqList=NQ;
	}
	
	public void setCreateInvariant(ArrayList<ArrayList<Mediation>> mMatrix, ArrayList<Integer> N){
		code = Action.CREATE_INVARIANT;
		this.mMatrix = mMatrix;
		this.nList=N;
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		if(code==Action.CHANGE_UPPER_MULT) 
			result = "Modifify the upper cardinality of relator's side in the mediation "+m.getName()+" to "+n; 
					
		else if(code==Action.CREATE_INVARIANT){
			for(ArrayList<Mediation> mList: mMatrix){
				result += "Create OCL invariant limiting to "+nList.get(mMatrix.indexOf(mList))+" the instances of the current relator that mediates the pair: (";
				int i =0; for(Mediation m: mList) { if(i==mList.size()-1) result += m.mediated().getName()+")"; else result += m.mediated().getName()+","; i++; }
				result+="\n";
			}		
					
		}else if(code==Action.CREATE_INVARIANT_WITH_QUALITIES){
			result = "Create two DataTypes namely startTime and endTime"+"\n";
			for(ArrayList<Mediation> mList: mMatrix){
				result += "Create OCL invariant limiting to "+nqList.get(mMatrix.indexOf(mList))+" the instances of the historical relator that mediates the pair (";
				int i =0; for(Mediation m: mList) { if(i==mList.size()-1) result += m.mediated().getName()+")"; else result += m.mediated().getName()+",";  i++; }
				result+="\n";
			}			
		}	
		
		return result; 
	}

}
