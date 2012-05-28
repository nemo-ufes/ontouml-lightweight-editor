package refontouml2alloy.bts.classifiers.atoms;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.SortalClass;
import RefOntoUML.impl.SortalClassImpl;


abstract public class AlloyAntiRigidSortalClass extends AlloyNonRigidClass
{
	public AlloyAntiRigidSortalClass(AntiRigidSortalClass x, EList<EObject> list)
	{
		super(x, list);
		level = findSortalLevel(x);
	}
	
	protected void setSetName(EList<EObject> list)
    {
		//This method ignores the possibility of having antirigid sortal specializations of a moment class, and should be modified in case they are permitted in the future
		SortalClass lowestSortal = null;
		for(Generalization g: this.getOntoClass().getGeneralization())
		{
			//Classifier c = (Classifier) g.getSource().get(0);
			Classifier c = (Classifier) g.getGeneral();
			if(c instanceof SortalClassImpl)
			{
				if(lowestSortal == null)
					lowestSortal = (SortalClass) c;
				else
				{
					//this should never happen in a valid model, unless the modeler has added redundant generalizations, in this case, we'll get the most specialized sortal
					for(Classifier generals: c.getGeneral())
					{
						//if we can find "lowestSortal" in the group of general classes of c, it means c is an specialization of "lowestSortal", thus, should be the lowest
						if(generals == lowestSortal)
						{
							lowestSortal =(SortalClass) c;
							//we found what we were looking for and can stop the loop
							break;
						}
					}
				}
			}
		}
		
		if(lowestSortal instanceof RigidSortalClass)
		{
			setName = lowestSortal.getName() + ":>exists";
		}
		else
		{
			setName = lowestSortal.getName();
		}
		
    }
}