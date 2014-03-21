package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.ParsingElement;

public class UndefPhaseThirdPage extends UndefPhasePage{
	
	Composite parent;
	private Label lblForEachPhase;

	/**
	 * Create the wizard.
	 */
	public UndefPhaseThirdPage(UndefPhaseOccurrence up) 
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
		
		lblForEachPhase = new Label(container, SWT.NONE);
		lblForEachPhase.setBounds(10, 10, 554, 15);
		lblForEachPhase.setText("For each phase, please specify the OCL derivation rule to define its extension.");
	}
}