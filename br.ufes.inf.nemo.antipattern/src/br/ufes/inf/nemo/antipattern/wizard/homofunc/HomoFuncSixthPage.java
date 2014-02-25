package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class HomoFuncSixthPage extends HomoFuncPage {

	private Button btnMakeTheIdentity;
	private Button btnCreateANew;
	private Label lblByYourPrevious;
	public HomoFuncSixthPage(HomoFuncOccurrence homoFunc) 
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
		
		lblByYourPrevious = new Label(container, SWT.WRAP);
		lblByYourPrevious.setBounds(10, 10, 554, 50);
		lblByYourPrevious.setText("By your previous answer, we conclude that <FunctionalWhole> is a collection. The change could not be made automatically because the type doesn't define and neither inherits an identity principle. Would you like to:");
		
		btnMakeTheIdentity = new Button(container, SWT.RADIO);
		btnMakeTheIdentity.setBounds(10, 66, 554, 16);
		btnMakeTheIdentity.setText("Make <FunctionalWhole> the identity provider");
		
		btnCreateANew = new Button(container, SWT.RADIO);
		btnCreateANew.setBounds(10, 88, 554, 16);
		btnCreateANew.setText("Create a new identity provider");
	}
	 
	@Override
	public IWizardPage getNextPage() 
	{			
		return super.getNextPage();
	}
}
