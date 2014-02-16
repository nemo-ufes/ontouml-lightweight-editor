package br.ufes.inf.nemo.ontouml2ecore;

public class OntoUML2EcoreOption {

	private boolean ignorePackageHierarchy;
	private boolean ignoreDerivation;
	
	public OntoUML2EcoreOption()
	{
		this.ignorePackageHierarchy=false;
		this.ignoreDerivation=true;
	}
	
	/**
	 * 
	 * @param ignorePackageHierarchy: ignore the packages in transformation
	 * @param ignoreDerivation: ignore derivation relationships in transformation
	 * @param temporalStructure: include the (branching-time) temporal structure on the generated UML model.
	 */
	public OntoUML2EcoreOption(boolean ignorePackageHierarchy, boolean ignoreDerivation)
	{
		this.ignorePackageHierarchy=ignorePackageHierarchy;
		this.ignoreDerivation=ignoreDerivation;
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

}
