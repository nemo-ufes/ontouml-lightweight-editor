package br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base;

import java.io.IOException;
import java.io.Writer;

public interface AlloyStaticRelationship 
{
	void writeTemporalCoExistenceConstraint(Writer out) throws IOException;
}
