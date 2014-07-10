package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;

public class MultiDepSecondPage extends MultiDepPage {
	
	private ArrayList<Property> optional;
	
	public Button customOrderButton;
	public Button noOrderButton;
	public MultiDepSpinnerTable table;
	public Composite container;
	
	SelectionAdapter radioAction = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
    	 
	    	if(noOrderButton.getSelection())
	    		table.setEnabled(false);
	    	if(customOrderButton.getSelection())
	    		table.setEnabled(true);
    	
		}
	};
	
	public MultiDepSecondPage(MultiDepOccurrence multiDep) {
		super(multiDep);
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		setControl(container);
		setPageComplete(false);
					
		customOrderButton = new Button(container, SWT.RADIO);
		customOrderButton.setText("Yes, there is a particular order");
		customOrderButton.addSelectionListener(radioAction);
		setAsEnablingNextPageButton(customOrderButton);
		
		noOrderButton = new Button(container, SWT.RADIO);
		noOrderButton.setText("No, the order does not matter");
		noOrderButton.addSelectionListener(radioAction);
		setAsEnablingNextPageButton(noOrderButton);
		
		Label lblAreTheseOptional = new Label(container, SWT.NONE);
		lblAreTheseOptional.setText("Are these optional relators created in a particular order?");
		
		table = new MultiDepSpinnerTable(container,SWT.BORDER);
		table.setEnabled(false);
		
		Label lblUseTheTable = new Label(container, SWT.NONE);
		lblUseTheTable.setText("Please use the table below to specify the order:");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(lblAreTheseOptional, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(noOrderButton, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(customOrderButton, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(lblUseTheTable, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(table, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblAreTheseOptional, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(noOrderButton)
					.add(6)
					.add(customOrderButton)
					.add(22)
					.add(lblUseTheTable)
					.add(6)
					.add(table, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
	}
	
	public void setProperties(ArrayList<Property> optional){
		this.optional = optional;		
		table.setProperties(optional);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{ 
		if (noOrderButton.getSelection())
		{
			//Action =============================
			MultiDepAction newAction = new MultiDepAction(occurrence);
			newAction.setAddSubtypesInNoOrder(optional); 
			getAntipatternWizard().replaceAction(0,newAction);	
			//======================================
			
		}
		if (customOrderButton.getSelection())
		{
			//Action =============================
			MultiDepAction newAction = new MultiDepAction(occurrence);
			newAction.setAddSubtypesInCustomOrder(table.getValues()); 
			getAntipatternWizard().replaceAction(0,newAction);	
			//====================================	
		}
	
		MultiDepThirdPage next = getAntipatternWizard().getThirdPage();
		return next;
		
	}
}
