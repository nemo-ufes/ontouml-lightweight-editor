package br.ufes.inf.nemo.ocl2alloy.options;

import java.util.ArrayList;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.ocl2alloy.parser.OCLParser;

/**
 * @author John Guerson
 */

public class OCLOptions {

	private ArrayList<Constraint> constraintsList = new ArrayList<Constraint>();	
	private ArrayList<String> constraintType = new ArrayList<String>();
	private ArrayList<String> transformationType = new ArrayList<String>();
	private ArrayList<Integer> commandScope = new ArrayList<Integer>();	
	
	/**
	 * Constructor.
	 * 
	 * @param constraints
	 */
	public OCLOptions(OCLParser oclparser)
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
	 * COnstructor.
	 */
	public OCLOptions()
	{
		
	}
	
	public ArrayList<Constraint> getConstraintList ()
	{
		return constraintsList;
	}
	
	public Integer getCommandScope(Constraint ct)
	{
		return commandScope.get(constraintsList.indexOf(ct));
	}
	
	public String getTransformationType(Constraint ct)
	{
		return transformationType.get(constraintsList.indexOf(ct));
	}
	
	public String getConstraintType(Constraint ct)
	{
		return constraintType.get(constraintsList.indexOf(ct));
	}
	
}
