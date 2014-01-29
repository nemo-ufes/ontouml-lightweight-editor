package br.ufes.inf.nemo.antipattern.wizard.relcomp;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.relcomp.RelCompOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelCompThirdPage extends RelCompPage {

	//GUI
	public Button btnSourceDependsOnTarget;
	public Button btnTargetDependsOnSource;
	public Button btnBoth;
	public Button btnNone;
	
	/**
	 * Create the wizard.
	 */
	public RelCompThirdPage(RelCompOccurrence relSpec) 
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
		lblQuestion.setText("To complete the analysis, let's evaluate the remainder constraint possibilities.\r\n\r\n" +
							"If an instance 'x' of '"+getRelComp().getA2Source().getName()+"' is connected to an instance 'y' of '"+getRelComp().getA2Target().getName()+
							"', through '"+getRelComp().getParser().getStringRepresentation(getRelComp().getA2())+"', is it FORBIDDEN that:");
		lblQuestion.setBounds(10, 10, 554, 66);
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        if (isPageComplete()==false) setPageComplete(true);
	      }
	    };
		    
	    setPageComplete(false);

	    btnSourceDependsOnTarget = new Button(container, SWT.RADIO);
		btnSourceDependsOnTarget.setBounds(10, 82, 554, 16);
		btnSourceDependsOnTarget.addSelectionListener(listener);
		btnSourceDependsOnTarget.setText("'x' be connected to any 'w' (instances of '') that 'y' is connected through ''");	
		
		btnTargetDependsOnSource = new Button(container, SWT.RADIO);
		btnTargetDependsOnSource.setBounds(10, 104, 554, 16);
		btnTargetDependsOnSource.addSelectionListener(listener);
		btnTargetDependsOnSource.setText("'y' be connected to any 'w' (instances of '') that 'x' is connected through ''");	
		
		if(getRelComp().a2EndsSpecializeA1Target()){
			btnSourceDependsOnTarget.setText("'x' be connected to any 'w' (instances of '"+getRelComp().getA1Source().getName()+"') that 'y' is connected through '"+
											 getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");	
			btnTargetDependsOnSource.setText("'y' be connected to any 'w' (instances of '"+getRelComp().getA1Source().getName()+"') that 'x' is connected through '"+
											 getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");	
		}
		else{
			btnSourceDependsOnTarget.setText("'x' be connected to any 'w' (instances of '"+getRelComp().getA1Target().getName()+"') that 'y' is connected through '"+
					 getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");
			btnTargetDependsOnSource.setText("'y' be connected to any 'w' (instances of '"+getRelComp().getA1Target().getName()+"') that 'x' is connected through '"+
					 getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");	
		}
		
		btnBoth = new Button(container, SWT.RADIO);
		btnBoth.setText("First two options are forbidden");
		btnBoth.setBounds(10, 126, 554, 16);
		
		Button btnNone = new Button(container, SWT.RADIO);
		btnNone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnNone.setText("None of the options are forbidden");
		btnNone.setBounds(10, 148, 554, 16);
		
		Label lblNote = new Label(container, SWT.WRAP | SWT.RIGHT);
		lblNote.setText("Note that if '"+getRelComp().getParser().getStringRepresentation(getRelComp().getA2())+"' is a symmetric relation and that is enforced in the model, the effects of the first three options are the same.");
		lblNote.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNote.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		lblNote.setBounds(10, 239, 554, 32);
		btnBoth.addSelectionListener(listener);
	}
	
	@Override
	public IWizardPage getNextPage() {
		return super.getNextPage();
	}
}
