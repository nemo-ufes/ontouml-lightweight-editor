package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;

public class RepRelSecondPage extends RepRelPage {
	
	public StyledText styledText;
	public Button btnYes;
	public Button btnNo;
	
	public RepRelSecondPage(RepRelOccurrence rr) {
		super(rr);
		setDescription("Page 2");
	}

	public void setRigids(ArrayList<Type> rigids)
	{		
		styledText.setText("Now, since you said that a relator could be connected with mediated types at the same time but not as many as desired... " +
			"\r\n\r\nChoose the maximum number of instances of "+repRel.getRelator().getName()+" connected with the same instances at the same time:");
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        if (isPageComplete()==false) setPageComplete(true);
	      }
	    };
			    
		setPageComplete(false);
				
		setControl(container);
		
		styledText = new StyledText(container, SWT.WRAP);
		styledText.setText("According to the previous selected options, the occurence of the antipattern still characterizes an error; " +
			"there is at least two associations end-points on the mediations (at the relator's side) with upper cardinality greater than 1.\r\n\r\n" +
			"Now, is it possible for two distinct instances of "+repRel.getRelator().getName()+" to mediate the exact same instances of the mediated types at the same time, as many times as desired?");
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBounds(10, 10, 554, 101);
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 117, 90, 16);
		btnYes.setText("Yes");
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(106, 117, 90, 16);
		btnNo.setText("No");
		btnNo.addSelectionListener(listener);
	}

	@Override
	public IWizardPage getNextPage() 
	{		
		if(btnYes.getSelection()) return ((RepRelWizard)getWizard()).getFinishing();
		else if(btnNo.getSelection()) return ((RepRelWizard)getWizard()).getThirdPage();	

		return super.getNextPage();
	}
}
