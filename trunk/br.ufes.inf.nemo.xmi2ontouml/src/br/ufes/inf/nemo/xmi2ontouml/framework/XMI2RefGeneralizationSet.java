package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;

import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import br.ufes.inf.nemo.xmi2ontouml.mapper.Mapper;

public class XMI2RefGeneralizationSet extends XMI2RefNamedElement
{
	public XMI2RefGeneralizationSet (Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = factory.createGeneralizationSet();
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		commonTasks();
	}
	
	@Override
	protected void deal()
	{
		super.deal();
		
		((GeneralizationSet)RefOntoUMLElement).setIsCovering(Boolean.parseBoolean((String)hashProp.get("iscovering")));
		((GeneralizationSet)RefOntoUMLElement).setIsDisjoint(Boolean.parseBoolean((String)hashProp.get("isdisjoint")));
	}
	
	@Override
	public void dealReferences()
	{
		try
		{
	    	for (Object gen : (List<?>)hashProp.get("generalization"))
	    	{
	    		// Only one of those properties must be set, since they are EOposite.
	    		((GeneralizationSet)RefOntoUMLElement).getGeneralization().add((Generalization)elemMap.get((String)gen));
//				((Generalization)gen).getGeneralizationSet().add(((GeneralizationSet)RefOntoUMLElement));
			}
		}
		catch (NullPointerException | IllegalArgumentException e)
		{
			if (!ignoreErrorElements)
				throw e;
		}
		finally
		{
			if (((GeneralizationSet)RefOntoUMLElement).getGeneralization().size() == 0 && ignoreErrorElements)
	    	{
				System.err.println("Debug: removing generalization set with no generalization ("+((GeneralizationSet)RefOntoUMLElement).getName()+")");
	    		EcoreUtil.remove(RefOntoUMLElement);
//	    		elemMap.remove(Mapper.getID(XMIElement));
	    	}
		}
	}
}
