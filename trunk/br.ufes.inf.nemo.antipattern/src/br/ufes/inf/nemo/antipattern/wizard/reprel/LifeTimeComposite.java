package br.ufes.inf.nemo.antipattern.wizard.reprel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

public class LifeTimeComposite extends Composite {
	public Label lblHowManySimoutanesouly;
	public Spinner spinner;
	
	public LifeTimeComposite(Composite arg0, int arg1) {
		super(arg0, arg1);
		
		lblHowManySimoutanesouly = new Label(this, SWT.NONE);
		lblHowManySimoutanesouly.setBounds(10, 6, 225, 25);
		lblHowManySimoutanesouly.setText("But how many simoutanesouly? (at most)");
		
		spinner = new Spinner(this, SWT.BORDER);
		spinner.setBounds(235, 6, 47, 22);
	}
	
	public void setColor(Color swtColor)
	{
		lblHowManySimoutanesouly.setBackground(swtColor);		
		setBackground(swtColor);		
	}	
	
	public int getN()
	{
		return spinner.getSelection();
	}
	
	public void disableAll()
	{
		spinner.setEnabled(false);
		lblHowManySimoutanesouly.setEnabled(false);
	}
	
	public void enableAll()
	{
		spinner.setEnabled(true);
		lblHowManySimoutanesouly.setEnabled(true);
	}
}
