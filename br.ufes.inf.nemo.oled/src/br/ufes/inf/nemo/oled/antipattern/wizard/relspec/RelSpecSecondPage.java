package br.ufes.inf.nemo.oled.antipattern.wizard.relspec;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelSpecSecondPage extends RelSpecPage {

	//GUI
	public Button btnYes;
	public Button btnNo;
	
	/**
	 * Create the wizard.
	 */
	public RelSpecSecondPage(RelSpecOccurrence relSpec) 
	{
		super(relSpec);
		setDescription("Associations: "+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())+" and "+relSpec.getParser().getStringRepresentation(relSpec.getSpecific()));
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		String question = 	"By your previous answer, we conclude that there is a missing restriction in your model. "+relSpec.getSpecific().getName()+" either subsets or redefines "+relSpec.getGeneral().getName()+". "+
							"To figure out which restriction should be added, answer the following question: "+
							"\n\nMust an instance 'x' of "+relSpec.getSpecificSource().getName()+" (or an instance 'y' of "+relSpec.getSpecificTarget().getName()+") be connected to exactly the same instances by "+relSpec.getGeneral().getName()+" and "+relSpec.getSpecific().getName()+"?"; 
		  
		styledText.setText(question);

		styledText.setEditable(false);
		styledText.setBounds(10, 10, 554, 97);
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 122, 163, 16);
		btnYes.setText("Yes (Redefinition)");
		btnYes.setSelection(true);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No (Subsetting)");
		btnNo.setBounds(10, 144, 163, 16);
		
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		if(btnNo.getSelection()) {
			// Action =====================
			//TODO: <Specific> subsets <General>
			return getRelSpecWizard().getFinishing(); 
		}
			
		else if(btnYes.getSelection()){			
			
			//CONDITION
			if(relSpec.isVariation4() || relSpec.isVariation5())
				return getRelSpecWizard().getThirdPage();
			else{
				// Action =====================
				//TODO: <Specific> redefines <General>
				return getRelSpecWizard().getFinishing();
			}
		}
		return super.getNextPage();
	}
}
