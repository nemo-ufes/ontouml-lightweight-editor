package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class ConstraintComposite extends Composite {

	UndefPhaseOccurrence up;
	StyledText oclText;
	private Label label_1;
	private Label label;
	private StyledText templateText;
	
	public ConstraintComposite(Composite parent, int style, UndefPhaseOccurrence up) 
	{
		super(parent, style);
		this.up=up;
		
		templateText = new StyledText(this, SWT.BORDER | SWT.READ_ONLY);
		templateText.setText("context <Phase> :: allInstances() : Set(<Phase>)\r\nbody : <Supertype>.allInstances()->select ( x | <CONDITION>)");
		templateText.setBounds(10, 31, 428, 34);
		
		label = new Label(this, SWT.NONE);
		label.setText("Template:");
		label.setBounds(10, 10, 554, 15);
		
		label_1 = new Label(this, SWT.NONE);
		label_1.setText("For each phase, please specify the OCL derivation rule to define its extension:");
		label_1.setBounds(10, 84, 554, 15);
		
		oclText = new StyledText(this, SWT.BORDER);
		oclText.setBounds(10, 105, 428, 118);
	}
}
