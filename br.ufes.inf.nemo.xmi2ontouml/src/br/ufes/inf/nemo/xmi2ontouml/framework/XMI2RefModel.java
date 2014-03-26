package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.ArrayList;
import java.util.List;

import RefOntoUML.Model;
import RefOntoUML.PrimitiveType;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public class XMI2RefModel extends XMI2RefPackage
{
	public static final PrimitiveType INTEGER_PRIMITIVE = createIntegerPrimitive();
	public static final PrimitiveType BOOLEAN_PRIMITIVE = createBooleanPrimitive();
	public static final PrimitiveType STRING_PRIMITIVE = createStringPrimitive();
	public static final PrimitiveType UNLIMITED_NATURAL_PRIMITIVE = createUnlimitedNaturalPrimitive();
	
	protected static List<XMI2RefConstraint> constraints = new ArrayList<XMI2RefConstraint>();
	protected static List<XMI2RefDiagram> diagrams = new ArrayList<XMI2RefDiagram>();
	
	public XMI2RefModel (Object XMIElement, XMIParser mapper) throws Exception
	{
		super(XMIElement, mapper, factory.createModel());
		
		//When creating a new Model, resets the elemMap and the constraints and diagrams list
		elemMap = new ElementMap();
		constraints = new ArrayList<XMI2RefConstraint>();
		diagrams = new ArrayList<XMI2RefDiagram>();
		
		elemMap.put(XMIElement, this);
		
		Model model = (Model) this.RefOntoUMLElement;
		
		model.getPackagedElement().add(INTEGER_PRIMITIVE);
		elemMap.put("Integer", new XMI2RefPrimitiveType(INTEGER_PRIMITIVE));
		
		model.getPackagedElement().add(BOOLEAN_PRIMITIVE);
		elemMap.put("Boolean", new XMI2RefPrimitiveType(BOOLEAN_PRIMITIVE));
		
		model.getPackagedElement().add(STRING_PRIMITIVE);
		elemMap.put("String", new XMI2RefPrimitiveType(STRING_PRIMITIVE));
		
		model.getPackagedElement().add(UNLIMITED_NATURAL_PRIMITIVE);
		elemMap.put("UnlimitedNatural", new XMI2RefPrimitiveType(UNLIMITED_NATURAL_PRIMITIVE));
		
		createSubElements();
	}
	
	@Override
	protected void deal()
	{
		super.deal();
		
		((Model)RefOntoUMLElement).setViewpoint((String)hashProp.get("viewpoint"));
	}
	
	@Override
	protected void createSubElements() throws Exception
	{
		super.createSubElements();
		
		for (Object diag : this.Mapper.getElements(XMIElement, ElementType.DIAGRAM))
		{
			XMI2RefDiagram xmi2refdiag = new XMI2RefDiagram(diag, Mapper);
			xmi2refdiag.createSubElements();
		}
	}
	
	private static PrimitiveType createIntegerPrimitive()
	{
		PrimitiveType integer = factory.createPrimitiveType();
		integer.setName("Integer");
		return integer;
	}
	
	private static PrimitiveType createBooleanPrimitive()
	{
		PrimitiveType bool = factory.createPrimitiveType();
		bool.setName("Boolean");
		return bool;
	}
	
	private static PrimitiveType createStringPrimitive()
	{
		PrimitiveType str = factory.createPrimitiveType();
		str.setName("String");
		return str;
	}
	
	private static PrimitiveType createUnlimitedNaturalPrimitive()
	{
		PrimitiveType unlnatural = factory.createPrimitiveType();
		unlnatural.setName("Unlimited Natural");
		return unlnatural;
	}
	
	public static List<XMI2RefConstraint> getConstraints()
	{
		return constraints;
	}
	
	public static List<XMI2RefDiagram> getDiagrams()
	{
		return diagrams;
	}
}
