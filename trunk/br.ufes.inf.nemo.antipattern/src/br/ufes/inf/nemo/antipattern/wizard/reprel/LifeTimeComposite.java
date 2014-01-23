package br.ufes.inf.nemo.antipattern.wizard.reprel;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;

public class LifeTimeComposite extends Composite {

	public List list;
	public Label lblHowManySimoutanesouly;
	
	public LifeTimeComposite(Composite arg0, int arg1) {
		super(arg0, arg1);
		
		lblHowManySimoutanesouly = new Label(this, SWT.NONE);
		lblHowManySimoutanesouly.setBounds(10, 10, 158, 21);
		lblHowManySimoutanesouly.setText("How many simoutanesouly?");
		
		list = new List(this, SWT.BORDER | SWT.V_SCROLL);
		list.setItems(new String[] {"*", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"});
		list.setBounds(174, 10, 37, 21);
	}
	
	public String getN()
	{
		return list.getItem(list.getSelectionIndex());
	}
	
	public void disableAll()
	{
		list.setEnabled(false);
		lblHowManySimoutanesouly.setEnabled(false);
	}
	
	public void enableAll()
	{
		list.setEnabled(true);
		lblHowManySimoutanesouly.setEnabled(true);
	}
}
