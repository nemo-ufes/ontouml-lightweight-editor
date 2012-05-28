package refontouml2alloy.bts.classifiers.relationalclassifiers;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyRelationalClassifier;
import RefOntoUML.Relationship;

public abstract class AlloyRelationship extends AlloyRelationalClassifier
{
	protected final Relationship relationship;
	public AlloyRelationship(Relationship rel)
	{
		relationship = rel;		
	}
	abstract public void writeDeclaration(FileWriter out) throws IOException; 
	protected abstract void setGenericName();	
	@Override
	abstract public String getName();
	@Override
	
	public String getStateName()
	{
		return this.getName();
	}
	public Relationship getRelationship() 
	{
		return relationship;
	}
	
	//Extra cardinality constraints, for example, should be printed in the state signature as sig rules
	abstract public void writeStateRules(FileWriter out) throws IOException;
}