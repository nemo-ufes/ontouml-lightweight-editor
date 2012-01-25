package br.ufes.inf.nemo.oled.util;


public class OLEDSettings {

	public enum Setting
	{
		SIMULATION_DEFAULT_FILE,
		SIMULATION_SOLUTION_FILE
	}
	
	private static OLEDSettings instance = new OLEDSettings();
	//private Map<IconType, String> settingMap = new HashMap<IconType, String>();
	
	/**
	 * Private constructor to enforce Singleton.
	 */
	private OLEDSettings() { }

	/**
	 * Returns the singleton instance.
	 * @return the singleton instance
	 */
	public static OLEDSettings getInstance() { return instance; }
	
	public String getSetting(Setting setting)
	{
		if(setting == Setting.SIMULATION_DEFAULT_FILE)
			return "simulation.als";
		else if(setting == Setting.SIMULATION_SOLUTION_FILE)
			return "solution_output.xml";
		
		return null;
	}

}
