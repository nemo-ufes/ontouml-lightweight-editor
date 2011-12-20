package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.Meronymic;
import br.ufes.inf.nemo.ontouml.transformation.v2.classifiers.AlloySigClass;
import br.ufes.inf.nemo.ontouml.transformation.v2.classifiers.AlloySigClassifier;

//SigMeronymics will only happen for Essential and Inseparable Meronymics
public class AlloySigMeronymic extends AlloyStaticMeronymic implements AlloySigRelationship, AlloySigRelationshipParent
{
	//final private AlloySigRelationshipImplementation asri;
	public AlloySigMeronymic(Meronymic rel, AlloyProperty t, AlloyProperty s) 
	{
		super(rel, t, s);
	}
	public AlloySigClassifier getHolderSigClassifier()
	{
		if(((Meronymic)this.relationship).isIsEssential())
			return (AlloySigClassifier) this.getSource().getEndType();
		else
			return (AlloySigClassifier) this.getTarget().getEndType();
	}
	public AlloySigClass getHolderSigClass()
	{
		return (AlloySigClass) getHolderSigClassifier();
	}
	public AlloySigClass getHoldedClassifier()
	{
		if(((Meronymic)this.relationship).isIsEssential())
			return (AlloySigClass) this.getTarget().getEndType();
		else
			return (AlloySigClass) this.getSource().getEndType();
	}
	public String getStateName()
	{
		return "("+getHolderSigClass().getName()+":>exists)."+this.getName();
	}
	@Override
	public AlloyProperty getStaticProperty() 
	{
		
		return null;
	}
	@Override
	public void writeTemporalCoExistenceConstraint(Writer out)
  throws IOException
  {
		//TODO:implement
  }
	@Override
	public void writePredicateShareability(Writer out) throws IOException
	{
		out.write("pred "+this.getName()+"_shares_pontual \n{\n\tone holded : "+getHoldedClassifier().getAtomNames()+" | one holder1,holder2 : "+getHolderSigClass().getAtomNames()+" | one s: State | holder1+holder2 in s.exists and holded in holder1."+this.getName()+"and holded in holder2."+this.getName()+"\n}\n");
		//out.write("pred "+this.getName()+"_shares_in_time \n{\n\tone holded : "+getHoldedClassifier().getAtomNames()+" | one holder1,holder2 : "+getHolderSigClass().getAtomNames()+" | whole1->p+whole2->p in State."+this.getName());
		//out.write("pred "+this.getName()+"_doesnt_share_pontual \n{\n\tone holded : "+getHoldedClassifier().getAtomNames()+" | one holder1,holder2 : "+getHolderSigClass().getAtomNames()+" | no s: State | whole1->p+whole2->p in s."+this.getName());
		//out.write("pred "+this.getName()+"_doesnt_share_in_time \n{\n\tone holded : "+getHoldedClassifier().getAtomNames()+" | one holder1,holder2 : "+getHolderSigClass().getAtomNames()+" | no s:State | whole1->p+whole2->p in s."+this.getName());
	}
	
}
