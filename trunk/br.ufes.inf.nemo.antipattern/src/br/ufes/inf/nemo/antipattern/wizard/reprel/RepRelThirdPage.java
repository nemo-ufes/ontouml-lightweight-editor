package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Mediation;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;

public class RepRelThirdPage extends RepRelPage {

	public RepRelTable rrtable;
	public Button btnCurrent;
	public Button btnHistorical;
	
	public RepRelThirdPage(RepRelOccurrence rr) 
	{
		super(rr);
		setDescription("Page 3");
	}

	@Override
	public void createControl(Composite parent) {
	
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		rrtable = new RepRelTable (container,SWT.BORDER, repRel.getMediations(),repRel.getRelator().getName());
		rrtable.getTable().setBounds(10, 96, 554, 99);
				
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setText("Since you marked that it is not possible for two relators mediate the same instances at the same time as many times as desired...\r\n\r\nThere must be a limit for the maximum number of instances of these \"repeated\" relators. Provide the limit N of instances of the Relator for each pair of the mediated types that you judge necessary.");
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBounds(10, 10, 554, 80);
		
		Button btnAddLine = new Button(container, SWT.NONE);
		btnAddLine.setBounds(489, 201, 75, 25);
		btnAddLine.setText("Add Line");
		
		Label lblDoesIsIntended = new Label(container, SWT.NONE);
		lblDoesIsIntended.setBounds(10, 233, 554, 15);
		lblDoesIsIntended.setText("Does "+repRel.getRelator().getName()+" is intended with current or historical semantics?");
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        if (isPageComplete()==false) setPageComplete(true);
	      }
	    };
			    
		setPageComplete(false);
			
		btnCurrent = new Button(container, SWT.RADIO);
		btnCurrent.setBounds(10, 254, 90, 16);
		btnCurrent.setText("Current");
		btnCurrent.addSelectionListener(listener);
		
		btnHistorical = new Button(container, SWT.RADIO);
		btnHistorical.setBounds(106, 254, 90, 16);
		btnHistorical.setText("Historical");
		btnHistorical.addSelectionListener(listener);
		
		btnAddLine.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 rrtable.addLine();				 
			 }
		});				
	}
	
	@Override
	public IWizardPage getNextPage() 
	{		
		if(btnCurrent.getSelection()){
			ArrayList<ArrayList<Mediation>> mMatrix = rrtable.getSelections();
			ArrayList<Integer> ns = rrtable.getNs();
			// Action =====================	
			RepRelAction newAction = new RepRelAction(repRel);
			newAction.setCreateInvariant(mMatrix, ns);
			getRepRelWizard().replaceAction(repRel.getMediations().size(), newAction);
			//=============================
		}
		if(btnHistorical.getSelection()){			
			ArrayList<ArrayList<Mediation>> mMatrix = rrtable.getSelections();
			ArrayList<Integer> ns = rrtable.getNs();			
			// Action =====================	
			RepRelAction newAction = new RepRelAction(repRel);
			newAction.setCreateInvariantWithQualities(mMatrix,ns);
			getRepRelWizard().replaceAction(repRel.getMediations().size(),newAction);	
			//=============================
		}
		if(btnHistorical.getSelection() || btnCurrent.getSelection()) return ((RepRelWizard)getWizard()).getFinishing();
		
		return super.getNextPage();
	}
}
