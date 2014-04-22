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
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

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
		btnSourceDependsOnTarget.setText("'x' is connected to at least <n> instances of '' to which 'y' is connected through ''");		
		btnSourceDependsOnTarget.addSelectionListener(listener);
		
		btnTargetDependsOnSource = new Button(container, SWT.RADIO);
		btnTargetDependsOnSource.setText("'y' is connected to at least <m> instances of '' to which 'x' is connected through ''");
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
		btnBoth.addSelectionListener(listener);
		
		btnNone = new Button(container, SWT.RADIO);
		btnNone.addSelectionListener(listener);
		btnNone.setText("None of the options are necessities");
		
		Label lblForN = new Label(container, SWT.NONE);
		lblForN.setText("for n = ");
		
		Label lblForM = new Label(container, SWT.NONE);
		lblForM.setText("for m = ");
		
		lblForNBoth = new Label(container, SWT.NONE);
		lblForNBoth.setText("for n = ");
		
		
		lblForMBoth = new Label(container, SWT.NONE);
		lblForMBoth.setText("and m = ");
		
		spinnerN = new Spinner(container, SWT.BORDER);
		setSpinnerDefaultProperties(spinnerN);
		
		spinnerM = new Spinner(container, SWT.BORDER);
		setSpinnerDefaultProperties(spinnerM);
		
		spinnerNBoth = new Spinner(container, SWT.BORDER);
		setSpinnerDefaultProperties(spinnerNBoth);
		
		spinnerMBoth = new Spinner(container, SWT.BORDER);
		setSpinnerDefaultProperties(spinnerMBoth);
		
		lblNote = new Label(container, SWT.WRAP | SWT.RIGHT);
		lblNote.setText("Note that if '"+getRelComp().getParser().getStringRepresentation(getRelComp().getA2())+"' is a symmetric relation and that is enforced in the model, the effects of the first three options are the same.");
		lblNote.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNote.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(lblQuestion, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
						.add(btnSourceDependsOnTarget, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
						.add(gl_container.createSequentialGroup()
							.add(lblForN)
							.add(6)
							.add(spinnerN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.add(btnTargetDependsOnSource, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
						.add(gl_container.createSequentialGroup()
							.add(lblForM, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.add(6)
							.add(spinnerM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.add(btnBoth, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
						.add(gl_container.createSequentialGroup()
							.add(lblForNBoth)
							.add(6)
							.add(spinnerNBoth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.add(6)
							.add(lblForMBoth, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.add(6)
							.add(spinnerMBoth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.add(btnNone, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
						.add(GroupLayout.TRAILING, lblNote, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblQuestion, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnSourceDependsOnTarget)
					.add(6)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(lblForN)
						.add(spinnerN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(5)
					.add(btnTargetDependsOnSource)
					.add(5)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(1)
							.add(lblForM))
						.add(spinnerM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(6)
					.add(btnBoth)
					.add(5)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(1)
							.add(lblForNBoth))
						.add(gl_container.createSequentialGroup()
							.add(1)
							.add(spinnerNBoth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.add(gl_container.createSequentialGroup()
							.add(1)
							.add(lblForMBoth))
						.add(spinnerMBoth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(5)
					.add(btnNone)
					.add(39)
					.add(lblNote, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.add(10))
		);
		container.setLayout(gl_container);
		
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
