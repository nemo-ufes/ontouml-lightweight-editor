package RefOntoUML.parser;

import java.text.MessageFormat;
import java.util.ArrayList;
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
	
	private Map<Element, ArrayList<String>> errorsMap = new HashMap<Element,  ArrayList<String>>();
	private String result = new String();
	private long verificationEndMilis = 0;
	private long validationStartMilis = 0;
	private BasicDiagnostic diag;
	
	/** Verify a model according to the RefOntoUML OCL synctatic rules. */  
	public void run(RefOntoUML.Package refmodel)
	{		
		result = "";
		errorsMap.clear();
		Diagnostician verificator = Diagnostician.INSTANCE;
		Map<Object, Object> context = new HashMap<Object, Object>();
		diag = new BasicDiagnostic();				
		validationStartMilis = System.currentTimeMillis();
		boolean verification = verificator.validate(refmodel, diag, context);
		verificationEndMilis = System.currentTimeMillis();		
		if(!verification)
		{
			result += "The model is not valid syntactically. The following error(s) where found:\n\n";			
			for (Diagnostic item : diag.getChildren()) 
			{				
				Element element = (Element) item.getData().get(0);				
				String message = ValidationMessage.getFinalMessage(item.getMessage());
				String currentError = message.substring(message.indexOf("- ")+2, message.length()) + "\n\n";
				if(errorsMap.containsKey(element))
				{
					errorsMap.get(element).add(currentError);
				}else{
					ArrayList<String> errorList = new ArrayList<String>();
					errorList.add(currentError);
					errorsMap.put(element, errorList);		
				}						
				result+=handleName(element) + " - " + currentError;
			}
		}		
		result += MessageFormat.format("Model verified in {0} ms, {1} error(s) found", (verificationEndMilis - validationStartMilis),  diag.getChildren().size());		
	}
		
	public String getTimingMessage()
	{
		return MessageFormat.format("Model verified in {0} ms, {1} error(s) found", (verificationEndMilis - validationStartMilis),  diag.getChildren().size());			
	}
	
	public Map<Element, ArrayList<String>> getMap()
	{
		return errorsMap;
	}
	
	public String getResult()
	{
		return result;
	}
	
	/** Handles the objects name when showing the error message  */
    private String handleName(Object element)
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
	
	private String getClassAsStereotype(EObject eObject) 
	{
		String ret = eObject.eClass().getName().toLowerCase().replace("association", "");
		
		return "<<" + ret + ">>";
	}
}
