package br.inf.ufes.nemo.transformation.ontouml2alloy.v3.base;

import java.io.IOException;
import java.io.Writer;

public abstract class AlloyBaseElement implements Printable {

	@Override
	public abstract String toString();

	@Override
	public void print(Writer out) throws IOException {
		out.write(toString());
	}

}
