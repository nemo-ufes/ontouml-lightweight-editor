package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.Meronymic;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyIsAnAtom;

public class AlloyMeronymic extends AlloyDirectedBinaryRelationship
{
	private static int unnamedMeronymicCounter = 0;
	public AlloyMeronymic(Meronymic rel, AlloyProperty t, AlloyProperty s) 
	{
		super(rel,t,s);
		if (rel.getName() == null) setGenericName();
	}

	protected void setGenericName() 
	{
		((Meronymic) this.relationship).setName("meronymicRelation"+unnamedMeronymicCounter);
		unnamedMeronymicCounter++;		
	}

	public String getName() 
	{
		return ((Meronymic) this.relationship).getName();
	}
	
	public void writePredicateShareability(Writer out) throws IOException
	{
	
		//In this method we print comentaries in the alloy code, use them as comentaries for the method as well
		out.write("// *** "+this.getName()+" validation ***\n\n");
		
		//only one of the opposing predicates/assertions will show an instance.
		
		//
		out.write("//this predicate shows an instance of the model if "+ this.getName()+" does not share parts in a given point in time");
		out.write("pred "+this.getName()+"_doesnt_share_pontual \n{\n\tall p : "+((AlloyIsAnAtom)(this.getTarget().getEndType())).getAtomNames()+" | all disj whole1,whole2 : "+((AlloyIsAnAtom)(this.getSource().getEndType())).getAtomNames()+" | no s: State | whole1->p+whole2->p in s."+this.getName()+"\n}\n");
		out.write("run "+this.getName()+"_doesnt_share_pontual for 5\n\n");
		
		//
		out.write("//this asertion shows an instance of the model where "+ this.getName()+" share parts in a given point in time");
		out.write("assert "+this.getName()+"_doesnt_share_pontual \n{\n\tall p : "+((AlloyIsAnAtom)(this.getTarget().getEndType())).getAtomNames()+" | all disj whole1,whole2 : "+((AlloyIsAnAtom)(this.getSource().getEndType())).getAtomNames()+" | no s: State | whole1->p+whole2->p in s."+this.getName()+"\n}\n");
		out.write("check "+this.getName()+"_doesnt_share_pontual for 5\n");
		
		//TODO: the below are due to existencial dependency? consider the other way around whole->part
		
		//		
		out.write("//this predicate shows an instance of the model if "+ this.getName()+"'s  parts cannot be used by other wholes anytime");
		out.write("pred "+this.getName()+"_shares_in_time \n{\n\tone p : "+((AlloyIsAnAtom)(this.getTarget().getEndType())).getAtomNames()+" | one disj whole1,whole2 : "+((AlloyIsAnAtom)(this.getSource().getEndType())).getAtomNames()+" | whole1->p+whole2->p in State."+this.getName()+"\n}\n");
		out.write("run "+this.getName()+"_shares_in_time for 5\n\n");
		
		//
		out.write("//this asertion shows an instance of the model where "+ this.getName()+" share parts");
		out.write("pred "+this.getName()+"_doesnt_share_in_time \n{\n\tone p : "+((AlloyIsAnAtom)(this.getTarget().getEndType())).getAtomNames()+" | one disj whole1,whole2 : "+((AlloyIsAnAtom)(this.getSource().getEndType())).getAtomNames()+" | no s:State | whole1->p+whole2->p in s."+this.getName()+"\n}\n");
		out.write("run "+this.getName()+"_doesnt_share_in_time for 5\n\n");
		
		//print alloy code comentary
		out.write("\n// *** End of "+this.getName()+" predicates ***\n\n");
	}
} 