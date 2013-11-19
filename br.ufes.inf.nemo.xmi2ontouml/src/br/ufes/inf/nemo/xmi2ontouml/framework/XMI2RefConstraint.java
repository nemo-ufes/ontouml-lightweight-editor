package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.List;

import RefOntoUML.Constraintx;
import RefOntoUML.Element;
import RefOntoUML.NamedElement;
import RefOntoUML.OpaqueExpression;
import br.ufes.inf.nemo.xmi2ontouml.mapper.Mapper;

public class XMI2RefConstraint extends XMI2RefNamedElement
{
	public XMI2RefConstraint (Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = factory.createConstraintx();
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		constraints.add(this);
		
		commonTasks();
	}
	
	@Override
	public void deal()
	{
		super.deal();
		
		OpaqueExpression exp = factory.createOpaqueExpression();
		exp.getBody().add((String)hashProp.get("body"));
		
		((Constraintx)RefOntoUMLElement).setSpecification(exp);
		
		if (((Constraintx)RefOntoUMLElement).getName() == null)
			((Constraintx)RefOntoUMLElement).setName("");
	}

	@Override
	public void dealReferences()
	{
		for (Object constrainedElement : (List<?>)hashProp.get("constrainedelement"))
		{
			((Constraintx)RefOntoUMLElement).getConstrainedElement().add((Element)elemMap.get((String)constrainedElement));
		}
	}
	
	public String getStringRepresentation()
	{
		String rule = new String();
		
		String body = ((OpaqueExpression)((Constraintx)RefOntoUMLElement).getSpecification()).getBody().get(0);
		
		if (!(body.startsWith("context")))
		{
			for (Element e : ((Constraintx)RefOntoUMLElement).getConstrainedElement())
			{
				if (e instanceof NamedElement && ((NamedElement) e).getName() != null && !((NamedElement) e).getName().isEmpty())
				{
					rule += "context "+((NamedElement)e).getName()+"\n";
					break;
				}
				else
					return "";
			}
			
			if (body.startsWith("inv") || body.startsWith("derive"))
				rule += body;
			else
				rule += "inv "+((Constraintx)RefOntoUMLElement).getName()+": "+body;
		}
		else
			rule = body;
		
		return rule;
	}
}
