package refontouml2alloy.bts.classifiers.atoms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import refontouml2alloy.bts.classifiers.AlloyClassifier;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigDatatypeRelationship;
import RefOntoUML.DataType;

public abstract class AlloyDatatype implements AlloyClassifier, AlloyIsAnAtom, AlloySigClassifier, AlloySigClassifierParent
{
	//We implement AlloySigClassifier methods by delegation to the AlloySigClassifierImplementation object
	private final AlloySigClassifierImplementation delegatee;
	public AlloyDatatype(DataType dt)
	{
		this.ontoDatatype = dt;
		delegatee = new AlloySigClassifierImplementation(this);
	}
	abstract public void printDeclaration(FileWriter out) throws IOException; 
	
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
		return " extends "+ontoDatatype.getGeneralization().get(0).getGeneral();
	}

	public String getAtomNames() 
	{
		return this.getName();
	}

	public void addSigDatatypeRelationship(AlloySigDatatypeRelationship r) 
	{
		getDelegatee().addSigDatatypeRelationship(r);
	}
	private AlloySigClassifierImplementation getDelegatee()
    {
	    return delegatee;
    }
	public void writeSigDatatypeRelationships(FileWriter out)
		throws IOException
	{
		getDelegatee().writeSigDatatypeRelationships(out);
	
	}
	public boolean hasSigDatatypeRelationships() 
	{
		return getDelegatee().hasSigDatatypeRelationships();
	}
	public HashSet<AlloySigDatatypeRelationship> getSigDatatypeRelationshipHashSet()
	{
		return getDelegatee().getSigDatatypeRelationshipHashSet();
	}

}
