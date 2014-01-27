package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Mediation;
import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class RepRelRefactoringPage extends RefactoringPage {
	
	protected RepRelOccurrence repRel;
		
	//GUI
	protected Composite content;
	public Button historicalCheck;
	public Button currentCheck;
	public ScrolledComposite scroll;
	public HashMap<Button,Spinner> mapping = new HashMap<Button,Spinner>();
	private Button btnAddLine;
	private RepRelTable rrtable;

	/**
	 * Create the wizard.
	 */
	public RepRelRefactoringPage(RepRelOccurrence repRel) 
	{
		super();	
		this.repRel = repRel;
				
		setTitle(RepRelAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public RepRelWizard getRepRelWizard(){
		return ( RepRelWizard)getWizard();
	}	

	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
	
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		container.setLayout(null);
		
		Label lblChooseTheAppropriate = new Label(container, SWT.NONE);
		lblChooseTheAppropriate.setBounds(11, 13, 552, 15);
		lblChooseTheAppropriate.setText("Choose the appropriate refactoring options:");
		
		scroll = new ScrolledComposite(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);		
		scroll.setLayout(new FillLayout());		
		scroll.setAlwaysShowScrollBars(false);
		
		content = new Composite(scroll, SWT.NONE);
		content.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		scroll.setBounds(11, 36, 552, 95);
		content.setLayout(new GridLayout(3, false));
		
		currentCheck = new Button(container, SWT.RADIO);
		currentCheck.setBounds(10, 222, 472, 15);
		currentCheck.setText("(Current Relator) "+repRel.getRelator().getName());
		
		historicalCheck = new Button(container, SWT.RADIO);
		historicalCheck.setBounds(11, 243, 471, 16);
		historicalCheck.setText("(Historical Relator) "+repRel.getRelator().getName());
		
		rrtable = new RepRelTable(container,SWT.BORDER, repRel.getMediations(),repRel.getRelator().getName());
		rrtable.getTable().setBounds(10, 147, 554, 64);
		
		btnAddLine = new Button(container, SWT.NONE);
		btnAddLine.setBounds(488, 217, 75, 25);
		btnAddLine.setText("Add Line");
				
		btnAddLine.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 rrtable.addLine();				 
			 }
		});	
		
		createOptions();
		
		container.redraw();
	}
	
	private void createOptions()	
	{			
		ArrayList<Mediation> list = repRel.getMediations();				
		for(Mediation m: list)
		{	
			RefOntoUML.Type source = m.getMemberEnd().get(0).getType();
			String upperSource = "*";
			if(m.getMemberEnd().get(0).getUpper() != -1) upperSource = Integer.toString(m.getMemberEnd().get(0).getUpper());
			String lowerSource = "*";
			if (m.getMemberEnd().get(0).getLower() != -1) lowerSource = Integer.toString(m.getMemberEnd().get(0).getLower());		
			RefOntoUML.Type target = m.getMemberEnd().get(1).getType();
						
			Label name = new Label(content, SWT.NONE);
			name.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));						
			name.setText(target.getName()+" -> ["+lowerSource+","+upperSource+"] "+source.getName());
			name.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			    
			final Button checkButton = new Button(content, SWT.CHECK);
			checkButton.setText("Change upper cardinality to ");
			checkButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			
			final Spinner spinner = new Spinner(content, SWT.BORDER);
			spinner.setSelection(1);
			
			SelectionAdapter listener = new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		        if(checkButton.getSelection()){
		        	spinner.setEnabled(true);
		        }
		        if(!checkButton.getSelection()){
		        	spinner.setEnabled(false);		        	
		        }
		      }
		    };
		    
			checkButton.addSelectionListener(listener);
			
			mapping.put(checkButton,spinner);			
		}
		
		scroll.setContent(content);
		scroll.setExpandHorizontal(true);
		scroll.setExpandVertical(true);
		scroll.setMinSize(content.computeSize(SWT.DEFAULT, SWT.DEFAULT));  
	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		//do the former group of actions (changing upper cardinalities)
		ArrayList<Integer> upperList = new ArrayList<Integer>();		
		((RepRelWizard)getWizard()).removeAllActions();		
		int i=0;
		for(Button b: mapping.keySet())
		{
			if(b.getSelection()){
				// Action =====================	
				RepRelAction newAction = new RepRelAction(repRel);
				newAction.setChangeUpperMult(repRel.getMediations().get(i), mapping.get(b).getSelection());
				getRepRelWizard().replaceAction(i,newAction);
				//=============================
				upperList.add(mapping.get(b).getSelection());
			}else{
				int upper = repRel.getMediations().get(i).getMemberEnd().get(0).getUpper();
				upperList.add(upper);
			}
			i++;
		}
		
		//verifies if, with the former options, we still characterize an anti-pattern...
		int greater = 0;
		for(int j=0; j<upperList.size();j++){
			if (upperList.get(j)==-1) greater++;
			else if (upperList.get(j)>1) greater++;
		}
		if(upperList.size()>0){
			if(greater>1) {
				// still an anti-pattern... so, do the final actions (creating OCL invariants)
				doFinalActions();
			}else{
				return ((RepRelWizard)getWizard()).getFinishing();	
			}
		}
				
		return ((RepRelWizard)getWizard()).getFinishing();	
	}
	
	private void doFinalActions()
	{
		if(historicalCheck.getSelection()){	
			ArrayList<ArrayList<Mediation>> mMatrix = rrtable.getSelections();
			ArrayList<Integer> ns = rrtable.getNs();			
			// Action =====================	
			RepRelAction newAction = new RepRelAction(repRel);
			newAction.setCreateInvariantWithQualities(mMatrix,ns);
			getRepRelWizard().replaceAction(repRel.getMediations().size(),newAction);	
			//=============================
		}
		if(currentCheck.getSelection()){
			ArrayList<ArrayList<Mediation>> mMatrix = rrtable.getSelections();
			ArrayList<Integer> ns = rrtable.getNs();
			// Action =====================	
			RepRelAction newAction = new RepRelAction(repRel);
			newAction.setCreateInvariant(mMatrix, ns);
			getRepRelWizard().replaceAction(repRel.getMediations().size(), newAction);
			//=============================
		}
	}
}
