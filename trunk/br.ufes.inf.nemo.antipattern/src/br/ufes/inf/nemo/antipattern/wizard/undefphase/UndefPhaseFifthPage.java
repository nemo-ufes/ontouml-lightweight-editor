package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.ParsingElement;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class UndefPhaseFifthPage extends UndefPhasePage{
	
	Composite parent;
	private Button btnNo;
	private Button btnYes;
	private Label lblIfTheTypes;
	
	/**
	 * Create the wizard.
	 */
	public UndefPhaseFifthPage(UndefPhaseOccurrence up) 
	{
		super(up);
		setDescription((new ParsingElement(up.getPartition(),true,"")).toString());
	}
	
	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		lblIfTheTypes = new Label(container, SWT.WRAP);
		lblIfTheTypes.setBounds(10, 10, 554, 50);
		lblIfTheTypes.setText("If the types in the partition are not defined by changes in quality values and also not defined by mode appearances, they might not be phases. Would you like to change their stereotypes?");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 66, 554, 16);
		btnNo.setText("No, keep the phases undefined [Not Recommended]");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 92, 554, 16);
		btnYes.setText("Yes");
	}
}
