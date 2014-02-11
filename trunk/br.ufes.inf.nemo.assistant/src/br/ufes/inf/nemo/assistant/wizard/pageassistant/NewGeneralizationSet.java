package br.ufes.inf.nemo.assistant.wizard.pageassistant;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NewGeneralizationSet extends WizardPageAssistant {

	/**
	 * Create the wizard.
	 */
	public NewGeneralizationSet() {
		super("Create new GeneralizationSet");
		setTitle("Create a new OntoUML class");
		setDescription("NewGeneralizationSet.description");
	}
	private String[] stereotypes;

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(null);
		
		Label lblSelectTheGeneral = new Label(container, SWT.NONE);
		lblSelectTheGeneral.setBounds(10, 10, 174, 15);
		lblSelectTheGeneral.setText("Setting the General for the class:");
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(190, 10, 215, 15);
		label.setText("<ClassName>");
		
		Label lblSelectTheGeneral_1 = new Label(container, SWT.NONE);
		lblSelectTheGeneral_1.setBounds(38, 50, 135, 15);
		lblSelectTheGeneral_1.setText("Select the General class:");
		
		Combo cbGeneral = new Combo(container, SWT.NONE);
		cbGeneral.setBounds(203, 44, 204, 23);
		
		Label lblSelectTheGeneralizationset = new Label(container, SWT.NONE);
		lblSelectTheGeneralizationset.setBounds(38, 84, 156, 15);
		lblSelectTheGeneralizationset.setText("Select the GeneralizationSet:");
		
		Combo cbGeneralizationSet = new Combo(container, SWT.NONE);
		cbGeneralizationSet.setEnabled(false);
		cbGeneralizationSet.setBounds(203, 77, 204, 23);
		
		Composite metaProperties = new Composite(container, SWT.NONE);
		metaProperties.setBounds(38, 125, 476, 123);
		metaProperties.setVisible(false);
		
		Label lblSet = new Label(metaProperties, SWT.NONE);
		lblSet.setBounds(4, 10, 272, 15);
		lblSet.setText("Set meta-properties from the GeneralizationSet:");
		
		Button btnDisjoint = new Button(metaProperties, SWT.CHECK);
		btnDisjoint.setBounds(305, 9, 93, 16);
		btnDisjoint.setText("Disjoint");
		
		Button btnComplete = new Button(metaProperties, SWT.CHECK);
		btnComplete.setBounds(305, 31, 93, 16);
		btnComplete.setText("Complete");
		
		Button btnNewGeneralizationset = new Button(container, SWT.NONE);
		btnNewGeneralizationset.setEnabled(false);
		btnNewGeneralizationset.setBounds(429, 77, 135, 25);
		btnNewGeneralizationset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnNewGeneralizationset.setText("New GeneralizationSet");
	}

	@Override
	public void performHelp() {
		Shell shell = new Shell(getShell());
		shell.setText("My Custom Help !!");

		Label label1 = new Label(shell, SWT.NONE);
		label1.setText("NEWCLASS.help");
		shell.open();
	}

	public void setStereotypes(String[] sts){
		stereotypes = sts;
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