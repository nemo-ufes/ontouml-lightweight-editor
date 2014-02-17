package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class NewClass extends WizardPageAssistant {
	private Text tfClassName;

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

		tfClassName = new Text(container, SWT.BORDER);
		tfClassName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent owner) {
				if(tfClassName.getText().isEmpty()){
					warning.setVisible(true);
					setPageComplete(false);
				}else{
					warning.setVisible(false);
					setPageComplete(true);
				}
				setCurrentClass(tfClassName.getText());
			}
		});
		tfClassName.setBounds(110, 63, 180, 21);

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

		if(isEndPage)
			setPageComplete(true);
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
		if(tfClassName.getText().isEmpty()){
			return false;
		}
		setCurrentClass(tfClassName.getText());
		return true;
	}

	@Override
	public String toString() {
		return "NewClass";
//		String s;
//		s = "Page: "+getName()+"{";
//		s += "\nclass: "+getClassName();
//		s += "\nstereotype: "+getStereotype();
//		s += "\n}";
//		return s;
	}

	/* get operations */

	public String getClassName(){
		return tfClassName.getText();
	}

	public String getStereotype(){
		return cbStereotypes.getItem(cbStereotypes.getSelectionIndex());
	}

} 