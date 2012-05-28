package refontouml2alloy.bts.classifiers.atoms;

import java.io.FileWriter;
import java.io.IOException;


import RefOntoUML.DataType;

public class AlloySimpleDatatype extends AlloyDatatype
{

	public AlloySimpleDatatype(DataType dt)
	{
		super(dt);
	}

	
	public void printDeclaration(FileWriter out) throws IOException 
	{
		out.write("sig "+this.getName()+this.getGeneral()+"{}\n");		
	}	
}
