package br.ufes.inf.nemo.ocl.ocl2alloy;

import java.util.ArrayList;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.ocl.parser.OCLParser;

/**
 * @author John Guerson
 */

public class OCL2AlloyOption {

	// Constraint
	private ArrayList<Constraint> constraintsList = new ArrayList<Constraint>();
	// Stereotype
	private ArrayList<String> constraintType = new ArrayList<String>();
	// Transformation Option
	private ArrayList<String> transformationType = new ArrayList<String>();
	// Default Scope
	private ArrayList<Integer> commandScope = new ArrayList<Integer>();
	// Default Bit-Width
	private ArrayList<Integer> bitScope = new ArrayList<Integer>();
	// World Scope
	private ArrayList<Integer> worldScope = new ArrayList<Integer>();
		
	public OCL2AlloyOption() {}
	
	@SuppressWarnings("unchecked")
	public OCL2AlloyOption(OCLParser oclparser)
	{		
		if (oclparser==null) return;
		
		for(Constraint ct: oclparser.getConstraints())
		{
			constraintsList.add(ct);
			constraintType.add(oclparser.getUMLReflection().getStereotype(ct));
			transformationType.add("FACT");
			commandScope.add(10);
			bitScope.add(7);
			worldScope.add(3);
		}		
	}
		
	//Getters	 
	public ArrayList<Constraint> getConstraintList () { return constraintsList;}
	public Integer getCommandScope(Constraint ct) { return commandScope.get(constraintsList.indexOf(ct)); }		
	public Integer getCommandBitwidth(Constraint ct) { return bitScope.get(constraintsList.indexOf(ct)); }
	public Integer getWorldScope(Constraint ct) { return worldScope.get(constraintsList.indexOf(ct)); }
	public String getTransformationType(Constraint ct) { return transformationType.get(constraintsList.indexOf(ct)); }	
	public ArrayList<String> getTransformationType() { return transformationType; }		
	public String getConstraintType(Constraint ct) { return constraintType.get(constraintsList.indexOf(ct)); }	
	public ArrayList<String> getConstraintType() { return constraintType; }
		
	//Setters	 
	public void setConstraintList(ArrayList<Constraint> constraintsList) { this.constraintsList = constraintsList; }	
	public void setCommandScope(ArrayList<Integer> scopeList) { commandScope = scopeList; }	
	public void setWorldScope(ArrayList<Integer> worldScopeList) { worldScope = worldScopeList; }
	public void setBiwidth(ArrayList<Integer> bitList) { bitScope = bitList; }
	public void setTransformationType(ArrayList<String> transformationTypeList) { this.transformationType = transformationTypeList; }
}
