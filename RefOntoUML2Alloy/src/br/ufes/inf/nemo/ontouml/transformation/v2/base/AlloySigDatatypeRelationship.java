package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.DirectedBinaryAssociation;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloySigClassifier;

public class AlloySigDatatypeRelationship extends AlloyReadOnlyDatatypeRelationship implements AlloySigRelationship, AlloySigRelationshipParent
{
	//CLEANUP public AlloySigDatatypeRelationship(DatatypeRelationship rel, AlloyProperty t, AlloyProperty s) 
	public AlloySigDatatypeRelationship(DirectedBinaryAssociation rel, AlloyProperty t, AlloyProperty s)
	{
		super(rel, t, s);
		
	}
	@Override
	public void writeDeclaration(Writer out) throws IOException 
	{
		getDelegatee().writeDeclaration(out);
	}
	
	public AlloyProperty getStaticProperty()
	{
		if (targetIsReadOnly())return target ;
		else if(sourceIsReadOnly()) return source;
		return null;
	}
	public AlloySigClassifier getHolderSigClassifier()
	{
		return ((AlloySigRelationshipImplementation)delegatee).getHolderSigClassifier();
	}
	@Override
	protected AlloySigRelationshipImplementation setDelegatee()
	{
		return new AlloySigRelationshipImplementation(this);
	}
	@Override
	protected AlloySigRelationshipImplementation getDelegatee()
  {
	    return (AlloySigRelationshipImplementation) delegatee;
  }
	@Override
	public void writeTemporalCoExistenceConstraint(Writer out)
  throws IOException
  {
		//theres no need for temporal co existence constraint for datatype relationships
  }
}