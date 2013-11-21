package br.ufes.inf.nemo.xmi2ontouml.framework;

import RefOntoUML.Constraintx;
import RefOntoUML.Namespace;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;

public abstract class XMI2RefNamespace extends XMI2RefNamedElement
{
	@Override
	protected void createSubElements() throws Exception
	{        
        for (Object constraint : Mapper.getElements(XMIElement, ElementType.CONSTRAINT))
        {
        	XMI2RefConstraint xmi2refconst = new XMI2RefConstraint(constraint, Mapper);
        	if (xmi2refconst.RefOntoUMLElement != null)
        	{
        		((Namespace)RefOntoUMLElement).getOwnedRule().add((Constraintx) xmi2refconst.getRefOntoUMLElement());
        	}
        }
        
        super.createSubElements();
	}
}
