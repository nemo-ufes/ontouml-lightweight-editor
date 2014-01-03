package br.ufes.inf.nemo.oled.antipattern.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;

public class RelRigThirdPage extends WizardPage {

	/**
	 * Create the wizard.
	 */
	public RelRigThirdPage() {
		super("wizardPage");
		setTitle(RelRigAntipattern.getAntipatternInfo().getAcronym() + " - Third Question");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setEditable(false);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText("Is it possible for an instance of <RigidType> to change the instances of <Relator> it is connected to?");
		styledText.setBounds(10, 10, 554, 15);
		
		Button button = new Button(container, SWT.RADIO);
		button.setText("Yes");
		button.setBounds(10, 31, 90, 16);
		
		Button btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No");
		btnNo.setBounds(10, 55, 90, 16);
	}
}
