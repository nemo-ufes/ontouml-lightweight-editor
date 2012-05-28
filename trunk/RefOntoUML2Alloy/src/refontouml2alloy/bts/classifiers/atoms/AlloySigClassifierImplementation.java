package refontouml2alloy.bts.classifiers.atoms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigDatatypeRelationship;


public class AlloySigClassifierImplementation implements AlloySigClassifier 
{
	//SigClassifiers may take advantage of readOnly DatatypeRelationships and declare them in the body of the signature
	protected HashSet<AlloySigDatatypeRelationship> readOnlyDatatypeRelationships;
	private final AlloySigClassifierParent parent;
	
	public AlloySigClassifierImplementation(AlloySigClassifierParent p)
	{
		parent = p;
		readOnlyDatatypeRelationships = new HashSet<AlloySigDatatypeRelationship>();
	}
	@Override
	public void addSigDatatypeRelationship(AlloySigDatatypeRelationship r) 
	{
		readOnlyDatatypeRelationships.add(r);
	}
	@Override
	public void writeSigDatatypeRelationships(FileWriter out) throws IOException 
	{
		for(AlloySigDatatypeRelationship dtr: readOnlyDatatypeRelationships)
		{
			dtr.writeDeclaration(out);			
		}
		
	}
	@Override
	public boolean hasSigDatatypeRelationships() 
	{
		return !readOnlyDatatypeRelationships.isEmpty();
	}
	@Override
	public HashSet<AlloySigDatatypeRelationship> getSigDatatypeRelationshipHashSet()
	{
		return readOnlyDatatypeRelationships;
	}
	@Override
    public String getAtomNames()
    {
	    return getParent().getAtomNames();
    }
	private AlloySigClassifierParent getParent()
    {
	    return parent;
    }
	@Override
    public String getName()
    {	    
	    return getParent().getName();
    }
	@Override
    public String getStateName()
    {
	    return getParent().getStateName();
    }
}
