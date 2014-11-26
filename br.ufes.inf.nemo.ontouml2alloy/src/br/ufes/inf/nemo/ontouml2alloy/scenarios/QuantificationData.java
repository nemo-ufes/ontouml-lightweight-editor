package br.ufes.inf.nemo.ontouml2alloy.scenarios;

class QuantificationData{
	private String variablePrefix;
	private String domain;
	private int numberOfVariables;
	
	public QuantificationData(String variablePrefix, String domain, int numberOfVariables) {
		this.variablePrefix = variablePrefix;
		this.domain = domain;
		this.numberOfVariables = numberOfVariables;
	}
	
	public String getVariable(int pos){
		if (numberOfVariables==1)
			return variablePrefix;
		else
			return variablePrefix+pos;
	}
	
	public String getDomain(){
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getVariablePrefix() {
		return variablePrefix;
	}

	public void setVariablePrefix(String variablePrefix) {
		this.variablePrefix = variablePrefix;
	}

	public int getNumberOfVariables(){
		return numberOfVariables;
	}
	
	public void setNumberOfVariables(int numberOfVariables) {
		this.numberOfVariables = numberOfVariables;
	}

	public String getVariableList(){
		String list = "";
		
		for (int i = 0; i < numberOfVariables; i++) {
			list += getVariable(i+1);
			if(i<numberOfVariables-1)
				list += ",";
		}
		
		return list;
	}
	
	public String getDeclaration(){
		return getVariableList()+":"+domain;
	}
	
	
}