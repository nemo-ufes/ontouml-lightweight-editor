package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class HomoFuncFifthPage extends HomoFuncPage {

	private Label lblByYourPrevious;
	private Button btnCreateNewType;
	private Button btnMakeTheIdentity;
	private Button btnChangeTheStereotype;

	public HomoFuncFifthPage(HomoFuncOccurrence homoFunc) 
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
		lblByYourPrevious.setBounds(10, 10, 554, 39);
		lblByYourPrevious.setText("By your previous answer, we conclude that <FunctionalWhole> is a collection. The change could not be automatically made because the type inherits its identity principle from another type. Would like to:");
		
		btnCreateNewType = new Button(container, SWT.RADIO);
		btnCreateNewType.setBounds(10, 55, 554, 16);
		btnCreateNewType.setText("Create new type to act as the identity provider");
		
		btnMakeTheIdentity = new Button(container, SWT.RADIO);
		btnMakeTheIdentity.setBounds(10, 77, 554, 16);
		btnMakeTheIdentity.setText("Make <FunctionalWhole> the identity provider");
		
		btnChangeTheStereotype = new Button(container, SWT.RADIO);
		btnChangeTheStereotype.setBounds(10, 99, 554, 16);
		btnChangeTheStereotype.setText("Change the stereotype of the identity provider");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{			
		return super.getNextPage();
	}
}
