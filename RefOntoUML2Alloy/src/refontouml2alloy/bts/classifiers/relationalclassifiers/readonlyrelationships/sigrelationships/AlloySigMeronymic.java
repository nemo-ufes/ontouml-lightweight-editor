package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import refontouml2alloy.bts.classifiers.atoms.AlloyIsAnAtom;
import refontouml2alloy.bts.classifiers.atoms.AlloySigClass;
import refontouml2alloy.bts.classifiers.atoms.AlloySigClassifier;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.AlloyReadOnlyMeronymic;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tononrigidclass.AlloyEssentialMeronymicToNRC;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tononrigidclass.AlloyInseparableMeronymicToNRC;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tosigs.AlloyEssentialInseparableMeronymic;
import RefOntoUML.Meronymic;

//SigMeronymics will only happen for Essential and Inseparable Meronymics 
abstract public class AlloySigMeronymic extends AlloyReadOnlyMeronymic implements AlloySigRelationship, AlloySigRelationshipParent
{
	public AlloySigMeronymic(Meronymic rel, AlloyAtomProperty t, AlloyAtomProperty s) 
	{
		super(rel, t, s);
	}
	@Override
	abstract public AlloySigClassifier getHolderSigClassifier();	
	public AlloySigClass getHolderSigClass()
	{
		return (AlloySigClass) getHolderSigClassifier();
	}
	
	@Override
	public String getStateName()
	{
		return "("+getHolderSigClass().getName()+":>exists)."+this.getName();
	}	
	@Override
	public void writeTemporalCoExistenceConstraint(FileWriter out)
    throws IOException
    {
		out.write("all s: State | this in s.exists implies "+this.getName()+" in s."+this.getHoldedClassifier().getStateName()+"\n");	
    }
	
	@Override
	public void writePredicateShareability(FileWriter out) throws IOException
	{
		out.write("pred "+this.getName()+"_shares_pontual \n{\n\tone holded : "+getHoldedClassifier().getAtomNames()+" | one holder1,holder2 : "+getHolderSigClass().getAtomNames()+" | one s: State | holder1+holder2 in s.exists and holded in holder1."+this.getName()+"and holded in holder2."+this.getName()+"\n}\n");
		//out.write("pred "+this.getName()+"_shares_in_time \n{\n\tone holded : "+getHoldedClassifier().getAtomNames()+" | one holder1,holder2 : "+getHolderSigClass().getAtomNames()+" | whole1->p+whole2->p in State."+this.getName());
		//out.write("pred "+this.getName()+"_doesnt_share_pontual \n{\n\tone holded : "+getHoldedClassifier().getAtomNames()+" | one holder1,holder2 : "+getHolderSigClass().getAtomNames()+" | no s: State | whole1->p+whole2->p in s."+this.getName());
		//out.write("pred "+this.getName()+"_doesnt_share_in_time \n{\n\tone holded : "+getHoldedClassifier().getAtomNames()+" | one holder1,holder2 : "+getHolderSigClass().getAtomNames()+" | no s:State | whole1->p+whole2->p in s."+this.getName());
		
		
		//In this method we print commentaries in the alloy code, use them as commentaries for the method as well
		out.write("// *** "+this.getName()+" validation ***\n\n");
		
		//only one of the opposing predicates/assertions will show an instance.
		
		//
		out.write("//this predicate shows an instance of the model if "+ this.getName()+" does not share parts in a given point in time\n");
		out.write("pred "+this.getName()+"_doesnt_share_pontual \n{\n\tall p : "+((AlloyIsAnAtom)(this.getHoldedClassifier())).getAtomNames()+" | all disj whole1,whole2 : "+((AlloyIsAnAtom)(this.getHolderSigClass())).getAtomNames()+" | no s: State | whole1->p+whole2->p in s."+this.getName()+"\n}\n");
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
    public AlloySigRelationshipImplementation setDelegatee()
    {
	   return new AlloySigRelationshipImplementation(this);
    }
	@Override
    public AlloySigRelationshipImplementation getDelegatee()
    {
	    return (AlloySigRelationshipImplementation) super.getDelegatee();
    }
	@Override
	public void writeDeclaration(FileWriter out) throws IOException 
	{
		getDelegatee().writeDeclaration(out);
	}
	
	static public AlloySigMeronymic factory(Meronymic rel, AlloyAtomProperty t, AlloyAtomProperty s)
	{
		if(rel.isIsEssential())
		{
			if(rel.isIsInseparable())
			{
				return new AlloyEssentialInseparableMeronymic(rel,t,s);
			}
			else
				return new AlloyEssentialMeronymicToNRC(rel,t,s);
			
				
		}
		else
		{
			if(rel.isIsInseparable())
			{
				return new AlloyInseparableMeronymicToNRC(rel,t,s);
			}
		}
		//if this occurs, AlloySigMeronymic factory has been incorrectly used
		throw new IllegalArgumentException("SigMeronymicRelationships must have at least one ReadOnly property and at least one sig class involved in the relationship");
			
	}

	@Override
    public String getSourceSigCardinalityConstraints()
    {
	    return getDelegatee().getSourceSigCardinalityConstraints();
    }

	@Override
    public String getTargetSigCardinalityConstraints()
    {
	    return getDelegatee().getTargetSigCardinalityConstraints();
    }
	
	//SigMeronymics are sigrelationships, therefore some of the constraints are embedded in the holder signature
	@Override
    public void writeStateRules(FileWriter out) throws IOException
    {
	    	    
    }
	@Override
    public void writeSigRules(FileWriter out) throws IOException
    {
		this.getDelegatee().writeSigRules(out);
		this.writeTemporalCoExistenceConstraint(out);
    }
	
}
