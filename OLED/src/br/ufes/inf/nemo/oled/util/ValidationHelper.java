package br.ufes.inf.nemo.oled.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;

import RefOntoUML.Model;

/**
 * Helper class for dealing with model validation
 * 
 *   @author Antognoni Albuquerque
 *   @version 1.0
 */
public class ValidationHelper {

	public static String validateModel(Model model)
	{
		Map<Object, Object> context = new HashMap<Object, Object>();
		BasicDiagnostic diag = new BasicDiagnostic();
		StringBuilder sb = new StringBuilder();		
		
		sb.append("--- Validation Results ---\n\n");
		long validationStartMilis = System.currentTimeMillis();
		boolean valid = ModelHelper.validate(model, diag, context);
		long validationEndMilis = System.currentTimeMillis();
		//int effectiveErrors = 0;
		
		if(!valid)
		{
			for (Diagnostic item : diag.getChildren()) {
				
				/*String message = handleMessage(item.getMessage());
				if(message != null)
				{
					effectiveErrors++;
					sb.append(handleName(item.getData().get(0)) + " - " + message + "\n\n");
				}*/
				
				sb.append(ModelHelper.handleName(item.getData().get(0)) + " - " + handleMessage(item.getMessage()) + "\n\n");
				
			}	
		}
		
		sb.append(MessageFormat.format("--- Model validated in {0} ms, {1} error(s) found ---", (validationEndMilis - validationStartMilis),  diag.getChildren().size()));
		return sb.toString();		
	}

	
	/**
	 * Handles the error message
	 */
	public static String handleMessage(String message)
	{
		int constraintStart = message.indexOf("'");
		int constraintEnd = message.indexOf("'", constraintStart + 1);
		String constraint = message.substring(constraintStart + 1, constraintEnd);
		String newMessage = ValidationResources.getInstance().getString(constraint);		
		if(newMessage != null)
			return newMessage;
		return message;
	}
}
