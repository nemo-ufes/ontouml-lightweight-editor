package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class HomoFuncSecondPage extends HomoFuncPage {

	public Button btnYes;
	public Button btnNo;
	
	public HomoFuncSecondPage(HomoFuncOccurrence homoFunc) 
	{
		super(homoFunc);
	}
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		Label lblThroughYourPrevious = new Label(container, SWT.WRAP);
		lblThroughYourPrevious.setBounds(10, 10, 554, 52);
		lblThroughYourPrevious.setText("Through your previous answer, we established that "+homoFunc.getWhole().getName()+" is indeed a functional complex. That indicates that there " +
			"are other type parts which are not captured by the model. Would you like to define new parts, which are not instances of "+homoFunc.getPartEnd().getType().getName()+"?");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(80, 68, 484, 16);
		btnYes.setText("Yes...");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 68, 64, 16);
		btnNo.setText("No.");
		
		PartsCreationComposite partsCreationComposite = new PartsCreationComposite(container, SWT.BORDER);
		partsCreationComposite.setBounds(0, 90, 574, 182);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnYes.getSelection()){
			
		}
		if(btnNo.getSelection()){
			
		}
		return super.getNextPage();
	}
}

