package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tosigs;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloyDependencyRelationship;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import RefOntoUML.DependencyRelationship;

public class AlloyDependencyRelationshipToSig extends
        AlloyDependencyRelationship implements AlloySigRelationshipToSig
{
	public AlloyDependencyRelationshipToSig(DependencyRelationship rel,
            AlloyAtomProperty t, AlloyAtomProperty s)
    {
	    super(rel, t, s);
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
