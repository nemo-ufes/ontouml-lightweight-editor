package br.ufes.inf.nemo.antipattern.wizard.mixrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.mixrig.MixRigAntipattern;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class MixRigRefactoringPage extends RefactoringPage {
	

	public MixRigOccurrence mixRig;
	private Button btnAddSelect;
	private Button btnChangeStereotype;
	
	private AddSelectTypeComposite addSelectComposite;
	private ChangeSubtypeRigidityComposite changeSubtypeComposite;
	
	private ExpandBar bar;
	private ExpandItem itemAddSelect;
	private ExpandItem itemChangeSubtype;
	

	
	
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
		lblPleaseChooseThe.setText("Please choose the appropriate a refactoring option: ");
		
		btnChangeStereotype = new Button(container, SWT.RADIO);
		btnChangeStereotype.setText("Change "+getMixRigWizard().mixinName+" stereotype to «"+newStereotype+"»");
		btnChangeStereotype.addSelectionListener(btnListener);
		
		btnChangeSubtype = new Button(container, SWT.RADIO);
		btnChangeSubtype.setText("Change subtypes stereotypes");
		btnChangeSubtype.addSelectionListener(btnListener);
		
		btnAddSelect = new Button(container, SWT.RADIO);
		btnAddSelect.setText("Create/Select "+getMixRigWizard().oppositeRigidity+" subtypes for "+getMixRigWizard().mixinName+"");
		btnAddSelect.addSelectionListener(btnListener);
		
		bar = new ExpandBar (container, SWT.V_SCROLL);
		bar.setSpacing(0);
		bar.setEnabled(true);
		
		try {
			addSelectComposite = new AddSelectTypeComposite(bar, SWT.BORDER, mixRig.getParser(), getMixRigWizard().allowedStereotypes(),addRemoveListener);
			addSelectComposite.setBounds(10, 111, 569, 255);
			addSelectComposite.setEnabled(false);
			changeSubtypeComposite = new ChangeSubtypeRigidityComposite(bar, SWT.BORDER, mixRig, getMixRigWizard().allowedStereotypes(), changeSubtypeListener);
			changeSubtypeComposite.setBounds(10, 103, 569, 168);
			changeSubtypeComposite.setEnabledToAllContents(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		itemChangeSubtype = new ExpandItem (bar, SWT.NONE, 0);
		itemChangeSubtype.setExpanded(false);
		itemChangeSubtype.setText("Change Subtypes' Stereotypes");
		itemChangeSubtype.setHeight(changeSubtypeComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		itemChangeSubtype.setControl(changeSubtypeComposite);		
		
		itemAddSelect = new ExpandItem (bar, SWT.NONE, 1);
		itemAddSelect.setExpanded(false);
		itemAddSelect.setText("Add or Select Subtypes");
		itemAddSelect.setHeight(addSelectComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		itemAddSelect.setControl(addSelectComposite);		
		
		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(label, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
								.add(bar, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
								.add(btnAddSelect, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
								.add(btnChangeSubtype, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
							.add(10))
						.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
							.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
								.add(GroupLayout.LEADING, lblPleaseChooseThe, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
								.add(btnChangeStereotype, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblPleaseChooseThe)
					.add(6)
					.add(btnChangeStereotype)
					.add(6)
					.add(btnChangeSubtype)
					.add(6)
					.add(btnAddSelect)
					.add(15)
					.add(label)
					.add(12)
					.add(bar, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
					.add(10))
		);
		container.setLayout(gl_container);

	}
	
	private SelectionAdapter btnListener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnChangeStereotype.getSelection()){
				setPageComplete(true);
				
				addSelectComposite.setEnabled(false);
				itemAddSelect.setExpanded(false);
				
				changeSubtypeComposite.setEnabledToAllContents(false);
				itemChangeSubtype.setExpanded(false);
			}
			
			if(btnAddSelect.getSelection()){
				addSelectComposite.setEnabled(true);
				itemAddSelect.setExpanded(true);
				
				changeSubtypeComposite.setEnabledToAllContents(false);
				itemChangeSubtype.setExpanded(false);
				
				if(addSelectComposite.getSelectedClassifier().size()+addSelectComposite.getNewClassifiers().size()>0)
					setPageComplete(true);
				else
					setPageComplete(false);
			}
			
			if(btnChangeSubtype.getSelection()){
				addSelectComposite.setEnabled(false);
				itemAddSelect.setExpanded(false);
				
				changeSubtypeComposite.setEnabledToAllContents(true);
				itemChangeSubtype.setExpanded(true);
				
				if(changeSubtypeComposite.getModifiedSubtypes().size()>0)
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		}
	};
	
	private SelectionAdapter addRemoveListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent event) {
			if(btnAddSelect.getSelection()){
				addSelectComposite.setEnabled(true);
				if(addSelectComposite.getSelectedClassifier().size()+addSelectComposite.getNewClassifiers().size()>0)
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		}
	};
	
	private SelectionListener changeSubtypeListener = new SelectionAdapter(){
		@Override
		public void widgetSelected(SelectionEvent event) {
        	if(btnChangeSubtype.getSelection()){
        		if(changeSubtypeComposite.getModifiedSubtypes().size()>0)
        			setPageComplete(true);
    			else
    				setPageComplete(false);			
        	}	
		}
	};
	private Button btnChangeSubtype;
	
	@Override
	public IWizardPage getNextPage() {
		
		getMixRigWizard().removeAllActions();
		MixRigAction action = new MixRigAction(mixRig);
		
		if(btnChangeStereotype.getSelection()){
			action.setChangeMixinStereotype();
		}
		else if(btnChangeSubtype.getSelection()){
			action.setChangeSubtypesStereotype(changeSubtypeComposite.getModifiedSubtypes());
		}
	
		else if(btnAddSelect.getSelection()){
			action.setAddSubtypes(addSelectComposite.getSelectedClassifier(), addSelectComposite.getNewClassifiers());
		}
		
		getMixRigWizard().addAction(0,action);
		return getMixRigWizard().getFinishing();
	}
}
