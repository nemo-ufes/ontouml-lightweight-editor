package br.ufes.inf.nemo.antipattern.wizard.relcomp;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.relcomp.RelCompOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelCompSecondPage extends RelCompPage {

	//GUI
	public Button btnSourceDependsOnTarget;
	public Button btnTargetDependsOnSource;
	public Button btnBoth;
	public Button btnNone;
	private Label lblForNBoth;
	private Label lblForMBoth;
	private Spinner spinnerN;
	private Spinner spinnerM;
	private Spinner spinnerNBoth;
	private Spinner spinnerMBoth;
	private Label lblNote;
	
	/**
	 * Create the wizard.
	 */
	public RelCompSecondPage(RelCompOccurrence relSpec) 
	{
		super(relSpec);
		setDescription("Associations: "+relComp.getParser().getStringRepresentation(relComp.getA1())+" and "+relComp.getParser().getStringRepresentation(relComp.getA2()));
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		Label lblQuestion = new Label(container, SWT.WRAP);
		lblQuestion.setText("Since none of the previous rules were necessary. Now, let's evaluate other constraint possibilities.\r\n\r\n" +
							"If an instance 'x' of '"+getRelComp().getA2Source().getName()+"' is connected to an instance 'y' of '"+getRelComp().getA2Target().getName()+
							"', through '"+getRelComp().getParser().getStringRepresentation(getRelComp().getA2())+"', is it NECESSARY that:");
		lblQuestion.setBounds(10, 10, 699, 64);
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        
	    	  if (isPageComplete()==false) setPageComplete(true);
	        
	    	  if(btnSourceDependsOnTarget.getSelection())
	    		  spinnerN.setEnabled(true);
	    	  else
	    		  spinnerN.setEnabled(false);
	    	  
	    	  if(btnTargetDependsOnSource.getSelection())
	    		  spinnerM.setEnabled(true);
	    	  else
	    		  spinnerM.setEnabled(false);
	    	  
	    	  if(btnBoth.getSelection()){
	    		  spinnerMBoth.setEnabled(true);
	    		  spinnerNBoth.setEnabled(true);
	    	  }
	    	  else{
	    		  spinnerMBoth.setEnabled(false);
	    		  spinnerNBoth.setEnabled(false);
	    	  }  
	      }
	    };
		    
	    setPageComplete(false);

		btnSourceDependsOnTarget = new Button(container, SWT.RADIO);
		btnSourceDependsOnTarget.setBounds(10, 80, 699, 16);
		btnSourceDependsOnTarget.setText("'x' is connected to at least <n> instances of '' to which 'y' is connected through ''");		
		btnSourceDependsOnTarget.addSelectionListener(listener);
		
		btnTargetDependsOnSource = new Button(container, SWT.RADIO);
		btnTargetDependsOnSource.setText("'y' is connected to at least <m> instances of '' to which 'x' is connected through ''");
		btnTargetDependsOnSource.setBounds(10, 129, 699, 16);		
		btnTargetDependsOnSource.addSelectionListener(listener);
		
		if(getRelComp().a2EndsSpecializeA1Target()){
			btnSourceDependsOnTarget.setText("'x' is connected to at least <n> instances of '"+getRelComp().getA1Source().getName()+"' to which 'y' is connected through '"+
											 getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");	
			btnTargetDependsOnSource.setText("'y' is connected to at least <m> instances of '"+getRelComp().getA1Source().getName()+"' to which 'x' is connected through '"+
											 getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");	
		}
		else{
			btnSourceDependsOnTarget.setText("'x' is connected to at least <n> instances of '"+getRelComp().getA1Target().getName()+"' to which 'y' is connected through '"+
					 getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");
			btnTargetDependsOnSource.setText("'y' is connected to at least <m> instances of '"+getRelComp().getA1Target().getName()+"' to which 'x' is connected through '"+
					 getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");	
		}
		
		
		
		btnBoth = new Button(container, SWT.RADIO);
		btnBoth.setText("First two options are true for <n> and <m>");
		btnBoth.setBounds(10, 178, 699, 16);
		btnBoth.addSelectionListener(listener);
		
		btnNone = new Button(container, SWT.RADIO);
		btnNone.addSelectionListener(listener);
		btnNone.setText("None of the options are necessities");
		btnNone.setBounds(10, 227, 699, 16);
		
		Label lblForN = new Label(container, SWT.NONE);
		lblForN.setBounds(10, 102, 39, 15);
		lblForN.setText("for n = ");
		
		Label lblForM = new Label(container, SWT.NONE);
		lblForM.setText("for m = ");
		lblForM.setBounds(10, 151, 39, 15);
		
		lblForNBoth = new Label(container, SWT.NONE);
		lblForNBoth.setText("for n = ");
		lblForNBoth.setBounds(10, 200, 39, 15);
		
		
		lblForMBoth = new Label(container, SWT.NONE);
		lblForMBoth.setText("and m = ");
		lblForMBoth.setBounds(108, 200, 43, 15);
		
		spinnerN = new Spinner(container, SWT.BORDER);
		spinnerN.setBounds(55, 102, 47, 22);
		setSpinnerDefaultProperties(spinnerN);
		
		spinnerM = new Spinner(container, SWT.BORDER);
		spinnerM.setBounds(55, 150, 47, 22);
		setSpinnerDefaultProperties(spinnerM);
		
		spinnerNBoth = new Spinner(container, SWT.BORDER);
		spinnerNBoth.setBounds(55, 200, 47, 22);
		setSpinnerDefaultProperties(spinnerNBoth);
		
		spinnerMBoth = new Spinner(container, SWT.BORDER);
		spinnerMBoth.setBounds(157, 199, 47, 22);
		setSpinnerDefaultProperties(spinnerMBoth);
		
		lblNote = new Label(container, SWT.WRAP | SWT.RIGHT);
		lblNote.setText("Note that if '"+getRelComp().getParser().getStringRepresentation(getRelComp().getA2())+"' is a symmetric relation and that is enforced in the model, the effects of the first three options are the same.");
		lblNote.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNote.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		lblNote.setBounds(10, 282, 699, 33);
		
	}
	
	private void setSpinnerDefaultProperties(Spinner spin){
		spin.setSelection(1);
		spin.setMinimum(1);
		spin.setEnabled(false);
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		RelCompAction action;
		getRelCompWizard().removeAllActions();
		
		if(btnNone.getSelection()){
			return getRelCompWizard().getThirdPage();
		}
		
		if(btnSourceDependsOnTarget.getSelection() || btnBoth.getSelection()){
			action = new RelCompAction(relComp);
			
			if(btnBoth.getSelection())
				action.setAtLeastSource(spinnerNBoth.getSelection());
			else
				action.setAtLeastSource(spinnerN.getSelection());
			
			getRelCompWizard().addAction(0, action);
		}
		
		if(btnTargetDependsOnSource.getSelection()|| btnBoth.getSelection()){
			action = new RelCompAction(relComp);
			
			if(btnBoth.getSelection())
				action.setAtLeastTarget(spinnerMBoth.getSelection());
			else 
				action.setAtLeastTarget(spinnerM.getSelection());
			getRelCompWizard().addAction(0, action);
		}
				
		return getRelCompWizard().getFinishing();
					
	}
}
