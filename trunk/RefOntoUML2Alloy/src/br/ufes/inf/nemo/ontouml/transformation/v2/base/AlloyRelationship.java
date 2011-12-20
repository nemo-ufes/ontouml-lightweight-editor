package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.Relationship;


public abstract class AlloyRelationship extends AlloyRelationalClassifier
{
	protected final Relationship relationship;
	public AlloyRelationship(Relationship rel)
	{
		relationship = rel;		
	}
	abstract public void printExtraConstraints(Writer out) throws IOException;
	//abstract public void printGeneralization(FileWriter out);
	abstract public void writeDeclaration(Writer out) throws IOException; 
	protected abstract void setGenericName();	
	abstract public String getName();
	public String getStateName()
	{
		return this.getName();
	}
	public Relationship getRelationship() 
	{
		return relationship;
	}
}