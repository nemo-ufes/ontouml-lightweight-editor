package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class MultiDepRefactoringPage extends RefactoringPage {
	
	public MultiDepOccurrence multiDep;
	/**
	 * Create the wizard.
	 */
	public MultiDepRefactoringPage(MultiDepOccurrence multiDep) 
	{
		super();	
		this.multiDep = multiDep;
		
		setTitle(MultiDepAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public MultiDepWizard getMultiDepWizard(){
		return (MultiDepWizard)getWizard();
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
	}
	

	@Override
	public IWizardPage getNextPage() {
		return super.getNextPage();		
	}
}
