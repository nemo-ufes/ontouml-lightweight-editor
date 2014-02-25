package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class HomoFuncSecondPage extends HomoFuncPage {

	public Button btnYes;
	public Button btnNo;
	private CreatePartComposite createPartComposite; 
	
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
		
		createPartComposite = new CreatePartComposite(container, SWT.BORDER,homoFunc);
		createPartComposite.setBounds(10, 90, 564, 182);
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  createPartComposite.enableCreation(true);
	      }
	    };
	    
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(221, 68, 69, 16);
		btnYes.setText("Yes...");
		btnYes.addSelectionListener(listener);
		
		SelectionAdapter listener2 = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  createPartComposite.enableCreation(false);
	      }
	    };
		    
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(296, 68, 64, 16);
		btnNo.setText("No.");
		btnNo.addSelectionListener(listener2);				
	}	
	
	@Override
	public IWizardPage getNextPage() 
	{		
		if(btnYes.getSelection()){
			
//			//Action =============================
//			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
//			newAction.setCreateNewPart(dialog.getPartStereotype(), dialog.getPartName(), dialog.getComponentOfName(), 
//				dialog.isShareable(), dialog.isEssential(), dialog.isImmutablePart(), dialog.isImmutableWhole(), dialog.isInseparable()); 
//			getHomoFuncWizard().replaceAction(0,newAction);	
//			//======================================
			
			return ((HomoFuncWizard)getWizard()).getThirdPage();
		}
		if(btnNo.getSelection()) {
			return ((HomoFuncWizard)getWizard()).getThirdPage();
		}
		return super.getNextPage();
	}
}

