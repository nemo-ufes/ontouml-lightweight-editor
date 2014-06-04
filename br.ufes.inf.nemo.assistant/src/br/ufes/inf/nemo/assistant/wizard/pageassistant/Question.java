package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import java.io.Serializable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Question extends WizardPageAssistant  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the wizard.
	 */
	public Question() {
		super("Question");
		setTitle("User Question");
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
			public void widgetSelected(SelectionEvent chk) {
				selected = true;
				if(selected && ((Button)chk.getSource()).getSelection()){
					if(canGoTrue){
						canGoNext = true;
						enableFinish(false);
					}else{
						canGoNext = false;
						enableFinish(true);
					}
				}
			}
		});
		btTrue.setBounds(10, 96, 90, 16);
		btTrue.setText("True");
		btTrue.setSelection(false);
		
		btFalse = new Button(container, SWT.RADIO);
		btFalse.setBounds(10, 117, 90, 16);
		btFalse.setText("False");
		btFalse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent chk) {
				selected = true;
				if(selected && ((Button)chk.getSource()).getSelection()){
					if(canGoFalse){
						canGoNext = true;
						enableFinish(false);
					}else{
						canGoNext = false;
						enableFinish(true);	
					}
				}
			}
		});
		btFalse.setSelection(false);
	}
	
	@Override
	public boolean nextTrue() {
		return btTrue.getSelection();
	}
	
	@Override
	public boolean nextFalse() {
		return btFalse.getSelection();
	}

	@Override
	public boolean canFlipToNextPage() {
		return (btTrue.getSelection() || btFalse.getSelection()) && canGoNext;
	}

	private boolean selected = false;
	private boolean canGoNext = true;
	private boolean canGoTrue = true;
	private boolean canGoFalse = true;

	public void setCanGoTrue(boolean b){
		canGoTrue = b;
	}

	public void setCanGoFalse(boolean b){
		canGoFalse = b;
	}

	private String question = "<Question>";
	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		String s;
		s = "Question: "+question;
		return s;
	}

	@Override
	public void init() {
		setTitle("User Question");
		setDescription("User Question");
	}
}
