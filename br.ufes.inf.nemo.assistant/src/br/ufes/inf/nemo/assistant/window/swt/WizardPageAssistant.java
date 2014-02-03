package br.ufes.inf.nemo.assistant.window.swt;

import org.eclipse.jface.wizard.WizardPage;

public abstract class WizardPageAssistant extends WizardPage {

	protected WizardPageAssistant(String pageName) {
		super(pageName);
	}

	public boolean nextTrue(){
		return false;
	}
	
	public boolean nextFalse(){
		return false;
	}
	
	public boolean next(){
		return false;
	}
	
	@Override
	public abstract boolean canFlipToNextPage();
}
