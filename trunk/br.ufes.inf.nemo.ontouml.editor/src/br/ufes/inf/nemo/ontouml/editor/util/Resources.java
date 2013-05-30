package br.ufes.inf.nemo.ontouml.editor.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public final class Resources {

	private static Resources instance = new Resources();
	
	private ResourceBundle editorResources = ResourceBundle.getBundle("resources.br.ufes.inf.nemo.ontouml.editor.ui.editor");
	
	private Map<String, Icon> iconMap = new HashMap<String, Icon>();

	/**
	 * Private constructor to enforce Singleton.
	 */
	private Resources() { }
	
	public static String getString(String key)
	{
		try {
			return instance.editorResources.getString(key);
		} 
		catch (Exception ex) 
		{
			//Do nothing
		}
		return null;
	}

	/**
	 * Returns the first character of a resource property.
	 * @param property the property to retrieve
	 * @return the first character as an int
	 */
	public static int getChar(String property) {
		String str = getString(property);
		return str == null ? 0 : str.charAt(0);
	}
	
	public static Icon getIcon(String key) {
		try {
			if (!instance.iconMap.containsKey(key)) 
			{
				String urlstr = getString(key);
				if (urlstr != null) 
				{
					instance.iconMap.put(key, new ImageIcon(instance.getClass().getClassLoader().getResource(urlstr)));
				}
			}
			
			return instance.iconMap.get(key);
		} 
		catch (Exception ex) 
		{
			//Do nothing
		}
		return null;
	}
	
	public static Image getImage(String key) {
		try {
			return ((ImageIcon)getIcon(key)).getImage();
		} 
		catch (Exception ex) 
		{
			//Do nothing
		}
		return null;
	}
}