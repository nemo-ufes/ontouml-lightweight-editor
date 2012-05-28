package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships;

import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyDirectedBinaryRelationshipInterface;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;

public interface AlloyStaticRelationshipParent extends AlloyDirectedBinaryRelationshipInterface, AlloyReadOnlyRelationship
{
	@Override
	AlloyAtomProperty getSource();
	@Override
	AlloyAtomProperty getTarget();
	@Override
	String getName();
	AlloyReadOnlyRelationshipImplementation getDelegatee();
	AlloyReadOnlyRelationshipImplementation setDelegatee();
}
