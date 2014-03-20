package br.ufes.inf.nemo.common.ontoumlverificator;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;

import RefOntoUML.Association;
import RefOntoUML.Element;
import RefOntoUML.Generalization;
import RefOntoUML.NamedElement;
import RefOntoUML.util.ValidationMessage;

public class SyntacticVerificator {
	
//	public static List<Element> elems = new ArrayList<Element>();
//	
	/**
     * Validates a model according to the RefOntoUML OCL synctatic rules.
     * @param refmodel the model that will be validated.
     */  
	public static VerificationResult verify(RefOntoUML.Package refmodel)
	{		
		String result = new String();
						
		Diagnostician validator = Diagnostician.INSTANCE;

		Map<Object, Object> context = new HashMap<Object, Object>();
		BasicDiagnostic diag = new BasicDiagnostic();

		Map<Element, String> errorsMap = new HashMap<Element, String>();
		
		long validationStartMilis = System.currentTimeMillis();
		boolean validation = validator.validate(refmodel, diag, context);
		long validationEndMilis = System.currentTimeMillis();
		
		// Returns true if the model is valid.
		if(!validation)
		{
			result += "The model is not valid syntactically. The following error(s) where found:\n\n";
			
			for (Diagnostic item : diag.getChildren()) {
				
				Element element = (Element) item.getData().get(0);
				String errors = "";
//				if (elems.contains(element))
//				{
					if(errorsMap.containsKey(element))
					{
						errors = errorsMap.get(element);
					}
					
					String message = ValidationMessage.getFinalMessage(item.getMessage());
					String currentError = message.substring(message.indexOf("- ")+2, message.length()) + "\n\n";
					errors += currentError;
					errorsMap.put(element, errors);
			
					result+=handleName(element) + " - " + currentError;
//				}
				
			}
		}
		
		result += MessageFormat.format("Model verified in {0} ms, {1} error(s) found", (validationEndMilis - validationStartMilis),  diag.getChildren().size());
		
		return new VerificationResult(validation, result, errorsMap);
	}
	
	/**
	 * Handles the objects name when showing the error message
	 */
    private static String handleName(Object element)
	{
		String name = "[unnamed]";
		if(element instanceof NamedElement)
		{
			NamedElement namedElement = (NamedElement) element;
			if (namedElement.getName() != null)
				name = namedElement.getName();
			else if (element instanceof Association)
			{
				name = ((Association) element).getMemberEnd().get(0).getType().getName() + "->" +
						((Association) element).getMemberEnd().get(1).getType().getName();
			}
		}
		else if(element instanceof Generalization)
		{
			name = ((Generalization) element).getSpecific().getName() + "->" +
					((Generalization) element).getGeneral().getName();
		}
		
		return MessageFormat.format("{0} ({1})", name, getClassAsStereotype((EObject) element));
	}
	
	private static String getClassAsStereotype(EObject eObject) 
	{
		String ret = eObject.eClass().getName().toLowerCase().replace("association", "");
		
		return "<<" + ret + ">>";
	}
}
