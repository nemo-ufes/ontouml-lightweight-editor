package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;

import RefOntoUML.Constraintx;
import RefOntoUML.Element;
import RefOntoUML.NamedElement;
import RefOntoUML.Namespace;
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
		
		XMI2RefModel.getConstraints().add(this);
		
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
		try
		{
			for (Object constrainedElement : (List<?>)hashProp.get("constrainedelement"))
			{
				((Constraintx)RefOntoUMLElement).getConstrainedElement().add((Element)elemMap.get((String)constrainedElement));
			}
		}
		catch (NullPointerException | IllegalArgumentException e)
		{
			//A constraintx should not produce an error
		}
		finally
		{
			if (((Constraintx)RefOntoUMLElement).getConstrainedElement().size() == 0)
			{
				System.out.println("Debug: removing constraint with error ("+((Constraintx)RefOntoUMLElement).getName()+" | Container: "+((Namespace)RefOntoUMLElement.eContainer()).getName()+")");
				EcoreUtil.remove(RefOntoUMLElement);
			}
		}
	}
	
	public String getStringRepresentation()
	{
		String rule = new String();
		
		String body = ((OpaqueExpression)((Constraintx)RefOntoUMLElement).getSpecification()).getBody().get(0)
				.replace("<b>", "")
				.replace("</b>", "")
				.replace("&lt;", "<")
				.replace("&gt;", ">")
				.trim();
		
		if (body.equals(""))
			return "";
		
		if (((Constraintx)RefOntoUMLElement).getName() != null && !((Constraintx)RefOntoUMLElement).getName().equals(""))
			rule += "--"+((Constraintx)RefOntoUMLElement).getName()+"\n";
		
		if (!(body.toLowerCase().startsWith("context")))
		{
			for (Element e : ((Constraintx)RefOntoUMLElement).getConstrainedElement())
			{
				if (e instanceof NamedElement && ((NamedElement) e).getName() != null && !((NamedElement) e).getName().isEmpty())
				{
					rule += "context _'"+((NamedElement)e).getName().replace("/", "").trim()+"'\n";
					break;
				}
				else
					return "";
			}
			
			if (body.toLowerCase().startsWith("inv") || body.toLowerCase().startsWith("derive"))
				rule += body;
			else
				rule += "inv: "+body;
		}
		else
			rule += body;
		
		return rule+"\n\n";
	}
}
