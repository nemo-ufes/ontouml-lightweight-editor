package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tosigs;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigRelationship;


//You should notice that here we consider categories as sigclasses, as they are merely the union between sigclasses
//the relevance of the division is whether we can declare the relationship in the signature and constraint with a fact or if the constraint should be added in the state signature
public interface AlloySigRelationshipToSig extends AlloySigRelationship
{
	//if the relationship needs extra cardinalities constraints, they are printed as facts
	public void writeCardinalitiesFact(FileWriter out) throws IOException;
	
}
