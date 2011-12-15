package br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base;

import RefOntoUML.DirectedBinaryAssociation;

public class AlloyDatatypeRelationship extends AlloyDirectedBinaryRelationship
{
	private static int unnamedDTRelCounter = 0;
	
	//CLEANUP public AlloyDatatypeRelationship(DatatypeRelationship rel, AlloyProperty t, AlloyProperty s)
	public AlloyDatatypeRelationship(DirectedBinaryAssociation rel, AlloyProperty t, AlloyProperty s)
	{
		super(rel,t,s);
		if (rel.getName() == null) setGenericName();
	}

	public String getName() 
	{
		//CLEANUP return ((DatatypeRelationship)relationship).getName();
		return ((DirectedBinaryAssociation)relationship).getName();
	}

	protected void setGenericName() 
	{
		//CLEANUP ((DatatypeRelationship) this.relationship).setName("DatatypeRelationship"+unnamedDTRelCounter);
		((DirectedBinaryAssociation) this.relationship).setName("DatatypeRelationship"+unnamedDTRelCounter);
		unnamedDTRelCounter++;		
	}

	
}
