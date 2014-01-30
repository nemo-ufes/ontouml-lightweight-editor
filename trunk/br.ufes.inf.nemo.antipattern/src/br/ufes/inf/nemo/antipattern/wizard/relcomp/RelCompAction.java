package br.ufes.inf.nemo.antipattern.wizard.relcomp;

import br.ufes.inf.nemo.antipattern.relcomp.RelCompOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class RelCompAction extends AntiPatternAction <RelCompOccurrence> {

	private int nLimit;
	private int mLimit;

	public RelCompAction(RelCompOccurrence ap) 
	{
		super(ap);	
	}
	
	public enum Action {INCLUDES_ALL_SOURCE, INCLUDES_ALL_TARGET, 
						EXCLUDES_ALL_SOURCE, EXCLUDES_ALL_TARGET, 
						AT_LEAST_SOURCE, AT_LEAST_TARGET} 
	
	@Override
	public void run()
	{
		if(code==Action.INCLUDES_ALL_SOURCE)
			ap.generateOCLIncludesAll(ap.getA2().getMemberEnd().get(0));
		if(code==Action.INCLUDES_ALL_TARGET)
			ap.generateOCLIncludesAll(ap.getA2().getMemberEnd().get(1));
		
		if(code==Action.EXCLUDES_ALL_SOURCE)
			ap.generateOCLExcludesAll(ap.getA2().getMemberEnd().get(0));
		if(code==Action.EXCLUDES_ALL_TARGET)
			ap.generateOCLExcludesAll(ap.getA2().getMemberEnd().get(1));
		
		if(code==Action.AT_LEAST_SOURCE)
			ap.generateOCLAtLeast(ap.getA2().getMemberEnd().get(0), nLimit);
		if(code==Action.AT_LEAST_TARGET)
			ap.generateOCLAtLeast(ap.getA2().getMemberEnd().get(1), mLimit);
	}
	
	public void setIncludesAllSource(){
		code=Action.INCLUDES_ALL_SOURCE;
	}
	
	public void setIncludesAllTarget(){
		code=Action.INCLUDES_ALL_TARGET;
	}
	
	public void setExcludesAllSource(){
		code=Action.EXCLUDES_ALL_SOURCE;
	}
	
	public void setExcludesAllTarget(){
		code=Action.EXCLUDES_ALL_TARGET;
	}
	
	public void setAtLeastSource(int nLimit){
		code=Action.AT_LEAST_SOURCE;
		this.nLimit = nLimit;
		this.mLimit = 0;
	}
	
	public void setAtLeastTarget(int mLimit){
		code=Action.AT_LEAST_TARGET;
		this.nLimit = 0;
		this.mLimit = mLimit;
	}
	
	
	@Override
	public String toString(){
		
		if(code==Action.INCLUDES_ALL_SOURCE)
			return "Create OCL constraint: Relation Dependency ("+ap.getA2Source().getName()+" - Includes All)";
		if(code==Action.INCLUDES_ALL_TARGET)
			return "Create OCL constraint: Relation Dependency ("+ap.getA2Target().getName()+" - Includes All)";	
		
		if(code==Action.EXCLUDES_ALL_SOURCE)
			return "Create OCL constraint: Relation Dependency ("+ap.getA2Source().getName()+" - Excludes All)";
		if(code==Action.EXCLUDES_ALL_TARGET)
			return "Create OCL constraint: Relation Dependency ("+ap.getA2Target().getName()+" - Excludes All)";	
		
		if(code==Action.AT_LEAST_SOURCE)
			return "Create OCL constraint: Relation Dependency ("+ap.getA2Source().getName()+" - At least <"+nLimit+">)";
		if(code==Action.AT_LEAST_TARGET)
			return "Create OCL constraint: Relation Dependency ("+ap.getA2Target().getName()+" - At least <"+mLimit+">)";	
		
		return "Undefined Action";
	}
}
