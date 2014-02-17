package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NewGeneralizationSetDialog extends Dialog {
	private Text genSet;
	private NewGeneralizationSet owner;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public NewGeneralizationSetDialog(Shell parentShell, NewGeneralizationSet owner) {
		super(parentShell);
		setShellStyle(SWT.DIALOG_TRIM);
		parentShell.setText("Creating a new GeneralizationSet");
		this.owner = owner;
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
		lblSelectTheClass.setBounds(10, 13, 140, 15);
		lblSelectTheClass.setText("GeneralizationSet name:");
		
		genSet = new Text(container, SWT.BORDER);
		genSet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent owner) {
				if(((Text)genSet).getText().isEmpty()){
					b.setEnabled(false);
				}else{
					b.setEnabled(true);
				}
			}
		});
		genSet.setBounds(149, 10, 164, 21);

		return container;
	}

	private Button b;
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		b = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		b.setEnabled(false);
		
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(330, 140);
	}
	
	public String getGeneralizationSet(){
		return genSet.getText();
	}
	
}
