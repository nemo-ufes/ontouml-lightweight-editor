package br.ufes.inf.nemo.antipattern.wizard.mixrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.mixrig.MixRigAntipattern;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class MixRigRefactoringPage extends RefactoringPage {
	

	public MixRigOccurrence mixRig;
	private Button btnCreateOrSelect;
	private Button btnChangeStereotype;
	private AddSelectTypeComposite addSelectComposite;
	private Label lblPleaseSelectExisting;
	
	private SelectionAdapter listener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnChangeStereotype.getSelection()){
				setPageComplete(true);
				addSelectComposite.setEnabled(false);
			}
			if(btnCreateOrSelect.getSelection()){
				setPageComplete(true);
				addSelectComposite.setEnabled(true);
			}
		}
	};
	
	/**
	 * Create the wizard.
	 */
	public MixRigRefactoringPage(MixRigOccurrence mixRig) 
	{
		super();	
		this.mixRig = mixRig;
		
		setTitle(MixRigAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public MixRigWizard getMixRigWizard(){
		return (MixRigWizard)getWizard();
	}
	
		
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		setPageComplete(false);
		
		String newStereotype;
		if(mixRig.rigidSubtypes())
			newStereotype = "Category";
		else
			newStereotype = "RoleMixin";
		
		Label lblPleaseChooseThe = new Label(container, SWT.NONE);
		lblPleaseChooseThe.setBounds(10, 10, 554, 15);
		lblPleaseChooseThe.setText("Please choose the appropriate a refactoring option: ");
		
		btnChangeStereotype = new Button(container, SWT.RADIO);
		btnChangeStereotype.setBounds(10, 40, 554, 16);
		btnChangeStereotype.setText("Change "+getMixRigWizard().mixinName+" stereotype to «"+newStereotype+"»");
		btnChangeStereotype.addSelectionListener(listener);
		
		btnCreateOrSelect = new Button(container, SWT.RADIO);
		btnCreateOrSelect.setBounds(10, 62, 554, 16);
		btnCreateOrSelect.setText("Create/Select "+getMixRigWizard().oppositeRigidity+" subtypes for "+getMixRigWizard().mixinName+"");
		btnCreateOrSelect.addSelectionListener(listener);
		
		try {
			addSelectComposite = new AddSelectTypeComposite(container, SWT.BORDER, mixRig.getParser(), getMixRigWizard().allowedStereotypes());
			addSelectComposite.setBounds(10, 111, 554, 255);
			addSelectComposite.setEnabled(false);
			
			lblPleaseSelectExisting = new Label(container, SWT.NONE);
			lblPleaseSelectExisting.setText("Please select existing types or create new ones using the table below:");
			lblPleaseSelectExisting.setBounds(10, 90, 509, 15);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		getMixRigWizard().removeAllActions();
		
		if(btnChangeStereotype.getSelection()){
			MixRigAction action = new MixRigAction(mixRig);
			action.setChangeMixinStereotype();
			getMixRigWizard().addAction(0, action);
		}
		
		if(btnCreateOrSelect.getSelection()){
			MixRigAction action = new MixRigAction(mixRig);
			action.setAddSubtypes(addSelectComposite.getSelectedClassifier(), addSelectComposite.getNewClassifiers());
			getMixRigWizard().addAction(0, action);
		}
		
		return getMixRigWizard().getFinishing();
	}
}
