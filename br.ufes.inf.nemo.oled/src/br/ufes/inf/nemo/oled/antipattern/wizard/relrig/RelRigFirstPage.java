package br.ufes.inf.nemo.oled.antipattern.wizard.relrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelRigFirstPage extends WizardPage {

	public RelRigOccurrence relRig;
	public int rigid;
	public RefOntoUML.Type rigidType;
	
	//GUI
	public Button btnYes;
	public Button btnNo;
	
	/**
	 * Create the wizard.
	 */
	public RelRigFirstPage(RelRigOccurrence relRig, int rigid) 
	{
		super("RelRigFirstPage");				
		this.relRig = relRig;
		this.rigid = rigid;
		
		rigidType = relRig.getRigidMediatedProperties().get(rigid).getType();
		String text = relRig.getOntoUMLParser().getStringRepresentation(rigidType);
		int n = (rigid+1);
		
		setTitle("Rigid Type #"+n+": "+text);
		setDescription("1/4");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText("Is it possible for an object that isn’t a "+rigidType.getName()+" to become one or an object that is an instance of "+rigidType.getName()+" cease to be it and still exist?");
		styledText.setEditable(false);
		styledText.setBounds(10, 10, 554, 38);
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnYes.setBounds(10, 54, 90, 16);
		btnYes.setText("Yes");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No");
		btnNo.setBounds(10, 78, 90, 16);
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		if(btnNo.getSelection()) 
			return ((RelRigWizard)getWizard()).getSecondPage(rigid);
			
		else if(btnYes.getSelection()){			
		
			// Action =====================			
			relRig.changeToRoleOrRoleMixin(rigidType);
			//=============================
			
			if(rigid < relRig.getRigidMediatedProperties().size()-1)
				return ((RelRigWizard)getWizard()).getFirstPage(rigid+1);				
			else { 
				((RelRigWizard)getWizard()).finishing.addFix(relRig.getFix()); 
				return ((RelRigWizard)getWizard()).finishing;
			}			
		}
		return super.getNextPage();
	}
}
