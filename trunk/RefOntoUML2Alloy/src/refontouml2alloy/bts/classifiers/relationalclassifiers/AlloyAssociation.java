package refontouml2alloy.bts.classifiers.relationalclassifiers;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyRelationship;
import RefOntoUML.Association;

public class AlloyAssociation extends AlloyRelationship
{
	private static int unnamedAssociationCounter = 0;
	public AlloyAssociation(Association rel) 
	{
		super(rel);
		if (rel.getName() == null) setGenericName();
	}
	@Override
	protected void setGenericName() 
	{
		((Association) this.relationship).setName("relation"+unnamedAssociationCounter);
		unnamedAssociationCounter++;		
	}
	@Override
	public String getName() 
	{
		return ((Association) this.relationship).getName();
	}

	@Override
	public void writeDeclaration(FileWriter out) 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
    public void writeStateRules(FileWriter out) throws IOException
    {
	    // TODO Auto-generated method stub
	    
    }

}