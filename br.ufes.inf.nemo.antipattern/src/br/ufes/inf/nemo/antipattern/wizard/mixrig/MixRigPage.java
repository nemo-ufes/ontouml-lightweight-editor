package br.ufes.inf.nemo.antipattern.wizard.mixrig;

import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigOccurrence;

public abstract class MixRigPage extends WizardPage {

	protected MixRigOccurrence mixRig;	
	
	/**
	 * Create the wizard.
	 */
	public MixRigPage(MixRigOccurrence mixRig) 
	{
		super("MixRigPage");		
		this.mixRig = mixRig;			
	}
		
	public MixRigWizard getMixRigWizard(){
		return (MixRigWizard)getWizard();
	}
	
	protected String getSubtypeList(int limit){
		String subtypeList = "";
		ArrayList<Classifier> subtypes = mixRig.getSubtypes();
		
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
