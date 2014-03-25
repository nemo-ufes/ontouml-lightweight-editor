package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.List;

import RefOntoUML.Comment;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public class XMI2RefComment extends XMI2RefElement
{
	public XMI2RefComment (Object XMIElement, XMIParser mapper) throws Exception
	{
		super(XMIElement, mapper, factory.createComment());
	}

	@Override
	protected void deal()
	{
		if (hashProp.get("body") != null)
			((Comment)RefOntoUMLElement).setBody((String)hashProp.get("body"));
		
		else
			((Comment)RefOntoUMLElement).setBody("");
	}

	@Override
	public void dealReferences()
	{
		try
		{
			for (Object annotatedElement : (List<?>)hashProp.get("annotatedelement"))
			{
				((Comment)RefOntoUMLElement).getAnnotatedElement().add(elemMap.getElement(annotatedElement));
			}
		}
		catch (NullPointerException | IllegalArgumentException e)
		{
			//A comment's annotated element should not produce an error
		}
//		finally
//		{
//			if (((Comment)RefOntoUMLElement).getAnnotatedElement().size() == 0)
//			{
//				System.out.println("Debug: removing comment with no annotated element");
//				EcoreUtil.remove(RefOntoUMLElement);
//				elemMap.remove(Mapper.getID(XMIElement));
//			}
//		}
	}

}
