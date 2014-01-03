package br.ufes.inf.nemo.oled.antipattern.wizard.relrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;

public class RelRigFourthPage extends WizardPage {

	public RelRigOccurrence relRig;
	public int rigid;
	public RefOntoUML.Type rigidType;
	
	//GUI
	public Button btnYes;
	public Button btnNo;
	
	/**
	 * Create the wizard.
	 */
	public RelRigFourthPage(RelRigOccurrence relRig, int rigid) {
		super("wizardPage");
		setTitle(RelRigAntipattern.getAntipatternInfo().getAcronym() + " - Fourth Question");
		this.relRig = relRig;
		this.rigid = rigid;
		
		rigidType = relRig.getRigidMediatedProperties().get(rigid).getType();
		String text = relRig.getOntoUMLParser().getStringRepresentation(rigidType);
		setDescription("Rigid Type #"+rigid+1+" : "+text);
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
		
		if(btnNo.getSelection()) {
			//TODO execute action			
		}else if(btnYes.getSelection()){			
			//TODO execute action						
		}
		
		if(rigid < relRig.getRigidMediatedProperties().size()-1){		
			return ((RelRigWizard)getWizard()).getFirstPage(rigid+1);				
		}else{
			return ((RelRigWizard)getWizard()).finishing;
		}
	}
}
