package br.ufes.inf.nemo.oled.antipattern.wizard;

import java.util.HashMap;

public class ActionWizard <E extends Enum<E>>{

	private E code;
	private HashMap<String, Object> parameters;
	
	public HashMap<String, Object> getParameters() {
		return parameters;
	}

	public ActionWizard (E code, HashMap<String, Object> parameters){
		this.code = code;
		if(parameters==null)
			this.parameters = new HashMap<String, Object>(); 
		else
			this.parameters = parameters;
	}
	
	public ActionWizard (E code){
		this.code = code;
		this.parameters = new HashMap<String, Object>();  
	}
	
	public ActionWizard (){
		this.code = null;
		this.parameters = new HashMap<String, Object>();  
	}

	public E getCode() {
		return code;
	}

	public void setCode(E code) {
		this.code = code;
	}
	
	public void setCodeAndCleanParameters(E code) {
		setCode(code);
		cleanParameters();
	}

	public void addParameter(String parameterName, Object parameterValue){
		parameters.put(parameterName, parameterValue);
	}
	
	public Object getParameterValue(String parameterName){
		return parameters.get(parameterName); 
	}
	
	public void cleanParameters(){
		parameters = new HashMap<String, Object>();
	}
	
}
