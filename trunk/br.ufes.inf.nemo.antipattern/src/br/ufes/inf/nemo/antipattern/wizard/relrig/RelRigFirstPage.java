package br.ufes.inf.nemo.antipattern.wizard.relrig;

import org.eclipse.jface.wizard.IWizardPage;
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

public class RelRigFirstPage extends RelRigPage {
	
	//GUI
	public Button btnYes;
	public Button btnNo;
	
	/**
	 * Create the wizard.
	 */
	public RelRigFirstPage(RelRigOccurrence relRig, int rigid) 
	{
		super(relRig,rigid);			
		
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
		
		StyledText styledText = new StyledText(container, SWT.WRAP | SWT.V_SCROLL);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText("Is it possible for an object that isn’t a "+rigidType.getName()+" to become one or an object that is an instance of "+rigidType.getName()+" cease to be it and still exist?");
		styledText.setEditable(false);
		styledText.setBounds(10, 10, 554, 58);
		
		SelectionAdapter listener = new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		        if (isPageComplete()==false) setPageComplete(true);
		      }
		    };
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 74, 554, 16);
		btnYes.setText("Yes");
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 98, 554, 16);
		btnNo.setText("No");
		btnYes.addSelectionListener(listener);

	}
	
	@Override
	public IWizardPage getNextPage() {
		
		if(btnNo.getSelection()) 
			return ((RelRigWizard)getWizard()).getSecondPage(rigid);
			
		else if(btnYes.getSelection()){			
		
			// Action =====================			
			RelRigAction newAction = new RelRigAction(relRig);
			newAction.setChangeStereotypeToRole(rigidType);
			
			getRelRigWizard().replaceAction(rigid,newAction);
			
			//=============================
			
			if(rigid < relRig.getRigidMediatedProperties().size()-1)
				return ((RelRigWizard)getWizard()).getFirstPage(rigid+1);				
			else { 								 
				return ((RelRigWizard)getWizard()).getFinishing();
			}
		}
		return super.getNextPage();
	}
	
}
