package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;

import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public class XMI2RefGeneralizationSet extends XMI2RefNamedElement
{
	public XMI2RefGeneralizationSet (Object XMIElement, XMIParser mapper) throws Exception
	{
		super(XMIElement, mapper, factory.createGeneralizationSet());
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
	    		((GeneralizationSet)RefOntoUMLElement).getGeneralization().add((Generalization)elemMap.getElement(gen));
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
			if ((((GeneralizationSet)RefOntoUMLElement).getGeneralization().size() == 0 || isDuplicateGS()) && ignoreErrorElements)
	    	{
				System.err.println("Debug: removing generalization set empty or duplicate ("+((GeneralizationSet)RefOntoUMLElement).getName()+")");
	    		EcoreUtil.remove(RefOntoUMLElement);
//	    		elemMap.remove(Mapper.getID(XMIElement));
	    	}
		}
	}
	
	private boolean isDuplicateGS()
	{
		List<PackageableElement> pkgbList = ((Package)RefOntoUMLElement.eContainer()).getPackagedElement();
		
		for(PackageableElement pkgbElem : pkgbList)
		{
			if (pkgbElem instanceof GeneralizationSet && pkgbElem != RefOntoUMLElement && 
					((GeneralizationSet) pkgbElem).getGeneralization() != null && 
					((GeneralizationSet) pkgbElem).getGeneralization().size() == ((GeneralizationSet)RefOntoUMLElement).getGeneralization().size() &&
					((GeneralizationSet)RefOntoUMLElement).getGeneralization().containsAll(((GeneralizationSet)pkgbElem).getGeneralization()))
				return true;
		}
		return false;
	}
}
