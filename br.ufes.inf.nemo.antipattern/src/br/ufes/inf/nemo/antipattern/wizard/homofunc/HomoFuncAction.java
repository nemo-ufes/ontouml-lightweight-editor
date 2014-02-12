package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class HomoFuncAction extends AntiPatternAction<HomoFuncOccurrence>{

	public HomoFuncAction(HomoFuncOccurrence ap) 
	{
		super(ap);
	}

	public enum Action { }
	
	@Override
	public void run() {
		
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		return result;
	}
}