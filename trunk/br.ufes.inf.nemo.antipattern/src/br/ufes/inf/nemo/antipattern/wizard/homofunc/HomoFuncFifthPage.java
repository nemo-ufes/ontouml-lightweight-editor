package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.SubKind;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class HomoFuncFifthPage extends HomoFuncPage {

	private Label lblByYourPrevious;
	private Button btnCreateNewIdentityProvider;
	private Button btnMakeWholeProvider;
	private Button btnChangeStereotypeProvider;

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
		lblByYourPrevious.setText("By your previous answer, we conclude that "+homoFunc.getWhole().getName()+" is a collection. The change could not be automatically made because " +
		"the type inherits its identity principle from another type. Would like to:");
		
		btnCreateNewIdentityProvider = new Button(container, SWT.RADIO);
		btnCreateNewIdentityProvider.setBounds(10, 55, 554, 16);
		btnCreateNewIdentityProvider.setText("Create new type to act as the identity provider");
		
		btnMakeWholeProvider = new Button(container, SWT.RADIO);
		btnMakeWholeProvider.setBounds(10, 77, 554, 16);
		btnMakeWholeProvider.setText("Make "+homoFunc.getWhole().getName()+" the identity provider");
		
		btnChangeStereotypeProvider = new Button(container, SWT.RADIO);
		btnChangeStereotypeProvider.setBounds(10, 99, 554, 16);
		btnChangeStereotypeProvider.setText("Change the stereotype of the identity provider");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{			
		if(btnCreateNewIdentityProvider.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setCreateNewIdentityProvider(homoFunc.getWhole()); 
			getHomoFuncWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(btnMakeWholeProvider.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setChangeToCollective(homoFunc.getWhole()); 
			getHomoFuncWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(btnChangeStereotypeProvider.getSelection())
		{
			Classifier identityProvider = homoFunc.getWholeIdentityProvider();
			if (identityProvider !=null){
				//Action =============================
				HomoFuncAction newAction = new HomoFuncAction(homoFunc);
				newAction.setChangeToCollective(identityProvider); 
				getHomoFuncWizard().replaceAction(0,newAction);	
				//======================================				
			}
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
