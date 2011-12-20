package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;

import br.ufes.inf.nemo.ontouml.transformation.v2.classifiers.AlloySigClassifier;

public interface AlloySigRelationship extends AlloyStaticRelationship
{
	public void writeDeclaration(Writer out) throws IOException;
	AlloySigClassifier getHolderSigClassifier();
}
