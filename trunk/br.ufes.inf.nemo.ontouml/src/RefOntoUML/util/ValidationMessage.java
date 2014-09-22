package RefOntoUML.util;

public class ValidationMessage
{
	public static String getFinalMessage (String message)
	{
		String[] out = ParseMessage(message);
		String d = getDescription(out[0]);
		
		return out[1] + " - " + d;
	}
	
	public static String[] ParseMessage (String message)
	{
		int i1, i2, j1, j2;
		String[] out = new String[2];
		out[0] = "";
		out[1] = "";
		
		i1 = message.indexOf("'", 0);
		i2 = message.indexOf("'", i1+1);
		j1 = message.indexOf("'", i2+1);
		j2 = message.indexOf("'", j1+1);
		
		//System.out.println(message);
		//System.out.println(i1 + " " + i2 + " " + j1 + " " + j2);
		
		// The constraint id
		out[0] += message.substring(i1+1, i2);
		// The model element
		out[1] += message.substring(j1+1, j2);

		return out;
	}
	
	public static String getDescription (String constraintid)
	{
		String description = null;
		
		if (constraintid.compareTo("SortalClassConstraint1") == 0)
		{
			description = "Every non abstract Sortal must have a Substance Sortal ancestor (or be a Substance Sortal)";
		}
		else if (constraintid.compareTo("SubstanceSortalConstraint1") == 0)
		{
			description = "Every non abstract Sortal must have a Substance Sortal ancestor (or be a Substance Sortal)";
		}
		else if (constraintid.compareTo("SubstanceSortalConstraint2a") == 0)
		{
			description = "Every class must not have more than one Substance Sortal ancestor";
		}
		else if (constraintid.compareTo("SubstanceSortalConstraint2b") == 0)
		{
			description = "A Substance Sortal cannot have a Rigid Sortal parent";
		}
		else if (constraintid.compareTo("MixinClassConstraint1") == 0)
		{
			description = "A MixinClass cannot have a Sortal parent (kind, quantity, collective, subkind, phase, role)";
		}
		else if (constraintid.compareTo("MixinClassConstraint2") == 0)
		{
			description = "A MixinClass is always Abstract";
		}
		else if (constraintid.compareTo("RigidSortalClassConstraint1") == 0)
		{
			description = "A RigidSortalClass cannot have an Anti-Rigid parent (role, phase, roleMixin)";
		}
		else if (constraintid.compareTo("CollectiveConstraint1") == 0)
		{
			description = "All the parts of an extensional Collective are essential";
		}
		else if (constraintid.compareTo("PhaseConstraint2") == 0)
		{
			description = "A Phase must be grouped in exactly one {disjoint, complete} Generalization Set with other Phases";
		}
		else if (constraintid.compareTo("RoleConstraint2") == 0)
		{
			description = "A Role must be connected (directly or indirectly) to a Mediation";
		}
		else if (constraintid.compareTo("RoleMixinConstraint1") == 0)
		{
			description = "A RoleMixin must be connected (directly or indirectly) to a Mediation";
		}
		else if (constraintid.compareTo("CategoryConstraint1") == 0)
		{
			description = "A Category cannot have a RoleMixin parent";
		}
		else if (constraintid.compareTo("MixinConstraint1") == 0)
		{
			description = "A Mixin cannot have a RoleMixin parent";
		}
		else if (constraintid.compareTo("IntrinsicMomentConstraint1") == 0)
		{
			description = "An IntrinsicMoment must be connected (directly or indirectly) to a Characterization";
		}
//		else if (constraintid.compareTo("ModeConstraint1") == 0)
//		{
//			description = "A Mode must be connected (directly or indirectly) to a Characterization";
//		}
		else if (constraintid.compareTo("RelatorConstraint1") == 0)
		{
			description = "A Relator must be connected (directly or indirectly) to a Mediation";
		}
		else if (constraintid.compareTo("RelatorConstraint2") == 0)
		{
			description = "The sum of the minimum cardinalities of the mediated ends must be greater or equal to 2";
		}
		else if (constraintid.compareTo("DirectedBinaryAssociationConstraint1") == 0)
		{
			description = "DirectedBinaryAssociations are always binary";
		}
		else if (constraintid.compareTo("MeronymicConstraint1") == 0)
		{
			description = "The sum of the minimum cardinalities of the parts must be greater or equal to 2";
		}
		else if (constraintid.compareTo("MeronymicConstraint2a") == 0)
		{
			description = "AntiRigid whole implies that specific part dependence with de re modality is not possible";
		}
		else if (constraintid.compareTo("MeronymicConstraint2b") == 0)
		{
			description = "Specific part dependence with de re modality implies specific part dependence with de dicto modality";
		}
		else if (constraintid.compareTo("MeronymicConstraint3") == 0)
		{
			description = "Specific whole dependence with de re modality implies specific whole dependence with de dicto modality";
		}
		else if (constraintid.compareTo("subQuantityOfConstraint1a") == 0)
		{
			description = "subQuantityOf relates individuals that are quantities (whole)";
		}
		else if (constraintid.compareTo("subQuantityOfConstraint1b") == 0)
		{
			description = "subQuantityOf relates individuals that are quantities (part)";
		}
		else if (constraintid.compareTo("subQuantityOfConstraint2") == 0)
		{
			description = "A part is always non-shareable";
		}
		else if (constraintid.compareTo("subQuantityOfConstraint3") == 0)
		{
			description = "A part is always essential";
		}
		else if (constraintid.compareTo("subQuantityOfConstraint4") == 0)
		{
			description = "The maximum cardinality of the part end is equal to 1";
		}
		else if (constraintid.compareTo("subCollectionOfConstraint1a") == 0)
		{
			description = "subCollectionOf relates individuals that are collectives (whole)";
		}
		else if (constraintid.compareTo("subCollectionOfConstraint1b") == 0)
		{
			description = "subCollectionOf relates individuals that are collectives (part)";
		}
		else if (constraintid.compareTo("subCollectionOfConstraint2") == 0)
		{
			description = "The maximum cardinality of the part end is equal to 1";
		}
		else if (constraintid.compareTo("memberOfConstraint1a") == 0)
		{
			description = "memberOf relates individuals that are functional complexes or collectives as parts of individuals that are collectives (whole)";
		}
		else if (constraintid.compareTo("memberOfConstraint1b") == 0)
		{
			description = "memberOf relates individuals that are functional complexes or collectives as parts of individuals that are collectives (part)";
		}
		else if (constraintid.compareTo("memberOfConstraint2") == 0)
		{
			description = "memberOf with essential parthood implies an extensional whole";
		}
		else if (constraintid.compareTo("componentOfConstraint1a") == 0)
		{
			description = "componentOf relates individuals that are functional complexes (whole)";
		}
		else if (constraintid.compareTo("componentOfConstraint1b") == 0)
		{
			description = "componentOf relates individuals that are functional complexes (part)";
		}
		else if (constraintid.compareTo("DependencyRelationshipConstraint1") == 0)
		{
			description = "The source end minimum cardinality must be greater or equal to 1";
		}
		else if (constraintid.compareTo("DependencyRelationshipConstraint2") == 0)
		{
			description = "The target end is read only";
		}
		else if (constraintid.compareTo("CharacterizationConstraint1") == 0)
		{
			description = "The source must be an IntrinsicMoment";
		}
		else if (constraintid.compareTo("CharacterizationConstraint2") == 0)
		{
			description = "The characterized end cardinality is exactly one";
		}
		else if (constraintid.compareTo("MediationConstraint1") == 0)
		{
			description = "The source must be a Relator";
		}
		else if (constraintid.compareTo("MediationConstraint2") == 0)
		{
			description = "The mediated end minimum cardinality must be greater or equal to 1";
		}
		else if (constraintid.compareTo("DerivationConstraint1a") == 0)
		{
			description = "The source must be a Material Association";
		}
		else if (constraintid.compareTo("DerivationConstraint1b") == 0)
		{
			description = "The target must be a Relator";
		}
		else if (constraintid.compareTo("DerivationConstraint2") == 0)
		{
			description = "The relator end cardinality is exactly one";
		}
		else if (constraintid.compareTo("MaterialAssociationConstraint1") == 0)
		{
			description = "Every MaterialAssociation must be connected to exactly one Derivation";
		}
		else if (constraintid.compareTo("MaterialAssociationConstraint2") == 0)
		{
			description = "The minimum cardinality of every end must be greater or equal to 1";
		}
		else if (constraintid.compareTo("MaterialAssociationConstraint3") == 0)
		{
			description = "A MaterialAssociation must be derived";
		}
		
		//===============================================
		//UML Constraints
		//===============================================
		else if (constraintid.compareTo("LowerAndUpperBound") == 0)
		{
			description = "The upper value of an Attribute and/or Association End must not be smaller than the lower value";
		}
		else if (constraintid.compareTo("GeneralizationConstraint1") == 0)
		{
			description = "An Object Type must only specialize/generalize another Object Type";
		}
		else if (constraintid.compareTo("ClassAttributeConstraint1") == 0)
		{
			description = "An attribute must have a minimum value of 1";
		}
		else if (constraintid.compareTo("DataTypeAttributeConstraint1") == 0)
		{
			description = "An attribute must have a minimum value of 1";
		}
		
		//===============================================
		//Reference Structures Extension
		//===============================================
		else if (constraintid.compareTo("StructurationConstraint1") == 0)
		{
			description = "A The source must be a ReferenceStructure";
		}
		else if (constraintid.compareTo("StructurationConstraint2") == 0)
		{
			description = "The structured end cardinality is exactly one";
		}
		else if (constraintid.compareTo("StructurationConstraint3") == 0)
		{
			description = "The target must be a Quality";
		}
		else if (constraintid.compareTo("OrdinalEnumerationConstraint1") == 0)
		{
			description = "An OrdinalEnumeration should have two or more ordinal literals";
		}
		else if (constraintid.compareTo("NominalStructureConstraint1") == 0)
		{
			description = "A Nominal Structure must be connected to a Structuration";
		}
		else if (constraintid.compareTo("MeasurementDimensionConstraint1") == 0)
		{
			description = "A Measurement Dimension must be part of a Measurement Domain or connected to a Structuration";
		}
		else if (constraintid.compareTo("MeasurementDomainConstraint1") == 0)
		{
			description = "A Measurement Domain must be connected to a Structuration";
		}
		else if (constraintid.compareTo("MeasurementDomainConstraint2") == 0)
		{
			description = "A Measurement Domain is composed by at least two dimensions";
		}
		else if (constraintid.compareTo("ComposedMeasurementRegionConstraint1") == 0)
		{
			description = "A Composed Measurement Region must have more than one measurement region";
		}
		else if (constraintid.compareTo("QualityConstraint1") == 0)
		{
			description = "A Quality must be connected to a Structuration";
		}
		else if (constraintid.compareTo("MeasurableQualityConstraint1") == 0)
		{
			description = "All Reference Structures of a Measurable Quality should be Measurement Reference Structures";
		}
		else if (constraintid.compareTo("NominalQualityConstraint1") == 0)
		{
			description = "All Reference Structures of a Nominal Quality should be Nominal Reference Structures";
		}
		else if (constraintid.compareTo("") == 0)
		{
			description = "";
		}
		else
		{
			description = constraintid;
		}
		
		
		return description;
	}
}
