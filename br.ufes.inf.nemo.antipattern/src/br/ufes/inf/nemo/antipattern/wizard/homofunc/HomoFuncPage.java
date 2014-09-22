package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;

public abstract class HomoFuncPage extends AntipatternWizardPage<HomoFuncOccurrence, HomoFuncWizard> {
	
	/**
	 * Create the wizard.
	 */
	public HomoFuncPage(HomoFuncOccurrence homoFunc) 
	{
		super(homoFunc);		
		setTitle(HomoFuncAntipattern.getAntipatternInfo().getName());		
		setDescription(	"Whole: "+OntoUMLNameHelper.getTypeAndName(homoFunc.getWhole(), true, false)+", " +
						"Part: "+OntoUMLNameHelper.getTypeAndName(homoFunc.getPartEnd().getType(), true, false));
	}
}
