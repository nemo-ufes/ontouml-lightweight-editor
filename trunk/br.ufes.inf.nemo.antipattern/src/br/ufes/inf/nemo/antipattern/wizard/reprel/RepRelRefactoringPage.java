package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class RepRelRefactoringPage extends RefactoringPage {
	
	protected RepRelOccurrence repRel;
	
	/**
	 * Create the wizard.
	 */
	public RepRelRefactoringPage(RepRelOccurrence repRel) 
	{
		super();	
		this.repRel = repRel;
				
		setTitle(RepRelAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public RepRelWizard getRepRelWizard(){
		return ( RepRelWizard)getWizard();
	}	

	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
}
