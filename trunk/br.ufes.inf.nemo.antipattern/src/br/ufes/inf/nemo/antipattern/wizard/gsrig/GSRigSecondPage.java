package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
		btnRigid.addSelectionListener(canGoToNextPageAdapter);
		
		btnAntiRigid = new Button(container, SWT.RADIO);
		btnAntiRigid.setBounds(10, 57, 644, 16);
		btnAntiRigid.setText("Only the anti-rigid subtypes");
		btnAntiRigid.addSelectionListener(canGoToNextPageAdapter);
		
		btnMixed = new Button(container, SWT.RADIO);
		btnMixed.setBounds(10, 79, 644, 16);
		btnMixed.setText("The rigid subtypes have one and the anti-rigid another");
		btnMixed.addSelectionListener(canGoToNextPageAdapter);
		
		setPageComplete(false);
	}
	
	private SelectionAdapter canGoToNextPageAdapter = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			if(btnMixed.getSelection() || btnAntiRigid.getSelection() || btnRigid.getSelection()){
				if(!isPageComplete())
					setPageComplete(true);
			}
			else {
				if(isPageComplete())
					setPageComplete(false);
			}
			
		}
	};
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnRigid.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateGSForRigids(); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
			return getGSRigWizard().getFinishing();
		}
		
		if(btnAntiRigid.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateGSForAntiRigids(); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
			return getGSRigWizard().getFinishing();
		}
		
		if(btnMixed.getSelection())
		{			
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateGSForBoth(); 
			getGSRigWizard().replaceAction(0,newAction);	
			//======================================
			return getGSRigWizard().getFinishing();
		}
		
		return super.getNextPage();
	}
}
	