package br.ufes.inf.nemo.common.ontouml;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;

import RefOntoUML.PackageableElement;
import RefOntoUML.impl.NamedElementImpl;
import RefOntoUML.util.ValidationMessage;

public class SyntacticVerificator {
	
	public static String verify(RefOntoUML.Package refmodel)
	{
		String result = new String();
		
		Diagnostician validator = Diagnostician.INSTANCE;

		Map<Object, Object> context = new HashMap<Object, Object>();
		BasicDiagnostic diag = new BasicDiagnostic();

		// Returns true if the model is valid.
		Map<PackageableElement, String> errorsMap = new HashMap<PackageableElement, String>();
		
		if(!validator.validate(refmodel, diag, context))
		{			
			result+="The model is not valid syntactically. The following error(s) where found:\n\n";
			
			for (Diagnostic item : diag.getChildren()) {
				
				PackageableElement element = (PackageableElement) item.getData().get(0);
				String errors = "";
				
				if(errorsMap.containsKey(element))
				{
					errors = errorsMap.get(element);
				}
				
				String message = ValidationMessage.getFinalMessage(item.getMessage());
				String currentError = message.substring(message.indexOf("- ")+2, message.length()) + "\n\n";
				errors += currentError;
				errorsMap.put(element, errors);
		
				result+=handleName(element) + " - " + currentError;
				
			}	
		} else {
			result+= "valid model.";
		}
		return result;
	}
	
    private static String handleName(Object element)
	{
		String name = "[unnamed]";
		if(element instanceof NamedElementImpl)
		{
			NamedElementImpl namedElement = (NamedElementImpl) element;
			if (namedElement.getName() != null)
				name = namedElement.getName();
		}
		
		return MessageFormat.format("{0} ({1})", name, getClassAsStereotype((EObject) element));
	}
	
	private static String getClassAsStereotype(EObject eObject) {
		String ret = eObject.eClass().getName().toLowerCase()
				.replace("association", "");
		return "<<" + ret + ">>";
	}
}
