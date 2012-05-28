package refontouml2alloy.bts.classifiers.relationalclassifiers;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.atoms.AlloyIsAnAtom;
import refontouml2alloy.bts.classifiers.atoms.AlloySigClass;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigMeronymic;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import refontouml2alloy.bts.structuralfeature.AlloyProperty;
import RefOntoUML.Meronymic;

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
		getRelationship().setName("meronymicRelation"+unnamedMeronymicCounter);
		unnamedMeronymicCounter++;		
	}

	public String getName() 
	{
		return getRelationship().getName();
	}
	
	public void writePredicateShareability(FileWriter out) throws IOException
	{
	
		//In this method we print commentaries in the alloy code, use them as commentaries for the method as well
		out.write("// *** "+this.getName()+" validation ***\n\n");
		
		//only one of the opposing predicates/assertions will show an instance.
		
		//
		out.write("//this predicate shows an instance of the model if "+ this.getName()+" does not share parts in a given point in time\n");
		out.write("pred "+this.getName()+"_doesnt_share_pontual \n{\n\tall p : "+((AlloyIsAnAtom)(this.getTarget().getEndType())).getAtomNames()+" | all disj whole1,whole2 : "+((AlloyIsAnAtom)(this.getSource().getEndType())).getAtomNames()+" | no s: State | whole1->p+whole2->p in s."+this.getName()+"\n}\n");
		out.write("run "+this.getName()+"_doesnt_share_pontual for 5\n\n");
		
		//
		out.write("//this asertion shows an instance of the model where "+ this.getName()+" share parts in a given point in time\n");
		out.write("assert "+this.getName()+"_doesnt_share_pontual \n{\n\tall p : "+((AlloyIsAnAtom)(this.getTarget().getEndType())).getAtomNames()+" | all disj whole1,whole2 : "+((AlloyIsAnAtom)(this.getSource().getEndType())).getAtomNames()+" | no s: State | whole1->p+whole2->p in s."+this.getName()+"\n}\n");
		out.write("check "+this.getName()+"_doesnt_share_pontual for 5\n");
		
		//TODO: the below are due to existential dependency? consider the other way around whole->part
		
		//		
		out.write("//this predicate shows an instance of the model if "+ this.getName()+"'s  parts cannot be used by other wholes anytime\n");
		out.write("pred "+this.getName()+"_shares_in_time \n{\n\tone p : "+((AlloyIsAnAtom)(this.getTarget().getEndType())).getAtomNames()+" | one disj whole1,whole2 : "+((AlloyIsAnAtom)(this.getSource().getEndType())).getAtomNames()+" | whole1->p+whole2->p in State."+this.getName()+"\n}\n");
		out.write("run "+this.getName()+"_shares_in_time for 5\n\n");
		
		//
		out.write("//this asertion shows an instance of the model where "+ this.getName()+" share parts\n");
		out.write("pred "+this.getName()+"_doesnt_share_in_time \n{\n\tone p : "+((AlloyIsAnAtom)(this.getTarget().getEndType())).getAtomNames()+" | one disj whole1,whole2 : "+((AlloyIsAnAtom)(this.getSource().getEndType())).getAtomNames()+" | no s:State | whole1->p+whole2->p in s."+this.getName()+"\n}\n");
		out.write("run "+this.getName()+"_doesnt_share_in_time for 5\n\n");
		
		//print alloy code commentary
		out.write("\n// *** End of "+this.getName()+" predicates ***\n\n");
	}
	@Override
	public Meronymic getRelationship()
	{
		return (Meronymic) super.getRelationship();
	}

	public static AlloyMeronymic factory(Meronymic m,
            AlloyAtomProperty t,
            AlloyAtomProperty s)
    {
		if		(	(m.isIsInseparable() && t.getEndType() instanceof AlloySigClass) 
				|| 	(m.isIsEssential() && s.getEndType() instanceof AlloySigClass)
				)
		{
			
			return	AlloySigMeronymic.factory(m,t,s);
		}
		else return new AlloyMeronymic(m,t,s);
	    
    }

} 