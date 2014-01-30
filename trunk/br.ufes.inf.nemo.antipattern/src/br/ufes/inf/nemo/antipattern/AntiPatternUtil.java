package br.ufes.inf.nemo.antipattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;


public class AntiPatternUtil {

	public static EObject getOriginal(EObject copy, Copier copier)
	{		
		for (EObject element : copier.keySet()) 
		{
			if(copier.get(element).equals(copy)) return element;
		}		
		return null;
	}

}
