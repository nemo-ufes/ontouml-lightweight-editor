package br.ufes.inf.nemo.ontouml.transformation.v2.classifiers;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.Class;

public class AlloyUltimateIdentityProvider extends AlloySigClass
{
	public AlloyUltimateIdentityProvider(Class x) 
	{
		super(x);
		this.level = 0;
	}

	protected void printSigHeader(Writer out)
	{
		try 
		{
			if(ontoClass.isIsAbstract())out.write("abstract ");
			out.write("sig "+ontoClass.getName());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}	
		
}