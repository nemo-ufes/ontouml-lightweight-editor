package br.ufes.inf.nemo.xmi2ontouml.framework;

import org.eclipse.emf.ecore.util.EcoreUtil;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public class XMI2RefGeneralization extends XMI2RefElement
{
	public XMI2RefGeneralization (Object XMIElement, XMIParser mapper) throws Exception
	{
		super(XMIElement, mapper, factory.createGeneralization());
	}

	@Override
	protected void deal()
	{
		if (hashProp.containsKey("issubstitutable"))
			((Generalization)RefOntoUMLElement).setIsSubstitutable(Boolean.parseBoolean((String)hashProp.get("issubstitutable")));
		
	}

	@Override
	public void dealReferences()
	{
		try
		{
			// General and Specific are EOposites.
			// Given that, it`s only necessary to define one of those properties.
			((Generalization)RefOntoUMLElement).setGeneral((Classifier)elemMap.getElement(hashProp.get("general")));
			//((Generalization)RefOntoUMLElement).setSpecific((Classifier)hashProp.get("specific"));
		}
		catch (NullPointerException | IllegalArgumentException e)
		{
			if (!ignoreErrorElements)
				throw e;
		}
		finally
		{
			if (((Generalization)RefOntoUMLElement).getGeneral() == null && ignoreErrorElements)
			{
				System.err.println("Debug: removing generalization with error (Container: "+((Classifier)RefOntoUMLElement.eContainer()).getName()+")");
	    		EcoreUtil.remove(RefOntoUMLElement);
//	    		elemMap.remove(Mapper.getID(XMIElement));
	    	}
		}
	}
}
