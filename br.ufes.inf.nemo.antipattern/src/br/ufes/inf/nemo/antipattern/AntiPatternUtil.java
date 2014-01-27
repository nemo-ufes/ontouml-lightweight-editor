package br.ufes.inf.nemo.antipattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import RefOntoUML.Property;

public class AntiPatternUtil {

	public static EObject getOriginal(EObject copy, Copier copier)
	{		
		for (EObject element : copier.keySet()) 
		{
			if(copier.get(element).equals(copy)) return element;
		}		
		return null;
	}
	
	//return true if the name was fixed and false otherwise
	public static boolean fixPropertyName(Property p){

		if(p!=null && (p.getName()==null || p.getName().trim().isEmpty())){
			String pName = p.getType().getName().trim().toLowerCase();
			pName.replaceAll("\\s+","");
			p.setName(pName);
			return true;
		}
		
		return false;
	}

}
