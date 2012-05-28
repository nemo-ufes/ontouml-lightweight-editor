package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tononrigidclass;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigRelationship;

public interface AlloySigRelationshipToNRC extends AlloySigRelationship
{
	@Override
	void writeStateRules(FileWriter out) throws IOException;
}
