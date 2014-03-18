package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class GSRigFifthPage extends GSRigPage {
	
	private Button btnIntentional;
	private Button btnDerived;
	private Button btnEachRigid;
	private Label lblConsideringThatThere;
	private ArrayList<String> antirigids = new ArrayList<String>();
	
	public GSRigFifthPage(GSRigOccurrence gsrig) 
	{
		super(gsrig);		
	}
	
	public void setAntirigids(ArrayList<String> antirigids)
	{
		this.antirigids = antirigids;
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		lblConsideringThatThere = new Label(container, SWT.WRAP);
		lblConsideringThatThere.setBounds(10, 10, 644, 36);
		lblConsideringThatThere.setText("Considering that there are rigid and anti-rigid subtypes in the generalization set, it implies that there is one or more implicit rigid implicit subtype of <RigidSuperType>. Would you like to create:");
		
		btnIntentional = new Button(container, SWT.RADIO);
		btnIntentional.setBounds(10, 53, 644, 16);
		btnIntentional.setText("One common intentional subtype for all anti-rigid ones");
		
		btnDerived = new Button(container, SWT.RADIO);
		btnDerived.setBounds(10, 75, 644, 16);
		btnDerived.setText("One common derived subtype for all anti-rigid ones, using negation");
		
		btnEachRigid = new Button(container, SWT.RADIO);
		btnEachRigid.setBounds(10, 97, 644, 16);
		btnEachRigid.setText("One rigid subtype for each anti-rigid");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnIntentional.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			if(antirigids.size()>0)
				newAction.setCreateCommonSubtypeForAntiRigids(antirigids);
			else
				newAction.setCreateCommonSubtypeForAntiRigids();
			getGSRigWizard().replaceAction(1,newAction);
			//======================================
		}
		
		if(btnDerived.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateDerivationByNegation(); 
			getGSRigWizard().replaceAction(1,newAction);
			//======================================
			
			//Action =============================
			newAction = new GSRigAction(gsrig);
			if(antirigids.size()>0)
				newAction.setCreateCommonSubtypeForAntiRigids(antirigids);
			else
				newAction.setCreateCommonSubtypeForAntiRigids();
			getGSRigWizard().replaceAction(2,newAction);
			//======================================
		}
		
		if(btnEachRigid.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			if(antirigids.size()>0)
				newAction.setCreateRigidSubtypeForAntiRigids(antirigids);
			else
				newAction.setCreateRigidSubtypeForAntiRigids();
			getGSRigWizard().replaceAction(1,newAction);
			//======================================
		}
				
		return ((GSRigWizard)getWizard()).getFinishing();
	}
}