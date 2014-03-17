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
		btnChangeToRole.setBounds(10, 41, 554, 16);
		btnChangeToRole.setText("Change <"+depPhase.getPhase().getName()+"> to «Role»");
		btnChangeToRole.addSelectionListener(btnAdpater);
		
		btnKeepAsPhase = new Button(container, SWT.RADIO);
		btnKeepAsPhase.setBounds(10, 63, 554, 16);
		btnKeepAsPhase.setText("Keep <"+depPhase.getPhase().getName()+"> as «Phase»");
		btnKeepAsPhase.addSelectionListener(btnAdpater);
		
		listMandatory = new List(container, SWT.BORDER);
		listMandatory.setBounds(10, 114, 238, 157);
		listOptional = new List(container, SWT.BORDER);
		listOptional.setBounds(326, 114, 238, 157);
		getDepPhaseWizard().addAllDependencies(listMandatory,listOptional);
		
		btnFromMandatoryToOptional = new Button(container, SWT.NONE);
		btnFromMandatoryToOptional.setBounds(269, 153, 37, 25);
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
		btnFromOptionalToMandatory.setBounds(269, 184, 37, 25);
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
		lblMandatory.setBounds(10, 93, 238, 15);
		lblMandatory.setText("Mandatory");
		
		Label lblOptional = new Label(container, SWT.NONE);
		lblOptional.setText("Optional");
		lblOptional.setBounds(326, 93, 238, 15);
		
		lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 554, 15);
		lblNewLabel.setText("Please select the refactoring option:");
		
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
