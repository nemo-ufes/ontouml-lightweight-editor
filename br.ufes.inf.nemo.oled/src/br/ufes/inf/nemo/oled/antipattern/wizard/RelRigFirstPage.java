package br.ufes.inf.nemo.oled.antipattern.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;

public class RelRigFirstPage extends WizardPage {

	RelRigOccurrence relRig;
	
	/**
	 * Create the wizard.
	 */
	public RelRigFirstPage(RelRigOccurrence relRig) {
		super("wizardPage");
		this.relRig = relRig;
		setTitle(RelRigAntipattern.getAntipatternInfo().getAcronym() + " - First Question");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText("Is it possible for an object that isn’t a <RigidType> to become one or an object that is an instance of <RigidType> cease to be it and still exist?");
		styledText.setEditable(false);
		styledText.setBounds(10, 10, 554, 38);
		
		Button btnRadioButton = new Button(container, SWT.RADIO);
		btnRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnRadioButton.setBounds(10, 54, 90, 16);
		btnRadioButton.setText("Yes");
		
		Button btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No");
		btnNo.setBounds(10, 78, 90, 16);
		
		
	}
}
