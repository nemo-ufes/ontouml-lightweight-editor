package br.ufes.inf.nemo.ontouml.transformation.v2.classifiers;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;

import br.ufes.inf.nemo.ontouml.transformation.v2.base.AlloySigDatatypeRelationship;

public interface AlloySigClassifier
{
	public void addSigDatatypeRelationship(AlloySigDatatypeRelationship r);
	public void writeSigDatatypeRelationships(Writer out) throws IOException;
	public boolean hasSigDatatypeRelationships();
	public HashSet<AlloySigDatatypeRelationship> getSigDatatypeRelationshipHashSet();
}
