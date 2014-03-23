package br.ufes.inf.nemo.antipattern.wizard.mixiden;

import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;

public abstract class MixIdenPage extends WizardPage {

	protected MixIdenOccurrence mixIden;	
	
	/**
	 * Create the wizard.
	 */
	public MixIdenPage(MixIdenOccurrence mixIden) 
	{
		super("MixIdenPage");		
		this.mixIden = mixIden;			
	}
		
	public MixIdenWizard getMixIdenWizard(){
		return (MixIdenWizard)getWizard();
	}
	
	protected String getSubtypeList(int limit){
		String subtypeList = "";
		ArrayList<Classifier> subtypes = mixIden.getSubtypes();
		
		int max;
		if (limit<=0 || limit >= subtypes.size())
			max = subtypes.size();
		else
			max = limit;
		
		for (int i = 0; i < max; i++) {
			
			if(i!=0)
				subtypeList += ", ";
			subtypeList += "<"+subtypes.get(i).getName()+">";
		}
		
		if(max!=subtypes.size())
			subtypeList+="...";
		
		return subtypeList;
	}
}
