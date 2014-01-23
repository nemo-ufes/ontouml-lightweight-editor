package br.ufes.inf.nemo.antipattern.wizard.relspec;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelSpecFourthPage extends RelSpecPage {

	//GUI
	public Button btnSpecificSource;
	public Button btnSpecificBoth;
	public Button btnSpecificTarget;
	public Button btnGeneralSource;
	public Button btnGeneralBoth;
	public Button btnGeneralTarget;
	
	/**
	 * Create the wizard.
	 */
	public RelSpecFourthPage(RelSpecOccurrence relSpec) 
	{
		super(relSpec);	
			
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
		
		styledText.setText(	"For which ends would like to create subtypes?");

		styledText.setEditable(false);
		styledText.setBounds(10, 10, 554, 16);
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        if (isPageComplete()==false) setPageComplete(true);
	      }
	    };
		    
	    setPageComplete(false);
	    
		btnSpecificSource = new Button(container, SWT.RADIO);
		btnSpecificSource.setBounds(10, 104, 449, 16);
		btnSpecificSource.setText(relSpec.getParser().getStringRepresentation(relSpec.getSpecificSource())+" (source end of "+relSpec.getParser().getStringRepresentation(relSpec.getSpecific())+")");
		btnSpecificSource.setSelection(true);
		btnSpecificSource.addSelectionListener(listener);
		
		btnSpecificTarget = new Button(container, SWT.RADIO);
		btnSpecificTarget.setText(relSpec.getParser().getStringRepresentation(relSpec.getSpecificTarget())+" (target end of "+relSpec.getParser().getStringRepresentation(relSpec.getSpecific())+")");
		btnSpecificTarget.setBounds(10, 126, 449, 16);
		btnSpecificTarget.addSelectionListener(listener);
		
		btnSpecificBoth = new Button(container, SWT.RADIO);
		btnSpecificBoth.setText(relSpec.getParser().getStringRepresentation(relSpec.getSpecificSource())+" and "+relSpec.getParser().getStringRepresentation(relSpec.getSpecificTarget())+" (both ends of "+relSpec.getParser().getStringRepresentation(relSpec.getSpecific())+")");
		btnSpecificBoth.setBounds(10, 148, 449, 16);
		btnSpecificBoth.addSelectionListener(listener);
		
		btnGeneralSource = new Button(container, SWT.RADIO);
		btnGeneralSource.setBounds(10, 38, 449, 16);
		btnGeneralSource.setText(relSpec.getParser().getStringRepresentation(relSpec.getGeneralSource())+" (source end of "+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())+")");
		btnGeneralSource.addSelectionListener(listener);
		
		btnGeneralTarget = new Button(container, SWT.RADIO);
		btnGeneralTarget.setText(relSpec.getParser().getStringRepresentation(relSpec.getGeneralTarget())+" (target end of "+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())+")");
		btnGeneralTarget.setBounds(10, 60, 449, 16);
		btnGeneralTarget.addSelectionListener(listener);
		
		btnGeneralBoth = new Button(container, SWT.RADIO);
		btnGeneralBoth.setText(relSpec.getParser().getStringRepresentation(relSpec.getGeneralSource())+" and "+relSpec.getParser().getStringRepresentation(relSpec.getGeneralTarget())+" (both ends of "+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())+")");
		btnGeneralBoth.setBounds(10, 82, 449, 16);
		btnGeneralBoth.addSelectionListener(listener);		
	}
	
	@Override
	public IWizardPage getNextPage() {

		ClassStereotype source = ClassStereotype.ROLE;
		ClassStereotype target = ClassStereotype.ROLE;
		
		RelSpecAction newAction = new RelSpecAction(relSpec);
		if(btnGeneralSource.getSelection()) {
			// Action =====================	
			newAction.setSpec_General_Source_Redefine(source);
			getRelSpecWizard().addAction(0,newAction);
		}
		if(btnGeneralTarget.getSelection()) {
			// Action =====================
			newAction.setSpec_General_Target_Redefine(target);
			getRelSpecWizard().addAction(0,newAction);
		}
		if(btnGeneralBoth.getSelection()) {
			// Action =====================
			newAction.setSpec_General_Both_Redefine(source,target);
			getRelSpecWizard().addAction(0,newAction);
		}
		if(btnSpecificSource.getSelection()) {			
			// Action =====================
			newAction.setSpec_Specific_Source_Redefine(source);
			getRelSpecWizard().addAction(0,newAction);
		}
		if(btnSpecificTarget.getSelection()) {
			// Action =====================
			newAction.setSpec_Specific_Target_Redefine(target);
			getRelSpecWizard().addAction(0,newAction);
		}
		if(btnSpecificBoth.getSelection()) {
			// Action =====================
			newAction.setSpec_Specific_Both_Redefine(source,target);
			getRelSpecWizard().addAction(0,newAction);
		}
				
		return getRelSpecWizard().getFinishing();
		
	}
}
