package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.common.resource.TypeName;
import RefOntoUML.MultiplicityElement;
import RefOntoUML.NamedElement;
import RefOntoUML.Property;
import RefOntoUML.Classifier;
import RefOntoUML.TypedElement;

/**
 * Has convenient methods to print errors that represents inconsistencies
 * in OntoUML models.
 * @author Vinicius
 *
 */
public class OntoUMLError extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2890861993353553866L;
	
	String error_msg;
	
	public OntoUMLError() {
		super();
		error_msg = "";
	}
  
	public OntoUMLError(String err) {
		super(err);
		error_msg = err;
	}

	public String getError() {
		return error_msg;
  	}
	
	@Override
	public String getMessage() {
		return error_msg;
  	}

	/**
	 * Informs that a Multiplicity Element does not have its multiplicity defined.
	 * @param multElem element with the multiplicity undefined
	 * @return the error String, identifying the element and its path
	 */
	public static String undefinedMultiplicityError(MultiplicityElement multElem)
	{
		String error = new String(), elementType;
		Classifier owner;
		Property prop = (Property) multElem;
		
		if (prop.getAssociation() == null)
		{
			elementType = "Attribute";
			owner = (Classifier)prop.eContainer();
		}
		else
		{
			elementType = "Association End";
			owner = prop.getAssociation();
		}
		
		error += "Warning: "+elementType+" '"+prop.getName()+"' has undefined multiplicity.\n";
		error += "Owner: "+"<<"+TypeName.getTypeName(owner)+">> "+owner.getName()+" ("+prop.getType().getName()+")\n";
		error += "Path: ";
		
		return error += getElementPath(prop)+"\n\n";
	}
	
	/**
	 * Informs that a TypedElement does not have its type defined.
	 * @param typedElem element with the type undefined
	 * @return the error String, identifying the element and its path
	 */
	public static String undefinedTypeError(TypedElement typedElem)
	{
		String error = new String(), elementType;
		Classifier owner;
		Property prop = (Property) typedElem;
		
		if (prop.getAssociation() == null)
		{
			elementType = "Attribute";
			owner = (Classifier)prop.eContainer();
		}
		else
		{
			elementType = "Association End";
			owner = prop.getAssociation();
		}
		error += "Warning: "+elementType+" '"+prop.getName()+"' has its type not defined.\n";
		error += "Owner: "+"<<"+TypeName.getTypeName(owner)+">> "+owner.getName()+"\n";
		error += "Path: ";
		
		return error += getElementPath(prop)+"\n\n";
	}
	
	/**
	 * Auxiliary functions that process the complete path of an element 
	 * in the model and puts it into a string.
	 * @param elem the element which the path must be processed
	 * @return the element path
	 */
	private static String getElementPath(NamedElement elem)
	{
		String path = elem.getName();
		
		for (EObject aux = elem.eContainer(); aux != null; aux = aux.eContainer()) {
			if (aux instanceof NamedElement && ((NamedElement)aux).getName() != null && ((NamedElement)aux).getName() != "") {
				path = ((NamedElement) aux).getName() + " -> " + path;
				
			} else {
				path = "<" + aux.eClass().getName() + "> -> " + path;
			}
		}
		
		return path;
	}
}
