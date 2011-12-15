package br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.DataType;

public class AlloySimpleDatatype extends AlloyDatatype
{

	public AlloySimpleDatatype(DataType dt)
	{
		super(dt);
	}

	
	public void printDeclaration(Writer out) throws IOException 
	{
		out.write("sig "+this.getName()+this.getGeneral()+"{}\n");		
	}	
}
