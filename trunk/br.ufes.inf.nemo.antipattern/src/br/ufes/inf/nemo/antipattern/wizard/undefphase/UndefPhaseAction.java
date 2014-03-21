package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class UndefPhaseAction extends AntiPatternAction< UndefPhaseOccurrence>{
	
	public UndefPhaseAction( UndefPhaseOccurrence ap) {
		super(ap);
	}

	public enum Action {
	}
	
	@Override
	public void run() {
	}
	
	@Override
	public String toString(){
		String result = new String();
				
		return result; 
	}
}