package br.ufes.inf.nemo.antipattern.wizard.relcomp;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import br.ufes.inf.nemo.antipattern.relcomp.RelCompAntipattern;
import br.ufes.inf.nemo.antipattern.relcomp.RelCompOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelCompRefactoringPage extends RefactoringPage {
	
	protected RelCompOccurrence relComp;
	public CCombo comboSourceDependsOnTarget, comboTargetDependsOnSource;
	public Button btnSourceDependsOnTarget, btnTargetDependsOnSource;
	public Spinner spinnerM, spinnerN;
	
	/**
	 * Create the wizard.
	 */
	public RelCompRefactoringPage(RelCompOccurrence relComp) 
	{
		super();	
		this.relComp = relComp;
				
		setTitle(RelCompAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public RelCompWizard getRelSpecWizard(){
		return (RelCompWizard)getWizard();
	}
		
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		SelectionAdapter listener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
	  
			  if (btnSourceDependsOnTarget.getSelection()){
				  comboSourceDependsOnTarget.setEnabled(true);
				  Device device = Display.getCurrent ();
				  comboSourceDependsOnTarget.setBackground(new Color(device, 255, 255, 255));
				  
				  if(comboSourceDependsOnTarget.getSelectionIndex()==1)
					  spinnerN.setEnabled(true);
			  }
			  else{
				  comboSourceDependsOnTarget.setEnabled(false);
				  spinnerN.setEnabled(false);
			  }
			  
			  if(btnTargetDependsOnSource.getSelection()){
				  comboTargetDependsOnSource.setEnabled(true);
				  Device device = Display.getCurrent ();
				  comboTargetDependsOnSource.setBackground(new Color(device, 255, 255, 255));
				  
				  if(comboTargetDependsOnSource.getSelectionIndex()==1)
					  spinnerM.setEnabled(true);
			  }
			  else {
				  comboTargetDependsOnSource.setEnabled(false);
				  spinnerM.setEnabled(false);
			  }		      
			}
		};
		
		SelectionAdapter listenerCombo = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
				if(comboSourceDependsOnTarget.getSelectionIndex()==1)
					spinnerN.setEnabled(true);
				else
					spinnerN.setEnabled(false);
				
				if(comboTargetDependsOnSource.getSelectionIndex()==1)
					spinnerM.setEnabled(true);
				else
					spinnerM.setEnabled(false);
			}
		};
			    
	    setPageComplete(true);
		
		Label lblChooseTheAppropriate = new Label(container, SWT.NONE);
		lblChooseTheAppropriate.setText("Choose the appropriate refactoring option by answering the following question:\r\n\r\n" +
										"If an instance 'x' of '"+getRelComp().getA2Source().getName()+"' is connected to an instance 'y' of '"+getRelComp().getA2Target().getName()+
										"', through '"+getRelComp().getParser().getStringRepresentation(getRelComp().getA2())+"', is it NECESSARY that:");
		lblChooseTheAppropriate.setBounds(10, 10, 699, 65);
		
		Label label = new Label(container, SWT.NONE);
		label.setText("for n = ");
		label.setBounds(10, 108, 39, 15);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("for m = ");
		label_1.setBounds(10, 173, 39, 15);
		
		btnSourceDependsOnTarget = new Button(container, SWT.CHECK);
		btnSourceDependsOnTarget.setBounds(10, 81, 112, 21);
		btnSourceDependsOnTarget.setText("'x' is connected to ");
		btnSourceDependsOnTarget.addSelectionListener(listener);
		
		comboSourceDependsOnTarget = new CCombo(container, SWT.BORDER);
		comboSourceDependsOnTarget.setBounds(128, 81, 94, 21);
		comboSourceDependsOnTarget.addSelectionListener(listenerCombo);
		
		spinnerN = new Spinner(container, SWT.BORDER);
		spinnerN.setBounds(55, 108, 47, 22);
		
		setUiDefaultProperties(spinnerN, comboSourceDependsOnTarget);
		
		String oppositeGeneralType = "";
		
		if(getRelComp().a2EndsSpecializeA1Target())
			oppositeGeneralType = relComp.getA1Source().getName();
		else
			oppositeGeneralType = relComp.getA1Target().getName();
		
		Label lblNewLabel = new Label(container, SWT.WRAP);
		lblNewLabel.setBounds(228, 84, 481, 39);
		lblNewLabel.setText("instances of '"+oppositeGeneralType+"' to which 'y' is connected to through '"+getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");
		
		btnTargetDependsOnSource = new Button(container, SWT.CHECK);
		btnTargetDependsOnSource.setText("'y' is connected to ");
		btnTargetDependsOnSource.setBounds(10, 146, 112, 21);
		btnTargetDependsOnSource.addSelectionListener(listener);
		
		comboTargetDependsOnSource = new CCombo(container, SWT.BORDER);
		comboTargetDependsOnSource.setBounds(128, 146, 94, 21);
		comboTargetDependsOnSource.addSelectionListener(listenerCombo);
		
		spinnerM = new Spinner(container, SWT.BORDER);
		spinnerM.setBounds(55, 170, 47, 22);
		
		setUiDefaultProperties(spinnerM,comboTargetDependsOnSource);
		
		Label lblInstancesOf = new Label(container, SWT.WRAP);
		
		
		lblInstancesOf.setText("instances of '"+oppositeGeneralType+"' to which 'x' is connected to through '"+getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");
		lblInstancesOf.setBounds(228, 149, 481, 39);
		
	}
	
	private void setUiDefaultProperties(Spinner spin, CCombo combo){
		spin.setSelection(1);
		spin.setMinimum(1);
		spin.setEnabled(false);
		combo.setItems(new String[] {"all", "at least <n>", "no"});
		combo.select(0);
		combo.setEnabled(false);
		combo.setEditable(false);
	}

	public RelCompOccurrence getRelComp(){
		return relComp;
	}

	@Override
	public IWizardPage getNextPage() {
		RelCompAction action;
		getRelSpecWizard().removeAllActions();
		
		if(btnSourceDependsOnTarget.getSelection()){
			action = new RelCompAction(relComp);
			//includesAll
			if(comboSourceDependsOnTarget.getSelectionIndex()==0)
				action.setIncludesAllSource();
			//excludesAll
			if(comboSourceDependsOnTarget.getSelectionIndex()==1)
				action.setAtLeastSource(spinnerN.getSelection());			
			//atLeast
			if(comboSourceDependsOnTarget.getSelectionIndex()==2)
				action.setExcludesAllSource();
			
			getRelSpecWizard().addAction(0, action);
		}
		
		if(btnTargetDependsOnSource.getSelection()){
			action = new RelCompAction(relComp);
			//includesAll
			if(comboTargetDependsOnSource.getSelectionIndex()==0)
				action.setIncludesAllTarget();
			//excludesAll
			if(comboTargetDependsOnSource.getSelectionIndex()==1)
				action.setAtLeastTarget(spinnerM.getSelection());			
			//atLeast
			if(comboTargetDependsOnSource.getSelectionIndex()==2)
				action.setExcludesAllTarget();
			
			getRelSpecWizard().addAction(0, action);
		}
		
		return 	getRelSpecWizard().getFinishing();				
	}
}
