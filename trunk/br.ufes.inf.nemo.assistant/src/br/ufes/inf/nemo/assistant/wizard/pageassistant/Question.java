package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Question extends WizardPageAssistant {

	/**
	 * Create the wizard.
	 */
	public Question() {
		super("Question");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
	}

	private Button btTrue;
	private Button btFalse;
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);

		Label lbQuestion = new Label(container, SWT.NONE);
		lbQuestion.setBounds(10, 10, 554, 70);
		lbQuestion.setText(question);

		btTrue = new Button(container, SWT.RADIO);
		btTrue.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setPageComplete(true);
				selected = true;
			}
		});
		btTrue.setBounds(10, 96, 90, 16);
		btTrue.setText("True");

		btFalse = new Button(container, SWT.RADIO);
		btFalse.setBounds(10, 117, 90, 16);
		btFalse.setText("False");
		btFalse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setPageComplete(true);
				selected = true;
			}
		});
	}

	@Override
	public boolean nextTrue() {
		System.out.println("nextTrue: "+btTrue.getSelection());
		return btTrue.getSelection();
	}

	@Override
	public boolean nextFalse() {
		System.out.println("nextFalse: "+btFalse.getSelection());
		return btFalse.getSelection();
	}

	@Override
	public boolean canFlipToNextPage() {
		return btTrue.getSelection() || btFalse.getSelection();
	}

	private boolean canGoTrue = true;
	private boolean canGoFalse = true;
	private boolean selected = false;

	public void setCanGoTrue(boolean b){
		btTrue.setEnabled(false);
		canGoTrue = b;
	}

	public void setCanGoFalse(boolean b){
		btFalse.setEnabled(false);
		canGoFalse = b;
	}


	private String question = "<Question>";
	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if((!canGoTrue || !canGoFalse) && !selected){
			MessageDialog.openInformation(getShell(), "Warning", "There is just one options in this case, but you can finish the wizard.");
		}
	}

	@Override
	public String toString() {
		String s;
		s = "Question: "+question;
		return s;
	}
}
