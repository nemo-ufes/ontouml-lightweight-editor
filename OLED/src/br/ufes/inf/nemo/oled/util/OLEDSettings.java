package br.ufes.inf.nemo.oled.util;

import java.util.Properties;

public enum OLEDSettings {
	
	USER_LOCALE("USER_LOCALE", "en-US"),
	SIMULATION_DEFAULT_FILE("SIMULATION_DEFAULT_FILE", "simulation.als"),
	SIMULATION_THEME_FILE("SIMULATION_SOLUTION_FILE", "simulation.thm"),
	SIMULATION_SOLUTION_FILE("SIMULATION_SOLUTION_FILE", "solution_output.xml"),
	OLED_SETTINGS_FILE("OLED_SETTINGS_FILE", "cnf.xml");
		
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
