package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class HomoFuncFourthPage extends HomoFuncPage {

	private Button btnYes;
	private Button btnNo;
	private Label lblInYourPrevious;

	public HomoFuncFourthPage(HomoFuncOccurrence homoFunc) 
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
		
		lblInYourPrevious = new Label(container, SWT.WRAP);
		lblInYourPrevious.setBounds(10, 10, 554, 82);
		lblInYourPrevious.setText("In your previous answer, you defined two or more subtypes of <FunctionalPart>. To guarantee the correctness of the change, we will now run the Whole Composed of Overlapping Part (WholeOver) anti-pattern.\r\n\r\nIs it possible for an object to be an instance of <Part-1>, <Part-2>, … and <Part-n> at the same time?\r\n");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(184, 99, 90, 16);
		btnYes.setText("Yes");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(280, 99, 90, 16);
		btnNo.setText("No");
	}
	

	@Override
	public IWizardPage getNextPage() 
	{	
		
		return super.getNextPage();
	}
}
