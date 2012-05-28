package refontouml2alloy.bts.classifiers.atoms;

import java.io.FileWriter;
import java.io.IOException;

import RefOntoUML.Class;

public class AlloyUltimateIdentityProvider extends AlloySigClass
{
	public AlloyUltimateIdentityProvider(Class x) 
	{
		super(x);
		this.level = 0;
	}

	protected void printSigHeader(FileWriter out)
	{
		try 
		{
			if(getOntoClass().isIsAbstract())out.write("abstract ");
			out.write("sig "+getOntoClass().getName());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}	
		
}