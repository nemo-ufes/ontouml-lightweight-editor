package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.atoms.AlloyIsAnAtom;
import refontouml2alloy.bts.classifiers.atoms.AlloySigClassifier;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tosigs.AlloySigRelationshipToSigImplementation;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tosigs.AlloySigRelationshipToSigParent;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import RefOntoUML.Meronymic;

abstract public class AlloyEssentialMeronymic extends AlloySigMeronymic implements AlloySigRelationshipToSigParent
{

	public AlloyEssentialMeronymic(Meronymic rel, AlloyAtomProperty t,
            AlloyAtomProperty s)
    {
	    super(rel, t, s);
    }
	@Override
	public AlloySigClassifier getHolderSigClassifier()
	{
		return (AlloySigClassifier) this.getSource().getEndType();		
	}
	@Override
	public AlloyIsAnAtom getHoldedClassifier()
	{
		return  (AlloyIsAnAtom) this.getTarget().getEndType();		
	}
	
	@Override
    public AlloyAtomProperty getHolderProperty()
    {
	    return this.getTarget();
    }
	@Override
    public AlloyAtomProperty getHoldedProperty()
    {
	    return this.getSource();
    }
	@Override
    public void writeCardinalitiesFact(FileWriter out) throws IOException
    {
		this.getDelegatee().writeCardinalitiesFact(out);
	    
    }	
	@Override
    public AlloySigRelationshipToSigImplementation getDelegatee()
    {
	    return (AlloySigRelationshipToSigImplementation) super.getDelegatee();
    }
	@Override
    public AlloySigRelationshipToSigImplementation setDelegatee()
    {
	   return new AlloySigRelationshipToSigImplementation(this);
    }
	
	
}
