package br.ufes.inf.nemo.oled.util;

import java.util.Properties;

import br.ufes.inf.nemo.oled.model.UmlProject;

public enum ProjectSettings {
	
	OWL_ONTOLOGY_IRI("OWL_ONTOLOGY_IRI", ""),
	OWL_GENERATE_FILE("OWL_GENERATE_FILE", "false"),
	OWL_FILE_PATH("OWL_FILE_PATH", ""),
	OWL_MAPPING_TYPE("OWL_MAPPING_TYPE", "SIMPLE");
	
	private final String propertyKey;
	private final String defaultValue;
	
	private ProjectSettings(String propertyKey, String defaultValue) {
		this.propertyKey = propertyKey;
		this.defaultValue = defaultValue;
	}

	public String getPropertyKey() {
		return this.propertyKey;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public String getValue(UmlProject project) {
		Properties properties = project.getProperties();
		if(properties != null)
			return properties.getProperty(getPropertyKey(), getDefaultValue());
		
		return getDefaultValue();
	}
	
	public void setValue(UmlProject project, String value) {
		Properties properties = project.getProperties();
		if(properties != null)
			properties.put(getPropertyKey(), value);
	}
	
	public int getIntValue(UmlProject project)
	{
		String value = getValue(project);
		return Integer.parseInt(value);
	}
	
	public boolean getBoolValue(UmlProject project)
	{
		String value = getValue(project);
		return Boolean.parseBoolean(value);
	}
}