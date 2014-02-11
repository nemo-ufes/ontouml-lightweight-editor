package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;

public class SelectClass extends Dialog {

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public SelectClass(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);
		
		Label lblSelectTheClass = new Label(container, SWT.NONE);
		lblSelectTheClass.setBounds(10, 13, 104, 15);
		lblSelectTheClass.setText("Select a stereotype");
		
		Combo combo = new Combo(container, SWT.NONE);
		combo.setBounds(116, 10, 165, 23);
		
		Label lblSelectAClass = new Label(container, SWT.NONE);
		lblSelectAClass.setBounds(10, 52, 104, 15);
		lblSelectAClass.setText("Select a class");
		
		Combo combo_1 = new Combo(container, SWT.NONE);
		combo_1.setBounds(116, 44, 165, 23);

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(397, 175);
	}
}
