package refontouml2alloy.bts.classifiers.atoms;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyDatatypeRelationship;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigDatatypeRelationship;

import RefOntoUML.DataType;

public class AlloyStructuredDatatype extends AlloyDatatype
{
	public AlloyStructuredDatatype(DataType dt) 
	{
		super(dt);
	}
	
	public void printDeclaration(FileWriter out) throws IOException 
	{
		out.write("sig "+this.getName()+" "+this.getGeneral());
		this.printSigBody(out);
		this.printSigRules(out);
	}
	private void printSigBody(FileWriter out) throws IOException 
	{	
		if(this.hasSigDatatypeRelationships())
		{
			out.write("\n{\n");
			for(AlloyDatatypeRelationship r : this.getSigDatatypeRelationshipHashSet())
			{
				r.writeDeclaration(out);
			}
			out.write("\n}\n");
		}
		else out.write("{}\n");
	}
	

	private void printSigRules(FileWriter out) throws IOException
	{
		boolean openedBrackets = false;
		for(AlloySigDatatypeRelationship r: this.getSigDatatypeRelationshipHashSet())
		{
			String x = r.getTargetSigCardinalityConstraints();
			if(x!=null)
			{	
				if(openedBrackets == false)
				{
					out.write("\t{\n");
					openedBrackets = true;
				}
				out.write(x);
				
			}
		}
		if(openedBrackets == true)out.write("\t}\n");
		
	}
}
