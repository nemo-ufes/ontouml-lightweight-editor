package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class GSRigSecondPage extends GSRigPage {

	private Label lblWhichSubtypesHave;
	private Button btnRigid;
	private Button btnAntiRigid;
	private Button btnMixed;

	public GSRigSecondPage(GSRigOccurrence gsrig) 
	{
		super(gsrig);		
	}

	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		lblWhichSubtypesHave = new Label(container, SWT.NONE);
		lblWhichSubtypesHave.setBounds(10, 10, 644, 15);
		lblWhichSubtypesHave.setText("Which subtypes have the same specialization criteria?");
		
		btnRigid = new Button(container, SWT.RADIO);
		btnRigid.setBounds(10, 35, 644, 16);
		btnRigid.setText("Only the rigid subtypes");
		
		btnAntiRigid = new Button(container, SWT.RADIO);
		btnAntiRigid.setBounds(10, 61, 644, 16);
		btnAntiRigid.setText("Only the anti-rigid subtypes");
		
		btnMixed = new Button(container, SWT.RADIO);
		btnMixed.setBounds(10, 83, 644, 16);
		btnMixed.setText("The rigid subtypes have one and the anti-rigid another");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnRigid.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateGSForRigids(); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
		}
		
		if(btnAntiRigid.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateGSForAntiRigids(); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
		}
		
		if(btnMixed.getSelection())
		{			
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateGSForBoth(); 
			getGSRigWizard().replaceAction(0,newAction);	
			//======================================
		}
		
		return ((GSRigWizard)getWizard()).getFinishing();
	}
}
	