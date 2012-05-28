package refontouml2alloy.bts.classifiers.relationalclassifiers;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Generalization;
import RefOntoUML.impl.AntiRigidSortalClassImpl;
import RefOntoUML.impl.NonRigidMixinClassImpl;

public class AlloyGeneralizationNonRigid extends AlloyGeneralization
{

	public AlloyGeneralizationNonRigid(Generalization rel)
    {
	    super(rel);
    }

	@Override
	public void writeStateRules(FileWriter out) throws IOException
	{
		out.write("\t"+this.getSubClass().getName()+" in "+ this.getSuperClass().getName()+"\n");
	}
	
	static public boolean isGeneralizationNonRigid(EObject e)
	{
		if(e instanceof Generalization)
		{
			Generalization g = (Generalization)e;
			if(g.getTarget().get(0) instanceof AntiRigidSortalClassImpl || g.getSource().get(0) instanceof NonRigidMixinClassImpl)
			{
				return true;
			}
		}
		
		return false;
		
	}
}
