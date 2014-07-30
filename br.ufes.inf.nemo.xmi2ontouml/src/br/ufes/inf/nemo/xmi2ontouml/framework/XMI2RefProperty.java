package br.ufes.inf.nemo.xmi2ontouml.framework;

import org.eclipse.emf.ecore.util.EcoreUtil;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Derivation;
import RefOntoUML.DirectedBinaryAssociation;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Mode;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import br.ufes.inf.nemo.xmi2ontouml.Creator;
import br.ufes.inf.nemo.xmi2ontouml.util.OntoUMLError;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public class XMI2RefProperty extends XMI2RefNamedElement
{
	protected static boolean autoGenerateNames = false;
	
	protected static boolean autoGenerateCardinality = true;
	
	public XMI2RefProperty (Object XMIElement, XMIParser mapper) throws Exception
	{
		super(XMIElement, mapper, factory.createProperty());
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
			((Property)RefOntoUMLElement).setType((Type) elemMap.getElement(hashProp.get("type")));
			
			// Makes sure the Properties (memberEnds) are added in the correct order
	    	// to be in accordance to the OntoUML specification
			Property prop = (Property) RefOntoUMLElement;
			if (prop.getAssociation() != null && prop.getAssociation() instanceof DirectedBinaryAssociation)
			{
				DirectedBinaryAssociation dbassoc = (DirectedBinaryAssociation) prop.getAssociation();
				if (dbassoc.getMemberEnd().size() == 2 && dbassoc.getMemberEnd().get(0).getType() != null && dbassoc.getMemberEnd().get(1).getType() != null)
				{
					if ((dbassoc instanceof Mediation && !(((Mediation)dbassoc).relator() instanceof Relator)) ||
			    			(dbassoc instanceof Characterization && !(((Characterization)dbassoc).characterizing() instanceof Mode)) ||
			    			(dbassoc instanceof Derivation && !(((Derivation)dbassoc).material() instanceof MaterialAssociation)) ||
			    			dbassoc.targetEnd().getAggregation() == AggregationKind.COMPOSITE ||
			    			dbassoc.targetEnd().getAggregation() == AggregationKind.SHARED)
					{
						if (dbassoc.getMemberEnd().get(0) == prop)
						{
							dbassoc.getMemberEnd().remove(prop);
							dbassoc.getMemberEnd().add(1, prop);
						} else
						{
							dbassoc.getMemberEnd().remove(prop);
							dbassoc.getMemberEnd().add(0, prop);
						}
						
						//And also sets the readOnly property true for the following associations,
						//accordingly to the OntoUML specification
						if (dbassoc instanceof Mediation || dbassoc instanceof Characterization || dbassoc instanceof Derivation)
							dbassoc.getMemberEnd().get(1).setIsReadOnly(true);
			    	}
				}
				
				
			}
			
			if (autoGenerateNames && (((Property)RefOntoUMLElement).getName() == null || ((Property)RefOntoUMLElement).getName().equals("")))
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
				Association assoc = (Association) RefOntoUMLElement.eContainer();
				String source = assoc.getMemberEnd().get(0).getType() == null ? null : assoc.getMemberEnd().get(0).getType().getName();
				String target = assoc.getMemberEnd().get(1).getType() == null ? null : assoc.getMemberEnd().get(1).getType().getName();
				System.err.println("Debug: removing association with missing end ("+assoc.getName()+
						" <<"+assoc.getClass().toString().replace("class RefOntoUML.impl.", "")+">>"+" | "+ "Links: "+source+" -> "+target+")");
	    		EcoreUtil.remove(RefOntoUMLElement);
	    		EcoreUtil.remove(assoc);
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
