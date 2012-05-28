package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.atoms.AlloySigClassifier;
import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyDatatypeRelationship;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigDatatypeRelationship;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import RefOntoUML.Association;

public class AlloyReadOnlyDatatypeRelationship extends AlloyDatatypeRelationship implements AlloyReadOnlyRelationship, AlloyStaticRelationshipParent
{
	final AlloyReadOnlyRelationshipImplementation delegatee;
	public AlloyReadOnlyDatatypeRelationship(Association rel,
			AlloyAtomProperty t, AlloyAtomProperty s) 
	{
		super(rel, t, s);
		//setDelegatee will return a AlloyStaticRelationshipImplementation in this classs and an AlloySigRelationshipImplementation in the subclass
		delegatee = setDelegatee();
	}

	@Override
	public void writeTemporalCoExistenceConstraint(FileWriter out) throws IOException 
	{
		//rationale is: if a->b exist, and, in the next state, the constrained atom still belongs to that class, the relation should still exist.
		//we do not check for source readonly the source side of a datatype relationship is not navigable
		if(getTarget().isReadOnly())
		{
			out.write("\t//Read-only contraints for "+this.getName()+"\n");
			out.write("\tall a: "+ getName()+"."+ getTarget().getEndType().getStateName() + "|" + "all b: "+ getName()+"["+ getSource().getEndType().getStateName()+ "] | " + 
					"let x = a->b | (x in "+ getName()+" and a in this.next.@"+getSource().getEndType().getStateName()+") implies "+ "x in this<:next.@"+ getName()+"\n" );
		}		
	}
	//Besides normal rules, static relationships have to enforce that the two atoms exist simultaneously
	@Override
	public void writeStateRules(FileWriter out) throws IOException
	{
		super.writeStateRules(out);
		this.writeTemporalCoExistenceConstraint(out);		
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
	public AlloyAtomProperty getSource()
	{
		return (AlloyAtomProperty) super.getSource();
	}
	@Override
	public AlloyAtomProperty getTarget()
	{
		return (AlloyAtomProperty) super.getTarget();
	}
	
	static public AlloyDatatypeRelationship factory(Association rel, AlloyAtomProperty t, AlloyAtomProperty s)
	{
		if(t.isReadOnly() && s.getEndType() instanceof AlloySigClassifier)
		{
			return AlloySigDatatypeRelationship.factory(rel,t,s);
		}
		else return new AlloyReadOnlyDatatypeRelationship(rel,t,s);
	}
	
	
		
}