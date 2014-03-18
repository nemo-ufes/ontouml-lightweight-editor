package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Derivation;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Mode;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import br.ufes.inf.nemo.xmi2ontouml.Creator;
import br.ufes.inf.nemo.xmi2ontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2ontouml.util.OntoUMLError;

public class XMI2RefProperty extends XMI2RefNamedElement
{
	protected static boolean autoGenerateNames = false;
	
	protected static boolean autoGenerateCardinality = true;
	
	public XMI2RefProperty (Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = factory.createProperty();
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		commonTasks();
	}

	@Override
	protected void deal()
	{
		super.deal();
		
		Property property = (Property) RefOntoUMLElement;
		
		property.setIsDerived(Boolean.parseBoolean((String)hashProp.get("isderived")));
		property.setIsDerivedUnion(Boolean.parseBoolean((String)hashProp.get("isderivedunion")));
		property.setDefault((String)hashProp.get("default"));
		property.setAggregation(AggregationKind.get((String)hashProp.get("aggregation")));
		
		//Structural Feature properties
		property.setIsReadOnly(Boolean.parseBoolean((String)hashProp.get("isreadonly")));
		
		//Feature properties
		property.setIsStatic(Boolean.parseBoolean((String)hashProp.get("isstatic")));
		
		//Redefinable Element properties
		property.setIsLeaf(Boolean.parseBoolean((String)hashProp.get("isleaf")));
		
		//Multiplicity Element properties
//		if (hashProp.get("lower") != null && hashProp.get("upper") != null)
//		{
			LiteralUnlimitedNatural upper = factory.createLiteralUnlimitedNatural();
			RefOntoUML.LiteralInteger lower = factory.createLiteralInteger();
			try
			{
				upper.setValue((Integer.parseInt((String)hashProp.get("upper"))));
				property.setUpperValue(upper);
				
				if (hashProp.get("lower").equals("-1"))
					lower.setValue(0);
				else
					lower.setValue((Integer.parseInt((String)hashProp.get("lower"))));
				property.setLowerValue(lower);
			}
			catch (NumberFormatException nfe)
			{
				Creator.warningLog += OntoUMLError.wrongMultiplicityFormat(property);
				
				if (autoGenerateCardinality) { upper.setValue(1); lower.setValue(1); }
			}
//		} else
//			Creator.warningLog += OntoUMLError.undefinedMultiplicityError(property);
		
		property.setIsOrdered(Boolean.parseBoolean((String)hashProp.get("isordered")));
		if (hashProp.containsKey("isunique"))
			property.setIsUnique(Boolean.parseBoolean((String)hashProp.get("isunique")));
	}

	@Override
	public void dealReferences()
	{
		try
		{
			((Property)RefOntoUMLElement).setType((Type) elemMap.get((String)hashProp.get("type")));
			
			// Makes sure the Properties (memberEnds) are added in the correct order
	    	// to be in accordance to the OntoUML specification
			Property property = (Property) RefOntoUMLElement;
			Association association = property.getAssociation();
			if (association != null)
			{
				List<Property> memberEnds = association.getMemberEnd();
				if (memberEnds.size() == 2 && memberEnds.get(0).getType() != null && memberEnds.get(1).getType() != null)
				{
					if ((association instanceof Mediation && !(memberEnds.get(0).getType() instanceof Relator)) ||
			    			(association instanceof Characterization && !(memberEnds.get(0).getType() instanceof Mode)) ||
			    			(association instanceof Derivation && !(memberEnds.get(0).getType() instanceof MaterialAssociation)) ||
			    			memberEnds.get(1).getAggregation() == AggregationKind.COMPOSITE ||
			    			memberEnds.get(1).getAggregation() == AggregationKind.SHARED)
					{
						if (memberEnds.get(0) == property)
						{
							association.getMemberEnd().remove(property);
							association.getMemberEnd().add(1, property);
						} else
						{
							association.getMemberEnd().remove(property);
							association.getMemberEnd().add(0, property);
						}
			    	}
				}
			}
			//And also sets the readOnly property true for the following associations,
			//accordingly to the OntoUML specification
			if ((property.eContainer() instanceof Mediation && !(property.getType() instanceof Relator)) ||
					(property.eContainer() instanceof Characterization && !(property.getType() instanceof Mode)) ||
					(property.eContainer() instanceof Derivation && property.getType() instanceof Relator))
				property.setIsReadOnly(true);
			
			if (autoGenerateNames && (((Property)RefOntoUMLElement).getName() == null) || ((Property)RefOntoUMLElement).getName().equals(""))
				((Property)RefOntoUMLElement).setName(((Property)RefOntoUMLElement).getType().getName().toLowerCase());
		}
		catch (NullPointerException | IllegalArgumentException e)
		{
			Creator.warningLog += OntoUMLError.undefinedTypeError(((Property)RefOntoUMLElement));
			
			if (!ignoreErrorElements)
				throw e;
		}
		finally
		{
			if (((Property)RefOntoUMLElement).getType() == null && RefOntoUMLElement.eContainer() instanceof Association && ignoreErrorElements)
			{
				System.out.println("Debug: removing property with error ("+((Property)RefOntoUMLElement).getName()+" | Container: "+RefOntoUMLElement.eContainer()+")");
	    		EcoreUtil.remove(RefOntoUMLElement);
			}
		}
	}

	public static boolean isAutoGenerateNames() {
		return autoGenerateNames;
	}

	public static void setAutoGenerateNames(boolean autoGenerateNames) {
		XMI2RefProperty.autoGenerateNames = autoGenerateNames;
	}

	public static boolean isAutoGenerateCardinality() {
		return autoGenerateCardinality;
	}

	public static void setAutoGenerateCardinality(boolean autoGenerateCardinality) {
		XMI2RefProperty.autoGenerateCardinality = autoGenerateCardinality;
	}
}
