package br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.MixinClass;

public class AlloyNonRigidMixinClass extends AlloyNonRigidClass
{
	public AlloyNonRigidMixinClass(MixinClass x, EList<EObject> list) 
	{
		super(x, list);
		level = findMixinLevel(x,list);
	}

	public void printSubsumersUnion(Writer out) 
	{		
		try 
		{
			out.write("\t"+this.ontoClass.getName()+" = "+this.sigClasses);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}