package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

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
		spinner.setBounds(10, 10, 47, 22);
		
		lblCardinality = new Label(this, SWT.NONE);
		lblCardinality.setBounds(63, 10, 364, 22);
		lblCardinality.setText("Upper cardinality on mediated side");
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
