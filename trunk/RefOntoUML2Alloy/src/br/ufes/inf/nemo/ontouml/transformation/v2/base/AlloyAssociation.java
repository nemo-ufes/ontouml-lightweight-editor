package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.Association;

public class AlloyAssociation extends AlloyRelationship
{
	private static int unnamedAssociationCounter = 0;
	public AlloyAssociation(Association rel) 
	{
		super(rel);
		if (rel.getName() == null) setGenericName();
	}
	
	protected void setGenericName() 
	{
		((Association) this.relationship).setName("relation"+unnamedAssociationCounter);
		unnamedAssociationCounter++;		
	}

	public String getName() 
	{
		return ((Association) this.relationship).getName();
	}

	
	public void printExtraConstraints(Writer out) throws IOException 
	{
		// TODO Auto-generated method stub
		
	}

	public void writeDeclaration(Writer out) 
	{
		// TODO Auto-generated method stub
		
	}

}