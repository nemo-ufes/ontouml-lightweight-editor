/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.palette;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Antognoni Albuquerque
 */
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
