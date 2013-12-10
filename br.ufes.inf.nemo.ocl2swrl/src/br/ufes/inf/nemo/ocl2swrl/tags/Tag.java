package br.ufes.inf.nemo.ocl2swrl.tags;

public enum Tag {
	functional, inversefunctional, transitive, symmetric, asymmetric, reflexive, irreflexive,//this cases cover the characteristics of a relation 
	subrelationof,//this case cover derived relations that will be included as sub-relation of other relation
	derive,//this case cover derivation rules wrote in invariants with implies operator
	cardinality;//this case cover rules to specify cardinalities of relations
	
	/**
	 * This function verifies if a stereotype is a tag
	 * 
	 * @param ctStereotype
	 */
	public static Boolean isTag(String ctStereotype){
		ctStereotype = ctStereotype.toLowerCase(); 
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