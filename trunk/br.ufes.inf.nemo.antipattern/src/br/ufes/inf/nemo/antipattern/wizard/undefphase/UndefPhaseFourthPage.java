package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.ParsingElement;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class UndefPhaseFourthPage  extends UndefPhasePage{
	
	Composite parent;
	private Label lblPhasesCanAlso;
	private Button btnNo;
	private Button btnYes;
	
	/**
	 * Create the wizard.
	 */
	public UndefPhaseFourthPage(UndefPhaseOccurrence up) 
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
		
		lblPhasesCanAlso = new Label(container, SWT.WRAP);
		lblPhasesCanAlso.setBounds(10, 10, 554, 62);
		lblPhasesCanAlso.setText("Phases can also be defined by the appearance of modes. For example, a kind Person may own a partition containing the Sick and Healthy phases. A Sick Person is one that has a mode Disease. Is that is the case?");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 78, 554, 16);
		btnNo.setText("No");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 102, 554, 16);
		btnYes.setText("Yes");
	}
}
