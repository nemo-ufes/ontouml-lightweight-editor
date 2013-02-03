package br.ufes.inf.nemo.common.resource;

import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Collective;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.Kind;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;

public class TypeName {
	/*Returns a string with the object type name*/
	public static String getTypeName (Object c){
		
		/*Classes*/
		if (c instanceof Kind)
			return "Kind";
		if (c instanceof Role)
			return "Role";
		if (c instanceof Phase)
			return "Phase";
		if (c instanceof SubKind)
			return "Subkind";
		if (c instanceof Category)
			return "Category";
		if (c instanceof RoleMixin)
			return "RoleMixin";
		if (c instanceof Mixin)
			return "Mixin";
		if (c instanceof Collective)
			return "Collective";
		if (c instanceof Quantity)
			return "Quantity";
		if (c instanceof Mode)
			return "Mode";
		if (c instanceof Relator)
			return "Relator";
		if (c instanceof DataType)
			return "DataType";
		
		/*Relations*/
		if (c instanceof Mediation)
			return "Mediation";
		if (c instanceof FormalAssociation)
			return "Formal";
		if (c instanceof MaterialAssociation)
			return "Material";
		if (c instanceof componentOf)
			return "componentOf";
		if (c instanceof subCollectionOf)
			return "subCollectionOf";
		if (c instanceof subQuantityOf)
			return "subQuantityOf";
		if (c instanceof memberOf)
			return "memberOf";
		if (c instanceof Characterization)
			return "Characterization";
		if (c instanceof Association)
			return "Association";
		if (c instanceof Generalization)
			return "Generalization";
		if (c instanceof Derivation)
			return "Derivation";
		
		else
			return "Unknown Type";

	}
}
