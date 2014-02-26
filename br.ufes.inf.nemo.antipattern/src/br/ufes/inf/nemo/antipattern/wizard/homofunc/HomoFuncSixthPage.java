package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.SubKind;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class HomoFuncSixthPage extends HomoFuncPage {

	private Button btnMakeWholeIdentityProvider;
	private Button btnCreateNewProvider;
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
		lblByYourPrevious.setText("By your previous answer, we conclude that "+homoFunc.getWhole().getName()+" is a collection. The change could not be made automatically " +
		"because the type doesn't define and neither inherits an identity principle. Would you like to:");
		
		btnMakeWholeIdentityProvider = new Button(container, SWT.RADIO);
		btnMakeWholeIdentityProvider.setBounds(10, 66, 554, 16);
		btnMakeWholeIdentityProvider.setText("Make "+homoFunc.getWhole().getName()+"the identity provider");
		
		btnCreateNewProvider = new Button(container, SWT.RADIO);
		btnCreateNewProvider.setBounds(10, 88, 554, 16);
		btnCreateNewProvider.setText("Create a new identity provider");
	}
	 
	@Override
	public IWizardPage getNextPage() 
	{			
		if(btnMakeWholeIdentityProvider.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setChangeToCollective(homoFunc.getWhole()); 
			getHomoFuncWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(btnCreateNewProvider.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setCreateNewIdentityProvider(homoFunc.getWhole()); 
			getHomoFuncWizard().replaceAction(0,newAction);	
			//======================================
		}
		
		if(homoFunc.getPartEnd().getType() instanceof Collective){
			return ((HomoFuncWizard)getWizard()).getSeventhPage();			
		}
		if(homoFunc.getPartEnd().getType() instanceof SubKind){
			for(Classifier c: (((Classifier)homoFunc.getPartEnd().getType()).allParents())){
				if (c instanceof Collective) return ((HomoFuncWizard)getWizard()).getSeventhPage();	
			}				
		}
		
		//Action =============================
		HomoFuncAction newAction = new HomoFuncAction(homoFunc);
		newAction.setChangeToMemberOf(homoFunc.getPartEnd().getAssociation()); 
		getHomoFuncWizard().replaceAction(1,newAction);	
		//======================================
		
		return getHomoFuncWizard().getFinishing();
	}
}
