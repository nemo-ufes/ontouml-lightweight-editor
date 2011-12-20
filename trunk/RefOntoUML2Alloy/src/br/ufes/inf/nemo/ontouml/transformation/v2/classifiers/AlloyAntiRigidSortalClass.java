package br.ufes.inf.nemo.ontouml.transformation.v2.classifiers;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AntiRigidSortalClass;


abstract public class AlloyAntiRigidSortalClass extends AlloyNonRigidClass
{
	public AlloyAntiRigidSortalClass(AntiRigidSortalClass x, EList<EObject> list)
	{
		super(x, list);
		level = findSortalLevel(x);
	}	
}