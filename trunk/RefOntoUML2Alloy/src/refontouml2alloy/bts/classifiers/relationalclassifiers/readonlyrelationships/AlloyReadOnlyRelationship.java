package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships;
import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyDirectedBinaryRelationshipInterface;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;

public interface AlloyReadOnlyRelationship extends AlloyDirectedBinaryRelationshipInterface
{
	//This method is used to write state rules
	void writeTemporalCoExistenceConstraint(FileWriter out) throws IOException;
	@Override
	public AlloyAtomProperty getTarget();
	@Override
	public AlloyAtomProperty getSource();
}
