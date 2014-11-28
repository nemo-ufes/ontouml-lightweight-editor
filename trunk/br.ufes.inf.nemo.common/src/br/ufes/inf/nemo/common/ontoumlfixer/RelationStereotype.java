package br.ufes.inf.nemo.common.ontoumlfixer;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Derivation;
import RefOntoUML.FormalAssociation;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Structuration;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;

public enum RelationStereotype implements Stereotype{
	FORMAL {
		@Override
		public String toString() {
	        return "Formal";
	    }
	}, 
	MATERIAL {
		@Override
		public String toString() {
	        return "Material";
	    }
	}, 
	CHARACTERIZATION {
		@Override
		public String toString() {
	        return "Characterization";
	    }
	}, 
	MEDIATION {
		@Override
		public String toString() {
	        return "Mediation";
	    }
	}, 
	DERIVATION {
		@Override
		public String toString() {
	        return "Derivation";
	    }	
	}, 
	ASSOCIATION {
		@Override
		public String toString() {
	        return "Association";
	    }
	}, 
	COMPONENTOF {
		@Override
		public String toString() {
	        return "ComponentOf";
	    }
	}, 
	SUBQUANTITYOF {
		@Override
		public String toString() {
	        return "SubQuantityOf";
	    }
	}, 
	SUBCOLLECTIONOF {
		@Override
		public String toString() {
	        return "SubCollectionof";
	    }
	}, 
	MEMBEROF {
		@Override
		public String toString() {
	        return "MemberOf";
	    }
	}, 
	GENERALIZATION {
		@Override
		public String toString() {
	        return "Generalization";
	    }
	}, 
	STRUCTURATION {
		@Override
		public String toString() {
	        return "Structuration";
	    }
	};
	
	static {
		FORMAL.metaClass = FormalAssociation.class;
		MATERIAL.metaClass = MaterialAssociation.class;
		CHARACTERIZATION.metaClass = Characterization.class;
		MEDIATION.metaClass = Mediation.class;
		DERIVATION.metaClass = Derivation.class;
		ASSOCIATION.metaClass = Association.class;
		COMPONENTOF.metaClass = componentOf.class;
		SUBQUANTITYOF.metaClass = subQuantityOf.class;
		SUBCOLLECTIONOF.metaClass = subCollectionOf.class;
		MEMBEROF.metaClass = memberOf.class;
		GENERALIZATION.metaClass = null; //FIX
		STRUCTURATION.metaClass = Structuration.class;
	}
	
	
	private Class<? extends EObject> metaClass;
	
	@Override
	public Class<? extends EObject> getMetaclass(){
		return metaClass;
	}
	
	public static RelationStereotype[] getAssociationStereotypes(){
		 RelationStereotype[] array = {ASSOCIATION, CHARACTERIZATION, COMPONENTOF, FORMAL, MATERIAL, MEDIATION, MEMBEROF, STRUCTURATION, SUBCOLLECTIONOF, SUBQUANTITYOF};
		 return array;
	}
}
