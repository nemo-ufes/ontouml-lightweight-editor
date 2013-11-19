package br.ufes.inf.nemo.xmi2ontouml.framework;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Mixin;
import RefOntoUML.RoleMixin;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;

public abstract class XMI2RefClassifier extends XMI2RefNamedElement
{
//	protected List<XMI2RefProperty> listProperties;
//	
//	protected List<XMI2RefGeneralization> listGeneralizations;
//	
//	public XMI2RefClassifier()
//	{
//		this.listProperties = new ArrayList<XMI2RefProperty>();
//		this.listGeneralizations = new ArrayList<XMI2RefGeneralization>();
//	}
	
	@Override
	protected void deal()
	{
		super.deal();
		
		//Categories, roleMixins and Mixins are abstract by default
		if (RefOntoUMLElement instanceof Category || RefOntoUMLElement instanceof RoleMixin || RefOntoUMLElement instanceof Mixin)
		{
			((Classifier)RefOntoUMLElement).setIsAbstract(true);
		} else
		{
			((Classifier)RefOntoUMLElement).setIsAbstract(Boolean.parseBoolean((String)hashProp.get("isabstract")));
		}
		
		((Classifier)RefOntoUMLElement).setIsLeaf(Boolean.parseBoolean((String)hashProp.get("isleaf")));
	}

	@Override
	protected void createSubElements() throws Exception
	{		
		for (Object gen : this.Mapper.getElements(XMIElement, ElementType.GENERALIZATION))
		{
			XMI2RefGeneralization xmi2refgen = new XMI2RefGeneralization(gen, Mapper);
//			listGeneralizations.add(xmi2refgen);
			((Classifier)RefOntoUMLElement).getGeneralization().add((Generalization)xmi2refgen.getRefOntoUMLElement());
		}
		
		super.createSubElements();
	}
}
