package br.ufes.inf.nemo.ocl2alloy;

import java.util.ArrayList;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.ocl.parser.OCLParser;


/**
 * @author John Guerson
 */

public class OCL2AlloyOptions {

	private ArrayList<Constraint> constraintsList = new ArrayList<Constraint>();
	
	private ArrayList<String> constraintType = new ArrayList<String>();
	
	private ArrayList<String> transformationType = new ArrayList<String>();
	
	private ArrayList<Integer> commandScope = new ArrayList<Integer>();	
	
	/**
	 * Constructor. Set default options from OCL parser.
	 * 
	 * @param constraints
	 */
	@SuppressWarnings("unchecked")
	public OCL2AlloyOptions(OCLParser oclparser)
	{
		this();
		
		if (oclparser==null) return;
		
		for(Constraint ct: oclparser.getConstraints())
		{
			constraintsList.add(ct);
			constraintType.add(oclparser.getUMLReflection().getStereotype(ct));
			transformationType.add("FACT");
			commandScope.add(10);
		}		
	}
		
	/**
	 * Constructor.
	 */
	public OCL2AlloyOptions() {}
	
	/*
	 * Getters... 
	 */
	public ArrayList<Constraint> getConstraintList () { return constraintsList;}	
	public Integer getCommandScope(Constraint ct) { return commandScope.get(constraintsList.indexOf(ct)); }		
	public String getTransformationType(Constraint ct) { return transformationType.get(constraintsList.indexOf(ct)); }	
	public ArrayList<String> getTransformationType() { return transformationType; }		
	public String getConstraintType(Constraint ct) { return constraintType.get(constraintsList.indexOf(ct)); }	
	public ArrayList<String> getConstraintType() { return constraintType; }
	
	/*
	 * Setters...
	 */
	public void setConstraintList(ArrayList<Constraint> constraintsList) { this.constraintsList = constraintsList; }	
	public void setCommandScope(ArrayList<Integer> scopeList) { commandScope = scopeList; }	
	public void setTransformationType(ArrayList<String> transformationTypeList) { this.transformationType = transformationTypeList; }
}
