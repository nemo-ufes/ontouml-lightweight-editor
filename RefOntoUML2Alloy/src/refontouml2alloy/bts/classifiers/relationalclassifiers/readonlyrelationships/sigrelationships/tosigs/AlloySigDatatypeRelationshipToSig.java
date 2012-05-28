package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tosigs;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigDatatypeRelationship;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import RefOntoUML.Association;

public class AlloySigDatatypeRelationshipToSig extends
        AlloySigDatatypeRelationship implements AlloySigRelationshipToSigParent
{

	public AlloySigDatatypeRelationshipToSig(Association rel,
            AlloyAtomProperty alloyProperty, AlloyAtomProperty alloyProperty2)
    {
	    super(rel, alloyProperty, alloyProperty2);
    }
	@Override
    public void writeCardinalitiesFact(FileWriter out) throws IOException
    {
	    this.getDelegatee().writeCardinalitiesFact(out);
	    
    }
	@Override
    public AlloySigRelationshipToSigImplementation setDelegatee()
	{
		return new AlloySigRelationshipToSigImplementation(this);
	}
	@Override
    public AlloySigRelationshipToSigImplementation getDelegatee()
	{
	    return (AlloySigRelationshipToSigImplementation) super.getDelegatee();
	}

}
