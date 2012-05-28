package refontouml2alloy.bts.classifiers.atoms;

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
	
	
	public String getAtomNames() 
	{
		return this.sigClasses;
	}

	public String getStateName() 
	{
		return "("+this.getAtomNames()+"):>exists";
	}
}