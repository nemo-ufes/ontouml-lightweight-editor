package br.ufes.inf.nemo.oled.util;

import java.util.Properties;

public enum OLEDSettings {
	
	USER_LOCALE("USER_LOCALE", "en-US"),
	
	OLED_SETTINGS_FILE("OLED_SETTINGS_FILE", "cnf.xml"),
	
	MODEL_DEFAULT_FILE("MODEL_DEFAULT_FILE", "model.refontouml"),
	
	PROJECT_DEFAULT_FILE("PROJECT_DEFAULT_FILE","project.dat"),
	
	OWL_DEFAULT_FILE("OWL_DEFAULT_FILE", "model.owl"),
	
	SBVR_DEFAULT_FILE("SBVR_DEFAULT_FILE", "model-sbvr.html"),
	
	SIMULATION_DEFAULT_FILE("SIMULATION_DEFAULT_FILE", "simulation.als"),
	
	SIMULATION_THEME_FILE("SIMULATION_SOLUTION_FILE", "standart_theme.thm"),
	
	SIMULATION_SOLUTION_FILE("SIMULATION_SOLUTION_FILE", "solution_output.xml"),
	
	RECENT_PROJECT_1("RECENT_PROJECT_1", ""),
	
	RECENT_PROJECT_2("RECENT_PROJECT_2", ""),
	
	RECENT_PROJECT_3("RECENT_PROJECT_3", ""),
	
	RECENT_PROJECT_4("RECENT_PROJECT_4", ""),
	
	RECENT_PROJECT_5("RECENT_PROJECT_5", "");
		
	private final String propertyKey;
	private final String defaultValue;
	
	private OLEDSettings(String propertyKey, String defaultValue) {
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
