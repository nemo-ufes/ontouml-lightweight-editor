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
		String str = "Maximum number of instances of "+repRel.getRelator().getName()+" with the same instances of ";
		for(Type t: rigids) { str+= t.getName()+" "; }
		str += "the mediated types at the same time:";
		styledText.setText(str);
		styledText.redraw();
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
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setBounds(10, 76, 554, 15);
		lblNewLabel_1.setText("Which types may be combined a limited number of times? How many times?");
		
		styledText = new StyledText(container, SWT.WRAP);
		styledText.setText("Maximum number of instances of "+repRel.getRelator().getName()+" with the same instances of the mediated types at the same time:");
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBounds(10, 97, 554, 45);
		
		spinner = new Spinner(container, SWT.BORDER);
		spinner.setBounds(10, 148, 47, 22);
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
