package br.ufes.inf.nemo.oled.util;

import br.ufes.inf.nemo.common.ontoumlverificator.SyntacticVerificator;
import br.ufes.inf.nemo.common.ontoumlverificator.VerificationResult;
import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;

/**
 * Helper class for dealing with model validation
 * 
 *   @author Antognoni Albuquerque
 *   @version 1.0
 */
public class VerificationHelper {
	
	/**
	 * Validates the model sintatically, against the rules defined in the ecore metamodel. 
	 * 
	 * @param model
	 * @return OperationResult the sintatical validation result
	 */
	public static OperationResult verifyModel(RefOntoUML.Package model)
	{
//		Map<Object, Object> context = new HashMap<Object, Object>();
//		BasicDiagnostic diag = new BasicDiagnostic();
//
//		long validationStartMilis = System.currentTimeMillis();
//		boolean valid = ModelHelper.validate(model, diag, context);
//		long validationEndMilis = System.currentTimeMillis();
//		
//		StringBuilder sb = new StringBuilder();		
//		Map<Element, String> errorsMap = new HashMap<Element, String>();
//		
//		if(!valid)
//		{
//			sb.append("The model is not valid sintatically. The following error(s) where found:\n\n");
//			
//			for (Diagnostic item : diag.getChildren()) {
//				
//				Element element = (Element) item.getData().get(0);
//				String errors = "";
//				
//				if(errorsMap.containsKey(element))
//				{
//					errors = errorsMap.get(element);
//				}
//				
//				String currentError = handleMessage(item.getMessage()) + "\n\n";
//				errors += currentError;
//				errorsMap.put(element, errors);
//				sb.append(ModelHelper.handleName(element) + " - " + currentError);
//				
//			}	
//		}
//		
//		sb.append(MessageFormat.format("Model verified in {0} ms, {1} error(s) found", (validationEndMilis - validationStartMilis),  diag.getChildren().size()));
		
		VerificationResult verifyResult = SyntacticVerificator.verify(model);
		
		return new OperationResult(verifyResult.isValid() ? ResultType.SUCESS : ResultType.ERROR, 
				verifyResult.getResultString(), new Object[] { verifyResult.getErrorsMap() });		
	}
	
//	/**
//	 * Handles the error message, returning a more friendly message
//	 * @param message
//	 * @return String the friendly message
//	 */
//	public static String handleMessage(String message)
//	{
//		int constraintStart = message.indexOf("'");
//		int constraintEnd = message.indexOf("'", constraintStart + 1);
//		String constraint = message.substring(constraintStart + 1, constraintEnd);
//		String newMessage = VerificationResources.getInstance().getString(constraint);		
//		if(newMessage != null)
//			return newMessage;
//		return message;
//	}
}
