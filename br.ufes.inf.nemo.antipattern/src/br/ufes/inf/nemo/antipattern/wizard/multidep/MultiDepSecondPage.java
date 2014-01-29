package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;
import org.eclipse.swt.widgets.Button;

public class MultiDepSecondPage  extends MultiDepPage {

	public ArrayList<Property> properties = new ArrayList<Property>();
	public MultiDepTable table;
	public Composite container;
	public Button btnAddLine;
	
	public MultiDepSecondPage(MultiDepOccurrence multiDep, ArrayList<Property> properties) 
	{
		super(multiDep);
		this.properties = properties;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		table = new MultiDepTable(container, SWT.NONE,properties);
		table.getTable().setBounds(10, 36, 554, 104);
//		Composite composite = new Composite(container,SWT.NONE);
//		composite.setBounds(10, 36, 554, 104);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 554, 15);
		lblNewLabel.setText("Is there a dependency between these relators?");
		
		btnAddLine = new Button(container, SWT.NONE);
		btnAddLine.setBounds(489, 146, 75, 25);
		btnAddLine.setText("Add Line");
		btnAddLine.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 table.addLine();				 
			 }
		});	
	}
	
	@Override
	public IWizardPage getNextPage() 
	{		
		return ((MultiDepWizard)getWizard()).getFinishing();
	}
}
