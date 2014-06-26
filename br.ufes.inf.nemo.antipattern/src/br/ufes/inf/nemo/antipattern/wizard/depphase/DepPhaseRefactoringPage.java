package br.ufes.inf.nemo.antipattern.wizard.depphase;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

public class DepPhaseRefactoringPage extends RefactoringPage {
	
	private List listMandatory;
	private List listOptional;
	private Button btnFromMandatoryToOptional;
	private Button btnFromOptionalToMandatory;
	
	public DepPhaseOccurrence depPhase;
	private Button btnChangeToRole;
	private Button btnKeepAsPhase;
	private Label lblNewLabel;
	
	/**
	 * Create the wizard.
	 */
	public DepPhaseRefactoringPage(DepPhaseOccurrence depPhase) 
	{
		super();	
		this.depPhase = depPhase;
		
		setTitle(DepPhaseAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public DepPhaseWizard getDepPhaseWizard(){
		return (DepPhaseWizard)getWizard();
	}
	
		
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		SelectionAdapter btnAdpater = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(btnKeepAsPhase.getSelection()==true){
					setEnableOfDependenciesUI(true);
					setPageComplete(true);
				}
				else if(btnChangeToRole.getSelection()==true){
					setEnableOfDependenciesUI(false);
					setPageComplete(true);
				}
				else {
					setPageComplete(false);
				}
			}
		};
		
		btnChangeToRole = new Button(container, SWT.RADIO);
		btnChangeToRole.setText("Change <"+depPhase.getPhase().getName()+"> to «Role»");
		btnChangeToRole.addSelectionListener(btnAdpater);
		
		btnKeepAsPhase = new Button(container, SWT.RADIO);
		btnKeepAsPhase.setText("Keep <"+depPhase.getPhase().getName()+"> as «Phase»");
		btnKeepAsPhase.addSelectionListener(btnAdpater);
		
		listMandatory = new List(container, SWT.BORDER);
		listOptional = new List(container, SWT.BORDER);
		getDepPhaseWizard().addAllDependencies(listMandatory,listOptional);
		
		btnFromMandatoryToOptional = new Button(container, SWT.NONE);
		btnFromMandatoryToOptional.setText("->");
		btnFromMandatoryToOptional.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: listMandatory.getSelection()){
					if(!getDepPhaseWizard().contains(listOptional,str)) { 
						listOptional.add(str); 
						listOptional.select(listOptional.indexOf(str));  
					} 
				}
				if(listMandatory.getSelectionIndex()>=0) {
					int prev = listMandatory.getSelectionIndex()-1;
					listMandatory.remove(listMandatory.getSelectionIndex());
					listMandatory.select(prev);					 
				}
			}
		});
		
		btnFromOptionalToMandatory = new Button(container, SWT.NONE);
		btnFromOptionalToMandatory.setText("<-");
		btnFromOptionalToMandatory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: listOptional.getSelection()){
					if(!getDepPhaseWizard().contains(listMandatory,str)) { 
						listMandatory.add(str); 
						listMandatory.select(listMandatory.indexOf(str));  
					} 
				}
				if(listOptional.getSelectionIndex()>=0) {
					int prev = listOptional.getSelectionIndex()-1;
					listOptional.remove(listOptional.getSelectionIndex());
					listOptional.select(prev);					 
				}
			}
		});
		
		Label lblMandatory = new Label(container, SWT.NONE);
		lblMandatory.setText("Mandatory");
		
		Label lblOptional = new Label(container, SWT.NONE);
		lblOptional.setText("Optional");
		
		lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("Please select the refactoring option:");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(lblNewLabel, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnChangeToRole, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnKeepAsPhase, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(gl_container.createSequentialGroup()
									.add(listMandatory, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
									.add(21)
									.add(gl_container.createParallelGroup(GroupLayout.LEADING)
										.add(btnFromMandatoryToOptional, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.add(btnFromOptionalToMandatory, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
									.add(20))
								.add(gl_container.createSequentialGroup()
									.add(lblMandatory, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
									.add(78)))
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(lblOptional, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
								.add(GroupLayout.TRAILING, listOptional, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblNewLabel)
					.add(16)
					.add(btnChangeToRole)
					.add(6)
					.add(btnKeepAsPhase)
					.add(14)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(lblMandatory)
						.add(lblOptional))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(listMandatory, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
						.add(gl_container.createSequentialGroup()
							.add(39)
							.add(btnFromMandatoryToOptional)
							.add(6)
							.add(btnFromOptionalToMandatory))
						.add(listOptional, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
					.add(8))
		);
		container.setLayout(gl_container);
		
		setPageComplete(false);
		setEnableOfDependenciesUI(false);
		
	}
	
	private void setEnableOfDependenciesUI(boolean value){
		btnFromMandatoryToOptional.setEnabled(value);
		btnFromOptionalToMandatory.setEnabled(value);
		listMandatory.setEnabled(value);
		listOptional.setEnabled(value);
	}
	
	public ArrayList<Property> getMandatoryDependencies(){
		return getDepPhaseWizard().getPropertyFromList(listMandatory);
	}
	
	public ArrayList<Property> getOptionalDependencies(){
		return getDepPhaseWizard().getPropertyFromList(listOptional);
	}
	
	@Override
	public IWizardPage getNextPage() {
		getDepPhaseWizard().removeAllActions();
		DepPhaseAction action;
		
		if(btnChangeToRole.getSelection()==true){
			action = new DepPhaseAction(depPhase);
			action.setChangeToRole();
			getDepPhaseWizard().addAction(0, action);
		}
		else {
			for (Property mandatory : getMandatoryDependencies()) {
				action = new DepPhaseAction(depPhase);
				action.setAddSupertypeAndMoveMediation(mandatory);
				getDepPhaseWizard().addAction(1, action);
			}
			for (Property optional : getOptionalDependencies()) {
				action = new DepPhaseAction(depPhase);
				action.setAddSubtypeAndMoveMediation(optional);
				getDepPhaseWizard().addAction(1, action);
			}
		}
		
		return getDepPhaseWizard().getFinishing();
	}
}
