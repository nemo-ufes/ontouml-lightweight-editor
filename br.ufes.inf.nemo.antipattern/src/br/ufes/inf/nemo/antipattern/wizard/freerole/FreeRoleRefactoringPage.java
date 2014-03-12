package br.ufes.inf.nemo.antipattern.wizard.freerole;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleAntipattern;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class FreeRoleRefactoringPage extends RefactoringPage {
		
	public FreeRoleOccurrence freeRole;
		
	/**
	 * Create the wizard.
	 */
	public FreeRoleRefactoringPage(FreeRoleOccurrence freeRole) 
	{
		super();	
		this.freeRole = freeRole;
		
		setTitle(FreeRoleAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public FreeRoleWizard getFreeRoleWizard(){
		return (FreeRoleWizard)getWizard();
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);				
	}
}
