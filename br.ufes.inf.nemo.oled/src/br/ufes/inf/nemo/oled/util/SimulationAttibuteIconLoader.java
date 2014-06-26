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
package br.ufes.inf.nemo.oled.util;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * This class accesses images for icons from the class path.
 *
 * @author Wei-ju Wu
 * @author Antognoni Albuquerque
 */
public final class SimulationAttibuteIconLoader {

	private Map<SimulationAttribute, String> urlMap = new HashMap<SimulationAttribute, String>();
	private Map<SimulationAttribute, Icon> iconMap = new HashMap<SimulationAttribute, Icon>();
	private Map<String, SimulationAttribute> attributeIconMap = new HashMap<String, SimulationAttribute>();
	private static SimulationAttibuteIconLoader instance = new SimulationAttibuteIconLoader();

	/**
	 * Private constructor.
	 */
	private SimulationAttibuteIconLoader() {
		for (SimulationAttribute item : SimulationAttribute.values()) {
			attributeIconMap.put(item.toString(), item);
		}
	
		urlMap.put(SimulationAttribute.COLOR_INHERIT, "resources/icons/x16/alloy/magic.gif");
		urlMap.put(SimulationAttribute.COLOR_BLACK, "resources/icons/x16/alloy/black.gif");
		urlMap.put(SimulationAttribute.COLOR_BLUE, "resources/icons/x16/alloy/cornflowerblue.gif");
		urlMap.put(SimulationAttribute.COLOR_YELLOW, "resources/icons/x16/alloy/gold.gif");
		urlMap.put(SimulationAttribute.COLOR_GRAY, "resources/icons/x16/alloy/lightgray.gif");
		urlMap.put(SimulationAttribute.COLOR_GREEN, "resources/icons/x16/alloy/limegreen.gif");
		urlMap.put(SimulationAttribute.COLOR_RED, "resources/icons/x16/alloy/palevioletred.gif");
		urlMap.put(SimulationAttribute.COLOR_WHITE, "resources/icons/x16/alloy/white.gif");
		
		urlMap.put(SimulationAttribute.SHAPE_INHERIT, "resources/icons/x16/alloy/box.gif");
		urlMap.put(SimulationAttribute.SHAPE_BOX, "resources/icons/x16/alloy/box.gif");
		urlMap.put(SimulationAttribute.SHAPE_CIRCLE, "resources/icons/x16/alloy/circle.gif");
		urlMap.put(SimulationAttribute.SHAPE_DIAMOND, "resources/icons/x16/alloy/diamond.gif");
		urlMap.put(SimulationAttribute.SHAPE_DOUBLECIRCLE, "resources/icons/x16/alloy/doublecircle.gif");
		urlMap.put(SimulationAttribute.SHAPE_DOUBLEOCTAGON, "resources/icons/x16/alloy/doubleoctagon.gif");
		urlMap.put(SimulationAttribute.SHAPE_EGG, "resources/icons/x16/alloy/egg.gif");
		urlMap.put(SimulationAttribute.SHAPE_ELLISPSE, "resources/icons/x16/alloy/ellipse.gif");
		urlMap.put(SimulationAttribute.SHAPE_HEXAGON, "resources/icons/x16/alloy/hexagon.gif");
		urlMap.put(SimulationAttribute.SHAPE_HOUSE, "resources/icons/x16/alloy/house.gif");
		urlMap.put(SimulationAttribute.SHAPE_INVHOUSE, "resources/bicons/x16/alloy/invhouse.gif");
		urlMap.put(SimulationAttribute.SHAPE_INVTRAPEZOID, "resources/icons/x16/alloy/invtrapezium.gif");
		urlMap.put(SimulationAttribute.SHAPE_INVTRIANGLE, "resources/icons/x16/alloy/invtriangle.gif");
		urlMap.put(SimulationAttribute.SHAPE_LINEDCIRCLE, "resources/icons/x16/alloy/Mcircle.gif");
		urlMap.put(SimulationAttribute.SHAPE_LINEDDIAMOND, "resources/icons/x16/alloy/Mdiamond.gif");
		urlMap.put(SimulationAttribute.SHAPE_LINEDSQUARE, "resources/icons/x16/alloy/Msquare.gif");
		urlMap.put(SimulationAttribute.SHAPE_OCTAGON, "resources/icons/x16/alloy/octagon.gif");
		urlMap.put(SimulationAttribute.SHAPE_PARALLELOGRAM, "resources/icons/x16/alloy/parallelogram.gif");
		urlMap.put(SimulationAttribute.SHAPE_TRAPEZOID, "resources/icons/x16/alloy/trapezium.gif");
		urlMap.put(SimulationAttribute.SHAPE_TRIANGLE, "resources/icons/x16/alloy/triangle.gif");
		urlMap.put(SimulationAttribute.SHAPE_TRIPLEOCTAGON, "resources/icons/x16/alloy/tripleoctagon.gif");
		
		urlMap.put(SimulationAttribute.STYLE_INHERIT, "resources/icons/x16/alloy/solid.gif");
		urlMap.put(SimulationAttribute.STYLE_BOLD, "resources/icons/x16/alloy/bold.gif");
		urlMap.put(SimulationAttribute.STYLE_DASHED, "resources/icons/x16/alloy/dashed.gif");
		urlMap.put(SimulationAttribute.STYLE_DOTTED, "resources/icons/x16/alloy/dotted.gif");
		urlMap.put(SimulationAttribute.STYLE_SOLID, "resources/icons/x16/alloy/solid.gif");

	}

	public List<SimulationAttribute> getColorSimulationAttributes()
	{
		List<SimulationAttribute> list = new ArrayList<SimulationAttribute>();
		
		list.add(SimulationAttribute.COLOR_INHERIT);
		list.add(SimulationAttribute.COLOR_BLUE);
		list.add(SimulationAttribute.COLOR_YELLOW);
		list.add(SimulationAttribute.COLOR_GRAY);
		list.add(SimulationAttribute.COLOR_GREEN);
		list.add(SimulationAttribute.COLOR_RED);
		list.add(SimulationAttribute.COLOR_WHITE);
		list.add(SimulationAttribute.COLOR_BLACK);
		
		return list;
	}
	
	public List<SimulationAttribute> getShapeSimulationAttributes()
	{
		List<SimulationAttribute> list = new ArrayList<SimulationAttribute>();
		
		list.add(SimulationAttribute.SHAPE_INHERIT);
		list.add(SimulationAttribute.SHAPE_BOX);
		list.add(SimulationAttribute.SHAPE_CIRCLE);
		list.add(SimulationAttribute.SHAPE_DIAMOND);
		list.add(SimulationAttribute.SHAPE_DOUBLECIRCLE);
		list.add(SimulationAttribute.SHAPE_DOUBLEOCTAGON);
		list.add(SimulationAttribute.SHAPE_EGG);
		list.add(SimulationAttribute.SHAPE_ELLISPSE);
		list.add(SimulationAttribute.SHAPE_HEXAGON);
		list.add(SimulationAttribute.SHAPE_HOUSE);
		list.add(SimulationAttribute.SHAPE_INVHOUSE);
		list.add(SimulationAttribute.SHAPE_INVTRAPEZOID);
		list.add(SimulationAttribute.SHAPE_INVTRIANGLE);
		list.add(SimulationAttribute.SHAPE_LINEDCIRCLE);
		list.add(SimulationAttribute.SHAPE_LINEDDIAMOND);
		list.add(SimulationAttribute.SHAPE_LINEDSQUARE);
		list.add(SimulationAttribute.SHAPE_OCTAGON);
		list.add(SimulationAttribute.SHAPE_PARALLELOGRAM);
		list.add(SimulationAttribute.SHAPE_TRAPEZOID);
		list.add(SimulationAttribute.SHAPE_TRIANGLE);
		list.add(SimulationAttribute.SHAPE_TRIPLEOCTAGON);
			
		return list;
	}
	
	public List<SimulationAttribute> getStyleSimulationAttributes()
	{
		List<SimulationAttribute> list = new ArrayList<SimulationAttribute>();
		
		list.add(SimulationAttribute.STYLE_INHERIT);
		list.add(SimulationAttribute.STYLE_BOLD);
		list.add(SimulationAttribute.STYLE_DASHED);
		list.add(SimulationAttribute.STYLE_DOTTED);
		list.add(SimulationAttribute.STYLE_SOLID);
		
		return list;
	}
	
	/**
	 * Returns the singleton instance.
	 * @return the singleton instance
	 */
	public static SimulationAttibuteIconLoader getInstance() { return instance; }

	/**
	 * Returns the icon for the specified icon type.
	 * @param attribute the icon type
	 * @return the icon
	 */
	public Icon getIcon(SimulationAttribute attribute) {
		if (!iconMap.containsKey(attribute)) {
			String urlstr = urlMap.get(attribute);
			if (urlstr != null) {
				iconMap.put(attribute,
						new ImageIcon(getClass().getClassLoader().getResource(urlstr)));
			}
		}
		return iconMap.get(attribute);
	}

	/**
	 * Returns the icon for the specified icon type name.
	 * @param typeName the icon type name
	 * @return the icon
	 */
	public Icon getIcon(String typeName) {
		return getIcon(attributeIconMap.get(typeName));
	}

	/**
	 * Returns the icon for the specified icon type name.
	 * @param typeName the icon type name
	 * @return the icon
	 */
	public Image getImage(String typeName) {
		try {
			SimulationAttribute type = attributeIconMap.get(typeName);
			return new ImageIcon(getClass().getClassLoader().getResource(urlMap.get(type))).getImage();
		} catch (Exception e) {
			return null;
		}
	}
}
