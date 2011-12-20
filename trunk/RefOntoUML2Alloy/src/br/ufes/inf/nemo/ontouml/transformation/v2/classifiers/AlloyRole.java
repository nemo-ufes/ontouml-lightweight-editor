package br.ufes.inf.nemo.ontouml.transformation.v2.classifiers;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AntiRigidSortalClass;

public class AlloyRole extends AlloyAntiRigidSortalClass
{
	public AlloyRole(AntiRigidSortalClass x, EList<EObject> list)
    {
	    super(x, list);
    }

	public void writePredicateDynamicClassification(Writer out) throws IOException
	{
		out.write("pred "+this.getName()+"_dynamic_classification_enforcement\n{\n\tone x: "+this.getAtomNames()+" | some s:State | x in State."+this.getName()+" and x not in s."+this.getName()+ " and x in s.exists\n}\n");
		out.write("run "+this.getName()+"_dynamic_classification_enforcement for 5\n");
	}	
}
