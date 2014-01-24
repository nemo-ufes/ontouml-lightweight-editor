package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;

public class RepRelSecondPage extends RepRelPage {
	
	public StyledText styledText;
	public Button btnCurrent;
	public Button btnHistorical;
	public Spinner spinner;
	
	public RepRelSecondPage(RepRelOccurrence rr) {
		super(rr);
	}

	public void setRigids(ArrayList<Type> rigids)
	{		
		styledText.setText("Now, since you said that a relator could be connected with mediated types at the same time but not as many as desired... " +
			"\r\n\r\nChoose the maximum number of instances of "+repRel.getRelator().getName()+" connected with the same instances at the same time:");
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		
		setPageComplete(true);
		
		setControl(container);	
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 554, 15);
		lblNewLabel.setText("Does "+repRel.getRelator().getName()+" is intended with current or historical semantic?");
		
		btnCurrent = new Button(container, SWT.RADIO);
		btnCurrent.setBounds(10, 31, 90, 16);
		btnCurrent.setText("Current");
		
		btnHistorical = new Button(container, SWT.RADIO);
		btnHistorical.setBounds(106, 31, 90, 16);
		btnHistorical.setText("Historical");
		
		styledText = new StyledText(container, SWT.WRAP);
		styledText.setText("Now, since you said that a relator could be connected with mediated types at the same time but not as many as desired... \r\n\r\nChoose the maximum number that the instances of <dynamic> could have being connected with the same instances at the same time:");
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBounds(10, 74, 554, 80);
		
		spinner = new Spinner(container, SWT.BORDER);
		spinner.setBounds(10, 160, 47, 22);
	}

	public int getValue()
	{
		return spinner.getSelection();
	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		if(btnCurrent.getSelection()){
			// Action =====================	
			RepRelAction newAction = new RepRelAction(repRel);
			newAction.setCreateInvariant(repRel.getMediations(), getValue());
			getRepRelWizard().addAction(repRel.getMediations().size(),newAction);	
			//=============================
		}
		if(btnHistorical.getSelection()){
			// Action =====================	
			RepRelAction newAction = new RepRelAction(repRel);
			newAction.setCreateInvariantWithQualities(repRel.getMediations(),getValue());
			getRepRelWizard().addAction(repRel.getMediations().size(),newAction);	
			//=============================			
		}
		if(btnCurrent.getSelection() || btnHistorical.getSelection())
			return ((RepRelWizard)getWizard()).getFinishing();
		return super.getNextPage();
	}
}
