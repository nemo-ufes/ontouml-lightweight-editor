package br.ufes.inf.nemo.xmi2ontouml.framework;

import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import br.ufes.inf.nemo.xmi2ontouml.Creator;
import br.ufes.inf.nemo.xmi2ontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;

public class XMI2RefPackage extends XMI2RefNamespace
{
	public XMI2RefPackage (Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = factory.createPackage();
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		commonTasks();
	}
	
	public XMI2RefPackage()
	{
	}

	@Override
	public void dealReferences()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createSubElements() throws Exception
	{
		// Create the Packageable Elements Inside the package
    	try
    	{
    		// Create Primitive Types first
    		packageIterator(XMI2RefPrimitiveType.class, ElementType.PRIMITIVE);
    		
            // Create nested Packages
            packageIterator(XMI2RefPackage.class, ElementType.PACKAGE);
            
	        // Create Classes
            packageIterator(XMI2RefClass.class, ElementType.CLASS);

	        // Create DataTypes
            packageIterator(XMI2RefDatatype.class, ElementType.DATATYPE);
            
	        // Create Enumerations
            packageIterator(XMI2RefEnumeration.class, ElementType.ENUMERATION);

	        // Create Associations
            packageIterator(XMI2RefAssociation.class, ElementType.ASSOCIATION);

	        // Create Generalization Sets
            packageIterator(XMI2RefGeneralizationSet.class, ElementType.GENERALIZATIONSET);

    	} catch (NullPointerException npe)
    	{
    		npe.printStackTrace();
    		Creator.warningLog += "Warning: Empty package '" + ((Package)RefOntoUMLElement).getName() + "'.\n";
    	}
    	
    	super.createSubElements();
	}
	
	protected <T> void packageIterator(Class<T> type, ElementType elementType) throws Exception
	{
		for (Object elem : this.Mapper.getElements(XMIElement, elementType))
		{
			XMI2RefElement xmi2RefElem = (XMI2RefElement) type.getConstructor(Object.class, Mapper.class).newInstance(elem, Mapper);
			if (xmi2RefElem.RefOntoUMLElement != null)
			{
				PackageableElement refOntoElem = (PackageableElement) xmi2RefElem.RefOntoUMLElement;
				((Package)RefOntoUMLElement).getPackagedElement().add(refOntoElem);
			}
        }
    }
}
