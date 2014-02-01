package br.ufes.inf.nemo.assistant.window.swt;

import java.io.Serializable;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
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

	static String srcname = "";
	@Override
	public void addPages() {
		
		NewClass page1 = new NewClass();
		page1.setStereotypes(new String[]{"Kind","Subkind"});
		page1.setSrcName(srcname);
		addPage(page1);
		
//		NewClass page2 = new NewClass();
//		page2.setStereotypes(new String[]{"Role","Phase"});
//		addPage(page2);
		
		NewGenericRelation page3 = new NewGenericRelation();
		page3.setClassName(srcname);
		addPage(page3);
		
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
	@Override
	public boolean performCancel() {
		return false;
	}
}
