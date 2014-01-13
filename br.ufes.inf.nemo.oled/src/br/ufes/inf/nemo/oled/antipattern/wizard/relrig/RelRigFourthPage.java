package br.ufes.inf.nemo.oled.antipattern.wizard.relrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;
import br.ufes.inf.nemo.oled.antipattern.wizard.WizardAction;
import br.ufes.inf.nemo.oled.antipattern.wizard.relrig.RelRigWizard.RelRigAction;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelRigFourthPage extends RelRigPage {

	public int rigid;
	public RefOntoUML.Type rigidType;
	
	//GUI
	public Button btnYes;
	public Button btnNo;
	
	/**
	 * Create the wizard.
	 */
	public RelRigFourthPage(RelRigOccurrence relRig, int rigid) {
		super(relRig);		

		this.rigid = rigid;		
		rigidType = relRig.getRigidMediatedProperties().get(rigid).getType();
		String text = relRig.getOntoUMLParser().getStringRepresentation(rigidType);
		int n = (rigid+1);		
		setTitle("Rigid Type #"+n+": "+text);
		setDescription("4/4");		
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setEditable(false);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText("Is possible for an instance of "+relRig.getRelator().getName()+" to change the instance of "+rigidType.getName()+" it is connected to?");
		styledText.setBounds(10, 10, 554, 15);
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes");
		btnYes.setBounds(10, 31, 90, 16);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No");
		btnNo.setBounds(10, 55, 90, 16);
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		if(btnNo.getSelection()) 
		{
			// Action =====================			
			WizardAction<RelRigAction> newAction = new WizardAction<RelRigAction>(RelRigAction.BOTH_READ_ONLY);
			getRelRigWizard().getActions().add(rigid,newAction);								
			//=============================
			
		}else if(btnYes.getSelection())
		{
			// Action =====================			
			WizardAction<RelRigAction> newAction = new WizardAction<RelRigAction>(RelRigAction.CHANGE_TO_MODE);
			getRelRigWizard().getActions().add(rigid,newAction);								
			//=============================
		}
		
		if(rigid < relRig.getRigidMediatedProperties().size()-1){		
			return ((RelRigWizard)getWizard()).getFirstPage(rigid+1);				
		}else{			
			return ((RelRigWizard)getWizard()).getFinishing();
		}
	}
}
