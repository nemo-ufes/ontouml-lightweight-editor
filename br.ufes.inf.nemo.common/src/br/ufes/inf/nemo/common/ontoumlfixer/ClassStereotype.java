package br.ufes.inf.nemo.common.ontoumlfixer;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Category;
import RefOntoUML.Collective;
import RefOntoUML.DataType;
import RefOntoUML.Kind;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.NominalQuality;
import RefOntoUML.NonPerceivableQuality;
import RefOntoUML.PerceivableQuality;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;

public enum ClassStereotype implements Stereotype {
	KIND {
		@Override
		public String toString() {
	        return "Kind";
	    }
	}, 
	SUBKIND {
		@Override
		public String toString() {
	        return "Subkind";
	    }
	},
	COLLECTIVE {
		@Override
		public String toString() {
	        return "Collective";
	    }
	},
	QUANTITY {
		@Override
		public String toString() {
	        return "Quantity";
	    }
	}, 
	PHASE {
		@Override
		public String toString() {
	        return "Phase";
	    }
	}, 
	ROLE {
		@Override
		public String toString() {
	        return "Role";
	    }
	}, 
	ROLEMIXIN {
		@Override
		public String toString() {
	        return "RoleMixin";
	    }
	}, 
	CATEGORY {
		@Override
		public String toString() {
	        return "Category";
	    }
	}, 
	MIXIN {
		@Override
		public String toString() {
	        return "Mixin";
	    }
	}, 
	RELATOR {
		@Override
		public String toString() {
	        return "Relator";
	    }
	}, 
	MODE {
		@Override
		public String toString() {
	        return "Mode";
	    }
	}, 
	DATATYPE {
		@Override
		public String toString() {
	        return "DataType";
	    }
	}, 
	PRIMITIVETYPE {
		@Override
		public String toString() {
	        return "PrimitiveType";
	    }
	}, 
	PERCEIVABLEQUALITY {
		@Override
		public String toString() {
	        return "PerceivableQuality";
	    }
	},
	NONPERCEIVABLEQUALITY {
		@Override
		public String toString() {
	        return "NonPerceivableQuality";
	    }
	}, 
	NOMINALQUALITY {
		
		@Override
		public String toString() {
	        return "NominalQuality";
	    }
		
	};
	
	static {
		KIND.metaClass = Kind.class;
		SUBKIND.metaClass = SubKind.class;
		COLLECTIVE.metaClass = Collective.class;
		QUANTITY.metaClass = Quantity.class;
		PHASE.metaClass = Phase.class;
		ROLE.metaClass = Role.class;
		ROLEMIXIN.metaClass = RoleMixin.class;
		CATEGORY.metaClass = Category.class;
		MIXIN.metaClass = Mixin.class;
		RELATOR.metaClass = Relator.class;
		MODE.metaClass = Mode.class;
		DATATYPE.metaClass = DataType.class;
		PRIMITIVETYPE.metaClass = PrimitiveType.class;
		PERCEIVABLEQUALITY.metaClass = PerceivableQuality.class;
		NONPERCEIVABLEQUALITY.metaClass = NonPerceivableQuality.class;
		NOMINALQUALITY.metaClass = NominalQuality.class;
	}
	
	
	private Class<? extends EObject> metaClass;
	
	@Override
	public Class<? extends EObject> getMetaclass(){
		return metaClass;
	}
	
	public static ClassStereotype[] getClassStereotypes(){
		 ClassStereotype[] array = {CATEGORY, COLLECTIVE, KIND, MIXIN, MODE, NOMINALQUALITY, NONPERCEIVABLEQUALITY, PERCEIVABLEQUALITY, PHASE, QUANTITY, RELATOR, ROLE, ROLEMIXIN, SUBKIND};
		 return array;
	}
}