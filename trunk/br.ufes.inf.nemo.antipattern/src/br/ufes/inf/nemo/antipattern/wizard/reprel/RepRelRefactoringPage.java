package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
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
	public Spinner currentSpinner;
	public Spinner historicalSpinner;
	public Button historicalCheck;
	public Button currentCheck;
	public ScrolledComposite scroll;
	public HashMap<Button,Spinner> mapping = new HashMap<Button,Spinner>();
	
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
		
		scroll = new ScrolledComposite(container, SWT.NONE | SWT.V_SCROLL | SWT.H_SCROLL);		
		scroll.setLayout(new FillLayout());		
		scroll.setAlwaysShowScrollBars(false);
		
		content = new Composite(scroll, SWT.NONE);
		scroll.setBounds(11, 36, 552, 130);
		content.setLayout(new GridLayout(4, false));
		
		currentCheck = new Button(container, SWT.RADIO);
		currentCheck.setBounds(11, 172, 552, 15);
		currentCheck.setText("(Current Relator) "+repRel.getRelator().getName());
		
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText("Limit the number of relators which can exist simultaneously mediating the same instances");
		styledText.setBounds(64, 193, 499, 19);
		
		historicalCheck = new Button(container, SWT.RADIO);
		historicalCheck.setBounds(11, 221, 552, 16);
		historicalCheck.setText("(Historical Relator) "+repRel.getRelator().getName());
		
		StyledText styledText_1 = new StyledText(container, SWT.WRAP);
		styledText_1.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText_1.setText("Limit the number of relators which are concurent and mediate the same instances");
		styledText_1.setBounds(64, 243, 499, 19);
				
		createOptions();
		
		currentSpinner = new Spinner(container, SWT.BORDER);
		currentSpinner.setBounds(11, 193, 47, 22);
		currentSpinner.setSelection(1);
		
		historicalSpinner = new Spinner(container, SWT.BORDER);
		historicalSpinner.setBounds(11, 243, 47, 22);
		historicalSpinner.setSelection(1);
		
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
			
			new Label(content, SWT.NONE);
			    
			final Button checkButton = new Button(content, SWT.CHECK);
			checkButton.setText("Change upper cardinality: ");
			
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
	public IWizardPage getNextPage() {
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
			}			
			i++;
		}	
		if(historicalCheck.getSelection()){
			// Action =====================	
			RepRelAction newAction = new RepRelAction(repRel);
			newAction.setCreateInvariantWithQualities(repRel.getMediations(), historicalSpinner.getSelection());
			getRepRelWizard().replaceAction(repRel.getMediations().size(),newAction);
			//=============================
		}
		if(currentCheck.getSelection()){
			// Action =====================	
			RepRelAction newAction = new RepRelAction(repRel);
			newAction.setCreateInvariant(repRel.getMediations(), currentSpinner.getSelection());
			getRepRelWizard().replaceAction(repRel.getMediations().size(),newAction);
			//=============================			
		}
		
		return ((RepRelWizard)getWizard()).getFinishing();	
	}
}
