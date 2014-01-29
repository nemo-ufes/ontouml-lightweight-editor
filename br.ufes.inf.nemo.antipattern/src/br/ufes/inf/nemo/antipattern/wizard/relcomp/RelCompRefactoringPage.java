package br.ufes.inf.nemo.antipattern.wizard.relcomp;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.relcomp.RelCompAntipattern;
import br.ufes.inf.nemo.antipattern.relcomp.RelCompOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Spinner;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelCompRefactoringPage extends RefactoringPage {
	
	protected RelCompOccurrence relComp;
	
	
	/**
	 * Create the wizard.
	 */
	public RelCompRefactoringPage(RelCompOccurrence relComp) 
	{
		super();	
		this.relComp = relComp;
				
		setTitle(RelCompAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public RelCompWizard getRelSpecWizard(){
		return (RelCompWizard)getWizard();
	}
		
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		Label lblChooseTheAppropriate = new Label(container, SWT.NONE);
		lblChooseTheAppropriate.setText("Choose the appropriate refactoring option by answering the following question:\r\n\r\nIf an instance 'x' of '<dynamic>' is connected to an instance 'y' of '<dynamic>', through '<dynamic>', is it NECESSARY that:");
		lblChooseTheAppropriate.setBounds(10, 10, 694, 65);
		
		Label label = new Label(container, SWT.NONE);
		label.setText("for n = ");
		label.setBounds(10, 108, 39, 15);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("for m = ");
		label_1.setBounds(10, 173, 39, 15);
		
		Button btnx = new Button(container, SWT.CHECK);
		btnx.setBounds(10, 81, 112, 21);
		btnx.setText("'x' is connected to ");
		
		CCombo combo = new CCombo(container, SWT.BORDER);
		combo.setItems(new String[] {"all", "at least <n>", "none"});
		combo.setBounds(128, 81, 94, 21);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(228, 84, 476, 21);
		lblNewLabel.setText("instances of '' that 'y' is connected to, through ''");
		
		Button btnyIsConnected = new Button(container, SWT.CHECK);
		btnyIsConnected.setText("'y' is connected to ");
		btnyIsConnected.setBounds(10, 146, 112, 21);
		
		CCombo combo_1 = new CCombo(container, SWT.BORDER);
		combo_1.setItems(new String[] {"all", "at least <n>", "none"});
		combo_1.setBounds(128, 146, 94, 21);
		
		Label lblInstancesOf = new Label(container, SWT.NONE);
		lblInstancesOf.setText("instances of '' that 'x' is connected to, through ''");
		lblInstancesOf.setBounds(228, 149, 476, 21);
		
		Spinner spinner = new Spinner(container, SWT.BORDER);
		spinner.setBounds(55, 170, 47, 22);
		
		Spinner spinner_1 = new Spinner(container, SWT.BORDER);
		spinner_1.setBounds(55, 108, 47, 22);
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        if (isPageComplete()==false) setPageComplete(true);
	      }
	    };
		    
	    setPageComplete(false);
	    
		
	}



	@Override
	public IWizardPage getNextPage() {
		return 	getRelSpecWizard().getFinishing();				
	}
}
