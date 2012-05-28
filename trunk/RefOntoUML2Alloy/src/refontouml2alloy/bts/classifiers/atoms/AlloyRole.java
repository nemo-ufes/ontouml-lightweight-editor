package refontouml2alloy.bts.classifiers.atoms;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AntiRigidSortalClass;

public class AlloyRole extends AlloyAntiRigidSortalClass
{
	public AlloyRole(AntiRigidSortalClass x, EList<EObject> list)
    {
	    super(x, list);
    }

	public void writePredicateDynamicClassification(FileWriter out) throws IOException
	{
		out.write("pred "+this.getName()+"_dynamic_classification_enforcement\n{\n\tone x: "+this.getAtomNames()+" | some s:State | x in State."+this.getName()+" and x not in s."+this.getName()+ " and x in s.exists\n}\n");
		out.write("run "+this.getName()+"_dynamic_classification_enforcement for 5\n");
	}	
}
