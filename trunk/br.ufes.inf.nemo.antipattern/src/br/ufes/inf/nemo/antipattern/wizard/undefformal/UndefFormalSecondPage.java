package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class UndefFormalSecondPage extends UndefFormalPage{
	
	public Composite parent;
	public Button btnYes;
	public Button btnNo;
	
	/**
	 * Create the wizard.
	 */
	public UndefFormalSecondPage(UndefFormalOccurrence uf) 
	{
		super(uf);
		setDescription("Source: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
	}
	
	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		Label label = new Label(container, SWT.WRAP);
		label.setBounds(10, 10, 554, 89);
	
		label.setText("Consider the instances ‘x’ of "+uf.getSource().getName()+" and ‘y’ of "+uf.getTarget().getName()+". Is the reason why ‘x’ is connected to ‘y’ " +
		"through <<formal>> "+uf.getFormal().getName()+", the occurrence of an external event, which also creates a truth-maker for the relation? To help you answering this question, consider the " +
		"example: the relation that holds between an Employee and the Company in which he works is a material relation. A Hiring event creates the relation alongside with " +
		"its truth-maker, the Employment (a relator).");
	
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(281, 105, 52, 16);
		btnYes.setText("Yes");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(223, 105, 52, 16);
		btnNo.setText("No");
	}	
	 
	@Override
	public IWizardPage getNextPage() 
	{	
		if (btnYes.getSelection()){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToMaterial(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(btnNo.getSelection()){
			return ((UndefFormalWizard)getWizard()).getThirdPage();
		}
	
		return super.getNextPage();
	}
}
