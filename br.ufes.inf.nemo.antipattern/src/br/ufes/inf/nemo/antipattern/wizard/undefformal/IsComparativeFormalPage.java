package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class IsComparativeFormalPage extends UndefFormalPage{

	public Composite parent;
	public Button btnYes;
	public Button btnNo;
	
	/**
	 * Create the wizard.
	 */
	public IsComparativeFormalPage(UndefFormalOccurrence uf) 
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
		
		Label lblCanBeReduced = new Label(container, SWT.WRAP);
		lblCanBeReduced.setBounds(10, 10, 554, 54);
		lblCanBeReduced.setText("Can "+occurrence.getFormal().getName()+" be reduced to the comparison of quality values of qualities (Data Types) that characterize " +
			occurrence.getSource().getName()+" and "+occurrence.getTarget().getName()+"? An example of a relation that can be derived is “heavier than”, that " +
			"holds between two people and which can be derived from the comparison of their weights.");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 70, 554, 16);
		btnYes.setText("Yes, this is a comparative domain formal relation");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 92, 554, 16);
		btnNo.setText("No, this is other formal relation");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnYes.getSelection()){
			return ((UndefFormalWizard)getWizard()).getFourthPage();
		}
		if(btnNo.getSelection()){
			MessageDialog.openInformation(getShell(), "Information", "You are representing a particular type of formal relation, which is not covered in OntoUML.");
			return ((UndefFormalWizard)getWizard()).getFinishing();
		}
		return super.getNextPage();
	}
}
