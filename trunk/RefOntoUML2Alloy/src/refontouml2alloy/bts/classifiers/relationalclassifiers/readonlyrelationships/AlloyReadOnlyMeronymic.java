package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyMeronymic;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import RefOntoUML.Meronymic;

public class AlloyReadOnlyMeronymic extends AlloyMeronymic implements AlloyReadOnlyRelationship, AlloyStaticRelationshipParent
{
	final AlloyReadOnlyRelationshipImplementation delegatee;
	public AlloyReadOnlyMeronymic(Meronymic rel, AlloyAtomProperty t, AlloyAtomProperty s) 
	{
		super(rel, t, s);
		delegatee = setDelegatee();
	}
	
	public AlloyReadOnlyRelationshipImplementation setDelegatee()
    {
	   return new AlloyReadOnlyRelationshipImplementation(this);
    }
	
	public AlloyReadOnlyRelationshipImplementation getDelegatee()
    {
	    return delegatee;
    }
	
	@Override
	public void writeTemporalCoExistenceConstraint(FileWriter out) throws IOException 
	{
		getDelegatee().writeTemporalCoExistenceConstraint(out);		
	}
	//Besides normal rules, static relationships have to enforce that the two atoms exist simultaneously
	@Override
	public void writeStateRules(FileWriter out) throws IOException
	{
		super.writeStateRules(out);
		this.writeTemporalCoExistenceConstraint(out);		
	}
	@Override
	public AlloyAtomProperty getSource()
	{
		return (AlloyAtomProperty) super.getSource();
	}
	@Override
	public AlloyAtomProperty getTarget()
	{
		return (AlloyAtomProperty) super.getTarget();
	}
}