package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.Meronymic;

public class AlloyStaticMeronymic extends AlloyMeronymic implements AlloyStaticRelationship, AlloyStaticRelationshipParent
{
	final AlloyStaticRelationshipImplementation delegatee;
	public AlloyStaticMeronymic(Meronymic rel, AlloyProperty t, AlloyProperty s) 
	{
		super(rel, t, s);
		delegatee = new AlloyStaticRelationshipImplementation(this);
	}
	@Override
	public void writeTemporalCoExistenceConstraint(Writer out) throws IOException 
	{
		delegatee.writeTemporalCoExistenceConstraint(out);		
	}
	public void printExtraConstraints(Writer out) throws IOException
	{
		super.printExtraConstraints(out);
		this.writeTemporalCoExistenceConstraint(out);		
	}
	
}