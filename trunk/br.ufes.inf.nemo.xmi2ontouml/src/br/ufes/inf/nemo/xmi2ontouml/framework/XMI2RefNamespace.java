package br.ufes.inf.nemo.xmi2ontouml.framework;

import RefOntoUML.Constraintx;
import RefOntoUML.Namespace;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public abstract class XMI2RefNamespace extends XMI2RefNamedElement
{
	private static boolean importConstraints = true;
	
	public XMI2RefNamespace() {}
	
	public XMI2RefNamespace(Object XMIElement, XMIParser mapper)
	{
		super(XMIElement, mapper);
	}
	
	public XMI2RefNamespace(Object XMIElement, XMIParser mapper, Namespace namespace)
	{
		super(XMIElement, mapper, namespace);
	}
	
	@Override
	protected void createSubElements() throws Exception
	{
		if (isImportConstraints())
		{
	        for (Object constraint : Mapper.getElements(XMIElement, ElementType.CONSTRAINT))
	        {
	        	XMI2RefConstraint xmi2refconst = new XMI2RefConstraint(constraint, Mapper);
	        	if (xmi2refconst.RefOntoUMLElement != null)
	        	{
	        		((Namespace)RefOntoUMLElement).getOwnedRule().add((Constraintx) xmi2refconst.getRefOntoUMLElement());
	        		xmi2refconst.createSubElements();
	        	}
	        }
		}
        
        super.createSubElements();
	}

	public static boolean isImportConstraints() {
		return importConstraints;
	}

	public static void setImportConstraints(boolean importConstraints) {
		XMI2RefNamespace.importConstraints = importConstraints;
	}
}
