package br.ufes.inf.nemo.ootos.ocl2owl_swrl.tags;

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
	
	/**
	 * This function verifies if a stereotype is a tag related to object properties
	 * 
	 * @param ctStereotype
	 */
	public static Boolean isObjectPropertyTag(String ctStereotype){
		if(	Tag.functional.toString().equals(ctStereotype) ||
			Tag.inversefunctional.toString().equals(ctStereotype) ||
			Tag.transitive.toString().equals(ctStereotype) ||
			Tag.symmetric.toString().equals(ctStereotype) ||
			Tag.reflexive.toString().equals(ctStereotype) ||
			Tag.irreflexive.toString().equals(ctStereotype) ||
			Tag.subrelationof.toString().equals(ctStereotype) )
		{
			return true;
		}
		return false;
	}
}