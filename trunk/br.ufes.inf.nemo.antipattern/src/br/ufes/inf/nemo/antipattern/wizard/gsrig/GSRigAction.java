package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;
import br.ufes.inf.nemo.common.ontoumlparser.ParsingElement;

public class GSRigAction  extends AntiPatternAction<GSRigOccurrence>{
		
	public GSRigAction(GSRigOccurrence ap) 
	{
		super(ap);
	}

	public enum Action { DELETE_GS, CREATE_GS_FOR_RIGIDS , CREATE_GS_FOR_ANTIRIGIDS, CREATE_GS_FOR_BOTH}
	
	public void setDeleteGS()
	{
		code = Action.DELETE_GS;		
	}
		
	public void setCreateGSForRigids()
	{
		code = Action.CREATE_GS_FOR_RIGIDS;		
	}
	
	public void setCreateGSForAntiRigids()
	{
		code = Action.CREATE_GS_FOR_ANTIRIGIDS;
	}	

	public void setCreateGSForBoth()
	{
		code = Action.CREATE_GS_FOR_BOTH;		
	}
	
	@Override
	public void run() 
	{
		if(code==Action.DELETE_GS);
		if(code==Action.CREATE_GS_FOR_RIGIDS);
		if(code==Action.CREATE_GS_FOR_ANTIRIGIDS);
		if(code==Action.CREATE_GS_FOR_BOTH);
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		if (code == Action.DELETE_GS)
		{
			result += "Delete "+(new ParsingElement(ap.getGs(),false,""));
		}
		if (code == Action.CREATE_GS_FOR_RIGIDS)
		{
			result += "Create new <<GeneralizationSet>> for rigids: ";
			int i=0;
			for(Classifier c: ap.getRigidSpecifics()) { 
				if(i==ap.getRigidSpecifics().size()-1) {
					result += getStereotype(c)+" "+c.getName(); 
				}else{
					result += getStereotype(c)+" "+c.getName()+",";
				}
			}
		}	
		if (code == Action.CREATE_GS_FOR_ANTIRIGIDS)
		{
			result += "Create new <<GeneralizationSet>> for antirigids: ";
			int i=0;
			for(Classifier c: ap.getAntiRigidSpecifics()) { 
				if(i==ap.getAntiRigidSpecifics().size()-1) {
					result += getStereotype(c)+" "+c.getName(); 
				}else{
					result += getStereotype(c)+" "+c.getName()+",";
				}
			}
		}			
		if (code == Action.CREATE_GS_FOR_BOTH)
		{
			result += "Create new <<GeneralizationSet>> for rigids: ";
			int i=0;
			for(Classifier c: ap.getRigidSpecifics()) { 
				if(i==ap.getRigidSpecifics().size()-1) {
					result += getStereotype(c)+" "+c.getName(); 
				}else{
					result += getStereotype(c)+" "+c.getName()+",";
				}
			}
			
			result += "Create new <<GeneralizationSet>> for antirigids: ";
			i=0;
			for(Classifier c: ap.getAntiRigidSpecifics()) { 
				if(i==ap.getAntiRigidSpecifics().size()-1) {
					result += getStereotype(c)+" "+c.getName(); 
				}else{
					result += getStereotype(c)+" "+c.getName()+",";
				}
			}
		}
		return result;
	}

}
