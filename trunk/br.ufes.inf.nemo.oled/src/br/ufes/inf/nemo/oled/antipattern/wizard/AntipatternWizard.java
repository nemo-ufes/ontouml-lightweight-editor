package br.ufes.inf.nemo.oled.antipattern.wizard;

import java.util.Collection;
import java.util.HashMap;

import org.eclipse.jface.wizard.Wizard;

import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public abstract class AntipatternWizard extends Wizard {

	protected boolean canFinish = true;
	
	protected AntipatternOccurrence ap;
	@SuppressWarnings("rawtypes")
	protected HashMap<Integer, AntiPatternAction> actions = new HashMap<Integer,AntiPatternAction>();
	
	//GUI
	protected PresentationPage presentation;
	protected FinishingPage finishing;
	protected RefactoringPage options;	
	
	public AntipatternWizard(AntipatternOccurrence ap, String windowTitle) {
		this.ap = ap;
	    canFinish=false;
	    setNeedsProgressMonitor(true); 
		setWindowTitle(windowTitle);
	}
	
	@SuppressWarnings("rawtypes")
	public Collection<AntiPatternAction> getActions() {
		return actions.values();
	}

	@SuppressWarnings("rawtypes")
	public AntiPatternAction getAction(int pos){
		int i=0;
		for(AntiPatternAction action: actions.values()){
			if (i==pos) return action;
			i++;
		}
		return null;
	}
	
	public void clearActions()
	{
		actions.clear();
	}
	
	@SuppressWarnings("rawtypes")
	public void addAction(int pos, AntiPatternAction action)
	{		
		actions.put(pos, action);		
	}
		
    @Override
    public boolean canFinish() {	 
    	return canFinish;	  
    };
    
	public AntipatternOccurrence getAp() {
		return ap;
	}

	public PresentationPage getPresentation() {
		return presentation;
	}

	public FinishingPage getFinishing() {
		canFinish=true;	
		finishing.addActions(getActions());
		return finishing;
	}
	
	public RefactoringPage getOptions() {
		return options;
	}

}
