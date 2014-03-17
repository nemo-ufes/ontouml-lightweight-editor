package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigAntipattern;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class GSRigRefactoringPage extends RefactoringPage {
	
	public GSRigOccurrence gsrig;

	/**
	 * Create the wizard.
	 */
	public GSRigRefactoringPage(GSRigOccurrence gsrig) 
	{
		super();	
		this.gsrig = gsrig;
		
		setTitle(GSRigAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public GSRigWizard getGSRigWizard(){
		return (GSRigWizard)getWizard();
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
}

	