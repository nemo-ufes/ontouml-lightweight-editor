package br.ufes.inf.nemo.ontouml.editor.util;

import java.util.Properties;


public enum Settings {
	
	USER_LOCALE("USER_LOCALE", "en-US"),
	
	MODEL_DEFAULT_FILE("MODEL_DEFAULT_FILE", "model.xmi"),
	
	PROJECT_DEFAULT_FILE("PROJECT_DEFAULT_FILE","project.onto"),
	
	RECENT_PROJECT_1("RECENT_PROJECT_1", ""),
	
	RECENT_PROJECT_2("RECENT_PROJECT_2", ""),
	
	RECENT_PROJECT_3("RECENT_PROJECT_3", ""),
	
	RECENT_PROJECT_4("RECENT_PROJECT_4", ""),
	
	RECENT_PROJECT_5("RECENT_PROJECT_5", "");
		
	private final String propertyKey;
	private final String defaultValue;
	
	private Settings(String propertyKey, String defaultValue) {
		this.propertyKey = propertyKey;
		this.defaultValue = defaultValue;
	}

	public String getPropertyKey() {
		return this.propertyKey;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public String getValue() {
		Properties properties = ConfigurationHelper.getProperties();
		if(properties != null)
			return properties.getProperty(getPropertyKey(), getDefaultValue());
		
		return getDefaultValue();
	}
	
	public void setValue(String value) {
		Properties properties = ConfigurationHelper.getProperties();
		if(properties != null)
			properties.put(getPropertyKey(), value);
	}
	
	public int getIntValue()
	{
		String value = getValue();
		return Integer.parseInt(value);
	}
	
	public boolean getBoolValue()
	{
		String value = getValue();
		return Boolean.parseBoolean(value);
	}
}
