package br.ufes.inf.nemo.antipattern.wizard.reprel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Classifier;
import RefOntoUML.Type;

public class SameTimeComposite extends Composite {

	public Button btnNo;
	public Button btnYes;
	public StyledText styledText;
	
	public SameTimeComposite(Composite arg0, int arg1, Classifier rel, Type type) 
	{
		super(arg0,SWT.NONE);
		
		styledText = new StyledText(this, SWT.WRAP);
		styledText.setText("Is it possible for two distinct instances of "+rel.getName()+" to mediate the exact same instances of "+type.getName()+" at the same time, as many times as desired?");
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBounds(10, 10, 380, 52);
		
		btnYes = new Button(this, SWT.RADIO);
		btnYes.setBounds(10, 60, 48, 16);
		btnYes.setText("Yes");
		btnYes.setSelection(true);
		
		btnNo = new Button(this, SWT.RADIO);
		btnNo.setBounds(61, 60, 48, 16);
		btnNo.setText("No");
	}
	
	public void setColor(Color swtColor)
	{
		styledText.setBackground(swtColor);
		btnYes.setBackground(swtColor);
		btnNo.setBackground(swtColor);
		setBackground(swtColor);		
	}	
	
	public boolean isYes()
	{
		return btnYes.getSelection();
	}
	
	public boolean isNo()
	{
		return btnNo.getSelection();
	}
	
	public void enableAll()
	{
		styledText.setBlockSelection(true);
		styledText.setEnabled(true);
		btnYes.setEnabled(true);
		btnNo.setEnabled(true);
	}
	
	public void disableAll()
	{
		styledText.setBlockSelection(false);
		styledText.setEnabled(false);		
		btnYes.setEnabled(false);
		btnNo.setEnabled(false);
	}

}
