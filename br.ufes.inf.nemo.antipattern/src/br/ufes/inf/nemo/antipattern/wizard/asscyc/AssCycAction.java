package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class AssCycAction extends AntiPatternAction<AssCycOccurrence>{


	public AssCycAction(AssCycOccurrence ap) 
	{
		super(ap);
	}

	public enum Action { }
	
	@Override
	public void run()
	{
		
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
	
	@Override
	public String toString()
	{
		String result = new String();
		
		return result;
	}
}
		