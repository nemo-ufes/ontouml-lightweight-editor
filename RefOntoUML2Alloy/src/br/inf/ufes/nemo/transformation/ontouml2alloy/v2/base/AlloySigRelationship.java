package br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base;

import java.io.IOException;
import java.io.Writer;

import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloySigClassifier;

public interface AlloySigRelationship extends AlloyStaticRelationship
{
	public void writeDeclaration(Writer out) throws IOException;
	AlloySigClassifier getHolderSigClassifier();
}
