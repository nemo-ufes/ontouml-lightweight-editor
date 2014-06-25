package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

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
		
		label = new Label(this, SWT.NONE);
		label.setText("Template:");
		
		label_1 = new Label(this, SWT.NONE);
		label_1.setText("For each phase, please specify the OCL derivation rule to define its extension:");
		
		oclText = new StyledText(this, SWT.BORDER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(label, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
						.add(templateText, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
						.add(label_1, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
						.add(oclText, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
					.add(12))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(label)
					.add(6)
					.add(templateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.add(19)
					.add(label_1)
					.add(6)
					.add(oclText, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.add(12))
		);
		setLayout(groupLayout);
	}
}
