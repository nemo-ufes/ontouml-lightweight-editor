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
import org.eclipse.swt.events.SelectionListener;
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
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import org.eclipse.swt.widgets.Control;

public class RepRelRefactoringPage extends RefactoringPage {
	
	protected RepRelOccurrence repRel;
		
	//GUI
	protected Composite content;
	public Button historicalButton;
	public Button currentButton;
	public ScrolledComposite scroll;
	public HashMap<Button,Spinner> mapping = new HashMap<Button,Spinner>();
	private Button addButton;
	private RepRelTable rrtable;
	private Button removeButton;
	private Button resetButton;

	private SelectionListener verifyAction = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			setPageComplete();
		 }
	};
	
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
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		Label lblChooseTheAppropriate = new Label(container, SWT.NONE);
		lblChooseTheAppropriate.setText("Choose the appropriate refactoring options:");
		
		scroll = new ScrolledComposite(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);		
		scroll.setLayout(new FillLayout());		
		scroll.setAlwaysShowScrollBars(false);
		
		content = new Composite(scroll, SWT.NONE);
		content.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		content.setLayout(new GridLayout(3, false));
		
		currentButton = new Button(container, SWT.RADIO);
		currentButton.setText("(Current Relator) "+repRel.getRelator().getName());
		
		historicalButton = new Button(container, SWT.RADIO);
		historicalButton.setText("(Historical Relator) "+repRel.getRelator().getName());
	
		rrtable = new RepRelTable(container,SWT.BORDER, repRel.getProblematicMediations(),verifyAction);
		
		addButton = new Button(container, SWT.NONE);
		addButton.setText("Add");
		
		removeButton = new Button(container, SWT.NONE);
		removeButton.setText("Remove");
		
		resetButton = new Button(container, SWT.NONE);
		resetButton.setText("Reset");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(11)
							.add(scroll, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
						.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
							.addContainerGap()
							.add(addButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(removeButton)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(resetButton)
							.addPreferredGap(LayoutStyle.RELATED))
						.add(gl_container.createSequentialGroup()
							.addContainerGap()
							.add(historicalButton, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.RELATED))
						.add(gl_container.createSequentialGroup()
							.addContainerGap()
							.add(currentButton, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.RELATED)))
					.add(11))
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.addContainerGap()
					.add(rrtable, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblChooseTheAppropriate, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblChooseTheAppropriate)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(scroll, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
						.add(resetButton)
						.add(removeButton)
						.add(addButton))
					.add(4)
					.add(rrtable, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
					.add(13)
					.add(currentButton, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(historicalButton)
					.add(1))
		);
		gl_container.linkSize(new Control[] {addButton, removeButton, resetButton}, GroupLayout.HORIZONTAL);
		container.setLayout(gl_container);
				
		addButton.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 rrtable.addLine();	
				 setPageComplete();
			 }
		});
		
		removeButton.addSelectionListener(new SelectionAdapter() {
			 @Override
	         public void widgetSelected(SelectionEvent e) {
				 rrtable.removeLine(rrtable.getSelectionIndex(), true);			
				 setPageComplete();
			 }
		});	
		
		resetButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rrtable.removeLines();
				rrtable.addLine();
				setPageComplete();
			 }
		});	
		
		historicalButton.addSelectionListener(verifyAction);
		currentButton.addSelectionListener(verifyAction);
		
		createOptions();
		container.redraw();
	}
	
	//TODO: Can go to next page listener
	public void setPageComplete(){
		setPageComplete(true);
	}
	
	private void createOptions()	
	{			
		ArrayList<Mediation> list = repRel.getProblematicMediations();				
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
				newAction.setChangeUpperMult(repRel.getProblematicMediations().get(i), mapping.get(b).getSelection());
				getRepRelWizard().replaceAction(i,newAction);
				//=============================
				upperList.add(mapping.get(b).getSelection());
			}else{
				int upper = repRel.getProblematicMediations().get(i).getMemberEnd().get(0).getUpper();
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
		if(historicalButton.getSelection()){	
			ArrayList<ArrayList<Mediation>> mMatrix = rrtable.getSelections();
			ArrayList<Integer> ns = rrtable.getNs();			
			// Action =====================	
			RepRelAction newAction = new RepRelAction(repRel);
			newAction.setCreateInvariantWithQualities(mMatrix,ns);
			getRepRelWizard().replaceAction(repRel.getProblematicMediations().size(),newAction);	
			//=============================
		}
		if(currentButton.getSelection()){
			ArrayList<ArrayList<Mediation>> mMatrix = rrtable.getSelections();
			ArrayList<Integer> ns = rrtable.getNs();
			// Action =====================	
			RepRelAction newAction = new RepRelAction(repRel);
			newAction.setCreateInvariant(mMatrix, ns);
			getRepRelWizard().replaceAction(repRel.getProblematicMediations().size(), newAction);
			//=============================
		}
	}
}
