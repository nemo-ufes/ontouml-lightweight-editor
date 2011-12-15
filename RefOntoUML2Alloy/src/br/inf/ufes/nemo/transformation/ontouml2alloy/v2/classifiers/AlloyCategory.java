package br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Category;

public class AlloyCategory extends AlloyClass
{
	protected String sigClasses;
	public AlloyCategory(Category x, EList<EObject> list) 
	{
		super(x);
		this.sigClasses =  AlloyClass.extractAtomNames(x, list);
		//Levels are not used for categories...
		//this.level = findMixinLevel(x, list);
	}
	
	public void printClassDeclaration(Writer out) 
	{
		try 
		{
			//header
			out.write("fun "+ontoClass.getName()+":("+sigClasses+")\n{\n"
			//body
					+"\t"+sigClasses+"\n}\n"	);
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
	}
	public String getAtomNames() 
	{
		return this.sigClasses;
	}

	public String getStateName() 
	{
		return "("+this.ontoClass.getName()+"):>exists";
	}
}