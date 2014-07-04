package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class SetUpperMultComposite extends Composite {

	public Spinner spinner;
	public Label lblCardinality;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SetUpperMultComposite(Composite parent, int style) {
		super(parent, style);
		
		spinner = new Spinner(this, SWT.BORDER);
		spinner.setMinimum(2);
		
		lblCardinality = new Label(this, SWT.NONE);
		lblCardinality.setText("Upper cardinality on mediated side");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(lblCardinality, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblCardinality, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
		);
		setLayout(groupLayout);
	}

	public void enable(boolean value){
		spinner.setEnabled(value);
		lblCardinality.setEnabled(value);		
	}
	
	public int getValue()
	{
		return spinner.getSelection();
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
