package br.ufes.inf.nemo.assistant.window.swt;

import java.io.Serializable;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

public class MyWizard extends Wizard implements Serializable{
	protected MyPageOne one;
	protected MyPageTwo two;

	public MyWizard() {
		super();
		setNeedsProgressMonitor(true);
		setHelpAvailable(true);
	}

	@Override
	public void addPages() {
		one = new MyPageOne();
		two = new MyPageTwo();
		addPage(one);
		addPage(two);
	}

	@Override
	public boolean performFinish() {
		// Print the result to the console
		System.out.println(one.getText1());
		System.out.println(two.getText1());
		return true;
	}

	public static void main(String[] args) {
		WizardDialog wizardDialog = new WizardDialog(new Shell(),new MyWizard());
		if (wizardDialog.open() == Window.OK) {
			System.out.println("Ok pressed");
		} else {
			System.out.println("Cancel pressed");
		}
	}
}
