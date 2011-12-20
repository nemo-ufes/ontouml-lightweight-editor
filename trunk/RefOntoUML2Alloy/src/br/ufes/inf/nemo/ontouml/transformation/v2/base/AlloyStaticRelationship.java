package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;

public interface AlloyStaticRelationship 
{
	void writeTemporalCoExistenceConstraint(Writer out) throws IOException;
}
