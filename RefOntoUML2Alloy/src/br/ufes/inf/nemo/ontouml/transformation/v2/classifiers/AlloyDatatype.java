package br.ufes.inf.nemo.ontouml.transformation.v2.classifiers;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;

import RefOntoUML.DataType;
import br.ufes.inf.nemo.ontouml.transformation.v2.base.AlloySigDatatypeRelationship;

public abstract class AlloyDatatype extends AlloyClassifier implements AlloyIsAnAtom, AlloySigClassifier
{
	//We implement AlloySigClassifier methods by delegation to the AlloySigClassifierImplementation object
	private final AlloySigClassifierImplementation asci;
	public AlloyDatatype(DataType dt)
	{
		this.ontoDatatype = dt;
		asci = new AlloySigClassifierImplementation();
	}
	abstract public void printDeclaration(Writer out) throws IOException; 
	
	private DataType ontoDatatype;
	public String getName() 
	{
		return ontoDatatype.getName();
	}
	public String getStateName() 
	{
		return ontoDatatype.getName();
	}

	protected String getGeneral() 
	{
		//We cannot deal with Datatype multiple inheritance, so we take only one general class
		if(ontoDatatype.getGeneralization().isEmpty())return "";
		return " extends " + ontoDatatype.getGeneralization().get(0).getGeneral().getName(); //ontoDatatype.getGeneralization().get(0).getName();
	}

	public String getAtomNames() 
	{
		return this.getName();
	}

	public void addSigDatatypeRelationship(AlloySigDatatypeRelationship r) 
	{
		asci.addSigDatatypeRelationship(r);
	}
	public void writeSigDatatypeRelationships(Writer out)
		throws IOException
	{
		asci.writeSigDatatypeRelationships(out);
	
	}
	public boolean hasSigDatatypeRelationships() 
	{
		return asci.hasSigDatatypeRelationships();
	}
	public HashSet<AlloySigDatatypeRelationship> getSigDatatypeRelationshipHashSet()
	{
		return asci.getSigDatatypeRelationshipHashSet();
	}

}
