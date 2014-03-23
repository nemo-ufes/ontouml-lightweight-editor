package br.ufes.inf.nemo.antipattern.wizard.mixiden;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.mixiden.MixIdenAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class MixIdenRefactoringPage extends RefactoringPage {
	

	public MixIdenOccurrence mixIden;
	private Button btnCreateOrSelect;
	private Button btnChangeStereotype;
	private AddSelectSortalComposite addSelectComposite;
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
	public MixIdenRefactoringPage(MixIdenOccurrence mixIdenOccurrence) 
	{
		super();	
		this.mixIden = mixIdenOccurrence;
		
		setTitle(MixIdenAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public MixIdenWizard getMixIdenWizard(){
		return (MixIdenWizard)getWizard();
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
		if(mixIden.isHasAntiRigid() && !mixIden.isHasRigid())
			newStereotype = "Role";
		else
			newStereotype = "SubKind";
		
		Label lblPleaseChooseThe = new Label(container, SWT.NONE);
		lblPleaseChooseThe.setBounds(10, 10, 554, 15);
		lblPleaseChooseThe.setText("Please choose the appropriate a refactoring option: ");
		
		btnChangeStereotype = new Button(container, SWT.RADIO);
		btnChangeStereotype.setBounds(10, 40, 554, 16);
		btnChangeStereotype.setText("Change "+getMixIdenWizard().mixinName+" stereotype to «"+newStereotype+"»");
		btnChangeStereotype.addSelectionListener(listener);
		
		btnCreateOrSelect = new Button(container, SWT.RADIO);
		btnCreateOrSelect.setBounds(10, 62, 554, 16);
		btnCreateOrSelect.setText("Create/Select subtypes with different identity principles");
		btnCreateOrSelect.addSelectionListener(listener);
		
		try {
			addSelectComposite = new AddSelectSortalComposite(container, SWT.BORDER, mixIden.getParser(), 
					getMixIdenWizard().allowedStereotypes(),getMixIdenWizard().identityProviderStereotypes());
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
		
		getMixIdenWizard().removeAllActions();
		
		if(btnChangeStereotype.getSelection()){
			MixIdenAction action = new MixIdenAction(mixIden);
			action.setChangeMixinStereotype();
			getMixIdenWizard().addAction(0, action);
		}
		
		if(btnCreateOrSelect.getSelection()){
			MixIdenAction action = new MixIdenAction(mixIden);
			action.setAddSubtypes(addSelectComposite.getNewSortalSubtypes());
			getMixIdenWizard().addAction(0, action);
		}
		
		return getMixIdenWizard().getFinishing();
	}
}
