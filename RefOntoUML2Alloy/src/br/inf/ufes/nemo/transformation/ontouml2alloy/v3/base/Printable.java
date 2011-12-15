package br.inf.ufes.nemo.transformation.ontouml2alloy.v3.base;

import java.io.IOException;
import java.io.Writer;

public interface Printable {
		
	void print(Writer out) throws IOException;
	
}
