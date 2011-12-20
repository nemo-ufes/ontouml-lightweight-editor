package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.DependencyRelationship;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyIsAnAtom;


public class AlloyDependencyRelationship extends AlloyDirectedBinaryRelationship
{
	private static int unnamedDepRelCounter = 0;
	public AlloyDependencyRelationship(DependencyRelationship rel, AlloyProperty t, AlloyProperty s) 
	{
		super(rel,t,s);
		if (rel.getName() == null) setGenericName();
	}

	protected void setGenericName() 
	{
		((DependencyRelationship) this.relationship).setName("dependencyRelation"+unnamedDepRelCounter);
		unnamedDepRelCounter++;		
	}
	public String getName() 
	{
		return ((DependencyRelationship) this.relationship).getName();
	}
	@Override
	public void writeDeclaration(Writer out) throws IOException 
	{
  	//writes the name of the relation.
  	out.write("\t"+this.getName()+":");
  	//we need now to write the mutiplicity of the relation.
  	out.write(" "+this.getTarget().setMultiplicity()+" "+
  			//the cast to AlloyIsAnAtom is valid since these existentiallyDependentRelationships are only between classes or datatypes
  			//we don't allow this optimization for relationships involving other relationships
  			((AlloyIsAnAtom)this.getTarget().getEndType()).getAtomNames() + ",\n");
  	//If it's not an Alloy Primitive, we'll need to write some State facts (using the # operator),
  	//to assure that other side of the multiplicity is treated correctly.
	}
}