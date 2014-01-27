package br.ufes.inf.nemo.antipattern.wizard.reprel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import RefOntoUML.Mediation;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;

public class TimeOptionComposite extends Composite {
	public Button btnAtTheSame;
	public Button btnThroughoutTime;
	public Label lblType;
	private Label lblHowManySimoutanesouly;
	private Spinner spinner;
	
	public TimeOptionComposite(Composite arg0, int arg1, RepRelOccurrence repRel, Mediation m) {
		super(arg0, arg1);		
		
		Type source = m.getMemberEnd().get(0).getType();
		Type target = m.getMemberEnd().get(1).getType();
		String upperSource = "*";
		if(m.getMemberEnd().get(0).getUpper() != -1) upperSource = Integer.toString(m.getMemberEnd().get(0).getUpper());
		String lowerSource = "*";
		if (m.getMemberEnd().get(0).getLower() != -1) lowerSource = Integer.toString(m.getMemberEnd().get(0).getLower());	
		
		setSize(560,97);
		
		lblType = new Label(this,SWT.NONE);
		lblType.setBounds(10, 10, 540, 15);
		lblType.setText(target.getName()+" -> ["+lowerSource+","+upperSource+"] "+source.getName());
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        if(btnAtTheSame.getSelection()){
	        	disableLifeTime();
	        }
	        if(btnThroughoutTime.getSelection()){
	        	enableLifeTime();
	        }
	      }
	    };
		    
		btnAtTheSame = new Button(this, SWT.RADIO);
		btnAtTheSame.setBounds(10, 62, 118, 25);
		btnAtTheSame.setText("at the same time");
		btnAtTheSame.addSelectionListener(listener);
		
		btnThroughoutTime = new Button(this, SWT.RADIO);
		btnThroughoutTime.setBounds(10, 31, 118, 25);
		btnThroughoutTime.setText("throughout time...");		
		
		lblHowManySimoutanesouly = new Label(this, SWT.NONE);
		lblHowManySimoutanesouly.setBounds(134, 36, 206, 15);
		lblHowManySimoutanesouly.setText("How many simoutanesouly? (at most)");
		
		spinner = new Spinner(this, SWT.BORDER);
		spinner.setBounds(346, 31, 47, 22);
		spinner.setSelection(1);
		btnThroughoutTime.addSelectionListener(listener);
		
	}
	
	public void selectSameTime()
	{
		btnAtTheSame.setSelection(true);
	}
	
	public boolean isSame()
	{
		return btnAtTheSame.getSelection();
	}
	
	public boolean isLifeTime()
	{
		return btnThroughoutTime.getSelection();
	}
	
	public int getN()
	{
		return spinner.getSelection();
	}
		
	public void disableLifeTime()
	{
		spinner.setEnabled(false);
		lblHowManySimoutanesouly.setEnabled(false);
	}
	
	public void enableLifeTime()
	{
		spinner.setEnabled(true);
		lblHowManySimoutanesouly.setEnabled(true);
	}
	
	public void setColor(Color swtColor)
	{
		lblHowManySimoutanesouly.setBackground(swtColor);
		//lblType.setBackground(swtColor);
		btnAtTheSame.setBackground(swtColor);
		btnThroughoutTime.setBackground(swtColor);
		setBackground(swtColor);
	}
}
