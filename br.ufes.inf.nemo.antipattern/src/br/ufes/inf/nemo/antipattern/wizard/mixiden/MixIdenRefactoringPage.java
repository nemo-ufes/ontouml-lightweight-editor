package br.ufes.inf.nemo.antipattern.wizard.mixiden;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class MixIdenRefactoringPage extends RefactoringPage {
	

	public MixIdenOccurrence mixIden;
	
	private Button btnAddSelect;
	private Button btnChangeStereotype;
	private Button btnChangeIdentity;
	
	private ExpandBar bar;
	private ExpandItem itemAddSelect;
	private ExpandItem itemIdentitySubtype;
	
	private AddSelectSortalComposite addSelectComposite;
	private ChangeIdentityProviderComposite changeIdentityComposite;
	
	private SelectionAdapter btnChangeStereotypeListener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnChangeStereotype.getSelection()){
				if(!isPageComplete()) setPageComplete(true);

				itemAddSelect.setExpanded(false);
				addSelectComposite.setAllEnabled(false);
				itemIdentitySubtype.setExpanded(false);
				changeIdentityComposite.setAllEnabled(false);
			}
		}
	};
	
	private SelectionAdapter btnCreateOrSelectListener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnAddSelect.getSelection()){
				if(!isPageComplete()) setPageComplete(true);
				
				itemAddSelect.setExpanded(true);
				addSelectComposite.setAllEnabled(true);
				itemIdentitySubtype.setExpanded(false);
				changeIdentityComposite.setAllEnabled(false);
			}
		}
	};
	
	private SelectionAdapter btnChangeIdentityListener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnChangeIdentity.getSelection()){
						
				itemAddSelect.setExpanded(false);
				addSelectComposite.setAllEnabled(false);
				itemIdentitySubtype.setExpanded(true);
				changeIdentityComposite.setAllEnabled(true);
				
				if(changeIdentityComposite.getSubtypesToFix().size()>0)
					setPageComplete(true);
				else
					setPageComplete(false);
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
		btnChangeStereotype.addSelectionListener(btnChangeStereotypeListener);
		
		btnChangeIdentity = new Button(container, SWT.RADIO);
		btnChangeIdentity.setText("Change subtypes's identity providers");
		btnChangeIdentity.setBounds(10, 62, 554, 16);
		btnChangeIdentity.addSelectionListener(btnChangeIdentityListener);
		
		btnAddSelect = new Button(container, SWT.RADIO);
		btnAddSelect.setBounds(10, 84, 554, 16);
		btnAddSelect.setText("Create/Select subtypes with different identity principles");
		btnAddSelect.addSelectionListener(btnCreateOrSelectListener);
		
		bar = new ExpandBar (container, SWT.V_SCROLL);
		bar.setSpacing(0);
		bar.setBounds(10, 119, 560, 312);
		bar.setEnabled(true);
		
		try {
			ArrayList<Classifier> forbbidenTypes = new ArrayList<Classifier>(mixIden.getIdentityProvider().allChildren());
			forbbidenTypes.add(mixIden.getIdentityProvider());
			
			addSelectComposite = new AddSelectSortalComposite(bar, SWT.BORDER, mixIden.getParser(), 
					mixIden.allowedSubtypeStereotypes(), mixIden.identityProviderStereotypes(), forbbidenTypes);
			addSelectComposite.setBounds(10, 111, 554, 255);
			addSelectComposite.setAllEnabled(false);
			
			changeIdentityComposite = new ChangeIdentityProviderComposite(bar, SWT.NONE, mixIden, btnChangeIdentityListener);
			changeIdentityComposite.setBounds(10, 111, 554, 255);
			changeIdentityComposite.setAllEnabled(false);
			
		} catch (Exception e) { e.printStackTrace(); }
		
		itemIdentitySubtype = new ExpandItem (bar, SWT.NONE, 0);
		itemIdentitySubtype.setExpanded(false);
		itemIdentitySubtype.setText("Change Subtypes' Identity");
		itemIdentitySubtype.setHeight(changeIdentityComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		itemIdentitySubtype.setControl(changeIdentityComposite);		
		
		itemAddSelect = new ExpandItem (bar, SWT.NONE, 1);
		itemAddSelect.setExpanded(false);
		itemAddSelect.setText("Add or Select Subtypes");
		itemAddSelect.setHeight(addSelectComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		itemAddSelect.setControl(addSelectComposite);		
		
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		getMixIdenWizard().removeAllActions();
		
		if(btnChangeStereotype.getSelection()){
			MixIdenAction action = new MixIdenAction(mixIden);
			action.setChangeMixinStereotype();
			getMixIdenWizard().addAction(0, action);
		}
		
		if(btnAddSelect.getSelection()){
			MixIdenAction action = new MixIdenAction(mixIden);
			action.setAddSubtypes(addSelectComposite.getNewSortalSubtypes());
			getMixIdenWizard().addAction(0, action);
		}
		
		return getMixIdenWizard().getFinishing();
	}
}
