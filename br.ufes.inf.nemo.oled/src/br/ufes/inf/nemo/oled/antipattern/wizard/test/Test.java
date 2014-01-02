package br.ufes.inf.nemo.oled.antipattern.wizard.test;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

public class Test {

	public static void main(String[] args)
	{
		WizardDialog wizardDialog = new WizardDialog(new Shell(),
	    new MyWizard());
	    if (wizardDialog.open() == Window.OK) {
	      System.out.println("Ok pressed");
	    } else {
	      System.out.println("Cancel pressed");
	    }
	}
}
