package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;

public class AlloyStaticRelationshipImplementation implements AlloyStaticRelationship
{
	final AlloyStaticRelationshipParent parent;
	public AlloyStaticRelationshipImplementation(AlloyStaticRelationshipParent h)
	{
		parent = h;
	}
	@Override
	public void writeTemporalCoExistenceConstraint(Writer out) throws IOException
	{
		//rationale is: if a->b exist, and, in the next state, the constrained atom still belongs to that class, the relation should still exist.
		if(parent.getSource().isReadOnly())
		{
			out.write("all a: "+ parent.getName()+"."+ parent.getTarget().getEndType().getStateName() + "|" + "all b: "+ parent.getName()+"["+ parent.getSource().getEndType().getStateName()+ "] | " + 
					"let x = a->b | (x in "+ parent.getName()+" and b in this.next.@"+parent.getTarget().getEndType().getStateName()+") implies "+ "x in this<:next.@"+ parent.getName() );
		}
		if(parent.getTarget().isReadOnly())
		{
			out.write("all a: "+ parent.getName()+"."+ parent.getTarget().getEndType().getStateName() + "|" + "all b: "+ parent.getName()+"["+ parent.getSource().getEndType().getStateName()+ "] | " + 
					"let x = a->b | (x in "+ parent.getName()+" and a in this.next.@"+parent.getSource().getEndType().getStateName()+") implies "+ "x in this<:next.@"+ parent.getName() );
		}
	}	
}
