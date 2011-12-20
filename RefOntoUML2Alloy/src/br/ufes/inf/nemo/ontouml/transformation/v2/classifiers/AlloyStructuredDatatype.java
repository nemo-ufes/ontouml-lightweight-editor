package br.ufes.inf.nemo.ontouml.transformation.v2.classifiers;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.DataType;
import br.ufes.inf.nemo.ontouml.transformation.v2.base.AlloyDatatypeRelationship;

public class AlloyStructuredDatatype extends AlloyDatatype
{
	public AlloyStructuredDatatype(DataType dt) 
	{
		super(dt);
	}
	
	public void printDeclaration(Writer out) throws IOException 
	{
		out.write("sig "+this.getName()+" "+this.getGeneral());
		this.printSigBody(out);
		this.printSigRules(out);
	}
	private void printSigBody(Writer out) throws IOException 
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
	

	private void printSigRules(Writer out) throws IOException
	{
		if(this.hasSigDatatypeRelationships())
		{
			out.write("\t{\n");
			for(AlloyDatatypeRelationship r: this.getSigDatatypeRelationshipHashSet())
			{
				String x = r.getTargetExtraCardinalityConstraints();
				if(x!=null)out.write(x);				
			}
			out.write("\t}\n");
		}
	}
}
