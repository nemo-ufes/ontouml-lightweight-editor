package br.ufes.inf.nemo.xmi2ontouml.framework;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Mixin;
import RefOntoUML.RoleMixin;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public abstract class XMI2RefClassifier extends XMI2RefNamespace
{	
	/**
	 * Option variable to decide how to threat unknowns stereotypes.
	 * 0 will create default Class/Association elements
	 * 1 will ignore the element
	 * Any other value will throw an exception and not produce the result model
	 */
	protected static int unknownStereotypeOpt = 0;
	
	public XMI2RefClassifier() {}
	
	public XMI2RefClassifier(Object XMIElement, XMIParser mapper)
	{
		super(XMIElement, mapper);
	}
	
	public XMI2RefClassifier(Object XMIElement, XMIParser mapper, Classifier classf)
	{
		super(XMIElement, mapper, classf);
	}
	
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
			if (xmi2refgen.RefOntoUMLElement != null)
			{
				((Classifier)RefOntoUMLElement).getGeneralization().add((Generalization)xmi2refgen.getRefOntoUMLElement());
				xmi2refgen.createSubElements();
			}
		}
		
		super.createSubElements();
	}
	
	public static int getUnknownStereotypeOpt() {
		return unknownStereotypeOpt;
	}

	public static void setUnknownStereotypeOpt(int unknownStereotypeOpt) {
		XMI2RefClassifier.unknownStereotypeOpt = unknownStereotypeOpt;
	}
}
