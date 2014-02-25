package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class HomoFuncAction extends AntiPatternAction<HomoFuncOccurrence>{

	public String partStereotype;
	public String partName;
	public String componentOfName;
	public boolean isShareable;
	public boolean isEssential;
	public boolean isImmutablePart;
	public boolean isImmutableWhole;
	public boolean isInseparable;
	
	public HomoFuncAction(HomoFuncOccurrence ap) 
	{
		super(ap);
	}

	public enum Action { CREATE_NEW_PART}
	
	@Override
	public void run(){
		
	}
	
	public void setCreateNewPart(String partStereotype, String partName, String componentOfName, boolean isShareable, boolean isEssential, boolean isImmutablePart, boolean isImmutableWhole, boolean isInseparable)
	{
		code = Action.CREATE_NEW_PART;
		this.partStereotype=partStereotype;
		this.partName=partName;
		this.componentOfName=componentOfName;
		this.isEssential=isEssential;
		this.isInseparable=isInseparable;
		this.isImmutablePart=isImmutablePart;
		this.isImmutableWhole=isImmutableWhole;
		this.isShareable=isShareable;				
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		return result;
	}
}