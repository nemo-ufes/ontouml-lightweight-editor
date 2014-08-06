package br.ufes.inf.nemo.oled.problems;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.oled.finder.FoundElement;

public class ProblemElement extends FoundElement {

	private String description = new String();
	public enum TypeProblem { SYNTACTIC, OLED }
	public TypeProblem typeProblem = TypeProblem.OLED;
	public int identifier = 0;
	
	public ProblemElement(EObject eobject, int identifier, String description, TypeProblem typeProblem) 
	{
		super(eobject);
		this.description = description;
		this.identifier=identifier;
		this.setTypeProblem(typeProblem);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TypeProblem getTypeProblem() {
		return typeProblem;
	}

	public void setTypeProblem(TypeProblem typeProblem) {
		this.typeProblem = typeProblem;
	}
	
	public String getTypeProblemString() { 
		if(typeProblem == TypeProblem.OLED) return "Application";
		else if (typeProblem == TypeProblem.SYNTACTIC) return "Syntactical";
		return "Unkown";
	}

	public int getIdentifier() {
		return identifier;
	}

	public String getIdentifierString(){
		return String.format("%02d", identifier);
	}
	
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	
}
