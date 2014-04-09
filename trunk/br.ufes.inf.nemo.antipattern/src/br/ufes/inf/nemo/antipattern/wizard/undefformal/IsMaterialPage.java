package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class IsMaterialPage extends UndefFormalPage{
	
	public Composite parent;
	public Button btnYes;
	public Button btnNo;
	
	/**
	 * Create the wizard.
	 */
	public IsMaterialPage(UndefFormalOccurrence uf) 
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
	
		label.setText("Consider the instances ‘x’ of "+occurrence.getSource().getName()+" and ‘y’ of "+occurrence.getTarget().getName()+". Is the reason why ‘x’ is connected to ‘y’ " +
		"through <<formal>> "+occurrence.getFormal().getName()+", the occurrence of an external event, which also creates a truth-maker for the relation? To help you answering this question, consider the " +
		"example: the relation that holds between an Employee and the Company in which he works is a material relation. A Hiring event creates the relation alongside with " +
		"its truth-maker, the Employment (a relator).");
	
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 115, 52, 16);
		btnYes.setText("Yes");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 137, 52, 16);
		btnNo.setText("No");
	}	
	 
	@Override
	public IWizardPage getNextPage() 
	{	
		if (btnYes.getSelection()){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(occurrence);
			newAction.setChangeToMaterial(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget());
			getAntipatternWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(btnNo.getSelection()){
			return ((UndefFormalWizard)getWizard()).getThirdPage();
		}
	
		return super.getNextPage();
	}
}
