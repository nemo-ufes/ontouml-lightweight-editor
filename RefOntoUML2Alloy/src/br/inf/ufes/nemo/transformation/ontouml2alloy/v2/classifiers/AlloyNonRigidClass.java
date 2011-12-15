package br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Class;

public abstract class AlloyNonRigidClass extends AlloyClass
{	
	protected String sigClasses;
	public AlloyNonRigidClass(Class x, EList<EObject> list) 
	{
		super(x);
		this.sigClasses = AlloyClass.extractAtomNames(x, list);
	}
	public String getStateName() 
	{
		return ontoClass.getName();
	}
	public void printClassDeclaration(Writer out) throws IOException
	{
		out.write("\t"+ontoClass.getName()+": set "+this.getAtomNames()+",\n");		
	}
	public String getAtomNames() 
	{
		return this.sigClasses;
	}
	
}