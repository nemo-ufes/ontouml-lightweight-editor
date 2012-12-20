package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.common.resource.TypeName;
import RefOntoUML.NamedElement;
import RefOntoUML.Property;
import RefOntoUML.Classifier;

public class OntoUMLError {

	public static String undefinedMultiplicityError(Property prop)
	{
		String error = new String(), errorElement;
		Classifier owner;
		
		if (prop.getAssociation() == null)
		{
			errorElement = "Attribute";
			owner = (Classifier)prop.eContainer();
		}
		else
		{
			errorElement = "Association End";
			owner = prop.getAssociation();
		}
		
		error += "Warning: "+errorElement+" '"+prop.getName()+"' has undefined multiplicity.\n";
		error += "Owner: "+"<<"+TypeName.getTypeName(owner)+">> "+owner.getName()+" ("+prop.getType().getName()+")\n";
		error += "Owner Path: ";
		
		String path = "\n";
		for (EObject aux = owner; aux != null; aux = aux.eContainer()) {
			if (aux instanceof NamedElement && ((NamedElement)aux).getName() != null && ((NamedElement)aux).getName() != "") {
				path = ((NamedElement) aux).getName() + " -> " + path;
				
			} else {
				path = "<" + aux.eClass().getName() + "> -> " + path;
			}
		}
		
		return error += path+"\n";
	}
}
