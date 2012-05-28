package refontouml2alloy.bts.classifiers.relationalclassifiers;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.AlloyClassifier;
import refontouml2alloy.bts.structuralfeature.AlloyProperty;

public interface AlloyDirectedBinaryRelationshipInterface extends AlloyClassifier
{
	public void writeDeclaration(FileWriter out) throws IOException;
	public AlloyProperty getTarget();
	public AlloyProperty getSource();
	public void writeStateRules(FileWriter out)throws IOException;
}
