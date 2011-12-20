package br.ufes.inf.nemo.ontouml.transformation.v2.classifiers;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;

import br.ufes.inf.nemo.ontouml.transformation.v2.base.AlloySigDatatypeRelationship;


public class AlloySigClassifierImplementation implements AlloySigClassifier 
{
	//SigClassifiers may take advantage of readOnly DatatypeRelationships and declare them in the body of the signature
	protected HashSet<AlloySigDatatypeRelationship> readOnlyDatatypeRelationships;
	//private final AlloySigClassifierOwnerInfo owner;
	//public AlloySigClassifierImplementation(AlloySigClassifierOwnerInfo o)
	public AlloySigClassifierImplementation()
	{
		//owner = o;
		readOnlyDatatypeRelationships = new HashSet<AlloySigDatatypeRelationship>();
	}
	
	//AlloySigRelationship
	public void addSigDatatypeRelationship(AlloySigDatatypeRelationship r) 
	{
		readOnlyDatatypeRelationships.add(r);
	}
	public void writeSigDatatypeRelationships(Writer out) throws IOException 
	{
		for(AlloySigDatatypeRelationship dtr: readOnlyDatatypeRelationships)
		{
			out.write("\t"+dtr.getName()+":"+dtr.getStaticProperty().setMultiplicity() +dtr.getStaticProperty().getEndType().getName()+",\n");
		}
		
	}
	public boolean hasSigDatatypeRelationships() 
	{
		return !readOnlyDatatypeRelationships.isEmpty();
	}
	public HashSet<AlloySigDatatypeRelationship> getSigDatatypeRelationshipHashSet()
	{
		return readOnlyDatatypeRelationships;
	}
}
