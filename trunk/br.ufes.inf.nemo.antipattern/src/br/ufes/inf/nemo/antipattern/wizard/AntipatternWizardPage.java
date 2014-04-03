package br.ufes.inf.nemo.antipattern.wizard;

import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;

public abstract class AntipatternWizardPage<T extends AntipatternOccurrence, W extends AntipatternWizard> extends WizardPage {

	protected T occurrence;	
	ArrayList<Button> enablingNextPageButtons;
	
	/**
	 * Create the wizard.
	 */
	public AntipatternWizardPage(T occurrence) 
	{
		super("AntipatternPage");		
		this.occurrence = occurrence;	
		this.enablingNextPageButtons = new ArrayList<Button>();
	}
	
	@SuppressWarnings("unchecked")
	public W getAntipatternWizard(){
		return (W)getWizard();
	}
	
	public void setAsEnablingNextPageButton(Button b){
		if(b!=null){
			b.addSelectionListener(canGoToNextPageRadioAdapter);
			enablingNextPageButtons.add(b);
			
		}
	}
	
	//enables next page if a radio button is selected
	protected SelectionAdapter canGoToNextPageRadioAdapter = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent event) {
			boolean hasSelection = false;
			
			for (Button	button : enablingNextPageButtons) {
				if(button.getSelection()){
					hasSelection = true;
					break;
				}
			}	
			
			if(isPageComplete()!=hasSelection)
				setPageComplete(hasSelection);
		}
	}; 

}
