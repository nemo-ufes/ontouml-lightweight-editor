package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class HomoFuncSeventhPage extends HomoFuncPage {

	private Label lblNowBothThe;
	private Button btnYes;
	private Button btnNo;

	public HomoFuncSeventhPage(HomoFuncOccurrence homoFunc) 
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
		
		lblNowBothThe = new Label(container, SWT.WRAP);
		lblNowBothThe.setBounds(10, 10, 554, 70);
		lblNowBothThe.setText("Now, both the whole the part are collections. To make sure that we correctly refactor the model, answer the " +
		"following: \r\n\r\nAre the members of "+homoFunc.getPartEnd().getType().getName()+" also members of "+homoFunc.getWhole().getName()+"?");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 83, 554, 16);
		btnYes.setText("Yes (subCollectionOf)");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 105, 554, 16);
		btnNo.setText("No (memberOf)");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{			
		if(btnNo.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setChangeToMemberOf(homoFunc.getPartEnd().getAssociation()); 
			getHomoFuncWizard().replaceAction(1,newAction);	
			//======================================
		}
		if(btnYes.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setChangeToSubCollectionOf(homoFunc.getPartEnd().getAssociation()); 
			getHomoFuncWizard().replaceAction(1,newAction);	
			//======================================
		}
		
		return getHomoFuncWizard().getFinishing();
	}
}
