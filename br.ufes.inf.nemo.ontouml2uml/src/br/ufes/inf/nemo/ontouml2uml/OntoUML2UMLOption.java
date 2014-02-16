package br.ufes.inf.nemo.ontouml2uml;

/**
 * This class contains the transformation options for the OntoUML2UML translation.
 * 
 * @author John Guerson
 *
 */
public class OntoUML2UMLOption {

	private boolean ignorePackageHierarchy;
	private boolean ignoreDerivation;
	private boolean temporalStructure;
	
	public OntoUML2UMLOption()
	{
		this.ignorePackageHierarchy=false;
		this.ignoreDerivation=true;
		this.temporalStructure=false;
	}
	
	/**
	 * 
	 * @param ignorePackageHierarchy: ignore the packages in transformation
	 * @param ignoreDerivation: ignore derivation relationships in transformation
	 * @param temporalStructure: include the (branching-time) temporal structure on the generated UML model.
	 */
	public OntoUML2UMLOption(boolean ignorePackageHierarchy, boolean ignoreDerivation, boolean temporalStructure)
	{
		this.ignorePackageHierarchy=ignorePackageHierarchy;
		this.ignoreDerivation=ignoreDerivation;
		this.temporalStructure=temporalStructure;
	}

	public boolean isIgnorePackageHierarchy() 
	{
		return ignorePackageHierarchy;
	}

	public void setIgnorePackageHierarchy(boolean ignorePackageHierarchy) 
	{
		this.ignorePackageHierarchy = ignorePackageHierarchy;
	}

	public boolean isIgnoreDerivation() 
	{
		return ignoreDerivation;
	}

	public void setIgnoreDerivation(boolean ignoreDerivation) 
	{
		this.ignoreDerivation = ignoreDerivation;
	}

	public boolean isTemporalStructure() 
	{
		return temporalStructure;
	}

	public void setTemporalStructure(boolean temporalStructure) 
	{
		this.temporalStructure = temporalStructure;
	}
	
	
}
