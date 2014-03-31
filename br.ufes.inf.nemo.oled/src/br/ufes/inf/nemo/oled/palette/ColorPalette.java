package br.ufes.inf.nemo.oled.palette;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public final class ColorPalette {

	public enum ThemeColor {
		GREEN_LIGHTEST, GREEN_LIGHT, GREEN_MEDIUM, GREEN_DARK,
		GREY_LIGHT, GREY_MEDIUM, GREY_DARK
	}
	
	private static ColorPalette instance = new ColorPalette();
	
	private Map<ThemeColor, Color> colorMap = new HashMap<ThemeColor, Color>();
	
	public static ColorPalette getInstance() { return instance; }
	
	private ColorPalette() {
		//Green tones, as the NEMO theme.
		colorMap.put(ThemeColor.GREEN_LIGHTEST, new Color(0xF2FCAC));
		colorMap.put(ThemeColor.GREEN_LIGHT, new Color(0xCAE70C)); //Alternatives BDEB07 , BFFA37 from STABILO_BOSS
		colorMap.put(ThemeColor.GREEN_MEDIUM, new Color(0xB1D329));
		colorMap.put(ThemeColor.GREEN_DARK, new Color(0x94B053));
		
		//Grey tones, complementary to the green ones.
		colorMap.put(ThemeColor.GREY_LIGHT, new Color(0xE0E0E0));
		colorMap.put(ThemeColor.GREY_MEDIUM, new Color(0xC0C0C0));
		colorMap.put(ThemeColor.GREY_DARK, new Color(0x82837E));
	}
	
	public Color getColor(ThemeColor color) {
		return colorMap.get(color);
	}
}
