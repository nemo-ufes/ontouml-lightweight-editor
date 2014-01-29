package br.ufes.inf.nemo.antipattern.wizard.relcomp;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.relcomp.RelCompOccurrence;
import org.eclipse.swt.widgets.Spinner;

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
	private Label lblNote;
	private Spinner spinner;
	private Spinner spinner_1;
	private Spinner spinner_2;
	private Spinner spinner_3;
	
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
		lblQuestion.setText(	"None of the previous rules were necessary. Now, let's evaluate other constraint possibilities.\r\n\r\n" +
							"If an instance 'x' of '"+getRelComp().getA2Source().getName()+"' is connected to an instance 'y' of '"+getRelComp().getA2Target().getName()+
							"', through '"+getRelComp().getParser().getStringRepresentation(getRelComp().getA2())+"', is it NECESSARY that:");
		lblQuestion.setBounds(10, 10, 554, 64);
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        if (isPageComplete()==false) setPageComplete(true);
	      }
	    };
		    
	    setPageComplete(false);

		btnSourceDependsOnTarget = new Button(container, SWT.RADIO);
		btnSourceDependsOnTarget.setBounds(10, 80, 554, 16);
		btnSourceDependsOnTarget.setText("'x' be connected to at least <n> 'w' (instances of '') that 'y' is connected through ''");		
		btnSourceDependsOnTarget.addSelectionListener(listener);
		
		btnTargetDependsOnSource = new Button(container, SWT.RADIO);
		btnTargetDependsOnSource.setText("'y' be connected to at least <m> 'w' (instances of '') that 'x' is connected through ''");
		btnTargetDependsOnSource.setBounds(10, 129, 554, 16);		
		btnTargetDependsOnSource.addSelectionListener(listener);
		
		if(getRelComp().a2EndsSpecializeA1Target()){
			btnSourceDependsOnTarget.setText("'x' be connected to at least <n> 'w' (instances of '"+getRelComp().getA1Source().getName()+"') that 'y' is connected through '"+
											 getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");	
			btnTargetDependsOnSource.setText("'y' be connected to at least <m> 'w' (instances of '"+getRelComp().getA1Source().getName()+"') that 'x' is connected through '"+
											 getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");	
		}
		else{
			btnSourceDependsOnTarget.setText("'x' be connected to at least <n> 'w' (instances of '"+getRelComp().getA1Target().getName()+"') that 'y' is connected through '"+
					 getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");
			btnTargetDependsOnSource.setText("'y' be connected to at least <m> 'w' (instances of '"+getRelComp().getA1Target().getName()+"') that 'x' is connected through '"+
					 getRelComp().getParser().getStringRepresentation(getRelComp().getA1())+"'");	
		}
		
		
		
		btnBoth = new Button(container, SWT.RADIO);
		btnBoth.setText("First two options are true for <n> and <m>");
		btnBoth.setBounds(10, 178, 554, 16);
		
		Button btnNone = new Button(container, SWT.RADIO);
		btnNone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnNone.setText("None of the options are necessities");
		btnNone.setBounds(10, 227, 554, 16);
		
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
		
		lblNote = new Label(container, SWT.WRAP | SWT.RIGHT);
		lblNote.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNote.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		lblNote.setText("Note that if '"+getRelComp().getParser().getStringRepresentation(getRelComp().getA2())+"' is a symmetric relation and that is enforced in the model, the effects of the first three options are the same.");
		lblNote.setBounds(10, 262, 554, 32);
		
		spinner = new Spinner(container, SWT.BORDER);
		spinner.setBounds(55, 102, 47, 22);
		
		spinner_1 = new Spinner(container, SWT.BORDER);
		spinner_1.setBounds(55, 150, 47, 22);
		
		spinner_2 = new Spinner(container, SWT.BORDER);
		spinner_2.setBounds(55, 200, 47, 22);
		
		spinner_3 = new Spinner(container, SWT.BORDER);
		spinner_3.setBounds(157, 199, 47, 22);
		btnBoth.addSelectionListener(listener);
	}
	
	@Override
	public IWizardPage getNextPage() {
		return super.getNextPage();
	}
}
