package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class NewClass extends WizardPageAssistant {
	private Text text;

	/**
	 * Create the wizard.
	 */
	public NewClass() {
		super("Create new class");
		setTitle("Create a new OntoUML class");
		setDescription("NEWCLASS.description");
	}

	private Combo cbStereotypes;
	private String[] stereotypes;
	private Label warning;

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(null);

		Label lblClassName = new Label(container, SWT.NONE);
		lblClassName.setBounds(10, 66, 83, 15);
		lblClassName.setText("Class Name");

		text = new Text(container, SWT.BORDER);
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(text.getText().isEmpty()){
					warning.setVisible(true);
					setPageComplete(false);
				}else{
					warning.setVisible(false);
					setPageComplete(true);
				}
				setCurrentClass(text.getText());
			}
		});
		text.setBounds(110, 63, 180, 21);


		Label lblClassStereotype = new Label(container, SWT.NONE);
		lblClassStereotype.setBounds(10, 102, 93, 15);
		lblClassStereotype.setText("Class Stereotype");

		cbStereotypes = new Combo(container, SWT.READ_ONLY);
		cbStereotypes.setBounds(110, 94, 180, 23);
		cbStereotypes.setItems(stereotypes);
		cbStereotypes.select(0);

		Label lblSetTheAttributes = new Label(container, SWT.NONE);
		lblSetTheAttributes.setBounds(10, 27, 316, 15);
		lblSetTheAttributes.setText("Set the attributes for the new class");

		warning = new Label(container, SWT.NONE);
		warning.setVisible(false);
		warning.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		warning.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		warning.setBounds(294, 59, 17, 25);
		warning.setText("!");
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
		if(text.getText().isEmpty()){
			return false;
		}
		return true;
	}
}