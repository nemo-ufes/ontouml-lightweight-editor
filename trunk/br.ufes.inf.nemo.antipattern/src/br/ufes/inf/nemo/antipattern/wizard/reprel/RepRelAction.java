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
	ArrayList<Mediation> mediationList = new ArrayList<Mediation>();
	int NQ;
	int N;
	
	public enum Action {CHANGE_UPPER_MULT, CREATE_INVARIANT_WITH_QUALITIES, CREATE_INVARIANT}

	@Override
	public void run() {
		
		if(code==Action.CHANGE_UPPER_MULT) ap.changeUpperMult(m,n);
		else if(code==Action.CREATE_INVARIANT_WITH_QUALITIES) ap.createInvariantWithQualities(mediationList,NQ);
		else if(code==Action.CREATE_INVARIANT) ap.createInvariant(mediationList,N);
	}
	
	public void setChangeUpperMult(Mediation m, Integer n){
		code = Action.CHANGE_UPPER_MULT;
		this.n = n;
		this.m=m;
	}
	
	public void setCreateInvariantWithQualities(ArrayList<Mediation> mediationList, int NQ){
		code = Action.CREATE_INVARIANT_WITH_QUALITIES;
		this.mediationList = mediationList;
		this.NQ=NQ;
	}
	
	public void setCreateInvariant(ArrayList<Mediation> mediationList, int N){
		code = Action.CREATE_INVARIANT;
		this.mediationList = mediationList;
		this.N=N;
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		if(code==Action.CHANGE_UPPER_MULT) 
			result = "Modifify the upper cardinality of relator's side in the mediation "+m.getName()+" to "+n; 
					
		else if(code==Action.CREATE_INVARIANT)
			result = "Create an OCL invariant limiting to "+N+" the instances of simoutaneous, current relator "+mediationList.get(0).relator().getName();
					
		else if(code==Action.CREATE_INVARIANT_WITH_QUALITIES)
			result = "Create two Qualities and an OCL invariant limiting to "+N+" the instances of simoutaneous, historical relator "+mediationList.get(0).relator().getName();
		
		return result; 
	}

}
