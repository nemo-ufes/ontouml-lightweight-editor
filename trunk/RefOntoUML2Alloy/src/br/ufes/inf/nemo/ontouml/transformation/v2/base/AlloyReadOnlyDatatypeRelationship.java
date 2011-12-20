package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.DirectedBinaryAssociation;


public class AlloyReadOnlyDatatypeRelationship extends AlloyDatatypeRelationship implements AlloyStaticRelationship, AlloyStaticRelationshipParent
{
	protected final AlloyStaticRelationshipImplementation delegatee;
	//CLEANUP public AlloyReadOnlyDatatypeRelationship(DatatypeRelationship rel, AlloyProperty t, AlloyProperty s) 
	public AlloyReadOnlyDatatypeRelationship(DirectedBinaryAssociation rel, AlloyProperty t, AlloyProperty s)
	{
		super(rel, t, s);
		//setDelegatee will return a AlloyStaticRelationshipImplementation in this classs and an AlloySigRelationshipImplementation in the subclass
		delegatee = setDelegatee();
	}

	@Override
	public void writeTemporalCoExistenceConstraint(Writer out) throws IOException 
	{
		getDelegatee().writeTemporalCoExistenceConstraint(out);		
	}
	
	public void printExtraConstraints(Writer out) throws IOException
	{
		super.printExtraConstraints(out);
		this.writeTemporalCoExistenceConstraint(out);		
	}
	protected AlloyStaticRelationshipImplementation setDelegatee()
	{
		return new AlloyStaticRelationshipImplementation(this);
	}
	protected AlloyStaticRelationshipImplementation getDelegatee()
  {
	    return delegatee;
  }
	
}