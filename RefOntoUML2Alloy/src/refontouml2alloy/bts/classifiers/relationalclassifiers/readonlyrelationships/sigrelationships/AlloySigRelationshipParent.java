package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships;

import refontouml2alloy.bts.classifiers.atoms.AlloyIsAnAtom;
import refontouml2alloy.bts.classifiers.atoms.AlloySigClassifier;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.AlloyStaticRelationshipParent;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;


public interface AlloySigRelationshipParent extends AlloyStaticRelationshipParent, AlloySigRelationship
{
	boolean targetIsReadOnly();
	//Sig relationships divide the cardinality declarations in two parts:
	//	-one goes in the relation declaration, in the signature
	public AlloyAtomProperty getHolderProperty();
	//	-the second goes in a separate, simple, fact;
	public AlloyAtomProperty getHoldedProperty();
	@Override
	AlloySigRelationshipImplementation getDelegatee();
	@Override
	AlloySigRelationshipImplementation setDelegatee();
	
	AlloySigClassifier getHolderSigClassifier();
	AlloyIsAnAtom getHoldedClassifier();
}
