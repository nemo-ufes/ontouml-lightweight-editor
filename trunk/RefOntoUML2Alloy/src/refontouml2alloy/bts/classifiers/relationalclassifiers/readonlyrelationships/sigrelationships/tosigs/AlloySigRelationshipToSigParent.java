package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tosigs;

import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigRelationshipParent;

public interface AlloySigRelationshipToSigParent extends
        AlloySigRelationshipToSig, AlloySigRelationshipParent
{
	@Override
    public AlloySigRelationshipToSigImplementation getDelegatee();
	@Override
    public AlloySigRelationshipToSigImplementation setDelegatee();
	
}
