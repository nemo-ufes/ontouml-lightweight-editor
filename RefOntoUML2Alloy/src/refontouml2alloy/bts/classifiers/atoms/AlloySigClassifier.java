package refontouml2alloy.bts.classifiers.atoms;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigDatatypeRelationship;


public interface AlloySigClassifier extends AlloyIsAnAtom
{
	public void addSigDatatypeRelationship(AlloySigDatatypeRelationship r);
	public void writeSigDatatypeRelationships(FileWriter out) throws IOException;
	public boolean hasSigDatatypeRelationships();
	public HashSet<AlloySigDatatypeRelationship> getSigDatatypeRelationshipHashSet();
}
