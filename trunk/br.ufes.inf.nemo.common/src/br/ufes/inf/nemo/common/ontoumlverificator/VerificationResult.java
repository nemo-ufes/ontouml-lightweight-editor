package br.ufes.inf.nemo.common.ontoumlverificator;

import java.util.Map;

import RefOntoUML.Element;

public class VerificationResult {

	private boolean valid;
	
	private String resultString;
	
	private Map<Element, String> errorsMap;
	
	public VerificationResult(boolean valid, String result, Map<Element, String> errorsMap)
	{
		this.valid = valid;
		this.resultString = result;
		this.errorsMap = errorsMap;
	}

	public boolean isValid() {
		return valid;
	}

	public String getResultString() {
		return resultString;
	}

	public Map<Element, String> getErrorsMap() {
		return errorsMap;
	}
}
