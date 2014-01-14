package br.ufes.inf.nemo.antipattern.wizard.relrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Mediation;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelRigSecondPage extends RelRigPage {

	//GUI
	public Button btnYes;
	public Button btnNo;
	
	/**
	 * Create the wizard.
	 */
	public RelRigSecondPage(RelRigOccurrence relRig, int rigid) {
		super(relRig,rigid);
		
		String text = relRig.getOntoUMLParser().getStringRepresentation(rigidType);
		int n = (rigid+1);		
		setTitle("Rigid Type #"+n+": "+text);
		setDescription("2/4");
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
		styledText.setText("Is it always necessary for an instance of "+rigidType.getName()+" to be connected to an instance of "+relRig.getRelator().getName()+"?");
		styledText.setBounds(10, 10, 554, 15);
		
		SelectionAdapter listener = new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		        if (isPageComplete()==false) setPageComplete(true);
		      }
		    };
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes");
		btnYes.setBounds(10, 31, 90, 16);
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No");
		btnNo.setBounds(10, 55, 90, 16);
		btnNo.addSelectionListener(listener);
	}	
	
	@Override
	public IWizardPage getNextPage() {
		
		if(btnYes.getSelection()) 
			return ((RelRigWizard)getWizard()).getThirdPage(rigid);
			
		else if(btnNo.getSelection()){			

			RelRigAction newAction = new RelRigAction(relRig);
			newAction.setAddRoleSubtype(rigidType,(Mediation) rigidEnd.getAssociation());
			getRelRigWizard().addAction(rigid,newAction);
			
			//=============================
									
			if(rigid < relRig.getRigidMediatedProperties().size()-1)
				return ((RelRigWizard)getWizard()).getFirstPage(rigid+1);				
			else{								
				return ((RelRigWizard)getWizard()).getFinishing();
			}
		}
		return super.getNextPage();
	}	
}
