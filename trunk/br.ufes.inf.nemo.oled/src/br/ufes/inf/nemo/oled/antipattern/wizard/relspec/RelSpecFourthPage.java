package br.ufes.inf.nemo.oled.antipattern.wizard.relspec;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.oled.antipattern.wizard.WizardAction;
import br.ufes.inf.nemo.oled.antipattern.wizard.relspec.RelSpecWizard.RelSpecAction;

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
		
		btnSpecificSource = new Button(container, SWT.RADIO);
		btnSpecificSource.setBounds(10, 104, 449, 16);
		btnSpecificSource.setText(relSpec.getParser().getStringRepresentation(relSpec.getSpecificSource())+" (source end of "+relSpec.getParser().getStringRepresentation(relSpec.getSpecific())+")");
		btnSpecificSource.setSelection(true);
		
		btnSpecificTarget = new Button(container, SWT.RADIO);
		btnSpecificTarget.setText(relSpec.getParser().getStringRepresentation(relSpec.getSpecificTarget())+" (target end of "+relSpec.getParser().getStringRepresentation(relSpec.getSpecific())+")");
		btnSpecificTarget.setBounds(10, 126, 449, 16);
		
		btnSpecificBoth = new Button(container, SWT.RADIO);
		btnSpecificBoth.setText(relSpec.getParser().getStringRepresentation(relSpec.getSpecificSource())+" and "+relSpec.getParser().getStringRepresentation(relSpec.getSpecificTarget())+" (both ends of "+relSpec.getParser().getStringRepresentation(relSpec.getSpecific())+")");
		btnSpecificBoth.setBounds(10, 148, 449, 16);
		
		btnGeneralSource = new Button(container, SWT.RADIO);
		btnGeneralSource.setBounds(10, 38, 449, 16);
		btnGeneralSource.setText(relSpec.getParser().getStringRepresentation(relSpec.getGeneralSource())+" (source end of "+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())+")");
		
		btnGeneralTarget = new Button(container, SWT.RADIO);
		btnGeneralTarget.setText(relSpec.getParser().getStringRepresentation(relSpec.getGeneralTarget())+" (target end of "+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())+")");
		btnGeneralTarget.setBounds(10, 60, 449, 16);
		
		btnGeneralBoth = new Button(container, SWT.RADIO);
		btnGeneralBoth.setText(relSpec.getParser().getStringRepresentation(relSpec.getGeneralSource())+" and "+relSpec.getParser().getStringRepresentation(relSpec.getGeneralTarget())+" (both ends of "+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())+")");
		btnGeneralBoth.setBounds(10, 82, 449, 16);
		
		
	}
	
	@Override
	public IWizardPage getNextPage() {

		ClassStereotype sourceStereotype = ClassStereotype.ROLE;
		ClassStereotype targetStereotype = ClassStereotype.ROLE;
		
		if(btnGeneralSource.getSelection()) {
			// Action =====================			
			WizardAction<RelSpecAction> newAction = new WizardAction<RelSpecAction>(RelSpecAction.SPEC_GENERAL_SOURCE_REDEFINE);
			newAction.addParameter("stereotype", sourceStereotype);
			getRelSpecWizard().getActions().add(0,newAction);			
		}
		if(btnGeneralTarget.getSelection()) {
			// Action =====================
			WizardAction<RelSpecAction> newAction = new WizardAction<RelSpecAction>(RelSpecAction.SPEC_GENERAL_TARGET_REDEFINE);
			newAction.addParameter("stereotype", targetStereotype);
			getRelSpecWizard().getActions().add(0,newAction);
		}
		if(btnGeneralBoth.getSelection()) {
			// Action =====================
			WizardAction<RelSpecAction> newAction = new WizardAction<RelSpecAction>(RelSpecAction.SPEC_GENERAL_BOTH_REDEFINE);
			newAction.addParameter("sourceStereotype", sourceStereotype);
			newAction.addParameter("targetStereotype", targetStereotype);
			getRelSpecWizard().getActions().add(0,newAction);
		}
		if(btnSpecificSource.getSelection()) {
			// Action =====================
			WizardAction<RelSpecAction> newAction = new WizardAction<RelSpecAction>(RelSpecAction.SPEC_SPECIFIC_SOURCE_REDEFINE);
			newAction.addParameter("stereotype", sourceStereotype);
			getRelSpecWizard().getActions().add(0,newAction);
		}
		if(btnSpecificTarget.getSelection()) {
			// Action =====================
			WizardAction<RelSpecAction> newAction = new WizardAction<RelSpecAction>(RelSpecAction.SPEC_SPECIFIC_TARGET_REDEFINE);
			newAction.addParameter("stereotype", sourceStereotype);
			getRelSpecWizard().getActions().add(0,newAction);
		}
		if(btnSpecificBoth.getSelection()) {
			// Action =====================
			WizardAction<RelSpecAction> newAction = new WizardAction<RelSpecAction>(RelSpecAction.SPEC_SPECIFIC_BOTH_REDEFINE);
			newAction.addParameter("sourceStereotype", sourceStereotype);
			newAction.addParameter("targetStereotype", targetStereotype);
			getRelSpecWizard().getActions().add(0,newAction);			
		}
		
		return getRelSpecWizard().getFinishing();
		
	}
}
