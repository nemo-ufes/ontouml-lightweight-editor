package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class GSRigFifthPage extends GSRigPage {
	
	private Button btnIntentional;
	private Button btnDerived;
	private Button btnEachRigid;
	private Label lblConsideringThatThere;

	public GSRigFifthPage(GSRigOccurrence gsrig) 
	{
		super(gsrig);		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		lblConsideringThatThere = new Label(container, SWT.WRAP);
		lblConsideringThatThere.setBounds(10, 10, 554, 36);
		lblConsideringThatThere.setText("Considering that there are rigid and anti-rigid subtypes in the generalization set, it implies that there is one or more implicit rigid implicit subtype of <RigidSuperType>. Would you like to create:");
		
		btnIntentional = new Button(container, SWT.RADIO);
		btnIntentional.setBounds(10, 53, 554, 16);
		btnIntentional.setText("One common intentional subtype for all anti-rigid ones");
		
		btnDerived = new Button(container, SWT.RADIO);
		btnDerived.setBounds(10, 75, 554, 16);
		btnDerived.setText("One common derived subtype for all anti-rigid ones, using negation");
		
		btnEachRigid = new Button(container, SWT.RADIO);
		btnEachRigid.setBounds(10, 97, 554, 16);
		btnEachRigid.setText("One rigid subtype for each anti-rigid");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnIntentional.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			//newAction.setChangeAllToComponentOf(assocList); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
		}
		
		if(btnDerived.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			//newAction.setChangeAllToComponentOf(assocList); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
		}
		
		if(btnEachRigid.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			//newAction.setChangeAllToComponentOf(assocList); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
		}
				
		return ((GSRigWizard)getWizard()).getFinishing();
	}
}