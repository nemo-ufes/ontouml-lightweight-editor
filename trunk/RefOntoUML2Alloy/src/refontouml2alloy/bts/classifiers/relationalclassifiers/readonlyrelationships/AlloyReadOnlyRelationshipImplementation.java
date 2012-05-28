package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships;
import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;


public class AlloyReadOnlyRelationshipImplementation implements AlloyReadOnlyRelationship
{
	final AlloyStaticRelationshipParent parent;
	public AlloyReadOnlyRelationshipImplementation(AlloyStaticRelationshipParent h)
	{
		parent = h;
	}
	
	//This method is used to write state rules
	@Override
	public void writeTemporalCoExistenceConstraint(FileWriter out) throws IOException
	{
		if(getParent().getSource().isReadOnly() || getParent().getTarget().isReadOnly())
		{
			out.write("\t//Read-only contraints for "+this.getName()+"\n");
		}
		//rationale is: if a->b exist, and, in the next state, the constrained atom still belongs to that class, the relation should still exist.
		if(getParent().getSource().isReadOnly())
		{
			out.write("\tall a: "+ getParent().getName()+"."+ getParent().getTarget().getEndType().getStateName() + "|" + "all b: "+ getParent().getName()+"["+ getParent().getSource().getEndType().getStateName()+ "] | " + 
					"let x = a->b | (x in "+ getParent().getName()+" and b in this.next.@"+getParent().getTarget().getEndType().getStateName()+") implies "+ "x in this<:next.@"+ getParent().getName()+"\n" );
		}
		if(getParent().getTarget().isReadOnly())
		{
			out.write("\tall a: "+ getParent().getName()+"."+ getParent().getTarget().getEndType().getStateName() + "|" + "all b: "+ getParent().getName()+"["+ getParent().getSource().getEndType().getStateName()+ "] | " + 
					"let x = a->b | (x in "+ getParent().getName()+" and a in this.next.@"+getParent().getSource().getEndType().getStateName()+") implies "+ "x in this<:next.@"+ getParent().getName()+"\n" );
		}
	}
	protected AlloyStaticRelationshipParent getParent()
    {
	    return parent;
    }

	@Override
    public AlloyAtomProperty getSource()
    {
	    return getParent().getSource();
    }

	@Override
    public AlloyAtomProperty getTarget()
    {
	    return getParent().getTarget();
    }

	@Override
    public void writeDeclaration(FileWriter out) throws IOException
    {
		getParent().writeDeclaration(out);
    }

	@Override
    public String getName()
    {
	    return getParent().getName();
    }

	@Override
    public String getStateName()
    {
	    return getParent().getStateName();
    }

	@Override
    public void writeStateRules(FileWriter out) throws IOException
    {
	    getParent().writeStateRules(out);	    
    }	
}
