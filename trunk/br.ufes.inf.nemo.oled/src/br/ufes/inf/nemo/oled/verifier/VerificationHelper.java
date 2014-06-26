/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.verifier;

import br.ufes.inf.nemo.common.ontoumlverificator.SyntacticVerificator;
import br.ufes.inf.nemo.common.ontoumlverificator.VerificationResult;
import br.ufes.inf.nemo.oled.util.OperationResult;
import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;

/**
 * Helper class for dealing with model validation
 * 
 * @author Antognoni Albuquerque
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
