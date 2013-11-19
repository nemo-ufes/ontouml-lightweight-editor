package br.ufes.inf.nemo.xmi2ontouml.framework;

import RefOntoUML.Class;
import RefOntoUML.Collective;
import RefOntoUML.Property;
import br.ufes.inf.nemo.xmi2ontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;

public class XMI2RefClass extends XMI2RefClassifier
{
	public XMI2RefClass (Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = solveStereotype(Mapper.getStereotype(this.XMIElement));
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		commonTasks();
	}
	
	private Class solveStereotype(String stereotype)
	{
		Class newclass = null;
		
		if (stereotype.equalsIgnoreCase("kind")) {
    		newclass = factory.createKind();
    	} 
		else if (stereotype.equalsIgnoreCase("subkind")) {
    		newclass = factory.createSubKind();
    	} 
    	else if (stereotype.equalsIgnoreCase("role")) {
    		newclass = factory.createRole();
    	} 
    	else if (stereotype.equalsIgnoreCase("phase")) {
    		newclass = factory.createPhase();
    	}
    	else if (stereotype.equalsIgnoreCase("category")) {
    		newclass = factory.createCategory();
    	}
    	else if (stereotype.equalsIgnoreCase("collective")) {
    		newclass = factory.createCollective();
    	}
    	else if (stereotype.equalsIgnoreCase("mixin")) {
    		newclass = factory.createMixin();
    	}
    	else if (stereotype.equalsIgnoreCase("mode")) {
    		newclass = factory.createMode();
    	}
    	else if (stereotype.equalsIgnoreCase("quantity")) {
    		newclass = factory.createQuantity();
    	}
    	else if (stereotype.equalsIgnoreCase("relator")) {
    		newclass = factory.createRelator();
    	}
    	else if (stereotype.equalsIgnoreCase("rolemixin")) {
    		newclass = factory.createRoleMixin();
    	}
    	else {
    		newclass = factory.createClass();
    	}
		
		return newclass;
	}

	@Override
	protected void deal()
	{
		super.deal();
		
		if (RefOntoUMLElement instanceof Collective)
		{
			((Collective)RefOntoUMLElement).setIsExtensional(Boolean.parseBoolean((String)hashProp.get("isextensional")));
		}
		
		((Class)RefOntoUMLElement).setIsActive(Boolean.parseBoolean((String)hashProp.get("isactive")));
	}
	
	@Override
	public void dealReferences()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void createSubElements() throws Exception
	{
		for (Object prop : this.Mapper.getElements(XMIElement, ElementType.PROPERTY))
		{
			XMI2RefProperty xmi2refprop = new XMI2RefProperty(prop, Mapper);
//			listProperties.add(xmi2refprop);
			((Class)RefOntoUMLElement).getOwnedAttribute().add((Property)xmi2refprop.getRefOntoUMLElement());
		}
		
		super.createSubElements();
	}
}
