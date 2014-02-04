package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NewPhase extends WizardPageAssistant {
	private Table table;

	/**
	 * Create the wizard.
	 */
	public NewPhase() {
		super("Create new phase");
		setTitle("Create new OntoUML Phases");
		setDescription("NEWPHASE.description");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(null);
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.setBounds(281, 257, 104, 25);
		btnNewButton.setText("Add new Phase");
		
		Button btAddNewRow = new Button(container, SWT.NONE);
		btAddNewRow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
			}
		});
		btAddNewRow.setBounds(161, 257, 104, 25);
		btAddNewRow.setText("Add new row");
		
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLocation(0, 0);
		table.setSize(574, 251);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		table.setLayoutData(data);
		
		TableColumn column = new TableColumn (table, SWT.NONE);
		column.setText ("Phase Name");
		column = new TableColumn (table, SWT.NONE);
		column.setText ("Rules");

		
	}

	@Override
	public boolean next() {
		return true;
	}
	
	@Override
	public boolean canFlipToNextPage() {
		if(isEndPage)
			return false;
		
		return false;
	}
}
