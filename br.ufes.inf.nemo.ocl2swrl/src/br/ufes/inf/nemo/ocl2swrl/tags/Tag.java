package br.ufes.inf.nemo.ocl2swrl.tags;

public enum Tag {
	Functional, InverseFunctional, Transitive, Symmetric, Asymmetric, Reflexive, Irreflexive,//this cases cover the characteristcs 
	SubRelationOf,//this case cover derived relations that will be included as sub-relation of other relation
	Derive,//this case cover derivation rules wrote in invariants with implies operator
	Cardinality;//this case cover rules to specify cardinalities of relations
	
	public static Boolean isTag(String ctStereotype){
		Boolean isTag = true;
		try {
			@SuppressWarnings("unused")
			Tag tag = Tag.valueOf(ctStereotype);
		} catch (Exception e) {
			isTag = false;
		}
		return isTag;
	}
}