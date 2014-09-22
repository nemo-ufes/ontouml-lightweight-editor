package br.ufes.inf.nemo.antipattern.wizard.reprel;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;

public abstract class RepRelPage extends AntipatternWizardPage<RepRelOccurrence, RepRelWizard> {

	protected String relator = "";
	
	public RepRelPage(RepRelOccurrence rr) 
	{
		super(rr);				
			
		setTitle(RepRelAntipattern.getAntipatternInfo().getName());
		
		if(rr!=null){
			setDescription("Relator: "+OntoUMLNameHelper.getName(rr.getRelator(), true, false));
			relator = OntoUMLNameHelper.getTypeAndName(rr.getRelator(), true, true);
		}
	}
	
	
	

}
