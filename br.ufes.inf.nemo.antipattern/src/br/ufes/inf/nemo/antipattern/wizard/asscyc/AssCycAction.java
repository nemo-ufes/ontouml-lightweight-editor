package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class AssCycAction extends AntiPatternAction<AssCycOccurrence>{

	public Association assoc;
	
	public AssCycAction(AssCycOccurrence ap) 
	{
		super(ap);
	}

	public enum Action { DERIVE_ONE_ASSOCIATION, CYCLE_FORBIDDEN, CYCLE_MANDATORY }
	
	@Override
	public void run()
	{
		if(code==Action.DERIVE_ONE_ASSOCIATION)
		{
			ap.deriveAssociation(assoc);
		}
		if(code==Action.CYCLE_FORBIDDEN)
		{
			ap.forbidCycle();
		}
		if(code==Action.CYCLE_MANDATORY)
		{
			ap.enforceCycle();
		}
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	public void setDeriveAssociation(Association assoc)
	{
		code=Action.DERIVE_ONE_ASSOCIATION;
		this.assoc = assoc;
	}
	
	public void setCycleForbidden()
	{
		code=Action.CYCLE_FORBIDDEN;
	}
	
	public void setCycleMandatory()
	{
		code=Action.CYCLE_MANDATORY;
	}
	
	@Override
	public String toString()
	{
		String result = new String();
		
		if(code==Action.DERIVE_ONE_ASSOCIATION)
		{
			result += "Create OCL derivation for association "+getStereotype(assoc)+" "+(assoc).getName()+": "+(assoc).getMemberEnd().get(0).getType().getName()+"->"+(assoc).getMemberEnd().get(1).getType().getName();
		}
		if(code==Action.CYCLE_FORBIDDEN)
		{
			result += "Create OCL invariant forbidding instance level cycle";
		}
		if(code==Action.CYCLE_MANDATORY)
		{
			result += "Create OCL invariant enforcing instance level cycle";
		}
		return result;
	}
}
		