package br.ufes.inf.nemo.antipattern.wizard.reprel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;

public class TimeOptionComposite extends Composite {
	public Button btnAtTheSame;
	public Button btnThroughoutTime;
	public Label lblType;
	private Label lblHowManySimoutanesouly;
	private Spinner spinner;
	
	public TimeOptionComposite(Composite arg0, int arg1, RepRelOccurrence repRel, Mediation m) {
		super(arg0, arg1);		
		
		Property relatorEnd = OntoUMLParser.getRelatorEnd(m);
		Type mediated = relatorEnd.getOpposite().getType();
		
		setSize(560,95);
		
		lblType = new Label(this,SWT.NONE);
		lblType.setText(OntoUMLNameHelper.getTypeAndName(mediated,true,true)+" - Multiplicity on relator end: "+OntoUMLNameHelper.getMultiplicity(relatorEnd, true, ".."));
		
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
		btnAtTheSame.setText("At the same time");
		btnAtTheSame.addSelectionListener(listener);
		
		btnThroughoutTime = new Button(this, SWT.RADIO);
		btnThroughoutTime.setText("Throughout time...");		
		
		lblHowManySimoutanesouly = new Label(this, SWT.NONE);
		lblHowManySimoutanesouly.setText("How many simoutanesouly? (at most)");
		
		spinner = new Spinner(this, SWT.BORDER);
		spinner.setSelection(1);
		spinner.setEnabled(false);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.addContainerGap()
					.add(btnThroughoutTime)
					.add(18)
					.add(lblHowManySimoutanesouly, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(152, Short.MAX_VALUE))
				.add(GroupLayout.TRAILING, groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, btnAtTheSame, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, lblType, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(5)
					.add(lblType, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnAtTheSame, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(btnThroughoutTime, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.add(lblHowManySimoutanesouly)
						.add(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(5))
		);
		setLayout(groupLayout);
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
		lblType.setBackground(swtColor);
		btnAtTheSame.setBackground(swtColor);
		btnThroughoutTime.setBackground(swtColor);
		setBackground(swtColor);
	}
}
