package br.ufes.inf.nemo.oled.antipattern.wizard.wholeover;

import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public abstract class WholeOverPage extends WizardPage {

	protected WholeOverOccurrence wholeOver;
	
	public WholeOverPage(WholeOverOccurrence ap, String pageName) 
	{
		super(pageName);				
		this.wholeOver = ap;
				
		setTitle(RelSpecAntipattern.getAntipatternInfo().getName());
	
	}
	
	public WholeOverWizard getWholeOverWizard(){
		return (WholeOverWizard)getWizard();
	}

}
