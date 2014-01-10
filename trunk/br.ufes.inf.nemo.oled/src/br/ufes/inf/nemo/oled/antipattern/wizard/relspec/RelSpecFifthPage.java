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

public class RelSpecFifthPage extends RelSpecPage {

	public Button btnGeneral;
	public Button btnSpecific;

	/**
	 * Create the wizard.
	 */
	public RelSpecFifthPage(RelSpecOccurrence relSpec) 
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
		
		styledText.setText("Select the association you would like to delete:");

		styledText.setEditable(false);
		styledText.setBounds(10, 10, 554, 16);
		
		btnGeneral = new Button(container, SWT.RADIO);
		btnGeneral.setText(relSpec.getParser().getStringRepresentation(relSpec.getSpecific()));
		btnGeneral.setBounds(10, 32, 449, 16);
		btnGeneral.setSelection(true);
		
		btnSpecific = new Button(container, SWT.RADIO);
		btnSpecific.setText(relSpec.getParser().getStringRepresentation(relSpec.getGeneral()));
		btnSpecific.setBounds(10, 54, 449, 16);
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		if(btnGeneral.getSelection()) {
			//ACTION
			//TODO: REMOVE GENERAL
		}
		if(btnSpecific.getSelection()) {
			//ACTION
			//TODO: REMOVE SPECIFIC
		}
			
		return getRelSpecWizard().getFinishing();
	}
}
