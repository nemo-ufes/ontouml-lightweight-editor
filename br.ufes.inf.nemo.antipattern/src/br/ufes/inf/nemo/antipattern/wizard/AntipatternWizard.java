package br.ufes.inf.nemo.antipattern.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

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
	protected HashMap<Integer, ArrayList<AntiPatternAction<?>>> actions = new HashMap<Integer,ArrayList<AntiPatternAction<?>>>();
	
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
	public Collection<AntiPatternAction> getAllActions() {
		
		ArrayList<AntiPatternAction> result = new ArrayList<AntiPatternAction>();
		
		for (Integer i : actions.keySet()) {
			result.addAll(actions.get(i));
		}
		
		return result;
	}

	public ArrayList<AntiPatternAction<?>> getAction(int pos){
		return actions.get(pos);
	}
	
	public void removeAllActions()
	{
		actions.clear();
	}
	
	public void removeAllActions(int pos){
		actions.remove(pos);
	}
	
	public void removeAllActions(int pos, Enum<?> e){
		
		if(actions.get(pos)!=null){
			Iterator<AntiPatternAction<?>> iterator = actions.get(pos).iterator();
			while(iterator.hasNext()){
				AntiPatternAction<?> action = iterator.next();
				if(action.getCode().equals(e))
					iterator.remove();
			}
		}
	}
	
	public void replaceAction(int pos, AntiPatternAction<?> action)
	{	
		ArrayList<AntiPatternAction<?>> indexedActions = new ArrayList<AntiPatternAction<?>>();
		indexedActions.add(action);
		actions.put(pos, indexedActions);	
	}
	
	public void addAction(int pos, AntiPatternAction<?> action){
		
		if(actions.get(pos)!=null)
			actions.get(pos).add(action);
		else {
			ArrayList<AntiPatternAction<?>> indexedActions = new ArrayList<AntiPatternAction<?>>();
			indexedActions.add(action);
			actions.put(pos, indexedActions);
		}
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
		finishing.addActions(getAllActions());
		return finishing;
	}
	
	public RefactoringPage getOptions() {
		return options;
	}
	
	public void runAllActions(){
		for (AntiPatternAction<?> action : getAllActions()) {			
			action.run();
		}
	}

}
